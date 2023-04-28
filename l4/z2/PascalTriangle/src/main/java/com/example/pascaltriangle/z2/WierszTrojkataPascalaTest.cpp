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

    for (int i = 0; i <= stoi(argv[1]); i++) {

        cout << element_list->wspolczynnik(i);

        if (i != stoi(argv[1])) {
            cout << " ";
        } else {
            cout << endl;
        }
        
    }
	delete element_list;
}
