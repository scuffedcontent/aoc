

def part_one(lines):
    G,E='',''
    for idx in range(0, len(lines[0])):
        O,Z=0,0
        for line in range(0, len(lines)):
            if lines[line][idx] == "0":
                Z+=1
            else:
                O+=1
        if Z>O:
            G+='0'
            E+='1'
        else:
            G+='1'
            E+='0'
    gamma = int(G,2)
    epsilon = int(E, 2)
    return gamma*epsilon

def part_two_oxygen(lines):
    idx=0
    while len(lines)>1:
        O,Z=0,0
        Ones=[]
        Zeroes=[]
        for line in range(0, len(lines)):
            if lines[line][idx] == "0":
                Z+=1
                Zeroes.append(lines[line])
            else:
                O+=1
                Ones.append(lines[line])
        if Z>O:
            lines=Zeroes
        else:
            lines=Ones
        idx+=1
    oxygen= int(lines[0],2)
    return oxygen
    print(oxygen)
def part_two_CO2(lines):
    idx=0
    while len(lines)>1:
        O,Z=0,0
        Ones=[]
        Zeroes=[]
        for line in range(0, len(lines)):
            if lines[line][idx] == "0":
                Z+=1
                Zeroes.append(lines[line])
            else:
                O+=1
                Ones.append(lines[line])
        if O<Z:
            lines=Ones
        else:
            lines=Zeroes
        idx+=1
    CarbonDioxide = int(lines[0],2)
    return CarbonDioxide
    print(CarbonDioxide)

    
    return part_one(lines)
with open("input.txt") as file:
    input = [x for x in file.read().split()]

print("Part One answer : ", part_one(input))
print("Part Two answer : ", part_two_oxygen(input))
print("Part Two answer : ", part_two_CO2(input))