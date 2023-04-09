#include <iostream>
#include <stdexcept>
#include "Figury.hpp"

int main(int argc, char* argv[])
{
    if (argc <= 2)
        throw std::logic_error("Zbyt mało argumentów");
    
    if (*argv[1] == 'o' && argc == 4)  // okrąg
        return 0;

    else if (*argv[1] == 'c' && argc == 8)  // czworokat
        if (*argv[2] == *argv[3] == *argv[4] == *argv[5] && 0.f < static_cast<float>(*argv[6] < 90.f)){

            Zadanie::Romb *figura = new Zadanie::Romb(1, 1, 1, 1, 1);

            std::cout << figura->length();
            std::cout << figura->field();
        }



            // Jak było:
            // Zadanie::Romb figura = Zadanie::Romb(1.0, 2.0, 3.0, 4.0, 5.0);

    // else if (*argv[1] == 'p' && argc == 4)  // pieciokat
    //     return 0;
    // else if (*argv[1] == 's' && argc == 4)  // szesciokat
    //     return 0;
    // else
    //     throw std::logic_error("Zły rodzaj figury");

    return 0;
}
