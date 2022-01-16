'''

Function that draws a Unit Circle to the screen using pygame

'''

import pygame
import math
from pygame import gfxdraw

white = (255,255,255)       #
black = (0, 0, 0)           #
red = (255, 0, 0)           #
green = (0, 150, 0)         # Colors that are used in application
gray = (150,150,150)        #
blue = (0,0,255)            #
light_gray = (220,220,220)  #

def button():
    angle_list = ["+15","+30","+45","Zero","Auto"] #list of button titles
    buttonX = width/8  #set where buttons must be
    buttonY = height/10 # this too

    button_background = pygame.Surface((width, buttonY), pygame.SRCALPHA) # create an alpha surface for apply buttons

    pygame.draw.rect(button_background, black, [0 , 0, 5*buttonX, height/10]) #next line's create rectangles for buttons
    pygame.draw.rect(button_background, light_gray, [1 , 1, buttonX-2, height/10 - 2])
    for i in range(1,5,1):
        pygame.draw.rect(button_background, light_gray, [buttonX*i + 1 , 1, buttonX-2, height/10 - 2])

    text_space = buttonX/2.8 #displacement between text
    for texto in angle_list: #add text to the surface and finally draw on main window
        angled = font.render(texto, True, black)
        button_background.blit(angled, (text_space, buttonY/3))
        text_space += buttonX

    window.blit(button_background, (0,0))

def grid(X, Y): #draw the grid behind the circle
    for space in range(int(width/4), width, circle_radius):
        if space == X:
            pygame.draw.aalines(window, black, True, [(space, 0),(space, height)])
        else:
            pygame.draw.aalines(window, light_gray, True, [(space, 0),(space, height)])
    for space in range(int(width/4), 0, -circle_radius):
        if space == X:
            pygame.draw.aalines(window, black, True, [(space, 0),(space, height)])
        else:
            pygame.draw.aalines(window, light_gray, True, [(space, 0),(space, height)])
    for space_Y in range(0, height, int(height/4)):
        if space_Y == Y:
            pygame.draw.aalines(window, black, True, [(0, space_Y),(width, space_Y)])
        else:
            pygame.draw.aalines(window, light_gray, True, [(0, space_Y),(width, space_Y)])

def lines(background, cor, boolean, coord): #draw every straight line that are seen

    try:
        pygame.draw.lines(background, cor, boolean, [(coord[0][0],coord[0][1]),(coord[1][0],coord[1][1])], 2)
    except:
        pygame.draw.aalines(background, cor, boolean, coord)

def text(angle):
    list = ["Angle: ", "Sin: ", "Cos: ","Tan: "] #list of names to display
    values = [""]*4 # every name will receive a value, though, in another list
    displacement = height # displacament for text to be display

    values[0] = str(round(math.degrees(angle * -1),1))
    values[1] = str(round(math.sin(angle * -1),2))
    values[2] = str(round(math.cos(angle * -1),2))
    if round(math.tan(angle * -1),2) > 1000: # tan of 90 degrees is not defined but will show a high value
        list[3] = "Tan: not defined"         # so i will hide the value and write not defined
        values[3] = ""
    else:
        list[3] = "Tan: "
        values[3] = round(math.tan(angle * -1),2)

    list.reverse() # i notice that my list start with the last element to be show so i inverted
    values.reverse()

    for word, trig in zip(list, values): #here i display, set x's and y's, and i add to the main window
        displacement -= width * 0.05
        text_trig = font.render(word + str(trig), True, black)
        window.blit(text_trig, (width-120-int(width * 0.2), displacement))

def draw(angle): # this function call the others and make the calculus of angle and position
    window.fill(white)

    circX = int(width/4) + circle_radius   #circle position x and y
    circY = int(height/2)
    grid(circX, circY) #call grid function


    x_pos = circX + circle_radius * math.cos(angle)  # this calculate how much the line have to move in X
    y_pos = circY + circle_radius * math.sin(angle)  # calculate how much the line have to move in Y

    tan = height/2 + circle_radius * math.tan(angle) # same as previously but for tangent
    tan_neg = height/2 - circle_radius * math.tan(angle) # same as previuosly but for tangent when negative

    lines(window, gray, True, [(x_pos, y_pos),(circX, y_pos)]) # Sin segment
    lines(window, gray, True, [(x_pos, y_pos),(x_pos, circY)]) # Cos segment
    lines(window, green, True, [(circX + circle_radius, circY),(circX + circle_radius, tan)]) # tan
    lines(window, green, True, [(circX - circle_radius, circY),(circX - circle_radius, tan_neg)]) # tan_neg
    lines(window, gray, True, [(circX, circY),(circX + circle_radius, tan)]) # sec
    lines(window, gray, True, [(circX, circY),(circX - circle_radius, tan_neg)]) # sec_neg
    lines(window, black, True, [(circX, circY),(x_pos, y_pos)]) # radius
    lines(window, blue, True, [(circX, circY),(x_pos, circY)]) # Cos
    lines(window, red, True, [(circX, circY),(circX, y_pos)]) # Sin

    pygame.gfxdraw.aacircle(window, circX, circY, circle_radius, black) #unit circle

    button()
    text(angle)
    pygame.display.flip() #end of first loop in application, this refresh the window


while True: #Here the program begin, console are asked the width and height for our application start<<<<<<<<<<<<<<<<<<<
    try:
        width = int(input("\nWrite the width: \n"))
        height = int(input("Write the height: \n"))

        angle_start = 0 #where our angle begin
        circle_radius = int(height/4) #radius of our unit circle based on height
        auto = False #set as false the autoloop through every single degree in unit circle

        pygame.init()
        window = pygame.display.set_mode((width, height))
        pygame.display.set_caption("Unit Circle")
        font = pygame.font.SysFont(None, int(width * 0.05))
        clock = pygame.time.Clock()
    except: #if width or height are invalid values
        print("wrong width and height ratio, try again! \n")
        continue
    print("successfully start!!")
    break

done = False #set False for our loop that watch every event that user do

while not done:

    clock.tick(60) # how many frames are going to be render

    if math.degrees(angle_start * -1) > 360: # reset angle after one full loop
        angle_start = 0

    for event in pygame.event.get(): #if you want to out from application, this break the loop and quit pygame
        if event.type == pygame.QUIT:
            done = True
            pygame.quit()
            quit()

        if event.type == pygame.MOUSEBUTTONDOWN: # to grab what button do you click and add to angle some amount

            mousex, mousey = pygame.mouse.get_pos()

            if mousey < height/10:
                if mousex < width/8:
                    angle_start -= math.radians(15)
                if mousex > width/8 and mousex < width/4:
                    angle_start -= math.radians(30)
                if mousex > width/4 and mousex < 3 * width/8:
                    angle_start -= math.radians(45)
                if mousex > 3 * width/8 and mousex < 4 * width/8:
                    angle_start = 0
                if mousex > 4 * width/8 and mousex < 5 * width/8:
                    if not auto:
                        auto = True
                    else:
                        auto = False

    if auto: #run the animation for every degree
        angle_start -= 0.01

    draw(angle_start) # draw everything
