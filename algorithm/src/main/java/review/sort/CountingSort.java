package review.sort;

import review.utils.StrUtils;

/**
 * 计数排序
 * @author: xiaoxiaoxiang
 * @date: 2020/10/24 16:12
 */
public class CountingSort {

    /**
     * 计数排序:
     * 时间复杂度(平均): O(n+k)
     * 时间复杂度(最坏): O(n+k)
     * 时间复杂度(最好): O(n+k)
     * 空间复杂度: O(n+k)
     * 稳定性: 稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */
    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        int[] a = {30,40,60,30,14,28,77,10,20,50,89,103};
        countingSort.countingSort(a);
        System.out.println(StrUtils.arrayToString(a, ","));
    }

    /**
     *  计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中.
     *  作为一种线性时间复杂度的排序,计数排序要求输入的数据必须是有确定范围的整数
     *
     *  计数排序(Counting sort) 是一种稳定的排序算法.计数排序使用一个额外的数组C,
     *  其中第i个元素是待排序数组A中值等于i的元素的个数. 然后根据数组C来将A中的元素排到正确的位置.
     *  它只能对整数进行排序.
     *
     *  步骤:
     *  1. 找出待排序的数组中最大和最小的元素
     *  2. 统计数组中每个值为i的元素出现的次数,存入数组C的第i项
     *  3. 反向填充目标数组: 将每个元素i放在新数组的第C(i)项,每放一个元素就将C(i)减去1
     *
     *  这种方法适合数据范围不是很大的场景,如果数据范围比较大(几百上千万之类的),很占用较多的内存
     */

    public void countingSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        // 1. 找出最大值和最小值
        int max = arr[0];
        int min = arr[0];
        for (int i=1;i<arr.length;i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 额外数组长度
        int[] newArr = new int[max - min + 1];
        for (int i=0;i<arr.length;i++) {
            // 已当前值减最小值的结果作为数组下标
            int index = arr[i] - min;
            // 计算出现的次数
            newArr[index] += 1;
        }
        // 填充回目标数组
        int targetIndex = 0;
        for (int i=0;i<newArr.length;i++) {
            int val = newArr[i];
            while (val > 0) {
                arr[targetIndex] = min + i;
                targetIndex++;
                val--;
            }
        }
    }
}
