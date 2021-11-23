'''
Advent of Code
2020
Python
Day Two
'''

def part_one(lines):
    for line in lines:
        line = line.split()
        numbers, letter, passwords = line[0], line[1][0], line[2]
        print(numbers)
        print(letter)
        print(passwords)

with open('input.txt') as f:
    input = f.read().splitlines()
print(part_one(input))
