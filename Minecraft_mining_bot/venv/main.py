import pyautogui as pt
from time import sleep
import pydirectinput as pdi
#helper function

def nav_to_image(image, clicks, off_x=0, off_y=0):
    position = pt.locateOnScreen(image)
    if position is None:
        print(f'{image} not found ...')
        return 0
    else:
        print(position)
        pdi.moveTo(position[0])
        #pdi.moveRel(off_x, off_y, duration=0.1)
        #pdi.leftClick(clicks=clicks, interval=0.3)
# def check_for_image(image):
#     position = pt.locateOnScreen(image, confidence=.3) 
#     if position is not None:
#         return True
#     else:
#         return False
# Moves the character
# x = attack  by default left click
# c = place block by default right click
# Need to change keybindings to match your keyboard


def move_character(key_press, duration, action='walking'):
    pdi.keyDown(key_press)

    if action == 'walking':
        print('Walking')
    elif action == 'attack':
        pdi.keyDown('x')

    sleep(duration)
    pdi.keyUp('x')
    pdi.keyUp(key_press)


def locate_lava():
    position = pt.locateOnScreen('images/lava.png', confidence=.4)

    if position is None:
        return False
    else:
        move_character('s', 2)
        print('Lava found!!')
        return True


def move_forward():
    pdi.keyDown('w')

def move_backward():
    pdi.keyDown('s')

def main():
#    sleep(3)
    nav_to_image(r'C:\Users\plump\Documents\GitHub\git_repos\Minecraft_mining_bot\images\start_game.png', clicks=3)
#    check_for_lava()
#    move_forward()


def check_for_lava():
    position = pt.locateOnScreen(r'C:\Users\plump\Documents\GitHub\git_repos\Minecraft_mining_bot\images\lava.png', confidence=1)
    if position is None:
        move_backward()
        move_backward()
        print('Lava found!!')
        return False
    else:
        return True

#Start the game



if __name__ == '__main__':
    while True:
        main()
