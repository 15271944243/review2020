package review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/ No.15 三数之和
 * @author: xiaoxiaoxiang
 * @date: 2022/6/8 8:20
 */
public class ThreeSum {

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * 输入：nums = []
     * 输出：[]
     *
     * 输入：nums = [0]
     * 输出：[]
     *
     * 提示：
     *
     * 0 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     */



    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        ThreeSum func = new ThreeSum();
        List<List<Integer>> result = func.threeSum2(nums);
        System.out.println(111);
    }

    /**
     * 思路一: 直接遍历
     * 思路二: 先排序，再遍历
     *
     * 最左（最小）数字的索引 k，另外两个数索引 i，j (i，j > k)
     * - 当 nums[k] > 0 时直接break跳出：因为 nums[j] >= nums[i] >= nums[k] > 0，即 33 个数字都大于 0，在此固定指针 k 之后不可能再找到结果了
     * - 当 k > 0 && nums[k] == nums[k - 1]时即跳过此元素nums[k]：因为已经将 nums[k - 1] 的所有组合加入到结果中，本次搜索只会得到重复组合。
     * - 当 i > k + 1 && nums[i] == nums[i - 1] 时即跳过此元素nums[i]：因为已经将 nums[i - 1] 的所有组合加入到结果中，本次搜索只会得到重复组合。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        // 排序
        quickSort(nums, 0, nums.length - 1);
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            for (int i = k + 1; i < nums.length - 1; i++) {
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int n = - (nums[k] + nums[i]);
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == n) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[k]);
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        result.add(tmp);
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 思路三: 先排序,再双指针
     *
     * 固定 3 个指针中最左（最小）数字的指针 k，双指针 i，j 分设在数组索引 (k, len(nums))(k,len(nums)) 两端，
     * 通过双指针交替向中间移动，记录对于每个固定指针 k 的所有满足 nums[k] + nums[i] + nums[j] == 0 的 i,j 组合
     * 1、 当 nums[k] > 0 时直接break跳出：因为 nums[j] >= nums[i] >= nums[k] > 0，即 33 个数字都大于 0，在此固定指针 k 之后不可能再找到结果了
     * 2、 当 k > 0且nums[k] == nums[k - 1]时即跳过此元素nums[k]：因为已经将 nums[k - 1] 的所有组合加入到结果中，本次双指针搜索只会得到重复组合。
     * 3、 i，j 分设在数组索引 (k, len(nums)) 两端，当i < j时循环计算s = nums[k] + nums[i] + nums[j]，并按照以下规则执行双指针移动：
     *  - 当s < 0时，i += 1并跳过所有重复的nums[i]；
     *  - 当s > 0时，j -= 1并跳过所有重复的nums[j]；
     *  - 当s == 0时，记录组合[k, i, j]至res，执行i += 1和j -= 1并跳过所有重复的nums[i]和nums[j]，防止记录到重复组合
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        // 排序
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int s = nums[k] + nums[i] + nums[j];
                if (s > 0) {
                    while(i < j && nums[j] == nums[--j]); //右指针后退并去重
                } else if (s < 0) {
                    while(i < j && nums[i] == nums[++i]); //左指针前进并去重
                } else {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[k]);
                    tmp.add(nums[i]);
                    tmp.add(nums[j]);
                    result.add(tmp);
                    while(i < j && nums[j] == nums[--j]);
                    while(i < j && nums[i] == nums[++i]);
                }
            }
        }
        return result;
    }


    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = partition(arr, left, right);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }

    private int partition(int[] arr, int left, int right) {
        // 基准数
        int pivot = arr[left];
        // 比基准数大的数的index
        int biggerIndex = left + 1;
        for (int i = biggerIndex; i <= right; i++) {
            if (arr[i] < pivot) {
                // 将比基准数小的数放在左侧,比基准数大的数放在右侧,可以通过交换位置实现
                // 思路: 找到比基准数大的数,把index记录下来,后面找到比基准数小的数,与之交换位置
                swap(arr, biggerIndex, i);
                biggerIndex++;
            }
        }
        // 将基准数与最后一个比基准数小的数交换位置,达到分区的效果
        swap(arr, biggerIndex - 1, left);
        return biggerIndex - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
