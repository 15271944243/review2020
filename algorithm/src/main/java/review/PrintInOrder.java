package review;

import java.util.concurrent.Semaphore;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/5/24 15:16
 */
public class PrintInOrder {

    private Semaphore two = new Semaphore(0);
    private Semaphore three = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {
        System.out.println("hello first");
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        Thread.sleep(3000L);
        two.release();
        System.out.println("release two");
    }

    public void second(Runnable printSecond) throws InterruptedException {
        System.out.println("hello second");
        two.acquire();
        System.out.println("acquire two");
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        three.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        three.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {
        PrintInOrder demo = new PrintInOrder();

        new Thread(()-> {
            try {
                demo.second(()->{
                    System.out.println("second");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try {
                demo.first(()->{
                    System.out.println("first");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


