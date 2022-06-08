

def getInput(filename):
    file = filename
    with open(file) as f:
        input = f.read()
        return input
