package review.waitnotify;

/**
 * 生产者线程
 */
public class Producer implements Runnable {

    private Info info = null;

    public Producer(Info info)
    {
        this.info=info;
    }

    @Override
    public void run() {
        boolean flag = false;
        for (int i=0;i<10;i++) {
            if (flag) {
                this.info.set("name+" + i, "content+" + i);
                flag = false;
            } else {
                this.info.set("name-" + i, "content-" + i);
                flag = true;
            }
        }
    }
}