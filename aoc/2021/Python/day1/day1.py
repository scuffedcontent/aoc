# Advent of Code 2021 Python


def part_one(lines):
    count=0
    for i in range(1, len(lines)):
        if lines[i] > lines[i-1]:
            count+=1
    return count

with open("input.txt") as file:
    inputs = [line for line in file.read()]
    print(inputs)
print('Part One answer : ', part_one(inputs))

sums = []

for i in range(2, len(inputs)):
    sums.append(inputs[i]+inputs[i-1]+inputs[i-2])

print("Part Two answer : ", part_one(sums))
