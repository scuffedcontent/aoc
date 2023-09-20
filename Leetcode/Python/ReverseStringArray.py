


class DumbSolution:
    def reverseString(self, s:List[str]) -> None:
        s.reverse()

class SimpleSolution:
    def reverseString(self, s: List[str]) -> None:
        s[:] = s[::-1]
        

class SplitSolution:
    def reverseString(self, s: List[str]) -> None:
        l,r=0,len(s)-1
        while l<r:
            s[l],s[r]=s[r],s[l]
            l,r=l+1,r-1

class Solution:
    def reverseString(self, s: List[str]) -> None:

        tmp=''
        i=0
        while (i < len(s)/2):
            tmp = s[i]
            s[i] = s[len(s)-i-1]
            s[len(s)-i-1] = tmp
            i+=1
