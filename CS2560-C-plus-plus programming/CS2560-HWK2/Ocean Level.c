/*
 ============================================================================
 Name        : Ocean.c
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

	double year1,year2,year3,year4; // The years correlate to the ocean level
	year1=3.1;
	year2=3.1+(1.5*5);
	year3=3.1+(1.5*7);
	year4=3.1+(1.5*10);
	printf("!!!Welcome to Ocean Level!!!\n");
	printf(" The Ocean level in 2018 is %.2lfmm\n ",year1);
	printf(" The Ocean level in 2023 is %.2lfmm\n",year2);
	printf(" The Ocean level in 2025 is %.2lfmm\n",year3);
	printf(" The Ocean level in 2028 is %.2lfmm\n",year4);
	return 0;
}
