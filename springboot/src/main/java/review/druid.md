### Alibaba Druid  是什么?

- github: https://github.com/alibaba/druid

Druid 能一款高性能、够提供强大的监控和扩展功能的数据库连接池。

Druid 可以打印 SQL 执行日志，Druid 提供了不同的 LogFilter，能支持 Common-Logging、Log4j 和 JDKLog，可以自行选择相应的 LogFilter。

#### Druid 的优点

- DruidDataSource 高效的数据库连接池。 (**高性能**)
- DruidDriver 能够提供基于 Filter - Chain 模式的插件体系。(**高扩展**)

> 本次使用的 Druid 源码是 1.2.8 版本

### Druid 整体架构

![Druid 整体架构](https://typora-xxx.oss-cn-shenzhen.aliyuncs.com/img/1654818277.png)

- 核心类：DruidDataSource() 、 DruidAbstractDataSource 

- 三个数组

```
// 存放连接的数组
private volatile DruidConnectionHolder[] connections;
// 将被剔除的连接的数组
private DruidConnectionHolder[]          evictConnections;
// 将进行 keepAlive 连接的数组
private DruidConnectionHolder[]          keepAliveConnections;
```

- 四个守护线程

```
// 负责添加连接的守护线程
private CreateConnectionThread           createConnectionThread;
// 负责销毁连接的守护线程
private DestroyConnectionThread          destroyConnectionThread;
// 负责记录监控数据的守护线程
private LogStatsThread                   logStatsThread;
private DestroyTask                      destroyTask;
```

ExceptionSorter： 当网络断开或者数据库服务器Crash时，连接池里面会存在“不可用连接”，连接池需要一种机制剔除这些“不可用连接”。在Druid和JBoss连接池中，剔除“不可用连接”的机制称为ExceptionSorter，实现的原理是根据异常类型/Code/Reason/Message来识别“不可用连接”。没有类似ExceptionSorter的连接池，在数据库重启或者网络中断之后，不能恢复工作，所以ExceptionSorter是连接池是否稳定的重要标志。

### 整体流程

// TODO 图

整体流程可以看做一个生产者、消费者模式，notEmpty.signal() 通知可以拿连接了，empty.signal() 通知可以创建连接了

1、初始化连接池

2、业务线程获从连接池取连接

3、守护线程

- 创建连接
- 剔除、保活连接
  - 主动对连接池内限制的连接对象进行检查，使其数量控制在 minIdle，在 keepAlive 开启时对闲置连接进行活性检查
  - 主动回收那些长期未 close 的连接，需要在 removeAbandon 开启时生效

4、回收连接

### 常用参数说明

- testOnBorrow：每次获取连接时执行 validationQuery 检查连接是否有效，会影响性能
- testWhileIdle：申请连接的时候，如果空闲时间大于 timeBetweenEvictionRunsMillis，执行validationQuery 检查连接是否有效
- testOnReturn：归还连接时执行 validationQuery 检查连接是否有效，会影响性能
- timeBetweenEvictionRunsMillis：空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效
  - 1、Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接
  - 2、空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效
- removeAbandoned：是否回收泄露的连接；当我们调用 Connection connection =  dataSource.getConnection() 获取连接后，如果时代码中某些地方忘记调用 connection.close()方法，在多次执行后连接池中所有的连接都会处于活动状态，连接池将被耗尽无法再获取新的连接，而这些活动连接实际又是空闲的连接并没有在工作，这就是连接泄露。但是一般我们使用的 ORM 框架都会帮我们做处理
- removeAbandonedTimeoutMillis：
- minEvictableIdleTimeMillis：连接被驱逐的最小空闲时间
- maxEvictableIdleTimeMillis：连接被驱逐的最大空闲时间
- keepAliveBetweenTimeMillis：连接的空闲时间大于它，就可以进行保活，必须大于 timeBetweenEvictionRunsMillis，且大于 30s



### 一、连接池初始化过程

![连接池初始化过程](https://typora-xxx.oss-cn-shenzhen.aliyuncs.com/img/1654819349.png)

使用 JavaConfig 的方式配置 Druid

```
@Configuration
public class DruidConfig {
    @Bean(initMethod = "init", destroyMethod = "close")
    @Primary
    public DruidDataSource dataSource(DruidConfigProperties configProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(configProperties.getDriverClassName());
        dataSource.setUrl(configProperties.getUrl());
        dataSource.setUsername(configProperties.getUsername());
        dataSource.setPassword(configProperties.getPassword());
        // ... 省略 dataSource.setXxx()
        return dataSource;
	}
}
```

#### 1、new DruidDataSource()

new DruidDataSource() 过程中会调用父类 DruidAbstractDataSource 的构造函数

```
public class DruidDataSource extends DruidAbstractDataSource implements DruidDataSourceMBean, ManagedDataSource, Referenceable, Closeable, Cloneable, ConnectionPoolDataSource, MBeanRegistration {
    public DruidDataSource(){
        this(false);
    }

    public DruidDataSource(boolean fairLock){
        super(fairLock);
        configFromPropety(System.getProperties());
    }
}

public abstract class DruidAbstractDataSource extends WrapperAdapter implements DruidAbstractDataSourceMBean, DataSource, DataSourceProxy, Serializable {
    public DruidAbstractDataSource(boolean lockFair){
        lock = new ReentrantLock(lockFair);

        notEmpty = lock.newCondition();
        empty = lock.newCondition();
    }
}
```

- empty 控制创建连接的 condition，当连接池中连接够用时，阻塞在添加连接的守护线程
- notEmpty 控制获取连接的 condition，当连接池中连接不够用时，获取连接的业务线程就阻塞到 notEmpty,且唤起阻塞在 empty 上的守护线程进行添加连接，然后唤起业务线程去尝试获取连接

#### 2、DruidDataSource.init()

new DruidDataSource() 之后,可以直接调用 DruidDataSource.init() 进行初始化，或者在 getConnection() 时进行初始化

2.1、初始化过滤器，通过 SPI 加载 Filter 并初始化，这里需要使用 @AutoLoad 注解，例如我们配置的日志过滤器、监控过滤器

```
public void init() throws SQLException {
	// ... 省略
    for (Filter filter : filters) {
        filter.init(this);
    }
    // ... 省略
    // 通过 SPI 加载 Filter 并初始化
    initFromSPIServiceLoader();
    // ... 省略
}
```

2.2、解析数据库配置，校验参数的合法性，例如 maxActive <=0
- 初始化 ExceptionSorter，例如我们使用的是 mysql，即为 MySqlExceptionSrter
- 初始化 ValidConnectionChecker

```
public void init() throws SQLException {
	// ... 省略
	initExceptionSorter();
	initValidConnectionChecker();
    // ... 省略
}
```

2.3、validationQueryCheck()，这里是检查[连接可用性检查]的相关类和参数的，并不是检查连接

2.4、初始化三个核心数组

```
private volatile DruidConnectionHolder[] connections;
private DruidConnectionHolder[]          evictConnections;
private DruidConnectionHolder[]          keepAliveConnections;

public void init() throws SQLException {
	// ... 省略
	connections = new DruidConnectionHolder[maxActive];
    evictConnections = new DruidConnectionHolder[maxActive];
    keepAliveConnections = new DruidConnectionHolder[maxActive];
    // ... 省略
}

```

2.5、根据 initSize 进行初始化连接

具体代码参考后面的创建连接内容即可

2.6、初始化四个核心线程   

```
private CreateConnectionThread           createConnectionThread;
private DestroyConnectionThread          destroyConnectionThread;
private LogStatsThread                   logStatsThread;
private DestroyTask                      destroyTask;

public void init() throws SQLException {
	// ... 省略
    createAndLogThread();
    createAndStartCreatorThread();
    createAndStartDestroyThread();
    // ... 省略
}

```

- createAndLogThread() 方法，创建并启动异步线程 logStatsThread，如果设置了 timeBetweenLogStatsMillis 则开启守护线程，收集监控指标打印 Log 日志
- createAndStartCreatorThread() 方法，创建并启动异步线程 createConnectionThread，它是负责添加连接的守护线程
- createAndStartDestroyThread() 方法，创建并启动异步线程 destroyConnectionThread 以及 destroyTask，它是负责销毁连接的守护线程

### 二、获取连接过程

![获取连接过程](https://typora-xxx.oss-cn-shenzhen.aliyuncs.com/img/1654819665.png)

获取连接入口在 DruidDataSource.getConnection() 方法，该方法里会先执行连接池的初始化，即 init() 方法，核心代码在 getConnectionDirect(long maxWaitMillis) 方法

1、getConnectionInternal(long maxWait)

```
protected int notFullTimeoutRetryCount = 0;

public DruidPooledConnection getConnectionDirect(long maxWaitMillis) throws SQLException {
    // ... 省略
    for (;;) {
        // ... 省略
        DruidPooledConnection poolableConnection;
        try {
            poolableConnection = getConnectionInternal(maxWaitMillis);
        } catch (GetConnectionTimeoutException ex) {
                if (notFullTimeoutRetryCnt <= this.notFullTimeoutRetryCount && !isFull()) {
                    notFullTimeoutRetryCnt++;
                    if (LOG.isWarnEnabled()) {
                        LOG.warn("get connection timeout retry : " + notFullTimeoutRetryCnt);
                    }
                    continue;
                }
                throw ex;
            }
	    // ... 省略
```

注意这里的 for 死循环，直接获取到连接或者获取连接超时，如果获取连接超时，则进行重试，默认 0 次，即不重试

getConnectionInternal(long maxWait) 的内容在下面讲解

2、是否开启 testOnBorrow

```
if (testOnBorrow) {
	// 检查连接是否有效
    boolean validate = testConnectionInternal(poolableConnection.holder, poolableConnection.conn);
    if (!validate) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("skip not validate connection.");
        }
        // 丢弃连接
        discardConnection(poolableConnection.holder);
        continue;
    }
}
```

调用 testConnectionInternal(DruidConnectionHolder holder, Connection conn)  检查连接是否有效，如果连接失效，则丢弃连接

官方不建议开启 testOnBorrow，因为它每次获取连接时要执行 validationQuery 检查连接是否有效，会影响性能

3、如果没有开启 testOnBorrow，则判断是否开启 testWhileIdle

```
if (testOnBorrow) {
	// ... 省略
} else {
	// 连接对象是否被 close
	if (poolableConnection.conn.isClosed()) {
        discardConnection(poolableConnection.holder); // 传入null，避免重复关闭
        continue;
    }
	
    if (testWhileIdle) {
        final DruidConnectionHolder holder = poolableConnection.holder;
        long currentTimeMillis             = System.currentTimeMillis();
        long lastActiveTimeMillis          = holder.lastActiveTimeMillis;
        long lastExecTimeMillis            = holder.lastExecTimeMillis;
        long lastKeepTimeMillis            = holder.lastKeepTimeMillis;

        if (checkExecuteTime
                && lastExecTimeMillis != lastActiveTimeMillis) {
            lastActiveTimeMillis = lastExecTimeMillis;
        }

        if (lastKeepTimeMillis > lastActiveTimeMillis) {
            lastActiveTimeMillis = lastKeepTimeMillis;
        }

        long idleMillis                    = currentTimeMillis - lastActiveTimeMillis;

        long timeBetweenEvictionRunsMillis = this.timeBetweenEvictionRunsMillis;

        if (timeBetweenEvictionRunsMillis <= 0) {
            timeBetweenEvictionRunsMillis = DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
        }

        if (idleMillis >= timeBetweenEvictionRunsMillis
                || idleMillis < 0 // unexcepted branch
                ) {
            boolean validate = testConnectionInternal(poolableConnection.holder, poolableConnection.conn);
            if (!validate) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("skip not validate connection.");
                }

                discardConnection(poolableConnection.holder);
                 continue;
            }
        }
    }
}
```

如果开启了 testWhileIdle，则计算该连接的空闲时间 idleMillis（当前时间 - 连接上次使用时间），并判断 idleMillis 是否大于我们配置的 timeBetweenEvictionRunsMillis，如果大于，则检查连接是否有效，如果连接已失效，则丢弃连接

- testOnBorrow：每次获取连接时执行 validationQuery 检查连接是否有效，会影响性能
- testWhileIdle：申请连接的时候，如果空闲时间大于 timeBetweenEvictionRunsMillis，执行validationQuery 检查连接是否有效
- testOnReturn：归还连接时执行 validationQuery 检查连接是否有效，会影响性能
- timeBetweenEvictionRunsMillis：连接的空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效

4、是否开启 removeAbandoned

```
if (removeAbandoned) {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    poolableConnection.connectStackTrace = stackTrace;
    poolableConnection.setConnectedTimeNano();
    poolableConnection.traceEnable = true;

    activeConnectionLock.lock();
    try {
        activeConnections.put(poolableConnection, PRESENT);
    } finally {
        activeConnectionLock.unlock();
    }
}
```

removeAbandoned 默认等于 false，如果开启了 removeAbandoned，则会将当前连接对象放入 activeConnections，用来防止连接泄露

- removeAbandoned：是否回收泄露的连接；当我们调用 Connection connection =  dataSource.getConnection() 获取连接后，如果时代码中某些地方忘记调用 connection.close()方法，在多次执行后连接池中所有的连接都会处于活动状态，连接池将被耗尽无法再获取新的连接，而这些活动连接实际又是空闲的连接并没有在工作，这就是连接泄露。但是一般我们使用的 ORM 框架都会帮我们做处理
- removeAbandonedTimeoutMillis：

### 三、getConnectionInternal(long maxWait)

![](https://typora-xxx.oss-cn-shenzhen.aliyuncs.com/img/1654819822.png)

```
if (createDirect) {
 // ... 省略
}

 try {
  // ... 省略
    if (createScheduler != null
         && poolingCount == 0
         && activeCount < maxActive
         && creatingCountUpdater.get(this) == 0
         && createScheduler instanceof ScheduledThreadPoolExecutor) {
     ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) createScheduler;
     if (executor.getQueue().size() > 0) {
         createDirect = true;
         continue;
     }
 }
 // ... 省略
}
```

createScheduler 与 createConnectionThread 互斥,我们一般不会使用 createScheduler，所以这里是不会走到 createDirect 里的

接下来是核心方法 pollLast 和 takeLast

```
private DruidPooledConnection getConnectionInternal(long maxWait) {
 // ... 省略
 DruidConnectionHolder holder;
 // ... 省略
 if (maxWait > 0) {
     holder = pollLast(nanos);
 } else {
     holder = takeLast();
 }
 // ... 省略
}
```
- 如果超时时间 > 0，则调用 pollLast(long nanos)
- 如果超时时间 = 0，即不超时，则调用 takeLast()

```
private DruidPooledConnection getConnectionInternal(long maxWait) throws SQLException {
	// ... 省略
	// 监控页面有用到,看这个连接被获取了几次
	holder.incrementUseCount();
	DruidPooledConnection poolalbeConnection = new DruidPooledConnection(holder);
	return poolalbeConnection;
}
```

最终成功获取到 holder 后，组装为 DruidPooledConnection 并返回

#### pollLast(long nanos)
```
private DruidConnectionHolder pollLast(long nanos) throws InterruptedException, SQLException {
    long estimate = nanos;

    for (;;) {
        if (poolingCount == 0) {
            emptySignal(); // send signal to CreateThread create connection
            // ... 省略
            if (estimate <= 0) {
                waitNanosLocal.set(nanos - estimate);
                return null;
            }
		   notEmptyWaitThreadCount++;
            if (notEmptyWaitThreadCount > notEmptyWaitThreadPeak) {
        		notEmptyWaitThreadPeak = notEmptyWaitThreadCount;
		   }
            try {
                long startEstimate = estimate;
                estimate = notEmpty.awaitNanos(estimate); // signal by recycle or creator
                notEmptyWaitCount++;
                notEmptyWaitNanos += (startEstimate - estimate);

                // ... 省略
            } catch (InterruptedException ie) {
                notEmpty.signal(); // propagate to non-interrupted thread
                notEmptySignalCount++;
                throw ie;
            } finally {
                notEmptyWaitThreadCount--;
            }

            if (poolingCount == 0) {
                if (estimate > 0) {
                    continue;
                }

                waitNanosLocal.set(nanos - estimate);
                return null;
            }
        }

        decrementPoolingCount();
        DruidConnectionHolder last = connections[poolingCount];
        connections[poolingCount] = null;

        long waitNanos = nanos - estimate;
        last.setLastNotEmptyWaitNanos(waitNanos);

        return last;
    }
}
```
如果连接池连接数为 0，触发 empty.signal()，这里是通知 CreateConnectionThread 进行创建连接（这里结合 CreateConnectionThread.run() 的代码）

接着触发

```
long startEstimate = estimate;
estimate = notEmpty.awaitNanos(estimate); // signal by recycle or creator
```

即等待 CreateConnectionThread 创建好连接或者有连接归还的通知，就可以获取到连接了

estimate 的值初始是 maxWait，由于是调用 awaitNanos 方法，所以每次被唤起 estimate 就会被刷新一次，根据 estimate 是否大于 0 来决定是否继续循环来获取连接

即 pollLast(long nanos) 是不会创建连接的，本质上是通过 empty、notEmpty 两个 Condition 来通知 CreateConnectionThread  进行创建连接，然后从 connections[] 数组中获取连接，但是如果超时，则直接返回 null

#### takeLast()

逻辑与 pollLast(long nanos) 一样，就是不触发超时，就不赘述了



#### 2、testConnectionInternal(DruidConnectionHolder holder, Connection conn)  检查连接是否有效

```
protected boolean testConnectionInternal(DruidConnectionHolder holder, Connection conn) {
	// ... 省略
    try {
        if (validConnectionChecker != null) {
            boolean valid = validConnectionChecker.isValidConnection(conn, validationQuery, validationQueryTimeout);
            long currentTimeMillis = System.currentTimeMillis();
            if (holder != null) {
                holder.lastValidTimeMillis = currentTimeMillis;
                holder.lastExecTimeMillis = currentTimeMillis;
            }

            if (valid && isMySql) { // unexcepted branch
                long lastPacketReceivedTimeMs = MySqlUtils.getLastPacketReceivedTimeMs(conn);
                if (lastPacketReceivedTimeMs > 0) {
                    long mysqlIdleMillis = currentTimeMillis - lastPacketReceivedTimeMs;
                    if (lastPacketReceivedTimeMs > 0 //
                            && mysqlIdleMillis >= timeBetweenEvictionRunsMillis) {
                        discardConnection(holder);
                        String errorMsg = "discard long time none received connection. "
                                + ", jdbcUrl : " + jdbcUrl
                                + ", version : " + VERSION.getVersionNumber()
                                + ", lastPacketReceivedIdleMillis : " + mysqlIdleMillis;
                        LOG.warn(errorMsg);
                        return false;
                    }
                }
            }

            if (valid && onFatalError) {
                lock.lock();
                try {
                    if (onFatalError) {
                        onFatalError = false;
                    }
                } finally {
                    lock.unlock();
                }
            }

            return valid;
        }
	// ... 省略
}
```



首先会判断 validConnectionChecker 是否为 null，因为在 init() 过程的 initValidConnectionChecker() 方法里，会创建 validConnectionChecker，所以这里是不为 null 的，接下来调用 validConnectionChecker.isValidConnection

> 在 init() 过程的 initValidConnectionChecker() 方法里，会创建 validConnectionChecker

```
 boolean valid = validConnectionChecker.isValidConnection(conn, validationQuery, validationQueryTimeout);
```

因为我们使用的是 mysql，所以这里就是 MySqlValidConnectionChecker，isValidConnection 方法过程如下

1、判断当前连接是否关闭，如果已关闭，直接 return false

2、判断是否启用 ping 方法，如果启用，则执行 ping，默认是使用 ping，ping 的通 return true，ping 不通，抛出异常

3、如果没有启用 ping 方法，则执行配置的 validateQuery 语句，默认是 "SELECT 1"，执行成功 return true，执行失败 return false

### 四、创建连接过程

![创建连接过程](https://typora-xxx.oss-cn-shenzhen.aliyuncs.com/img/1654819998.png)

### 1.CreateConnectionThread.run()

一进来也是死循环

```
// 如果还没完成 initialSize，则不会进入下面的 empty.await()
if (emptyWait && asyncInit && createCount < initialSize) {
    emptyWait = false;
}
if (emptyWait) {
    // 必须存在线程等待，才创建连接
    if (poolingCount >= notEmptyWaitThreadCount 
            && (!(keepAlive && activeCount + poolingCount < minIdle)) // 在keepAlive场景不能放弃创建
            && !isFailContinuous()
    ) {
        empty.await();
    }

    // 防止创建超过maxActive数量的连接
    if (activeCount + poolingCount >= maxActive) {
        empty.await();
        continue;
    }
}
```

1、如果还没完成 initialSize，则不会进入下面的 empty.await()

2、还记得在 takeLast() 和 pollLast(long nanos) 中会使 notEmptyWaitThreadCount++ 以及 notEmpty.awaitNanos(estimate)，即有线程等待获取连接，如果没有线程等待获取连接，或者防止创建超过maxActive数量的连接，这里会进入 empty.await()，即不会创建连接，而是等待通知后再去创建

```
PhysicalConnectionInfo connection = null;
try {
    connection = createPhysicalConnection();
}
// ... 省略
if (connection == null) {
    continue;
}
boolean result = put(connection);
if (!result) {
    JdbcUtils.close(connection.getPhysicalConnection());
    LOG.info("put physical connection to pool failed.");
}
```

然后执行 createPhysicalConnection() 创建物理连接，过程较简单，本质上就是调用 java.sql.Driver.connect(String url, java.util.Properties info)

然后调用 put(PhysicalConnectionInfo physicalConnectionInfo) 方法
```
put(PhysicalConnectionInfo physicalConnectionInfo) {
    try {
        holder = new DruidConnectionHolder(DruidDataSource.this, physicalConnectionInfo);
    }
    // ... 省略
    return put(holder, physicalConnectionInfo.createTaskId, false);    
}
```
创建 DruidConnectionHolder 对象，并继续调用 put(DruidConnectionHolder holder, long createTaskId, boolean checkExists) 方法

```
private boolean put(DruidConnectionHolder holder, long createTaskId, boolean checkExists) {
	// 将当前创建的连接放入 connections[] 数组中
    connections[poolingCount] = holder;
    // poolingCount++
    incrementPoolingCount();
    // 通知 getConnectionInternal 方法，可以获取连接了
    notEmpty.signal();            
}
```

1、connections[poolingCount] = holder   将当前创建的连接放入 connections[] 数组中

2、incrementPoolingCount()   即 poolingCount++

3、notEmpty.signal()  通知 getConnectionInternal 方法，可以获取连接了； 还记得在 takeLast() 和 pollLast(long nanos) 中的 notEmpty.awaitNanos(estimate) 吗

### 五、销毁、保活连接过程

![销毁、保活连接过程](https://typora-xxx.oss-cn-shenzhen.aliyuncs.com/img/1654820132.png)

### 1. DestroyConnectionThread.run()

```
public class DestroyConnectionThread extends Thread {
    @Override
    public void run() {
        for (;;) {
            if (timeBetweenEvictionRunsMillis > 0) {
                Thread.sleep(timeBetweenEvictionRunsMillis);
            } else {
                Thread.sleep(1000); //
            }

            destroyTask.run();
        }
    }
}    
```

每隔 timeBetweenEvictionRunsMillis 时间，默认 1min，执行一次 DestroyTask.run()

```
public class DestroyTask implements Runnable {
    @Override
    public void run() {
        shrink(true, keepAlive);
	    // 是否回收泄露的连接
        if (isRemoveAbandoned()) {
            removeAbandoned();
        }
    }
}
```

默认 isRemoveAbandoned() = false,这里只用关注 shrink(true, keepAlive)

```
public void shrink(boolean checkTime, boolean keepAlive) {
	    final int checkCount = poolingCount - minIdle;
    final long currentTimeMillis = System.currentTimeMillis();
    for (int i = 0; i < poolingCount; ++i) {
        DruidConnectionHolder connection = connections[i];
        if (checkTime) {
         	// phyTimeoutMillis 默认值是 -1,一般不配置
            if (phyTimeoutMillis > 0) {
                long phyConnectTimeMillis = currentTimeMillis - connection.connectTimeMillis;
                if (phyConnectTimeMillis > phyTimeoutMillis) {
                    evictConnections[evictCount++] = connection;
                    continue;
                }
            }
            // 空闲时间
            long idleMillis = currentTimeMillis - connection.lastActiveTimeMillis;

            if (idleMillis < minEvictableIdleTimeMillis
                    && idleMillis < keepAliveBetweenTimeMillis
            ) {
                break;
            }

            if (idleMillis >= minEvictableIdleTimeMillis) {
                if (checkTime && i < checkCount) {
                    evictConnections[evictCount++] = connection;
                    continue;
                } else if (idleMillis > maxEvictableIdleTimeMillis) {
                    evictConnections[evictCount++] = connection;
                    continue;
                }
            }

            if (keepAlive && idleMillis >= keepAliveBetweenTimeMillis) {
                keepAliveConnections[keepAliveCount++] = connection;
            }
        }
    }
}
```

这里是对 connection[] 数组里的连接做处理

- phyTimeoutMillis 默认值是 -1,一般不配置
- 如果 idleMillis 小于 minEvictableIdleTimeMillis,且小于 keepAliveBetweenTimeMillis,不进行驱逐
- 如果 idleMillis 大于等于 minEvictableIdleTimeMillis
   - 对 connections[] 数组中的前 checkCount 个连接放入 evictConnections[] 数组
   - 如果 idleMillis 大于 maxEvictableIdleTimeMillis,将该连接放入 evictConnections[] 数组,不管该连接是否是前 checkCount 个
- 对于 idleMillis 大于等于 keepAliveBetweenTimeMillis 的连接放入 keepAliveConnections[] 数组中，进行保活

- minEvictableIdleTimeMillis：连接被驱逐的最小空闲时间
- maxEvictableIdleTimeMillis：连接被驱逐的最大空闲时间

```
int removeCount = evictCount + keepAliveCount;
if (removeCount > 0) {
    System.arraycopy(connections, removeCount, connections, 0, poolingCount - removeCount);
    Arrays.fill(connections, poolingCount - removeCount, poolingCount, null);
    poolingCount -= removeCount;
}
keepAliveCheckCount += keepAliveCount;

if (keepAlive && poolingCount + activeCount < minIdle) {
    needFill = true;
}
```
- 如果 n = 驱逐数量 + 保活数量 > 0，则 将前 n 个连接从 connections[] 数组中移除
- 如果 n = 连接池中剩余的线程数量+正在使用的线程数量 < minIdle，且需要保活，则要补全线程

```
if (evictCount > 0) {
    for (int i = 0; i < evictCount; ++i) {
        DruidConnectionHolder item = evictConnections[i];
        Connection connection = item.getConnection();
        JdbcUtils.close(connection);
        destroyCountUpdater.incrementAndGet(this);
    }
    Arrays.fill(evictConnections, null);
}
```
对于 evictConnections[] 数组的连接，会将连接 close 掉

本质上驱逐连接就是把某个连接从 connection[] 数组中移除，并关闭它

```
if (keepAliveCount > 0) {
    // keep order
    for (int i = keepAliveCount - 1; i >= 0; --i) {
        DruidConnectionHolder holer = keepAliveConnections[i];
        Connection connection = holer.getConnection();
        holer.incrementKeepAliveCheckCount();

        boolean validate = false;
        try {
            this.validateConnection(connection);
            validate = true;
        } catch (Throwable error) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("keepAliveErr", error);
            }
            // skip
        }

        boolean discard = !validate;
        if (validate) {
            holer.lastKeepTimeMillis = System.currentTimeMillis();
            boolean putOk = put(holer, 0L, true);
            if (!putOk) {
                discard = true;
            }
        }

```
对于 keepAliveConnections[] 数组的连接，会先判断连接是否有效，这里最终还是调的 mysqlValidConnectionChecker.isValidConnection 方法，如果连接有效，则将该连接重新放入到 connection[] 数组中

// TODO 关于参数配置方面

### 六、关闭连接过程

![关闭连接过程](https://typora-xxx.oss-cn-shenzhen.aliyuncs.com/img/1654820296.png)

#### 1、DruidPooledConnection.close()

```
@Override
public void close() throws SQLException {
    boolean isSameThread = this.getOwnerThread() == Thread.currentThread();

    if (!isSameThread) {
    dataSource.setAsyncCloseConnectionEnable(true);
    }

    if (dataSource.isAsyncCloseConnectionEnable()) {
    syncClose();
    return;
    }
}
```

先会判断进行 close 操作的线程是否与使用该连接的线程是同一个线程，如果不是，就执行 syncClose()，不管是同步还是异步，都执行的是 recycle() 方法

```
public void recycle() throws SQLException {
    // ... 省略
    if (!this.abandoned) {
        DruidAbstractDataSource dataSource = holder.getDataSource();
        dataSource.recycle(this);
    }

    this.holder = null;
    conn = null;
    transactionInfo = null;
    closed = true;
}
```

因为 abandoned 默认是 false，且我们也不会把它置为 true，所以这里会进入，dataSource.recycle(this)，即回收连接，最后会将当前 DruidPooledConnection 对象里的 holder、connection 对象都置为 null

```
public class DruidDataSource extends DruidAbstractDataSource implements DruidDataSourceMBean, ManagedDataSource, Referenceable, Closeable, Cloneable, ConnectionPoolDataSource, MBeanRegistration {
	protected void recycle(DruidPooledConnection pooledConnection) throws SQLException {
        final boolean isAutoCommit = holder.underlyingAutoCommit;
        final boolean isReadOnly = holder.underlyingReadOnly;

        try {
            // check need to rollback?
            if ((!isAutoCommit) && (!isReadOnly)) {
                pooledConnection.rollback();
            }
        // ... 省略
```

首先会判断事务是否进行回滚

```
// reset holder, restore default settings, clear warnings
boolean isSameThread = pooledConnection.ownerThread == Thread.currentThread();
if (!isSameThread) {
    final ReentrantLock lock = pooledConnection.lock;
    lock.lock();
    try {
        holder.reset();
    } finally {
        lock.unlock();
    }
} else {
    holder.reset();
}
```

然后执行 holder.reset() 即重置为初始值，以及清空告警

```
if (testOnReturn) {
    boolean validate = testConnectionInternal(holder, physicalConnection);
    if (!validate) {
        JdbcUtils.close(physicalConnection);

        destroyCountUpdater.incrementAndGet(this);

        lock.lock();
        try {
            if (holder.active) {
                activeCount--;
                holder.active = false;
            }
            closeCount++;
        } finally {
            lock.unlock();
        }
        return;
    }
}
```

如果我们开启了 testOnReturn，就会在归还连接时执行 validationQuery 检查连接是否有效，但是这会影响性能，不建议开启

```
try {
    if (holder.active) {
        activeCount--;
        holder.active = false;
    }
    closeCount++;

    result = putLast(holder, currentTimeMillis);
    recycleCount++;
} finally {
    lock.unlock();
}
if (!result) {
    JdbcUtils.close(holder.conn);
    LOG.info("connection recyle failed.");
}
```

最后，如果连接并没有关闭，则会执行 putLast(DruidConnectionHolder e, long lastActiveTimeMillis) 将该连接重新放入到 connections[] 数组中，如果放入失败，例如连接池已经满了(poolingCount >= maxActive)，就直接关闭连接

```
 boolean putLast(DruidConnectionHolder e, long lastActiveTimeMillis) {
        if (poolingCount >= maxActive || e.discard || this.closed) {
            return false;
        }

        e.lastActiveTimeMillis = lastActiveTimeMillis;
        connections[poolingCount] = e;
        incrementPoolingCount();

        if (poolingCount > poolingPeak) {
            poolingPeak = poolingCount;
            poolingPeakTime = lastActiveTimeMillis;
        }

        notEmpty.signal();
        notEmptySignalCount++;

        return true;
    }
```

### 总结

// TODO 

### Q&A 

1、testOnBorrow、testWhileIdle、testOnReturn 分别代表什么

2、notEmpty、empty 这两个 Condition 分别起了什么作用