'''

Given a number 'numRows' create a Pascals Triangle with the numRows given.
A Pascals Triangles rows are determined by add the two numbers above the index of a given row.

ex.
    1
    11
    121
    1331
    14641


'''
class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        result=[[1]]

        for i in range(numRows-1):
            tempRow = [0] + result[-1] +[0]
            row = []
            for j in range(len(result[-1])+1):
                row.append(tempRow[j]+tempRow[j+1])
                result.append(row)
        return result
        #print(result)

#'''


