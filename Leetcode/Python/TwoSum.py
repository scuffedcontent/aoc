'''

Example interview 
Two Sum
Input nums = [1,2,3,9]
totalSum = 8

'''
nums={1,2,3,9}
target=3
seen={}
for i, num in enumerate(nums):
    if target-num in seen:
        print([seen[target-num], i])
    elif num not in seen:
        seen[num] = i