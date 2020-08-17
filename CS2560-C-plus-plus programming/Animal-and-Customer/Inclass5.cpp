// Inclass5.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include "pch.h"
#include <iostream>
#include <cstdio>
#include <string>

class Animal {
	std::string _name;
	std::string _type;
	std::string _sound;
	// private constructor prevents construction of base class
	Animal() {};
protected:
	// protected constructor for use by derived classes
	Animal(const std::string & n, const std::string & t, const std::string & s)
		: _name(n), _type(t), _sound(s) {
		printf("Animal Constructed\n");
	}
public:
	void speak() const;
	const std::string & name() const { return _name; }
	const std::string & type() const { return _type; }
	const std::string & sound() const { return _sound; }
	std::string snap() { return "Animal_Snap"; }     //redefine same signature
	virtual std::string move() = 0;  //pure virtual function
};

void Animal::speak() const {
	printf("%s the %s says %s\n", _name.c_str(), _type.c_str(), _sound.c_str());
}

// Dog class - derived from Animal
class Dog : public Animal {
	int walked;
public:
	Dog(std::string n) : Animal(n, "dog", "woof"), walked(0) {
		printf("Dog Constructed\n");
	};
	int walk() { return ++walked; }
	std::string snap() { return "Dog_Snap"; }    //redefine same signature but, I can still call as it was statically linked
	virtual std::string move() { return "Dog_Move"; }
};

// Cat class - derived from Animal
class Cat : public Animal {
	int petted;
public:
	Cat(std::string n) : Animal(n, "cat", "meow"), petted(0) {
		printf("Cat Constructed\n");
	};
	int pet() { return ++petted; }
	virtual std::string move() { return "Cat_Move"; }
};

class Donkey :public Animal {
	int carried;
public:
	Donkey(std::string n) : Animal(n, "donkey", "hee-haw"), carried(0) {
		printf("Donkey constructed\n");
	};
	int carry() { return ++carried; }
	virtual std::string move() { return "Donkey_Move"; }
};

// Pig class - derived from Animal
class Pig : public Animal {
	int fed;
public:
	Pig(std::string n) : Animal(n, "pig", "oink"), fed(0) {
		printf("Pig Constructed\n");
	};
	int feed() { return ++fed; }
	virtual std::string move() { return "Pig_Move"; }
};

//Poly
std::string displayName(const Animal &A) {
	return A.name();
}


int main(int argc, char ** argv) {
	Dog d("Rover");
	Cat c("Fluffy");
	Pig p("Arnold");
	Donkey donk("Eeyore");

	//Poly  
	//calling a specific ANIMAL -> a derived class of base
	printf("%s\n", displayName(d).c_str());
	printf("%s\n", displayName(c).c_str());
	printf("%s\n", displayName(p).c_str());

	//d.snap()  //who get called?
	printf("%s\n", d.snap().c_str());
	printf("%s\n", d.Animal::snap().c_str());

	//virtual function calls
	printf("%s\n", d.move().c_str());
	printf("%s\n", c.move().c_str());
	printf("%s\n", p.move().c_str());


	d.speak();
	c.speak();
	p.speak();
	donk.speak();

	printf("the dog has been walked %d times\n", d.walk());
	printf("the cat has been petted %d times\n", c.pet());
	printf("the pig has been fed %d times\n", p.feed());
	printf("the Donkey has been carried %d times\n", donk.carry());
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
