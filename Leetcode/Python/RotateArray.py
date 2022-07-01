class Fast_Solution:
    def rotate(self, nums: List[int], k: int) -> None:

    #nums = [1, 2, 3, 4, 5]
        k = k%len(nums) # % gets correct index if out of range

        tmp = nums[-k:] # cuts the amount to be shifted off the back
        del nums[-k:] # makes room for original array to be shifted
        nums[:0] = tmp # saves cut part to front of array
        print(nums)

class Faster_Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        k = k % len(nums)
        nums[:] = nums[-k:]+nums[:-k]
