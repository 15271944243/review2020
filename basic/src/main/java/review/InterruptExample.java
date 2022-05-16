package review;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2022/5/16 23:19
 */
public class InterruptExample {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        task.start();
        Thread.sleep(1000);
        // 调用 interrupt 方法,给 task 线程发送一个中断信号
        task.interrupt();
    }

    static class Task extends Thread {
        @Override
        public void run() {
            while (true) {
                // 中断信号判断
                if (this.isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " 线程停止了");
                    break;
                }
                System.out.println("hello");
            }
        }
    }
}
