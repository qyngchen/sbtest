package com.qyngchen.leecode.one;


/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/two-sum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author chenqingyang
 */
public class Solution {

  public static int[] twoSum(int[] nums, int target) {

    int[] result = new int[2];
    int num = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int i1 = 0; i1 < nums.length; i1++) {
        if (i == i1) {
          continue;
        }
        if (nums[i] + nums[i1] == target) {
          result[0] = i;
          result[1] = i1;
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{1, 3, 3, 3, 4, 5};
    int[] ints = twoSum(nums, 9);
    for (int anInt : ints) {
      System.out.println(anInt);
    }
  }
}
