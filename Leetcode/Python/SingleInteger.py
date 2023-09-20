'''

Given a List of Integers where all hava a duplicate except one.
Determine which Integer has not been duplicated
Ex. nums=[1,3,5,2,3,5,1] answer=2

'''


nums=[1,3,5,2,3,5,1]

# The easiest solution is using bit XOR
# XOR compares integers and if = become 0
# so only remaining integer is one without a duplicate

a = 0

for i in nums:
    a ^= i
print(a)
