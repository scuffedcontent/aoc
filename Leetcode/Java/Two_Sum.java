import java.util.HashMap;
import java.util.Map;

//  Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//  Example => Input: nums = [3,2,4], target = 6 ==> Output: [1,2]
//  For the record if someone is reading this they should know this is not the most effecient

// Sub-optimal Brute Force approach

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j =i + 1; j < nums.length; j++) {
                int integer = target - nums[i];
                
                if (nums[j] == integer) {
                    return new int[] {i, j};
                }
            }
        }

        throw new IllegalArgumentException("no match found");
    }
}

// two-pass solution

class goodSolution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}

// one-pass optimal solution

class optimalSolution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}