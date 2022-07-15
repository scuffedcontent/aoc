validTest="()"
str="()}]a"
valid="(){}[]"
for i in range(0, len(str)):
    if str[i] in valid:
        print("True")
