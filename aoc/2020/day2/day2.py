'''

Advent of Code - 2020
Day Two
Python

'''

def part_one(lines):
    part_one_count=0
    for line in lines:
        line = line.split()
        entry, letter, passwords = line[0], line[1][0], line[2]
        numbers = entry.split(sep='-')
        num_start = int(numbers[0])
        num_end = int(numbers[1])
        if num_start <= passwords.count(letter) <= num_end:
            part_one_count+=1
    return part_one_count

def part_two(lines):
    part_two_count=0
    for line in lines:
        line = line.split()
        entry, letter, passwords = line[0], line[1][0], line[2]
        numbers = entry.split(sep='-')
        first_number = int(numbers[0])-1
        second_number = int(numbers[1])-1
        try_one = passwords[first_number] == letter
        try_two = passwords[second_number] == letter
        if try_one ^ try_two:
            part_two_count+=1

    return part_two_count

with open('input.txt') as f:
    input = f.read().splitlines()
print('Part One answer : ', part_one(input))
print('Part Two answer : ', part_two(input))
