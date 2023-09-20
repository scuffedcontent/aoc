'''
Advent of Code
Day 1
Python

'''

def part_one(lines):
    for j in lines:
        for k in lines:
            if j + k == 2020:
                return  j * k

def part_two(lines):
    for j in lines:
        for k in lines:
            for l in lines:
                if j + k + l == 2020:
                    return  j * k * l

with open('input.txt') as file:
    inputs = [int(line) for line in file ]

print('Part One answer : ', part_one(inputs))
print('Part Two answer : ', part_two(inputs))
