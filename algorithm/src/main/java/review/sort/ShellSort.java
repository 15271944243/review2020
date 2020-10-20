package review.sort;

import review.utils.StrUtils;

/**
 * 希尔排序
 * @author: xiaoxiaoxiang
 * @date: 2020/10/20 17:45
 */
public class ShellSort {

    /**
     * 希尔排序:
     * 时间复杂度(平均): O(n^1.3)
     * 时间复杂度(最坏): O(n^2)
     * 时间复杂度(最好): O(n)
     * 空间复杂度: O(1)
     * 稳定性: 稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] a = {30,40,60,30,14,28,77,10,20,50,89,103};
        shellSort.shellSort(a);
        System.out.println(StrUtils.arrayToString(a, ","));
    }



    /**
     * 第一个突破O(n^2)的排序算法,是插入排序的改进版.
     * 它与插入排序的不同之处在于,它会优先比较距离较远的元素.
     * 希尔排序又叫缩小增量排序.
     */

    public void shellSort(int[] arr) {

    }

}
