package review.waitnotify;

/**
 * 消费者线程
 */
public class Consumer implements Runnable{

    private Info info=null;

    public Consumer(Info info)
    {
        this.info=info;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.info.get();
        }
    }
}