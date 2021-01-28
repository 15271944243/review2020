package review.juc.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * {@link java.util.concurrent.locks.StampedLock} 的 java doc demo
 * StampedLock 的性能之所以比 ReadWriteLock 还要好,其关键是 StampedLock 支持乐观读的方式
 * @author: xiaoxiaoxiang
 * @date: 2021/1/28 15:26
 */
public class StampedLockDemo {

    private int x, y;

    final StampedLock sl = new StampedLock();

    //计算到原点的距离
    double distanceFromOrigin() {
        // 乐观读
        long stamp = sl.tryOptimisticRead();
        // 读入局部变量 // 读的过程数据可能被修改
        int curX = x, curY = y;
        // 判断执行读操作期间
        // 是否存在写操作,如果存在
        // 则sl.validate返回false
        if (!sl.validate(stamp)) {
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(curX * curX + curY * curY);
    }

}
