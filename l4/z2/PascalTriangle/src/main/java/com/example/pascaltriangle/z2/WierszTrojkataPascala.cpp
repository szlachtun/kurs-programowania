#include "WierszTrojkataPascalaHeader.hpp"
#include <stdexcept>

WierszTrojkataPascala::WierszTrojkataPascala (int n) {
	for (int i = 0; i <= n; i++) {
		line_nums.push_back(factorial(n) / (factorial(i) * factorial(n - i)));
	}
}

WierszTrojkataPascala::~WierszTrojkataPascala() = default;

int WierszTrojkataPascala::wspolczynnik(int m) {
	return line_nums[m];
}

int WierszTrojkataPascala::ilosc() {
	return line_nums.size();
}

int WierszTrojkataPascala::factorial (int n) {
	if (n == 0) {
		return 1;
	} else if (n > 0) {
		int result = 1;
		for (int i = n; i >= 1; i--) {
			result *= i;
		}
		return result;
	}
	else {
		throw std::invalid_argument("negative number factorial \n");
	}
}
