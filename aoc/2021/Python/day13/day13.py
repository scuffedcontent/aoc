#!/usr/bin/env python3

"""Advent of Code 2021 Day 13 - Transparent Origami"""


with open('input.txt', 'r') as aoc_input:
    dots, folds = aoc_input.read().split('\n\n')

dot_dict = {}
for dot in dots.split('\n'):
    x, y = dot.strip().split(',')
    dot_dict[(int(x), int(y))] = '#'

part_one = False
for fold in folds.strip().split('\n'):
    axis, index = fold.strip('fold along ').split('=')
    index = int(index)

    folded = {}
    for dot in dot_dict:
        x, y = dot

        if axis == 'y':
            new_y = index + (index - y) if y > index else y
            folded[(x, new_y)] = '#'

        elif axis == 'x':
            new_x = index + (index - x) if x > index else x
            folded[(new_x, y)] = '#'

    if not part_one:
        # Answer One
        print("Number of dots visible after first fold:", len(folded))
        part_one = True

    dot_dict = folded

max_x = max(dot_dict.keys())[0]
max_y = max(dot_dict.keys(), key=lambda x: x[1])[1]

# Answer Two
print("Eight letter code to activate the thermal imaging system:", end='\n\n')
for y in range(max_y + 1):
    row = ''
    for x in range(max_x + 1):
        row += dot_dict.get((x, y), ' ')
    print(row)
print()
