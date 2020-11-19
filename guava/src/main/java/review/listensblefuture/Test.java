package review.listensblefuture;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 未完待续
 * @author: xiaoxiaoxiang
 * @date: 2020/7/21 09:15
 */
public class Test {

    /**
     * 使用ThreadFactoryBuilder定义一个线程池
     */
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    /**
     * 自定义线程池
     * corePoolSize  核心线程池大小
     * maximumPoolSize 线程池最大容量
     * keepAliveTime 线程池空闲时,线程存活的时间
     * unit 存活时间单位
     * workQueue 工作队列
     * threadFactory 线程工厂
     * handler 处理当线程队列满了,也就是执行拒绝策略
     */
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
            namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());



    public void aaa() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(executor);

        //像线程池提交任务，并得到ListenableFuture
        ListenableFuture<String> listenableFuture = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        });
        //可以通过addListener对listenableFuture注册回调，但是通常使用Futures中的工具方法
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        }, service);
    }
}
