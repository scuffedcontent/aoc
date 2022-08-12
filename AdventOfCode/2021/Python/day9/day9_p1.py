'''

Advent of Code
Day 10 Part one
Python

'''


def get_neighbors(grid, x, y):
    ret= []
    if x>0:
        ret.append(grid[x-1][y])
    if y>0:
        ret.append(grid[x][y-1])
    if x<len(grid)-1:
        ret.append(grid[x+1][y])
    if y < len(grid[0])-1:
        ret.append(grid[x][y+1])
    return ret
def part_one(grid):
    total=0
    for x in range(len(grid)):
        for y in range(len(grid[0])):
            neighbors=get_neighbors(grid,x,y)
            current = grid[x][y]
            if current<min(neighbors):
                total += current +1
    return total

if __name__ == "__main__":
    with open("input.txt") as f:
        grid=[]
        for line in f:
            grid.append([int(v) for v in line.strip()])
    print(grid)
    print(part_one(grid))
