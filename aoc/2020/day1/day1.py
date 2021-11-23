# Find the two numbers that add to 2020 
# Then multiply their intergers

# btw for all those whom fall upon this repo
# this truly is the lazy mans way of solving
# this puzzle because of the amount of times you
# need to iterate through this list of numbers


# Part One

with open("input.txt") as f:
    data = [int(line) for line in f]

for j in data:
    for k in data:
        if j + k == 2020:
            print('Part One answer is: ', j * k)

# Part Two

for j in data:
    for k in data:
        for l in data:
            if j + k + l == 2020:
                print('Part Two answer is: ', j * k * l)
