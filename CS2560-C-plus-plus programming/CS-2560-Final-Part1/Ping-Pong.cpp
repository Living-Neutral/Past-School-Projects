// Ping-Pong.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>
#include <SFML/Graphics.hpp>
#include <time.h>

// Screen size variables
int vertical = 30, horizontal = 30;
int size = 16;
int height = vertical * size;
int width = horizontal * size;
int barVelocityX=1,barVelocityY=2;

// The length and movements for the bars
int barLength = 5;
int movement,movement1;

// delay for the game
float delay = .1f;

// For the upper bar
struct bar1 {
	int x,y;
}b[5];


// For the lower bar
struct bar2 {
	int x,y;
}b1[5];

// The ball itself  
struct ball {
	int x, y;
}Ball;



void slide()
{
	// Bar 1's movement
	for (int i = barLength; i > 0; i--)
	{
		b[i].x = b[i - 1].x;	
	}

	// Bar 2's movement
	for (int i = barLength; i > 0; i--)
	{
		b1[i].x = b1[i - 1].x;
	}

	// moving the bar 
	if (movement == 1)
	{
		b[0].x -= 1;
	}

	if (movement == 2)
	{
		b[0].x += 1;
		
	}

	// moving the second bar
	if (movement1 == 1)
	{
		b1[0].x -= 1;
	
	}

	if (movement1 == 2)
	{
		b1[0].x += 1;		
	}


	/*resets the bars at an invisible wall	
	*/
	if (b[0].x > horizontal)
		b[0].x = 0;
	if (b[0].x < 0)
		b[0].x = horizontal;


	if (b1[0].x > horizontal)
		b1[0].x = 0;
	if (b1[0].x < 0)
		b1[0].x = horizontal;
}

void moveBall()
{
	// checks boundary of the left and right wall and bounces the ball 
	if (Ball.x > horizontal || Ball.x < 0)
		barVelocityX *= -1;

	// If it hits the bar the ball will deflect
	if (((Ball.x == b1[0].x) || (Ball.x == b1[1].x) || (Ball.x == b1[2].x) || (Ball.x == b1[3].x) || (Ball.x == b1[4].x)) && (Ball.y > vertical))	
		barVelocityY *= -1;
	
	// If it hits the bar the ball will deflect
	if (((Ball.x == b[0].x) || (Ball.x == b[1].x) || (Ball.x == b[2].x) || (Ball.x == b[3].x) || (Ball.x == b[4].x)) && (Ball.y < 0))	
		barVelocityY *= -1;
	

	Ball.x += barVelocityX;
	Ball.y += barVelocityY;
}


int main()
{
	srand(time(0));

	// loading game screen and handling the sprites
	sf::RenderWindow  window(sf::VideoMode(width,height),"Ping pong Game");
	sf::Texture t1,t2,t3;
	t1.loadFromFile("images/black.png");
	t2.loadFromFile("images/green.png");
	t3.loadFromFile("images/red.png");
	sf::Sprite backgroundSprite(t1);
	sf::Sprite barSprite(t2);
	sf::Sprite ballSprite(t3);

	// setting the inital position
	b[0].x = 20;
	b[1].x = 21;
	b[2].x = 22;
	b[3].x = 23;
	b[4].x = 24;


	// For bar 2
	b1[0].x = 20;
	b1[1].x = 21;
	b1[2].x = 22;
	b1[3].x = 23;
	b1[4].x = 24;


	// For the ball
	Ball.x = 13;
	Ball.y = 13;

	// establishing game clock
	sf::Clock clock;
	float timer = 0.0f;
	// checking while window is open
	while (window.isOpen())
	{
		//After every loop adds to the timer how much has elapsed
		float time = clock.getElapsedTime().asSeconds();
		clock.restart();
		timer += time;

		//Allow us to check when a user does something
		sf::Event e;

		//Check when the window is closed
		while (window.pollEvent(e))
		{
			//If user presses x in the top right, Windows, top left, Mac,  close the window
			//Quits the game after the ball passes a boundary of the y
			if ((e.type == sf::Event::Closed)||(Ball.y>35)||(Ball.y < -5))
			{
				window.close();
			}
		}

		// Handling the top bar's movement
		if (sf::Keyboard::isKeyPressed(sf::Keyboard::A)) movement = 1;
		if (sf::Keyboard::isKeyPressed(sf::Keyboard::D))movement = 2;

	
		// Handling the bottom bar's movement
		if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left)) movement1 = 1;
		if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right))movement1 = 2;

		// resetting the timer to the delay
		if (timer > delay)
		{
			timer=0;
			slide();
			moveBall();
		}
		
            // drawing the screen
			for (int i = 0; i < horizontal; i++)
			{
				for (int j = 0; j < vertical; j++)
				{
					//Set position of sprite1 one at a time
					backgroundSprite.setPosition(i*size, j*size);
					//Draw sprite1 but, do not show it on screen. 
					window.draw(backgroundSprite);
				}
			}

			// moving the both bars
			//bar 1
			for (int i = 0; i < barLength; i++)
			{
				barSprite.setPosition(b[i].x*size,  (b[i].y*size));
				window.draw(barSprite);
			}

			// bar 2
			for (int i = 0; i < barLength; i++)
			{
				barSprite.setPosition(b1[i].x*size, (b1[i].y*size)+470);
				window.draw(barSprite);
			}

			// ball
			ballSprite.setPosition(Ball.x*size,Ball.y*size);
			window.draw(ballSprite);
			

			window.display();
	}
	return 0;
}


