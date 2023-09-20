'''
You are given a sorted unique integer array nums.
A range [a,b] is the set of all integers from a to b (inclusive).
Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
Each range [a,b] in the list should be output as:
"a->b" if a != b
"a" if a == b

Input: nums = [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: The ranges are:
[4,5] --> "4->5"
[7,7] --> "7"

'''
class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:
        res = []
        
        for num in nums:
            if not res or num != res[-1][-1] + 1:
                if res and len(res[-1]) >= 2:
                    res[-1] = [res[-1][0]] + [res[-1][-1]]
                    
                res.append([num])
                
            else:
                res[-1].append(num)
        
        if res and len(res[-1]) >= 2:
            res[-1] = [res[-1][0]] + [res[-1][-1]]
        
        return ['->'.join(map(str, x)) for x in res]