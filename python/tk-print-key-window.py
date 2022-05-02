from tkinter import *

def printEvent(event):
    print("You pressed : " + event.keysym)
    label.config(text=event.keysym)

window = Tk()

window.bind("<Key>", printEvent)

label = Label(window, font=("Terminus",25))

label.pack()

window.mainloop()
