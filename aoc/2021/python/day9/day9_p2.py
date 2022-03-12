data=[]
with open("input.txt") as file:
    line = file.readline()
    while line:
        full = [x for x in line.strip('\n')]
        stretched = [int(x) for x in full]
        data.append(stretched)
        line=file.readline()

def GetValue(data, point):
    if (point[0]<0) or (point[0]>=len(data)):
        return 10
    if (point[1]<0) or (point[1]>=len(data[0])):
        return 10
    return data[point[0]][point[1]]

basins = []
for x in range(len(data)):
    for y in range(len(data[0])):
        count=0
        open=[[x,y]]
        closed=[]
        while len(open) > 0:
            if (open[0] not in closed) and GetValue(data, open[0]) < 9:
                count +=1
                closed.append(open[0])
                xx=open[0][0]
                yy=open[0][1]
                for element in [[xx+1, yy], [xx-1,yy],[xx,yy+1],[xx,yy-1]]:
                    open.append(element)
            del(open[0])
        for element in closed:
            data[element[0]][element[1]]=9
        if count>0:
            basins.append(count)

basins = sorted(basins, reverse=True)
print("Part two solution : ", basins[0] * basins[1]* basins[2])
