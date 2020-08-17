// CS2560 Midterm2.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>
#include <vector>
class Human;

class Human
{
	std::string name;
	int age;
	char sex;
	Human() {};

protected:
	Human(const std::string &n, const int &a, const char &s)
		:name(n), age(a), sex(s) {
		printf("Human constructed");
	};

	virtual ~Human()
	{
		printf("Destructing human!\n");
	}

public:
	void setName(const std::string &n) { name = n; };
	void setAge(const int &a) { age = a; };
	void setSex(const char &s) { sex = s; };
	std::string getName() { return name; }
	int getAge() { return age; }
	char getSex(){return sex;}
};


class Child :public Human
{
private:
	std::string momName;
    std::string dadName;
	int allowance;
	//Child() {};
public:
	Child(const std::string &n, const int &a, const char &s, const std::string &pname, const std::string &mname):Human(n, a, s),dadName(pname),momName(mname),allowance(0)
	{printf("child made!\n");};

	std::string getmomName() { return momName; }
	std::string getdadName() { return dadName; }
	void getParents() { printf("Dad:,%s and Mom: %s", dadName.c_str(),momName.c_str()); };
    int getAllowance() { return allowance; }
	void setAllowance(const int &a) { allowance = a; }
	void setmomName(const std::string &pname) { dadName =pname; }
	void setdadName(const std::string &mname) { momName = mname; }
	
};

class Parent :public Human
{
private:
	//Parent() {};
	std::vector<std::string> children;
	int allowance;

public:
	void setAllowance(const int &a) {  }
	Parent(const std::string &n, const int &a, const char &s) :Human(n, a, s)
	{printf("Parent made!\n");}
	void setAllowance(const int &a) { allowance = a; };
	int getAllowance() { return allowance; }

};

/*
friend void setAllowance(const Parent &parent, const Child &child)
{
	child.setAllowance(parent.getAllowance());
}
*/

int main()
{
    std::cout << "Hello World!\n"; 
	Parent Homer("Homer Simpson",36,'M');
	Parent Marge("Marge Simpson",34,'F');
	Child Lisa("Lisa Simpson", 12, 'F',Homer.getName(),Marge.getName());
	Child Bart("Bart Simpson",10,'M', Homer.getName(), Marge.getName());
	Child Maggie("Maggie Simpson", 10, 'M', Homer.getName(), Marge.getName());
}

void displayInfo(const Child &child)
{
	std::cout << child.getName() << std::endl;
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
