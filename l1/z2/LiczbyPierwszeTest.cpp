#include "LiczbyPierwszeHeader.hpp"
#include <iostream>
#include <stdexcept>

using namespace std;

int main(int argc, char *argv[]){
	try {
		if (stoi(argv[1]) < 2){
			cout << argv[1] << " - Nieprawidłowy zakres" << endl;
			return 0;
		}
	} catch (std::invalid_argument) {
		cout << argv[1] << " - Nieprawidłowa dana" << endl;
			return 0;
	}

	
	LiczbyPierwsze prime_list(stoi(argv[1]));

	for (int arg_num = 2; arg_num < argc; arg_num++) {
		try {
			if (stoi(argv[arg_num]) < 0 || stoi(argv[arg_num]) > prime_list.count() - 1){
				cout << argv[arg_num] << " - Liczba spoza zakresu" << endl;
			}
			else {
				int selected_num = prime_list.liczba(stoi(argv[arg_num]));
				cout << argv[arg_num] << " - " << selected_num << endl;
			}

		} catch (std::invalid_argument) {
			cout << argv[arg_num] << " - Nieprawidłowa dana" << endl;
		}
	}
	return 0;
}
