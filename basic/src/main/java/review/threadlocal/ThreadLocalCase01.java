package review.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal会出现内存泄漏问题
 * @author: xiaoxiaoxiang
 * @date: 2020/8/13 13:57
 */
public class ThreadLocalCase01 {

    /**
     * 发生垃圾回收,若这个对象只被弱引用指向,那么就会被回收
     * -verbose:gc -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("sdfsdfsdf");
        System.gc();
        // 由于强引用threadLocal还存在,所以GC时并不会回收Entry
        String aaa = threadLocal.get();
        System.out.println(aaa);
    }
}
