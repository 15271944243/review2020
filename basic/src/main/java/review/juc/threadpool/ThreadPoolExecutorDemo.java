package review.juc.threadpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/4/14 18:27
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutorDemo demo = new ThreadPoolExecutorDemo();
        demo.invokeAll();
    }

    public void invokeAll() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(256), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        }, new ThreadPoolExecutor.AbortPolicy());

        MyTask task1 = new MyTask("qwe");
        MyTask  task2 = new MyTask("czf");
        MyTask task3 = new MyTask("fdg");

        Collection<MyTask> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        try {
            List<Future<String>> futureList =  executor.invokeAll(taskList);
            for (Future<String> future : futureList) {
                try {
                    String t = future.get();
                    System.out.println("t:" + t);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }

    static class MyTask implements Callable<String> {
        private String str;

        public MyTask(String str) {
            this.str = str;
        }

        @Override
        public String call() throws Exception {
            return this.str;
        }
    }
}
