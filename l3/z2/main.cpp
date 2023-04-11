#include <iostream>
#include <vector>
#include <cmath>

#include "Czworokat.hpp"
#include "Okrag.hpp"
#include "Piecokat.hpp"
#include "Szesciokat.hpp"

int main(int argc, char *argv[]) {
    if (argc <= 2)  // brak option-ów
        throw std::logic_error("Zbyt mało argumentów");

    std::vector<float> val{};
    for (int i = 2; i < argc; ++i) {
        val.push_back(std::atof(argv[i]));
    }
    Zadanie::Figura *figura{};

    if (*argv[1] == 'o' && val.size() == 1) {
        if (Zadanie::Okrag::validate(val[0]))
            figura = new Zadanie::Okrag(val[0], "okrag");
    } else if (*argv[1] == 'c' && val.size() == 5) {
        if (Zadanie::Romb::validate(val[0], val[1], val[2], val[3], val[4]))
            figura = new Zadanie::Romb(val[0], val[4]);
        else if (Zadanie::Kwadrat::validate(val[0], val[1], val[2], val[3], val[4]))
            figura = new Zadanie::Kwadrat(val[0]);
        else if (Zadanie::Prostokat::validate(val[0], val[1], val[2], val[3], val[4]))
            figura = new Zadanie::Prostokat(val[0], val[2]);
        else
            throw std::logic_error("Niewiadomy czworokąt");
    } else if (*argv[1] == 'p' && val.size() == 1) {
        if (Zadanie::Piecokat::validate(val[0]))
            figura = new Zadanie::Piecokat(val[0]);
    } else if (*argv[1] == 's' && val.size() == 1) {
        if (Zadanie::Szesciokat::validate(val[0]))
            figura = new Zadanie::Szesciokat(val[0]);
    } else
        throw std::logic_error("Niewiadoma figura");

    if (figura) {
        std::cout << "Nazwa figury: " << figura->getFigureName() << std::endl;
        std::cout << "Pole figury: " << figura->area() << std::endl;
        std::cout << "Obwód figury: " << figura->length() << std::endl;
    } else
        throw std::logic_error("Figura nie została utworzona");

    return 0;
}
