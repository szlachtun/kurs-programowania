#include <stdexcept>
#include <vector>
#include <string>
#pragma once

class WierszTrojkataPascala {
	private:
  		std::vector<int> line_nums;
	public:
  		int factorial(int n);
  		int wspolczynnik(int m);
  		int ilosc();
  		WierszTrojkataPascala(int n);
  		~WierszTrojkataPascala();
};

class WrongData : std::invalid_argument {
	public:
		WrongData(std::string message) : std::invalid_argument(message.c_str()) {};
};
