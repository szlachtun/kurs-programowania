#include "Szesciokat.hpp"
#include <cmath>

namespace Zadanie {
    Szesciokat::Szesciokat(float a, std::string figureName)
            : mA(a), Figura(std::move(figureName)) {
    }

    float Szesciokat::area() {
        return static_cast<float>((3 * std::pow(mA, 2) * std::sqrt(3)) / 2);
    }

    float Szesciokat::length() {
        return 6 * mA;
    }

    bool Szesciokat::validate(float a) {
        if (a > 0)
            return true;
        else
            return false;
    }

}
