/*
 ============================================================================
 Name        : Stock.c
 Author      : Jeffrey McDonald
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
	printf("Joe it's your stockbroker here is the totals for the commission.\n");
	double rawStock,purchaseCommission,saleCommission,commissions,stockSale,total,profit;
	rawStock=45500.00;
	purchaseCommission=910.00;
	stockSale=56900.00;
	saleCommission=1138;
	total=stockSale-rawStock;
	commissions=purchaseCommission+saleCommission;
	profit=total-commissions;
	printf("The stock you paid for $ %.2lf\n",rawStock);
	printf("The commission for buying $%.2lf\n",purchaseCommission);
	printf("You Sold $%.2lf\n",stockSale);
	printf("The commission for selling $%.2lf\n",saleCommission);
	if(total<0)
	printf("You lost this much $%.2lf\n",profit);
	else
	printf("You made this much $%.2lf\n",profit);
	printf("If you want to do any other purchases call me!");
	return EXIT_SUCCESS;
}
