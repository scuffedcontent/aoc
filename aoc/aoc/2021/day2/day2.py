'''

Advent of Code 2021
Day 2
Python

'''
def part_one(lines):
    x,y=0,0
    for line in lines:
        line=line.split(" ")
        amount,direction=int(line[1]),(line[0])
        if direction == "forward":
            x+=amount
        elif direction == "up":
            y-=amount
        elif direction == "down":
            y+=amount
    return x * y

def part_two(lines):
    x,y,a=0,0,0
    for line in lines:
        line=line.split(" ")
        amount=int(line[1])
        direction=(line[0])
        if direction == "up":
            a-=amount
        if direction == "down":
            a+=amount
        if direction == "forward":
            x+=amount
            y+=(a*amount)
    print(x,y,a)
    return x*y

with open("input.txt") as file:
    data = file.read().splitlines()
print(part_one(data))
print(part_two(data))

