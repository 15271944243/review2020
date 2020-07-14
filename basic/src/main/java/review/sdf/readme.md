### SimpleDateFormat的线程安全问题与解决方案

#### SimpleDateFormat线程不安全

详见测试类`review.sdf.SdfThreadUnsafeTest`

#### SimpleDateFormat为什么是线程不安全的

SimpleDateFormat(下面简称sdf)类内部有一个Calendar对象引用,它用来储存和这个sdf相关的日期信息;
拿sdf.format方法来说,该方法会调用`calendar.setTime(date);`,如果两个线程同时调用同一个sdf对象的
format方法,这个`calendar`对象是共享的,很可能就会出现线程B的`calendar.setTime(date);`
覆盖掉线程A的`calendar.setTime(date);`

#### 如何解决
 
1. 使用java 8的DateTimeFormatter代替SimpleDateFormat
2. 每次使用SimpleDateFormat的时候就去new一个对象,但这样不好,消耗比较多,不推荐
3. 创建一个SimpleDateFormat对象,使用时对它加锁,性能较差,不推荐
4. 使用ThreadLocal,参考`review.sdf.SimpleDateFormatUtil`