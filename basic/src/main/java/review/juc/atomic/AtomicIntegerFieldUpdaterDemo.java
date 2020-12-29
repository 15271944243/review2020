package review.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater 用来对一个对象中volatile int类型进行原子更新操作
 * 1. Updater更新的的必须是int类型变量, 不能是其包装类型
 * 2. Updater更新的必须是volatile修饰的变量
 * 3. Updater更新的不能是static修饰的变量, 因为Unsafe.objectFieldOffset()方法不支持静态变量(CAS操作本质上是通过对象实例的偏移量来直接进行赋值)
 * 4. Updater只能修改它可见范围内的变量, 因为Updater是通过反射来获得这个变量
 *
 * 如果要更新的变量是包装类型, 那么可以使用AtomicReferenceFieldUpdater
 * @author: xiaoxiaoxiang
 * @date: 2020/12/10 08:56
 */
public class AtomicIntegerFieldUpdaterDemo {

    private static final AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    /**
     * 相比与使用AtomicInteger, 使用AtomicIntegerFieldUpdater目的是为了节约内存
     *
     * 当使用AtomicInteger时, 相当于每new一个User2对象时, 既要new一个AtomicInteger age,
     * 即有n个User2对象, 就有n个AtomicInteger age对象
     *
     * 当使用AtomicIntegerFieldUpdater + volatile int age 时,
     * 首先AtomicIntegerFieldUpdater一般是当作常量使用,不会每new一个对象就new一个AtomicIntegerFieldUpdater
     * 其次尽管每个User对象都有volatile int age属性,但是int相对AtomicInteger,少了对象头的大小, 节约了内存
     * @param args
     */
    public static void main(String[] args) {
        User user = new User();
        User user2 = new User();

        User2 user3 = new User2();
        User2 user4 = new User2();

        for (int i=0;i<10;i++) {
            final int t = i;
            Thread thread = new Thread(()->{
                System.out.println("user age: " + atomicIntegerFieldUpdater.getAndIncrement(user));
                System.out.println("user3 age: " + user3.age.getAndIncrement());
                if (t < 5) {
                    System.out.println("user2 age: " + atomicIntegerFieldUpdater.getAndIncrement(user2));
                    System.out.println("user4 age: " + user4.age.getAndIncrement());
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
