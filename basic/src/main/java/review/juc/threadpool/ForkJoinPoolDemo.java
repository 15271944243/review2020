package review.juc.threadpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 计算斐波那契数列
 * @author: xiaoxiaoxiang
 * @date: 2021/2/6 17:10
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) {
        // 创建分治任务线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        // 创建分治任务
        Fibonacci fibonacci = new Fibonacci(30);
        // 启动分治任务
        Integer result = forkJoinPool.invoke(fibonacci);
        System.out.println("result: " + result);
    }

    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if (n <= 1) {
                return 1;
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            return f2.compute() + f1.join();
        }
    }
}
