package review.juc.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2021/2/3 14:25
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1, 2, 3});
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        Iterator<Integer> iterator2 = list.iterator();

        iterator.forEachRemaining(System.out::print);
        System.out.println();
        iterator2.forEachRemaining(System.out::print);
        System.out.println();
    }
}
