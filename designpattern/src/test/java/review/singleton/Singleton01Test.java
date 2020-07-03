package review.singleton;


import org.junit.Test;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2020/7/2 17:53
 */
public class Singleton01Test {

    @Test
    public void getSingleton() {

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton06 singleton06 = Singleton06.getSingleton();
                    System.out.println(singleton06.toString());

                    //  Singleton08 singleton08 = Singleton08.getSingleton();
                    //  System.out.println(singleton08.toString());
                }
            }).start();
        }
    }
}
