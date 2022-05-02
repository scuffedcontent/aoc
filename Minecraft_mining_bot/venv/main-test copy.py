import pyautogui as pt


class Clicker:
    def __init__(self, target_png, speed):
        self.target_png = target_png
        self.speed = speed
        pt.FAILSAFE = True

    def nav_to_image(self):
        try:
            pt.keyDown(key_press)

            if action == 'walking':
                print('Walking')
            elif action == 'attack':
                pt.keyDown('x')

            #sleep(duration)
                pt.keyUp('x')
                pt.keyUp(key_press)
            

        except:
            print('No image found...')
            return 0


if __name__ == '__main__':

  
    # Initialises the clicker
    clicker = Clicker(r'C:\Users\plump\Documents\GitHub\git_repos\Minecraft_mining_bot\images\start_game.png', speed=1)

    end = 0
    while True:
        # Checks whether there are still circles
        if clicker.nav_to_image() == 0:
            end += 1

        # End the loop
        if end > 20:
            break