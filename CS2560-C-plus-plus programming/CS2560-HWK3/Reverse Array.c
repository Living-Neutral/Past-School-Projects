/*
 ============================================================================
 Name        : Reverse.c
 Author      : Jeffrey McDonald
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
int *reverse(int array[],int);
int main(void)
{
	int *p;
    int array1[10]={1,2,3,4,5,6,7,8,9,10};
	printf("Hello to reverse array!\n");
	printf("Here is array 1\n");
	for(int y=0;y<10;y++)
		printf("Array1 index %d is: %d\n",y,array1[y]);
    printf("Now here is the Reverse array!\n");
    p=reverse(array1,10);
    for(int i=0;i<10;i++)
    {
    	printf("Reverse Array index %d is:%d \n",i,p[i] );
    }
	return EXIT_SUCCESS;
}

int *reverse(int list[],int length)
{
    int i,index;
    static int array2[10];
    for(i=length-1,index=0;i>=0;i--,index++)
    {
    	array2[index]=list[i];
    }
    return array2;
}
