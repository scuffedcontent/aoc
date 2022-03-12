'''

Advent of code
Day 7 part One
Python

'''

import sys

with open("input.txt") as file:
    data = [int(x) for x in file.readline().split(",")]

least = sys.maxsize
for stop in range(0, 10000):
    value = 0 
    for element in data:
        value += abs(element - stop)
    if value < least:
        least = value

print('Part one solution : ', least)
