
'''
Leetcode
Validate Parenthesis

'''
class Solution:
    def validateParenthesis():
        s = str
        onStack = []
        isClosed = {')':'(', ']':'[','}':'{'}
        for c in s:
            if c in isClosed:
                if onStack and onStack[-1] == isClosed[c]:
                    onStack.pop()
            else:
                return False
        else:
            onStack.append(c)
        return True if not onStack else False