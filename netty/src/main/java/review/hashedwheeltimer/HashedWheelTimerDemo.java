package review.hashedwheeltimer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/16 10:24
 */
public class HashedWheelTimerDemo {

    /**
     * timeout1 cancel 了,所以没有输出内容
     * timeout2 和 timeout3 的打印时间相差了 5s,这是由于 timeout2 阻塞了 5s 造成的
     * 时间轮中的任务执行是串行的,当一个任务执行的时间过长,会影响后续任务的调度和执行,很可能产生任务堆积的情况
     * @param args
     */
    public static void main(String[] args) {
        HashedWheelTimer timer = new HashedWheelTimer();
        Timeout timeout1 = timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("timeout1: " + new Date());
            }
        }, 10, TimeUnit.SECONDS);
        if (!timeout1.isExpired()) {
            timeout1.cancel();
        }
        Timeout timeout2 = timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("timeout2: " + new Date());
                Thread.sleep(5000L);
            }
        }, 1, TimeUnit.SECONDS);

        Timeout timeout3 = timer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("timeout3: " + new Date());
            }
        }, 3, TimeUnit.SECONDS);

    }
}
