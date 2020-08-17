#include<iostream>
#include<string>
using namespace std;

int main()
{
	const int ROW_MAX=10;
	const int COLUMN_MAX = 10;
	
	int table[ROW_MAX][COLUMN_MAX];
	for (int i=0;i<ROW_MAX;i++)
	{
		int factor1=1;
		int factor2 = 1;
		for (int j=0; j < ROW_MAX; j++)
		{
			cout<<factor1+" ";
			cout << (factor1*factor2);
			factor1++;
		}
		printf(factor2 + "\n");
		factor2++;

	}

	return 0;
}