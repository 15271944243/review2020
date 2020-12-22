package review.jksj.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/12/21 15:59
 */
public final class IdUtil {
    private IdUtil() {
    }

    private static final AtomicLong IDX = new AtomicLong();

    public static long nextId() {
        return IDX.incrementAndGet();
    }
}
