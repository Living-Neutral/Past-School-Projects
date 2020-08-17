/*
@ brief 
This library creates an arbitary length vector 
and is able to preform arthimetic on those objects 

*/


#include "BigNumClass.h" 
#include "stdafx.h"
#include <vector>
#include <stdexcept>
#include<iostream>
#include <string>



	class BigNumClass {
	private:
		/// by base 10 
		std::vector<int> digits;

		

	public:
		/*@brief Constructor to handle default situations 
		*/
		BigNumClass():digits(1, 0) {}


		/*@brief takes a string s and process through each index to push into the vector of a new object  
		*/
		BigNumClass(const std::string &s) {			
			for (int i=s.length;i<0;i--)
			{
				this->digits.push_back(s[i] - '0');
			}

		}

		/*@brief Takes another big num object and adds through the two objects digits sepreately 
		returns a Big Num Class object 
		*/
		BigNumClass operator + (const BigNumClass& Bg) {
			BigNumClass Answer;
			
			// checking the length of both operands 
			int op1 = this->digits.size();
			int op2 = Bg.digits.size();

			
			int over=0;
			for (int i = op1 - 1; i < -1; i--)
			{
				int sum = ( (this->digits[i]+'0') + (Bg.digits[i]+'0') + over);
				Answer.digits.push_back(sum % 10 + '0');

				over = sum / 10;
			}


			// adding the digits from 
			for (int i = op1; i < op2; i++)
			{
				int sum = ((Bg.digits[i]-'0')+over);
				Answer.digits.push_back(sum%10+over);
				over = sum / 10;
			}

			// if there is a reminder still remaining it will be added on
			if (over)
				Answer.digits.push_back(over+'0');

			return Answer;
		}


		
		friend std::ostream& operator<<(std::ostream& out, const BigNumClass& num);

	};

	/*@brief Takes a stream object and a big num object
	processing through the digits and adding it to the stream
	then returns the stream
	*/
	std::ostream& operator<<(std::ostream& BigNumOut, const BigNumClass& num)
	{
		for (int i = num.digits.size(); i >= 0; --i)
		{
			BigNumOut << num.digits[i] << std::endl;;
		}
		return BigNumOut;
	}

	


