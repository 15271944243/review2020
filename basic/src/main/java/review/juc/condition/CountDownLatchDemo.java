package review.juc.condition;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * {@link java.util.concurrent.CountDownLatch} 的 java doc demo
 * @author: xiaoxiaoxiang
 * @date: 2021/1/29 16:55
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
//        Driver driver = new Driver();
//        driver.main(3);
        Driver2 driver2 = new Driver2();
        driver2.main(3);
    }

    static class Driver {

        void main(int n) throws InterruptedException {
            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch doneSignal = new CountDownLatch(n);
            for (int i = 0; i < n; ++i) {
                new Thread(new Worker(startSignal, doneSignal)).start();
            }
            Thread.sleep(1000L);
            System.out.println("main Thread startSignal countDown");
            startSignal.countDown();
            System.out.println("main Thread doneSignal await");
            doneSignal.await();
            System.out.println("main Thread over !");
        }
    }

    static class Worker implements Runnable {
        private final CountDownLatch startSignal;

        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() +  ": startSignal await");
                startSignal.await();
                doWork();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() +  ": doneSignal countDown");
                doneSignal.countDown();
            }
        }
        void doWork() {
            System.out.println(Thread.currentThread().getName() +  ": worker do work!");
        }
    }


    static class Driver2 {
        void main(int n) throws InterruptedException {
            CountDownLatch doneSignal = new CountDownLatch(n);
            Executor e = Executors.newFixedThreadPool(5);
            for (int i = 0; i < n; ++i) {
                e.execute(new WorkerRunnable(doneSignal, i));
            }
//            Thread.sleep(1000L);
            System.out.println("main Thread doneSignal await");
            doneSignal.await();
            System.out.println("main Thread over !");
        }
    }

    static class WorkerRunnable implements Runnable {
        private final CountDownLatch doneSignal;

        private final int i;

        WorkerRunnable(CountDownLatch doneSignal, int i) {
            this.doneSignal = doneSignal;
            this.i = i;
        }
        @Override
        public void run() {
            try {
                doWork(i);
            } finally {
                System.out.println(Thread.currentThread().getName() +  ": doneSignal countDown");
                doneSignal.countDown();
            }
        }

        void doWork(int i) {
            System.out.println(Thread.currentThread().getName() +  ", i: " + i +  ",worker do work!");
        }
    }
}
