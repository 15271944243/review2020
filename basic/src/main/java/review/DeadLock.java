package review;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/2/3 17:54
 */
public class DeadLock implements Runnable {
    public int flag;
    static Object o1 = new Object();
    static Object o2 = new Object();

    @Override
    public void run() {
        System.out.println("线程: " + Thread.currentThread().getName() + "的flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    System.out.println("flag == 1 的线程获得锁o1");
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("flag == 1 的线程获得了两把锁");
                }
            }
        }
        if (flag == 2) {
            synchronized (o2) {
                try {
                    System.out.println("flag == 1 的线程获得锁o2");
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("flag == 2 的线程获得了两把锁");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();
        deadLock1.flag = 1;
        deadLock2.flag = 2;
        new Thread(deadLock1, "t1").start();
        new Thread(deadLock2, "t2").start();
    }
}
