package review;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 微信红包算法
 * @author: xiaoxiaoxiang
 * @date: 2021/5/24 08:52
 */
public class WechatRedPackage {


    public static void main(String[] args) {
        WechatRedPackage demo = new WechatRedPackage();
        int num = 10;
        BigDecimal money = new BigDecimal(100);
        BigDecimal[] result = new BigDecimal[10];
        for (int i = num; i > 0 ; i--) {
            result[10 - i] = demo.distributeRedPackage(i, money);
            money =  money.subtract(result[10 - i]);
        }
        System.out.println(1111);
    }

    /**
     * 采用二倍均值法,即剩余红包金额为M,剩余人数为N,那么有如下公式:
     * 每次抢到的金额 = (0.01 ~ M / N X 2)
     *
     * 假设有10个人,红包总额100元
     *
     * 1. 100 / 10 * 2 = 20,所以第一个人的随机范围是（0,20),假设第一个人随机到10元,那么剩余金额是100-10=90 元
     * 2. 90 / 9 * 2 = 20,所以第二个人的随机范围是（0，20 ),假设第二个人随机到10元,那么剩余金额是90-10=80 元
     * 以此类推
     *
     * @param num      剩余人数
     * @param money    剩余红包金额
     */
    public BigDecimal distributeRedPackage(int num, BigDecimal money) {
        if (num == 1) {
            return money;
        }
        BigDecimal minMoney = new BigDecimal("0.01") ;
        BigDecimal maxMoney = money.divide(new BigDecimal(num), 2, RoundingMode.FLOOR);
        maxMoney = maxMoney.multiply(new BigDecimal(2));
        BigDecimal result = maxMoney.multiply(BigDecimal.valueOf(Math.random())).setScale(2, RoundingMode.FLOOR);
        // 最小值
        if (result.compareTo(minMoney) < 0) {
            return minMoney;
        }
        return result;
    }

    /*public double distributeRedPackage(int num, double money) {
        if (num == 1) {
            // 这里还是会有精度问题,最好使用 BigDecimal
            return Math.ceil(money * 100) / 100;
        }
        double minMoney = 0.01;
        double maxMoney = money / num * 2;
        double m = Math.random() * maxMoney;
        // 最小值
        if (m < minMoney) {
            return minMoney;
        }
        // 两位小数
        m = Math.floor(m * 100) / 100;
        return m;
    }*/
}
