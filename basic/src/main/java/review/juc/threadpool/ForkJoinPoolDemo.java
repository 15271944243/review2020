package review.juc.threadpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool 充分利用多核CPU
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
        private static final long serialVersionUID = 5465270634711379349L;
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
            // 当前线程也要干活,所以这里应该这么写, 而不是 f1.fork(); f2.fork(); f1.join(); f2.join()
            // 如果要用两次fork()再join,需要用这样的顺序：a.fork(); b.fork(); b.join(); a.join();这个要求在JDK官方文档里有说明
            return f2.compute() + f1.join();
        }
    }
}
