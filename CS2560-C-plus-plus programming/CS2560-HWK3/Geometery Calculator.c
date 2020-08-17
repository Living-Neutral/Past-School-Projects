/*
 ============================================================================
 Name        : Geometery.c
 Author      : Jeffrey McDonald
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void CircleArea();
void RectangleArea();
void TriangleArea();

int main(void)
{
   char redo;
   do{

     int choice;
     do{

	   printf("!!!Geometry calculator!!!\n");
	   printf("1.Calculate the Area of a Circle\n");
	   printf("2.Calculate the Area of a rectangle\n");
	   printf("3.Calculate the Area of a Triangle\n");
	   printf("4.Quit\n");
	   printf("Which do you want to do ?: ");
	   fflush(stdout);
	   scanf("%d",&choice);

     switch(choice)
	{
	   case 1:
	   CircleArea();
	   break;

	   case 2:
	   RectangleArea();
	   break;

	   case 3:
	   TriangleArea();
	   break;

	   case 4:
	   exit(0);
	}

   }while((choice<1)||(choice>4));
    printf("\nDo you want to do another calculation(y/n)?: "); //for the code to restart if user wants
    fflush(stdout);
    scanf(" %c",&redo);
}while((redo=='y')|| (redo=='Y'));
	return 0;
}

void CircleArea()
	{
		int radius;
        double area;
        const double pi=3.14159;
        printf("What is the radius?: ");
        fflush(stdout);
        scanf("%d",&radius);
        area=radius*pi;
        printf("The area is %.2lf",area);
	}


void RectangleArea()
{
   float area,length,width;
   printf("What is the length and width?: ");
   fflush(stdout);
   scanf("%f %f",&length,&width);
   area = length*width;
   printf("The area is %.2lf",area);
}

void TriangleArea()
{
   float base,height,area;
   printf("What is the base and height?: ");
   fflush(stdout);
   scanf("%f %f",&base,&height);
   area=.5*(base*height);
   printf("The area is %.2lf", area);
}

