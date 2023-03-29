#include <vector>
# pragma once

class LiczbyPierwsze {
	private:
		std::vector<int> prime_nums;
	public:
		int liczba(int m);
		int count();
		LiczbyPierwsze(int n);
};
