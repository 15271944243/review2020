package review.gc;

/**
 * volatile禁止指令重排序
 * @author: xiaoxiaoxiang
 * @date: 2020/11/26 14:24
 */
public class TestVolatile {

    int a=0;
    volatile int b=0;

    public void method1() {
        int r2 = a;
        b = 1;
        System.out.println("r2=" + r2);
    }

    public void method2() {
        int r1 = b;
        a = 2;
        System.out.println("r1=" + r1);
    }

    /**
     * b 是volatile 修饰的
     * 所以 b=1 happens-before int r1=b; 所以结果是 r1=1 r2=0
     * @param args
     */
    public static void main(String[] args) {
        TestVolatile t = new TestVolatile();
        Thread t1 = new Thread(() -> {
            t.method1();

        });
        Thread t2 = new Thread(() -> {
            t.method2();
        });
        t1.start();
        t2.start();
    }
}
