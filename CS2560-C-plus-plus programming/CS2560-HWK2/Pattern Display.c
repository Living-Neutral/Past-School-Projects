/*
 ============================================================================
 Name        : Pattern.c
 Author      : Jeffrey McDonald
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void)
{

	printf("!!!Pattern Display!!!");
	for(int i =0;i<10;i++)
	{
		for(int j=0;j<i;j++)
			printf("+");
		printf("\n");
	}
	printf("\n");

	for(int k=10;k>=1;k--)
		{
			for(int l =1;l<=k;l++)
				printf("+");
			printf("\n");
		}


	return 0;
}
