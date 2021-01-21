package review.waitnotify;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/1/21 15:16
 */
public class Info {
    private String name = "name";
    private String content = "content";
    /**
     * 标识位，表示生产者是否可以进行生产，true表示可以，false表示不可以
     */
    private boolean flag = true;

    /**
     * 生产者生产资源
     * @param name
     * @param content
     */
    public synchronized void set(String name,String content) {
        // 如果不可以进行生产，则对Info对象加锁
        if(!flag) {
            try {
                //让拥有Info对象的生产者线程进入等待状态
                super.wait();
                //this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果可以进行生产,则设置name与content属性
        this.setName(name);
//        try {
//            Thread.sleep(30);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        this.setContent(content);
        System.out.println("生产："+this.getName()+":-->"+this.getContent());
        // 修改标志位为false,表示可以消费,不能生产
        flag=false;
        // 唤醒拥有Info对象的线程
        super.notify();
    }

    /**
     * 消费者消费资源
     */
    public synchronized void get() {
        // 如果flag==true表示可以生产不可以消费
        if(flag) {
            try {
                // 让拥有Info资源的消费者线程进入等待
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        try {
//            Thread.sleep(30);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("消费："+this.getName()+":-->"+this.getContent());
        // 修改标志位为true,表示消费者拿走资源,生产者可以生产
        flag = true;
        // 唤醒生产者进程
        super.notify();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
