'''
Leetcode
Remove the Duplicates from an Array
Example Input => [1, 1, 2, 3, 3, 4, 5] Output => [1, 2, 3, 4, 5]

'''


class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        next_non_duplicate = 1
        i = 1
        while(i < len(nums)):
            if nums[next_non_duplicate - 1] != nums[i]:
                nums[next_non_duplicate] = nums[i]
                next_non_duplicate += 1
            i += 1
        return next_non_duplicate



class Fastest_Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        
        res = 1
        tmp = nums[0]
        
        for i in nums:
            if i != tmp:
                tmp = nums[res] = i
                res += 1
        
        return res

class Fasterer_Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        r=1
        t=nums[0]
        for i in nums:
            if i!=t:
                t=nums[r]=i
                r+=1
        return r
