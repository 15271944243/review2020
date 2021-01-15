package review.juc;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue 提供了 put() 和 take() 的阻塞方法,可以向队列中添加对象和取出对象
 * 对象被添加到 DelayQueue 后,会根据 compareTo() 方法进行优先级排序
 * getDelay() 方法用于计算消息延迟的剩余时间,只有 getDelay <=0 时,该对象才能从 DelayQueue 中取出
 * @author: xiaoxiaoxiang
 * @date: 2021/1/15 15:26
 */
public class DelayQueueDemo {


    public static void main(String[] args) throws InterruptedException {
        /**
         * 相比于 Timer,DelayQueue 只实现了任务管理的功能,需要与异步线程配合使用.
         * DelayQueue 使用优先级队列实现任务的优先级排序,新增 Schedule 和取消 Cancel 操作的时间复杂度也是 O(logn)。
         */
        DelayQueue<SampleTask> delayQueue = new DelayQueue<>();
        long now = System.currentTimeMillis();
        delayQueue.put(new SampleTask(now + 1000));
        delayQueue.put(new SampleTask(now + 2000));
        delayQueue.put(new SampleTask(now + 3000));
        for (int i = 0; i < 3; i++) {
            System.out.println(new Date(delayQueue.take().getTime()));
        }
    }

    static class SampleTask implements Delayed {

        long time;

        public SampleTask(long time) {
            this.time = time;
        }

        public long getTime() {
            return time;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS), o.getDelay(TimeUnit.MILLISECONDS));
        }
    }
}
