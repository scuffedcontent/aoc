'''

Advent of Code
Day 7 part Two
Python

'''

import sys
#from functools import cache

with open('input.txt') as file:
    data = [int(x) for x in file.readline().split(',')]

def sumUp(x):
    return x * (x - 1) // 2
least = sys.maxsize
for stop in range(0, 10000):
    value = 0
    for element in data:
        value += sumUp(abs(element - stop)+1)
    if value < least:
        least = value
print("part two solution : ", least)
