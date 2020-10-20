package review.sort;

import review.utils.StrUtils;

/**
 * 归并排序
 * @author: xiaoxiaoxiang
 * @date: 2020/10/20 17:52
 */
public class MergeSort {

    /**
     * 插入排序:
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
        MergeSort mergeSort = new MergeSort();
        int[] a = {30,40,60,30,14,28,77,10,20,50,89,103};
        mergeSort.mergeSort(a);
        System.out.println(StrUtils.arrayToString(a, ","));
    }

    /**
     * 基本思想是:
     * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用.
     * 将已有序的子序列合并,得到完全有序的序列;即先使每个子序列有序,再使子序列段间有序.
     * 若将两个有序表合并成一个有序表,称为2-路归并.
     *
     * 1. 把长度为n的输入序列分成两个长度为n/2的子序列
     * 2. 对这两个子序列分别采用归并排序
     * 3. 将两个排序好的子序列合并成一个最终的排序序列
     *
     * @param arr  待排序的数组
     */
    public void mergeSort(int[] arr){

    }
}
