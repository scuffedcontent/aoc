"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""

 Leetcode #1480
 Running sum of an Array.
 Example => Input: nums = [1,2,3,4] => Output: [1,3,6,10]

"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""

nums = [1,2,3,4]

currentSum = 0
tempArray=[]

for i in nums:
    currentSum+=i
    tempArray.append(currentSum)
    nums=tempArray
    print(nums)

"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""

class FastSolution:
    def runningSum(self, nums: List[int]) -> List[int]:
        sum = 0
        for i in range(0,len(nums)):
            sum+=nums[i]
            nums[i] = sum
        return nums

class FastestSolution:
    def runningSum(self, nums: List[int]) -> List[int]:
        p = 0
        for i, v in enumerate(nums):
            p += v
            nums[i] = p
        return nums

"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""