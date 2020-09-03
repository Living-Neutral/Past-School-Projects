import pygame


pygame.init()

WIN = pygame.display.set_mode((800,800))
pygame.display.set_caption("Test screen")

clock = pygame.time.Clock()

clock.tick(60)

RED = (255,0,0)
GREEN = (0,255,0)
BLUE = (0,0,255)
YELLOW = (255,255,0)
WHITE = (255,255,255)
BLACK = (0,0,0)
PURPLE = (128,0,128)
ORANGE = (255,165,0)
GREY = (128,128,128)
TURQUOISE = (64,224,208)
run = True

class rect:
    def __init__(self,x,y,width,height):
        self.x = x
        self.y = y
        self.width = width
        self.height = height

    def getX(self):
        return self.x

    def getY(self):
        return self.y

    def get_width(self):
        return self.width

    def get_height(self):
        return self.height

    def setX(self,new_x_pos):
         self.x = new_x_pos


top_bar = rect(300,0,200,50)
bottom_bar = rect(300,750,200,50)

top_bar_velocity = 0
bottom_bar_velocity = 0
while run:
    clock.tick(80)
    WIN.fill(WHITE)

    pygame.draw.rect(WIN, BLUE, (top_bar.getX(), top_bar.getY(), top_bar.get_width(), top_bar.get_height()))
    pygame.draw.rect(WIN, BLUE, (bottom_bar.getX(), bottom_bar.getY(), bottom_bar.get_width(), bottom_bar.get_height()))

    top_bar.setX(top_bar.getX() + top_bar_velocity)
    bottom_bar.setX(bottom_bar.getX()+bottom_bar_velocity)

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            run = False


    keystate = pygame.key.get_pressed()

    if not keystate[pygame.K_LEFT] or not keystate[pygame.K_RIGHT]:
        top_bar_velocity = 0

    if top_bar.getX()*200>=800 or top_bar.getX()<=0:
        top_bar_velocity = 0
        print('top_bar x:'+str(top_bar.getX()))
        print('top_bar v:'+str(top_bar_velocity))

    if not keystate[pygame.K_a] or not keystate[pygame.K_d]:
        bottom_bar_velocity = 0

    if keystate[pygame.K_a]:
        bottom_bar_velocity = -5

    if keystate[pygame.K_d]:
        bottom_bar_velocity = 5

    if keystate[pygame.K_LEFT]:
        top_bar_velocity = -5

    if keystate[pygame.K_RIGHT]:
        top_bar_velocity = 5






    pygame.display.update()
pygame.quit()