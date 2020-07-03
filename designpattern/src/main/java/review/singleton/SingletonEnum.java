package review.singleton;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/3 15:34
 */
public enum SingletonEnum {

    INSTANCE;

    private Singleton09 singleton09;

    private SingletonEnum() {
        singleton09 = new Singleton09();
    }

    public Singleton09 getSingleton09() {
        return singleton09;
    }
}
