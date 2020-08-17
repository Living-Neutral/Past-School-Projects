// Multiplication table.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>
#include <string>
using namespace std;
int main()
{
    cout << "Hello World!\n"; 
	int factor1;
	int factor2;

	int table[11][11];
	
	for (int i = 1; i <= 10; i++)
	{
		factor1 = i;
		for (int j = 1; j <10; j++)
		{
			factor2 = j;
			table[i][j]=(factor1*factor2);
		}
	}

	for (int k = 1; k <= 10; k++)
	{

		for (int l = 1; k <= 10; l++)
		{
			cout<<table[k][l];
		}
		cout << endl;

	}


	return 0;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
