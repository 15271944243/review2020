package review.juc;


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
    }

    public static void main(String[] args) {
        LearnCompletableFuture obj = new LearnCompletableFuture();
        obj.learn();

        System.out.println(111);
    }
}
