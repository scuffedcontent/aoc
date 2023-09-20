'''

    Leetcode Question #217
    Contains Duplicate

'''
nums=[2,3,4,5,1,1]
# Using Hashset (On)

class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        s = set()
        for num in nums:
            if num in s:
                return True
            s.add(num)
        return False


# Using sort (2n)
class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        # time complexity for '.sort()' =>  O(n log n)
        nums.sort()
        for i in range(1,len(nums)):
            if nums[i]==nums[i-1]:
                print('true')
