/*
 ============================================================================
 Name        : population.c
 Author      : Jeffrey McDonald
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
void newPopulation(int,int,int,int);
int main(void)
{
	int population,years,birth_rate,death_rate;
	printf("Welcome to population checker !!!\n");
	do{
	printf("\nWhat is the population right now?: ");
	fflush(stdout);
	scanf("%d",&population);
	}while(population<1);

	do{
	printf("\nWhat is the birth rate(out of a 100)?: ");
	fflush(stdout);
	scanf("%d",&birth_rate);
	}while(birth_rate<0);

	do{
	printf("\nWhat is the death rate(out of a 100)?: ");
	fflush(stdout);
	scanf("%d",&death_rate);
	}while(death_rate<0);


	printf("\nFor how many years?: ");
	fflush(stdout);
	scanf("%d",&years);


	newPopulation(population,birth_rate,death_rate,years);
	return 0;
}

void newPopulation(int people,int birth, int death, int time)
{
   int change;
   int newPeople=people;
   if(birth>death)
   change=birth-death;
   else if(birth==death)
   change=0;
   else
   change=-1*(death-birth);
	 	  printf("%d",change);
	 	 for(int i=0;i<=time;i++)
	 	 	       {
	 	 	          newPeople+=change;
	 	 	          if(newPeople<1)
	 	 	          {
	 	 	        	  printf("\nEvery one is dead");
	 	 	        	  exit(0);
	 	 	          }
	 	 	          else
	 	 	          printf("\nFor year %d the population was %d\n",i,newPeople);
	 	 	       }

	       }
