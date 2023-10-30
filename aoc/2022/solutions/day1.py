# Advent of Code 2022 Python
def sol(part):
    res=0
    with open("../inputs/day1_input.txt") as file:
        elves = [line for line in file.read().strip().split('\n\n')]
        total=[]
        for elf in elves:
            total.append(sum(map(int, elf.split('\n'))))
        if part == 1:
            res = max(total)
        else:
            res = sum(sorted(total)[-3:])
        return res

if __name__ == "__main__":
    print(sol(1))
    print(sol(2))