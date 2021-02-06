package review.juc.async;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/2/6 10:51
 */
public class CompletableFutureDemo {

    /**
     * runAsync、supplyAsync 有什么区别?
     * join 和 get 有什么区别
     * @param args
     */
    public static void main(String[] args) {
        CompletableFutureDemo demo = new CompletableFutureDemo();
        demo.tea();
        System.out.println("------------------");
        demo.getPrice();
    }

    /**
     * 泡茶: 洗水壶、烧开水、洗茶壶、洗茶杯、拿茶叶
     */
    private void tea() {
        // 任务1: 洗水壶、烧开水
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("T1:洗水壶...");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T1:烧开水...");
            sleep(5, TimeUnit.SECONDS);
        });
        // 任务2: 洗茶壶、洗茶杯、拿茶叶
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶...");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T2:洗茶杯...");
            sleep(2, TimeUnit.SECONDS);
            System.out.println("T2:拿茶叶...");
            sleep(1, TimeUnit.SECONDS);
            return "龙井";
        });
        //任务3: 任务1和任务2完成后执行:泡茶
        CompletableFuture<String> future3 = future.thenCombine(future2, (k1 , k2) ->{
            System.out.println("T3:拿到茶叶:" + k2);
            System.out.println("T3:泡茶...");
            return "上茶:" + k2;
        });

        future3.join();
    }

    void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多任务执行完成
     * 异步获取 南航、海航、东航的机票价格,最后汇总
     *
     * 其实也可以使用ForkJoinPool
     */
    private void getPrice() {
        ConcurrentHashMap<String, Integer> priceMap = new ConcurrentHashMap<>();
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Task("nanhang", priceMap));
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(new Task("haihang", priceMap));
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(new Task("donghang", priceMap));

        CompletableFuture<Void> allTask = CompletableFuture.allOf(future, future2, future3);
        try {
            // 设置超时时间, 假如future2超时,而future 和 future3没超时,就会获取 future 和 future3 的结果
            allTask.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        priceMap.forEach((k ,v) -> {
            System.out.println(k + "机票, 价格: " + v);
        });
    }


    static class Task implements Runnable {

        String productId;

        ConcurrentHashMap<String, Integer> priceMap = new ConcurrentHashMap<>();

        public Task(String productId, ConcurrentHashMap<String, Integer> priceMap) {
            this.productId = productId;
            this.priceMap = priceMap;
        }

        @Override
        public void run() {
            int price = 0;
            try {
                Thread.sleep((long) (Math.random() * 4000));
                price = (int) (Math.random() * 4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            priceMap.put(productId, price);
        }
    }
}
