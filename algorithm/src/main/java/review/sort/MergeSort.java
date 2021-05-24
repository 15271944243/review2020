package review.sort;

import review.utils.StrUtils;

import java.util.Arrays;

/**
 * 归并排序
 * @author: xiaoxiaoxiang
 * @date: 2020/10/20 17:52
 */
public class MergeSort {

    /**
     * 归并排序:
     * 时间复杂度(平均): O(n*log2n)
     * 时间复杂度(最坏): O(n)
     * 时间复杂度(最好): O(n*log2n)
     * 空间复杂度: O(n)
     * 稳定性: 稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] a = {30,40,60,30,14,28,77,10,20,50,89,103};
        a = mergeSort.mergeSort(a);
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
    public int[] mergeSort(int[] arr){
        if (arr.length < 2) {
            return arr;
        }
        int middle = arr.length / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, middle);
        int[] rightArr = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(mergeSort(leftArr), mergeSort(rightArr));
    }

    private int[] merge(int[] leftArr, int[] rightArr) {
        int[] reuslt = new int[leftArr.length + rightArr.length];
        int m = 0, n = 0;
        for(int i=0; i< reuslt.length;i++) {
            if (m >= leftArr.length) {
                // 左边的都放完了,就放右边的
                reuslt[i] = rightArr[n++];
            } else if (n >= rightArr.length) {
                // 右边的都放完了,就放左边的
                reuslt[i] = leftArr[m++];
            } else {
                // 两边都没放完,比较大小,谁小就放谁(按从小到大的顺序)
                if (leftArr[m] <= rightArr[n]) {
                    reuslt[i] = leftArr[m++];
                } else {
                    reuslt[i] = rightArr[n++];
                }
            }
        }
        return reuslt;
    }
}
