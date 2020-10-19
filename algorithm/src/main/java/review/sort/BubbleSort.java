package review.sort;

import review.utils.StrUtils;

/**
 * 冒泡排序
 * @author: xiaoxiaoxiang
 * @date: 2020/7/4 14:04
 */
public class BubbleSort {

    /**
     * 冒泡排序:
     * 时间复杂度(平均): O(n^2)
     * 时间复杂度(最坏): O(n^2)
     * 时间复杂度(最好): O(n)
     * 空间复杂度: O(1)
     * 稳定性: 稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] a = {20,40,30,10,60,50};
        bubbleSort.bubbleSort01(a);
        System.out.println(StrUtils.arrayToString(a, ","));
        int[] b = {20,40,30,10,60,50};
        bubbleSort.bubbleSort02(b);
        System.out.println(StrUtils.arrayToString(b, ","));
    }

    /**
     * 冒泡排序-基础版
     * @param arr
     */
    public void bubbleSort01(int[] arr) {
        // 用来记录循环次数
        int c = 0;
        for (int i=arr.length-1; i>0; i--) {
            for (int j=0; j<i; j++) {
                c++;
                if (arr[j+1] < arr[j]) {
                    arr[j+1] ^= arr[j];
                    arr[j] ^= arr[j+1];
                    arr[j+1] ^= arr[j];
                }
            }
        }
        System.out.println("01: c=" + c);
    }

    /**
     * 冒泡排序-升级版
     * 添加一个标记,如果一趟遍历中发生了交换,则标记为true,否则为false
     * 如果某一趟没有发生交换,说明排序已经完成！
     * @param arr
     */
    public void bubbleSort02(int[] arr) {
        // 用来记录循环次数
        int c = 0;
        for (int i=arr.length-1; i>0; i--) {
            boolean flag = false;
            for (int j=0; j<i; j++) {
                c++;
                if (arr[j+1] < arr[j]) {
                    arr[j+1] ^= arr[j];
                    arr[j] ^= arr[j+1];
                    arr[j+1] ^= arr[j];
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        System.out.println("02: c=" + c);
    }
}
