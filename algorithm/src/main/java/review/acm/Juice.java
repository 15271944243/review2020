package review.acm;

import java.util.Scanner;

/**
 * ACM 模式
 * @author: xiaoxiaoxiang
 * @date: 2021/5/15 14:33
 */
public class Juice {

    /**
     * 有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？
     * ”答案是5瓶，方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。
     * 然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？
     *
     * 输入描述:
     * 输入文件最多包含10组测试数据，每个数据占一行，仅包含一个正整数n（1<=n<=100），表示小张手上的空汽水瓶数。n=0表示输入结束，
     * 你的程序不应当处理这一行。
     *
     * 输出描述:
     * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
     *
     * 输入例子1:
     * 3
     * 10
     * 81
     * 0
     *
     * 输出例子1:
     * 1
     * 5
     * 40
     * @param args
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int r = juice(n, 0);
            System.out.println(r);
        }
    }

    private static int juice(int n, int m) {
        if (n < 3) {
            // 退出递归的条件
            if (n == 2) {
                // 可以找老板借一瓶,喝完凑3个空瓶,后换一瓶并归还
                return m + 1;
            } else {
                return m;
            }
        }
        // 当前空瓶能换取的汽水数量
        int t1 = n / 3;
        // 当前换取后剩余空瓶数量
        int t2 = n % 3 + t1;
        // 已喝的汽水数量
        m = m + t1;
        // 递归
        m = juice(t2, m);
        return m;
    }
}
