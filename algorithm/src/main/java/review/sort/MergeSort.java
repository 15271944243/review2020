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
        mergeSort.mergeSort(a, 0, a.length-1);
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
    public void mergeSort(int[] arr, int start, int end){
        if (start < end) {
            int middle = start + ((end - start) >> 1);
            mergeSort(arr, start, middle);
            mergeSort(arr, middle + 1, end);
            //合并操作
            merge(arr, start, middle, end);
        }
    }

    public void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                temp[k] = nums[i];
                i++;
                k++;
            } else {
                temp[k] = nums[j];
                j++;
                k++;
            }
        }
        while (i <= mid) {
            temp[k] = nums[i];
            i++;
            k++;
        }
        while (j <= end) {
            temp[k] = nums[j];
            j++;
            k++;
        }
        for (int t = 0; t < temp.length; t++) {
            nums[t + start] = temp[t];
        }
    }
}
