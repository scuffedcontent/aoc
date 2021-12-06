with open("input.txt") as file:
    data = [int(x) for x in file.readline().split(',')]

for days in range(256):
    index = 0
    endIndexForDay = len(data)
    while index < endIndexForDay:
        if data[index] == 0:
            data[index] = 6
            data.append(8)
        else:
            data[index] -= 1
        index += 1
print("Part One Solution : ", len(data))
