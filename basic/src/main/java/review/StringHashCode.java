package review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 如何构建 HashCode 一样的 String
 * 参考: https://mp.weixin.qq.com/s/1V304OsOPPuBdI6GAzBUYQ
 * @author: xiaoxiaoxiang
 * @date: 2020/11/2 10:12
 */
public class StringHashCode {

    /**
     * 对于任意两个字符串 xy 和 ab，如果它们满足 x-a=1，即第一个字符的 ASCII 码值相差为 1，
     * 同时满足 b-y=31，即第二个字符的 ASCII 码值相差为 -31。那么这两个字符的 hashCode 一定相等
     */
    public static void main(String[] args) {
        // 参考ASCII表
        System.out.println("Aa= " + "Aa".hashCode());
        System.out.println("BB= " + "BB".hashCode());

        System.out.println("Ab= " + "Ab".hashCode());
        System.out.println("BC= " + "BC".hashCode());

        System.out.println("Ac= " + "Ac".hashCode());
        System.out.println("BD= " + "BD".hashCode());

        // 如果我们以 Aa，BB 为种子数据，经过多次排列组合，可以得到任意个数的 hashCode 一样的字符串。
        // 字符串的长度随着个数增加而增加。
        System.out.println("AaBB= " + "AaBB".hashCode());
        System.out.println("BBAa= " + "BBAa".hashCode());

        List<String> list = hashCodeSomeList(1);
        List<String> list2 = hashCodeSomeList(2);
        List<String> list3 = hashCodeSomeList(3);
        List<String> list4 = hashCodeSomeList(4);
        HashMap<String, Integer> map = new HashMap<>();
        list.stream().forEach(e -> map.put(e, 1));
        list2.stream().forEach(e -> map.put(e, 1));
        list3.stream().forEach(e -> map.put(e, 1));
        list4.stream().forEach(e -> map.put(e, 1));
        System.out.println(1);
    }


    /**
     * 种子数据：两个长度为 2 的 hashCode 一样的字符串
     */
    private static String[] SEED = new String[]{"Aa", "BB"};

    /**
     * 生成 2 的 n 次方个 HashCode 一样的字符串的集合
     */
    public static List<String> hashCodeSomeList(int n) {
        List<String> initList = new ArrayList<String>(Arrays.asList(SEED));
        for (int i = 1; i < n; i++) {
            initList = createByList(initList);
        }
        return initList;
    }

    public static List<String> createByList(List<String> list) {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < SEED.length; ++i) {
            for (String str : list) {
                result.add(SEED[i] + str);
            }
        }
        return result;
    }
}
