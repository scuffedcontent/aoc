# Advent of Code 2021 Python


def part_one(lines):
    count=0
    for i in range(1, len(lines)):
        if lines[i] > lines[i-1]:
            count+=1
    return count

with open("input.txt") as file:
    inputs = [int(line) for line in file]

print('Part One answer : ', part_one(inputs))


