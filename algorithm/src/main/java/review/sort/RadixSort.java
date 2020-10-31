package review.sort;

import review.utils.StrUtils;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 基数排序
 * @author: xiaoxiaoxiang
 * @date: 2020/10/27 16:18
 */
public class RadixSort {

    /**
     * 基数排序:
     * 时间复杂度(平均): O(n*k)
     * 时间复杂度(最坏): O(n*k)
     * 时间复杂度(最好): O(n*k)
     * 空间复杂度: O(n+k)
     * 稳定性: 稳定
     *
     * - 稳定: 如果a原本在b前面,而a=b,排序之后a仍然在b的前面
     * - 不稳定: 如果a原本在b的前面,而a=b,排序之后 a 可能会出现在 b 的后面
     */
    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();
        int[] a = {50,55,89,103,77,99,86,43,64,15,22,30,40,60,30,14,28,77,10,20,3,4,99,8,16,23,101,46,44,51,65,75,81,96};
        radixSort.radixSort(a);
        System.out.println(StrUtils.arrayToString(a, ","));
    }


    /**
     * 基数排序是按照低位先排序,然后收集;再按照高位排序,然后再收集;依次类推,直到最高位.
     * 有时候有些属性是有优先级顺序的,先按低优先级排序,再按高优先级排序.
     * 最后的次序就是高优先级高的在前,高优先级相同的低优先级高的在前.
     *
     * 步骤:
     * 1. 取得数组中的最大数,并取得位数
     * 2. arr为原始数组,从最低位开始取每个位组成radix数组
     * 3. 对radix进行计数排序(利用计数排序适用于小范围数的特点);
     *
     * @param arr
     */
    public void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int maxNum = getMaxNum(arr);
        int maxDigit = getMaxDigit(maxNum);

        int t = 10;
        ArrayList<ArrayList<Integer>> bucketList = getArrayList();
        for (int i=0;i<=maxDigit;i++) {
            int radix = 1;
            if (i > 0) {
                radix = t;
                t = t * 10;
            }
            for (int n=0;n<arr.length;n++) {
                // 求第i位的数值
                int d = arr[n] / radix % 10;
                bucketList.get(d).add(arr[n]);
            }
            putResult(bucketList, arr);
        }
    }

    private int getMaxNum(int[] arr) {
        int max = arr[0];
        for (int i=1;i<arr.length;i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    private int getMaxDigit(int maxNum) {
        int maxDigit = 0;
        while (maxNum / 10 != 0) {
            maxDigit++;
            maxNum = maxNum / 10;
        }
        return maxDigit;
    }

    private ArrayList<ArrayList<Integer>> getArrayList() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            list.add(new ArrayList<>());
        }
        return list;
    }

    private void putResult(ArrayList<ArrayList<Integer>> list, int[] arr) {
        int i = 0;
        for (ArrayList<Integer> arrayList : list) {
            for (Integer integer : arrayList) {
                arr[i] = integer;
                i++;
            }
            arrayList.clear();
        }
    }
}
