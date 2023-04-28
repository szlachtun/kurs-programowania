#include "WierszTrojkataPascalaHeader.hpp"
#include <string>
#include <iostream>
#include <stdexcept>

using namespace std;


int main(int argc, char *argv[]){
	try {
		if (stoi(argv[1]) < 0 || stoi(argv[1]) > 12){
			cout << argv[1] << " - Nieprawidłowy numer wiersza" << endl;
			return 0;
		}
	} catch (WrongData) {
		cout << argv[1] << " - Nieprawidłowa dana" << endl;
		return 0;
	} catch (...) {
		cout << argv[1] << " - błędny pierwszy argument" << endl;
		return 0;
	}

	WierszTrojkataPascala* element_list = new WierszTrojkataPascala(stoi((argv[1])));

	for (int arg_num = 2; arg_num < argc; arg_num++) {
		try {
			int element_index = stoi(argv[arg_num]);
			if (!(0 <= element_index && element_index < element_list->ilosc() && element_index <= 12)){
				cout << argv[arg_num] << " - Liczba spoza zakresu" << endl;
			}
			else {
				cout << element_index << " - " << element_list->wspolczynnik(element_index) << endl;
			}

		} catch (WrongData) {
			cout << argv[arg_num] << " - Nieprawidłowa dana" << endl;
		} catch (...) {
			cout << argv[arg_num] << " - ..." << endl;
		}
	}
	delete element_list;
}
