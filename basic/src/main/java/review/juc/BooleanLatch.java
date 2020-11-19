package review.juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AQS Java doc Sample 实现一个类似CountDownLatch的单个信号的共享锁
 * @author: xiaoxiaoxiang
 * @date: 2020/11/19 10:59
 */
public class BooleanLatch {

    private final Sync sync = new Sync();

    public boolean isSignalled() {
        return sync.isSignalled();
    }

    public void signal() {
        sync.releaseShared(1);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    private static class Sync extends AbstractQueuedSynchronizer {

        boolean isSignalled() {
            return getState() != 0;
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return isSignalled() ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }
}
