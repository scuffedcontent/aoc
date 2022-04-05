data = []
with open('input.txt') as file:
    line = file.readline()
    while line:
        data.extend(line.strip('\n').split('|')[1].split(' ')[1:])
        line = file.readline()

total = 0
for element in data:
    if len(element) in [2,3,4,7]:
        total +=1

print("Part One solution : ", total)
