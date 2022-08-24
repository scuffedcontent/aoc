/* 
 * Leetcode Running Sum of an Array
 *
 * Given an Array of integers
 * return the sum of them all.
 * Ex. nums=[1,2,3,4] answer = 10
 *
 */


class Solution {
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        return nums;
    }
}
