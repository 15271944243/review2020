package review.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater 用来对一个对象中volatile int类型进行原子更新操作
 * 1. Updater更新的的必须是int类型变量, 不能是其包装类型
 * 2. Updater更新的必须是vloatile修饰的变量
 * 3. Updater更新的不能是static修饰的变量, 因为Unsafe.objectFieldOffset()方法不支持静态变量(CAS操作本质上是通过对象实例的偏移量来直接进行赋值)
 * 4. Updater只能修改它可见范围内的变量, 因为Updater是通过反射来获得这个变量
 *
 * 如果要更新的变量是包装类型, 那么可以使用AtomicReferenceFieldUpdater
 * @author: xiaoxiaoxiang
 * @date: 2020/12/10 08:56
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

        User user = new User();

        for (int i=0;i<10;i++) {
            Thread thread = new Thread(()->{
                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(user));
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
