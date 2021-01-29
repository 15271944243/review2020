package review.juc.condition;

import java.util.concurrent.Semaphore;

/**
 * {@link java.util.concurrent.Semaphore} 的 java doc demo
 * @author: xiaoxiaoxiang
 * @date: 2021/1/29 13:51
 */
public class SemaphoreDemo {

    private static final int MAX_AVAILABLE = 100;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);

    public Integer getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Integer x) {
        if (markAsUnused(x)) {
            available.release();
        }
    }

    // Not a particularly efficient data structure; just for demo

    protected Integer[] items = new Integer[]{1, 2, 4, 3, 5, 6};
    protected boolean[] used = new boolean[MAX_AVAILABLE];

    protected synchronized Integer getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    protected synchronized boolean markAsUnused(Integer item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item.equals(items[i])) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
