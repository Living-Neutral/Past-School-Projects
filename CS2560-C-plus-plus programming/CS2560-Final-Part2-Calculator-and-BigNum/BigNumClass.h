#ifndef BIGNUMCLASS_H
#define BIGNUMCLASS_H
#pragma once
#include "stdafx.h"


	class BigNumClass {
	
	
	public:
		 BigNumClass operator +(const BigNumClass& Bg);
		 BigNumClass operator -(const BigNumClass& Bg);
		 BigNumClass operator *(const BigNumClass& Bg);
		 BigNumClass operator /(const BigNumClass& Bg);
		 BigNumClass operator %(const BigNumClass& Bg);
	};


#endif