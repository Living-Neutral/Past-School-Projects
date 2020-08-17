/*
 ============================================================================
 Name        : Tic-Tac-Toe.c
 Author      : Jeffrey McDonald
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>
/*
void printGameboard(char array[][]);
int Player1Row();
int Player1Column();
int Player2Row();
int Player2Column();
*/
int main(void)
{
	int firstPlayerR,firstPlayerC,secondPlayerR,secondPlayerC;
	firstPlayerR=-1;
	firstPlayerC=-2;
	secondPlayerR=-3;
	secondPlayerC=-4;
	printf("!!!Welcome to Tic-Tac-Toe game!!!\n");
	char gameboard[3][3];
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
			gameboard[i][j]='*';
	}

	for(int p=0;p<3;p++)
	{
		do{
		do{
		firstPlayerR=Player1Row();
		firstPlayerC=Player1Column();
		}while(firstPlayerR==secondPlayerR);
        gameboard[firstPlayerR][firstPlayerC]=='X';
	    printGameboard(gameboard);

		do{
		secondPlayerR=Player2Row();
		secondPlayerC=Player2Column();
		}while(firstPlayerR==secondPlayerR);
		gameboard[firstPlayerR][firstPlayerC]='O';
	    printGameboard(gameboard);

		}while(Player1Win()==Player2Win);
		if(Player1Win()==1)
			printf("Player 1 has won!");
		else if(Player2Win()==1)
			printf("Player 2 has won!");
		else
			exit(0);
	}
	return 0;
}

int Player1Row()
{
	int choice;
	printf("Choose your row:");
	fflush(stdout);
    scanf("%d",&choice);
	return choice;
}


int Player1Column()
{
	int choice;
	printf("Choose your Column:");
	fflush(stdout);
	scanf("%d",&choice);
	return choice;
}

int Player2Row()
{
	int choice;
	do
	{
	printf("Choose your Column:");
	fflush(stdout);
	scanf("%d",&choice);
	}while((choice<0)||(choice>2));
	return choice;
}

int Player2Column()
{
	int choice;
	do{
	printf("Choose your Column");
	fflush(stdout);
    scanf("%d",&choice);
	}while((choice<0)||(choice>2));
	return choice;
}

void printGameboard(char array[][3])
{
	printf("Here is the gameboard\n");
	for(int k=0;k<3;k++)
		{
			for(int l=0;l<3;l++)
			{
			printf(	"%c",array[k][l]);
			}
			printf("\n");
		}

}

int Player1Win(char array[][3])
{
	if( (array[0][0]=='X') && (array[0][1]=='X') && (array[0][2]=='X'))
		return 1;
	else if( (array[1][0]=='X') && (array[1][1]=='X') && (array[1][2]=='X'))
		return 1;
	else if( (array[2][0]=='X') && (array[2][1]=='X') && (array[2][2]=='X'))
		return 1;

	else if( (array[0][0]=='X') && (array[1][0]=='X') && (array[2][0]=='X'))
		return 1;
	else if( (array[1][1]=='X') && (array[2][2]=='X') && (array[2][0]=='X'))
		return 1;
	else if( (array[0][2]=='X') && (array[1][2]=='X') && (array[2][2]=='X'))
			return 1;

	else if( (array[0][0]=='X') && (array[1][1]=='X') && (array[2][2]=='X'))
				return 1;
	else if( (array[0][2]=='X') && (array[1][1]=='X') && (array[2][0]=='X'))
				return 1;

	else
		return 0;
}
int Player2Win(char array[][3])
{
	if( (array[0][0]=='O') && (array[0][1]=='O') && (array[0][2]=='O'))
		return 1;
	else if( (array[1][0]=='O') && (array[1][1]=='O') && (array[1][2]=='O'))
		return 1;
	else if( (array[2][0]=='O') && (array[2][1]=='O') && (array[2][2]=='O'))
		return 1;

	else if( (array[0][0]=='O') && (array[1][0]=='O') && (array[2][0]=='O'))
		return 1;
	else if( (array[1][1]=='O') && (array[2][2]=='O') && (array[2][0]=='O'))
		return 1;
	else if( (array[0][2]=='O') && (array[1][2]=='O') && (array[2][2]=='O'))
			return 1;

	else if( (array[0][0]=='O') && (array[1][1]=='O') && (array[2][2]=='O'))
				return 1;
	else if( (array[0][2]=='O') && (array[1][1]=='O') && (array[2][0]=='O'))
				return 1;

	else
		return 0;
}

