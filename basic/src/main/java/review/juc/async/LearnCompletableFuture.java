package review.juc.async;


import review.juc.NamedThreadFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 学习 {@link java.util.concurrent.CompletableFuture}
 * @author: xiaoxiaoxiang
 * @date: 2020/7/22 17:33
 */
public class LearnCompletableFuture {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
            0, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            new NamedThreadFactory("learnCompletableFuture"),
            new ThreadPoolExecutor.AbortPolicy());

    public void learn() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "learn CompletableFuture";
        }, executor);
        future1.thenAccept(e -> {
            System.out.println(e);
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "learn CompletableFuture";
        }, executor);
        future2.thenAccept(e -> {
            System.out.println(e);
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "learn CompletableFuture";
        }, executor);
        future3.thenAccept(e -> {
            System.out.println(e);
        });

        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(()->7/0, executor)
                .thenApply(r->r*10)
                .exceptionally(e->0);

        future1.join();
        future2.join();
        future3.join();
        System.out.println(future4.join());

        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {
            // 第一个实例的结果
            return "hello";
        }).thenCompose(resultA -> CompletableFuture.supplyAsync(() -> {
            // 把上一个实例的结果传递到这里
            return resultA + " world";
        })).thenCompose(resultAB -> CompletableFuture.supplyAsync(() -> {
            return resultAB + ", I'm xxx";
        }));
        System.out.println(future5.join());
        executor.shutdown();
    }

    public static void main(String[] args) {
        LearnCompletableFuture obj = new LearnCompletableFuture();
        obj.learn();

//        System.out.println(111);
    }
}
