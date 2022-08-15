'''
Given an array return True if duplicate return False if not

'''

nums = [1, 2, 3, 1]
seen = set()
for i in nums:
    if i in seen:
        print("True")
    seen.add(i)
print("False")
