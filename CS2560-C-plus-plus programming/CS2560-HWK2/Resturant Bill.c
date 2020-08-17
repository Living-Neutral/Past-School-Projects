/*
 ============================================================================
 Name        : Resturant.c
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
	printf("!!!Welcome to Restaurant bill!!!\n");
	printf("Your Bill is ");
	double meal_charge,tax,tip,subtotal,total;
	meal_charge=88.67;
	tax=0.0675*meal_charge;
	subtotal=meal_charge+tax;
	tip=subtotal*.02;
	total=subtotal+tip;
	printf("The meal charge is $%.2lf\n",meal_charge);
	printf("The tax is $%.2lf\n",tax);
	printf("The tip is $%.2lf\n",tip);
	printf("Total is $%.2lf",total);
	return 0;
}
