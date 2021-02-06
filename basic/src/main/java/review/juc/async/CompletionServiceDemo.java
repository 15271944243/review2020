package review.juc.async;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletionService 可以用来批量执行异步任务
 * @author: xiaoxiaoxiang
 * @date: 2021/2/6 16:51
 */
public class CompletionServiceDemo {

    // ExecutorCompletionService

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executorService);
        cs.submit(() -> {
            return 1;
        });
        cs.submit(() -> {
            return 2;
        });
        cs.submit(() -> {
            return 3;
        });
        for (int i = 0; i < 3; i++) {
            try {
                Integer r = cs.take().get();
                System.out.println("result:" + r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
