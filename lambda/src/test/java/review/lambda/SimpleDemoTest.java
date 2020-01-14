package review.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/10/23 09:17
 */
public class SimpleDemoTest {

    /**
     * 在哪里以及如何使用 Lambda?
     * 1. 函数式接口(带有@FunctionalInterface的接口)
     */

    @Test
    public void runable() {
        // Java 8之前：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        //Java 8方式：
        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
    }

    @Test
    public void foreach() {
        // Java 8之前：
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String feature : features) {
            System.out.println(feature);
        }

        // Java 8之后：
        features.forEach(n -> System.out.println(n));
        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }

    @Test
    public void map() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("2", "3");
        map.put("3", "4");
        map.forEach((k, v) -> System.out.println("k:" + k + ",v:" + v));

//        cost.stream().map(x -> x + x*0.05).forEach(x -> System.out.println(x));
    }

    @Test
    public void compare() {
        // Java 8之前：
        Comparator<Integer> comparator01 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        // Java 8之后：
        Comparator<Integer> comparator02 = ((o1, o2) -> o1.compareTo(o2));
    }


}