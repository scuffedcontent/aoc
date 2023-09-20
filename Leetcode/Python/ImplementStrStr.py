haystack=['h','e','a','a','a','l','l','g']
needle =['l','l','g']


l=len(needle)

for i in range(len(haystack)):
    if haystack[i:i+l]==needle:
        print(haystack[i:i+l])
    
print('test')
