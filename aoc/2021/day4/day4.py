import sys
infile=sys.argv[1] if len(sys.argv)>1 else 'input.txt'
ans=0
numbers = None
B=[]
F=[]
board=[]
for line in open(infile):
    line=line.strip()
    if numbers is None:
        numbers = [int(x) for x in line.split(',')]
    else:
        if line:
            board.append([int(x) for x in line.split()])
        else:
            if board:
                B.append(board)
            board=[]
B.append(board)
for b in B:
    F.append([[False for _ in range(5)] for _ in range(5)])
WON= [False for _ in range(len(B))]
for num in numbers:
    for i,b in enumerate(B):
        for r in range(5):
            for c in range(5):
                if b[r][c] == num:
                    F[i][r][c] = True

        won=False
        for r in range(5):
            ok=True
            for c in range(5):
                if not F[i][r][c]:
                    ok = False
            if ok:
                won=True
        for c in range(5):
            ok=True
            for r in range(5):
                if not F[i][r][c]:
                    ok=False
            if ok:
                won=True
        if won:
            WON[i]=True
            if all([WON[j] for j in range(len(B))]):
                unmarked=0
                for r in range(5):
                    for c in range(5):
                        if not F[i][r][c]:
                            unmarked+=b[r][c]
                print(unmarked * num)
                sys.exit()
