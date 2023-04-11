#include "Piecokat.hpp"
#include <cmath>

namespace Zadanie {
    Piecokat::Piecokat(float a)
            : mA(a) {
    }

    float Piecokat::area() {
        return static_cast<float>(5 * std::pow(mA, 2) * (std::cos(M_PI / 5.0) / std::sin(M_PI / 5.0)) / 4);
    }

    float Piecokat::length() {
        return 5 * mA;
    }

    bool Piecokat::validate(float a) {
        if (a > 0)
            return true;
        else
            return false;
    }

}
