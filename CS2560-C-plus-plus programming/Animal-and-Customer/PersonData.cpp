// PersonData.cpp : This file contains the 'main' function. Program execution begins and ends there.
#include "pch.h"
#include <iostream>
#include <string>

class PersonData{
private:
	std::string firstName; 
	std::string lastName;
	std::string address;
	std::string city;
	std::string state;
	int phone, zip_code;
	PersonData() {};

protected:
	PersonData(const std::string &f, const std::string &l, const std::string &a, const std::string &c, const std::string &s, int p, int z):
		firstName(f), lastName(l), address(a), city(c), state(s), phone(p), zip_code(z)
	{printf("Person constructed.\n");}

public:
	//getters	
	const std::string getFirstName() const { return firstName; }
	const std::string getLastName() const { return lastName; }
	const std::string getAddress() const { return address; }
	const std::string getCity() const { return city; }
	const std::string getState() const { return state; }
	int getphone() { return phone; }
	int getZip() { return zip_code; }
};

class CustomerData :public PersonData {
	int customerId;
	bool mailing_list;

public:
	CustomerData(int cId, bool m_s, std::string f, std::string l, std::string a, std::string c, std::string s, int p, int z):
	PersonData(f, l, a, c, s, p, z),customerId(cId),mailing_list(m_s)
	{printf("Customer created\n"); }

	void setMailinglist(bool m_s) { mailing_list = m_s; }
	int getCustomerId() { return customerId; }

	bool getMailingList()
	{
		return mailing_list;
	}
};

class preferredCustomer:public CustomerData
{
	double purchaseAmount, discount;

public:
	preferredCustomer(double pA,int cId, bool m_s, std::string f, std::string l, std::string a, std::string c, std::string s, int p, int z) :
		CustomerData(cId, m_s, f, l, a, c, s, p, z), purchaseAmount(pA) 
	{
		if (purchaseAmount < 0)
		{
			printf("Preferred Customer wasn't created\n");
		}

		else if (purchaseAmount >= 500)
		{
			discount = .05;
			printf("Preferred Customer created\n");
		}

		else if (purchaseAmount >= 1000)
		{
			discount = .06;
			printf("Preferred Customer created\n");
		}

		else if (purchaseAmount >= 1500)
		{
			discount = .07;
			printf("Preferred Customer created\n");
		}

		else if (purchaseAmount >= 2000)
		{
			discount = .1;
			printf("Preferred Customer created\n");
		}

		else
			printf("Preferred Customer wasn't created\n");
	}	

	void setAmount(double A) 
	{ 
		purchaseAmount = A; 
		if (purchaseAmount < 0)
			printf("Can't have a negative amount");
		
		else if ((purchaseAmount >= 500)&&(purchaseAmount<1000))
			discount = .05;

		else if ((purchaseAmount >= 1000) && (purchaseAmount < 1500))
			discount = .06;
		else if ((purchaseAmount >= 1500) && (purchaseAmount < 2000))
			discount = .07;

		else if ((purchaseAmount >= 2000))
			discount = .1;
	}

	double getAmount() { return purchaseAmount; }
	double getDiscount() { return discount; }	
};


void DisplayInfo(CustomerData &p)
{
	std::cout << "\nName: " + p.getFirstName() + " " + p.getLastName() + "\n";
	std::cout << "Address: " + p.getAddress() + " ," + p.getCity() + " ," + p.getState() + " ,";
	printf("%d\n", p.getZip());
	printf("Phone Number:%d\n", p.getphone());
	printf("Customer Id:%d\n", p.getCustomerId());
	if (p.getMailingList() == true)
		std::cout << "You are on the mailing list!" << std::endl;
	else
		std::cout << "You aren't on the mailing list!" << std::endl;

}


void DisplayInfo(preferredCustomer &p)
{
	std::cout << "\nName: " + p.getFirstName() + " " + p.getLastName() + "\n";
	std::cout << "Address: "+p.getAddress()+" ,"+p.getCity()+" ,"+p.getState()+" ,";
	printf("%d\n", p.getZip());
	printf("Phone Number:%d\n",p.getphone());
	printf("Customer Id:%d\n", p.getCustomerId());
	if (p.getMailingList() == true)
		std::cout << "You are on the mailing list!" << std::endl;
	else
		std::cout << "You aren't on the mailing list!" << std::endl;
	printf("The customer paid this much $%.2lf and has this much in discounts %.2lf\n",p.getAmount(),p.getDiscount());
}



int main()
{
	CustomerData p(5778,true,"Jeffrey", "M", "somewhere", "somecity", "somestate", 558, 5775);
	preferredCustomer a(500, 697951, true, "Someone", "Somename", "somewhere", "somecity", "somestate", 5579, 68794);
	DisplayInfo(p);
	p.setMailinglist(false);
	DisplayInfo(p);

	DisplayInfo(a);
	a.setAmount(1000);
	DisplayInfo(a);
	a.setAmount(1500);
	DisplayInfo(a);
	a.setAmount(2000);
	DisplayInfo(a);
}
