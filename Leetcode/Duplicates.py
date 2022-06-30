'''
Given an array 
traverse the array
return True if there is a duplicate integer 
return False if there is not

'''

nums = [1, 2, 3, 1]
seen = set()
for i in nums:
    if i in seen:
        print("True")
    seen.add(i)
print("False")
