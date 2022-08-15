
# Leetcode Remove all elements of the array matching the given value
# In place (no new array)
# and return length of the array
# nums = [3,2,2,3]
# n = 3
# example output: 2

class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        while(i<len(nums)):
            if(nums[i]==val):
                nums.remove(nums[i])
            else:
                i+=1
        return len(nums)

class FastestSolution:
    def removeElement(self, nums: List[int], val: int) -> int:
        #ind = nums.index(val)
        while val in nums:
            ind = nums.index(val)
            nums.pop(ind)
        
        
        
        return len(nums)