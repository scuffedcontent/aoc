'''

nums n = [1,2,3,5]
target t = 3
target2 t2 = 4

'''


n=[1,2,3,5]
t=6
t2=4

# testcase solution
class EasySolution:
    def searchInsert(self, n: List[int], t: int) -> int:
        for i in range(len(n)):
            if n[i] == t:
                print("solution idx: ", i)
                return i
            elif n[i] > t:
                print("solution idx should be :", i)
                return i
        print(len(n))
        return len(n)


class FastestSolution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        start = 0
        end = len(nums)-1
        
        while end-start > 0:
            midpoint = (end+start)//2
            if nums[midpoint] == target:
                return midpoint
            elif nums[midpoint] < target:
                start = midpoint+1
            else:
                end = midpoint-1
                
        if target <= nums[start]:
            return start
        else:
            return start+1




class FasterSolution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums) - 1
        while left <= right:
            pivot = (left + right) // 2
            if nums[pivot] == target:
                return pivot
            if target < nums[pivot]:
                right = pivot - 1
            else:
                left = pivot + 1
        return left

class FastSolution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        for idx in range(len(nums)):
            if nums[idx] >= target:
                return idx
            
        return len(nums)
