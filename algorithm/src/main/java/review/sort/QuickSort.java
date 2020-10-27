package review.sort;

import review.utils.StrUtils;

/**
 * @description: 快速排序
 * @author: xiaoxiaoxiang.
 * @createDate: 2018/5/22
 */
public class QuickSort {

    /**
     * 快速排序:
     * 时间复杂度(平均): O(nlog2n)
     * 时间复杂度(最坏): O(n^2)
     * 时间复杂度(最好): O(nlog2n)
     * 空间复杂度: O(nlog2n)
     * 稳定性: 不稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] a = {30,40,60,30,14,28,77,10,20,50};
        quickSort.quickSort(a,0,a.length-1);
        System.out.println(StrUtils.arrayToString(a, ","));
    }

    /**
     * 它的基本思想是: 选择一个基准数,通过一趟排序将要排序的数据分割成独立的两部分
     * 其中一部分的所有数据都比另外一部分的所有数据都要小
     * 然后,再按此方法对这两部分数据分别进行快速排序,
     * 整个排序过程可以递归进行,以此达到整个数据变成有序序列.
     * 步骤:
     * 1. 从数列中挑出一个基准值
     * 2. 将所有比基准值小的摆放在基准前面,所有比基准值大的摆在基准的后面(相同的数可以到任一边)；
     * 在这个分区退出之后，该基准就处于数列的中间位置
     * 3.递归地把"基准值前面的子数列"和"基准值后面的子数列"进行排序
     * @param arr  待排序的数组
     * @param left  数组的左边界(例如，从起始位置开始排序，则l=0)
     * @param right  数组的右边界(例如，排序截至到数组末尾，则r=a.length-1)
     */
    public void quickSort(int[] arr,int left,int right){
        if(left >= right){
            return;
        }
        int index = partition(arr, left, right);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }

    private int partition(int[] arr,int left,int right) {
        // 基准数
        int pivot = arr[left];
        // 比基准数大的数的index
        int biggerIndex = left + 1;
        for (int i = biggerIndex; i <= right; i++) {
            // 将比基准数小的数放在左侧,比基准数大的数放在右侧,可以通过交换位置实现
            // 思路: 找到比基准数大的数,把index记录下来,后面找到比基准数小的数,与之交换位置
            if (arr[i] < pivot) {
                StrUtils.swap(arr, biggerIndex, i);
                biggerIndex++;
            }
        }
        // 将基准数与最后一个比基准数小的数交换位置,达到分区的效果
        StrUtils.swap(arr, left, biggerIndex - 1);
        return biggerIndex - 1;
    }
}
