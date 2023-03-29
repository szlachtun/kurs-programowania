#include "LiczbyPierwszeHeader.hpp"
#include <vector>

LiczbyPierwsze::LiczbyPierwsze(int n) { 
	LiczbyPierwsze::prime_nums.push_back(2);
	for (int i = 2; i <= n; i++) {
		bool is_ok = true;

		for (int index = 0; index < prime_nums.size(); index++){
			if (i % prime_nums[index] == 0){
				is_ok = false;
			}
		}

		if (is_ok){
			prime_nums.push_back(i);
		}
	}
}

int LiczbyPierwsze::liczba(int m){
	return prime_nums[m];
}

int LiczbyPierwsze::count(){
	return prime_nums.size();
}
