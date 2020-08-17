// ArrayClass.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>

class ArrayClass{
private :
	int size;
public:
	ArrayClass()
	{
		ArrayClass(0);

	}
	ArrayClass(int length)
	{
		double *Array = new double[length]();
	}

	ArrayClassDestructor()
	{
		delete[]Array;
	}

	void store(int entry,double *Array)
	{
		for (int i = 0; Array.length; i++)
		{
			if (Array[i] == 0)
				Array[i] = entry;
			else
				continue;

		}
	}

	int highest(double *Array)
	{

		int highest=Array[0];
		for (int i = 0; i < Array.length; i++)
		{
			if (highest < Array[i])
				highest = Array[i];
			else
				continue;
		}
		return highest;
	}

	int lowest(double *Array)
	{

		int lowest = Array[0];
		for (int i = 0; i < Array.length; i++)
		{
			if (lowest > Array[i])
				lowest = Array[i];
			else
				continue;
		}
		return lowest;
	}

	
	int search(int entry, double *Array)
	{
		int found;

		for (int i = 0; i < Array.length; i++)
		{
			if (entry==Array[i])
				found = Array[i];
			else
				continue;
		}
		return found;
	}

};

int main()
{
    std::cout << "Hello World!\n"; 
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
