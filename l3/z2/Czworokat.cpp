#include "Czworokat.hpp"
#include <stdexcept>
#include <cmath>

namespace Zadanie {
    Figura::Figura(std::string figureName)
            : mFigureName(std::move(figureName)) {
    }

    std::string Figura::getFigureName() {
        return mFigureName;
    }

    Czworokat::Czworokat(float a, float b, float c, float d, float angle, std::string figureName)
            : mA(a), mB(b), mC(c), mD(d), mAngle(angle), Figura(std::move(figureName)) {
    }

    float Czworokat::area() {
        if (mA == mB && mC == mD) {
            return static_cast<float>(mA * mC * std::sin(M_PI * mAngle / 180.0));
        } else
            throw std::logic_error("Zły czworokąt");
    }

    float Czworokat::length() {
        return mA + mB + mC + mD;
    }

    bool Czworokat::equal(float a, float b) {
        if (a - b <= 0.6f)
            return true;
        else
            return false;
    }

    bool Romb::validate(float a, float b, float c, float d, float angle) {
        if (equal(a, b) && equal(b, c) && equal(c, d) &&
            (std::ceil(angle) >= 1 && std::floor(angle) <= 89))
            return true;
        else
            return false;
    }

    bool Kwadrat::validate(float a, float b, float c, float d, float angle) {
        if (equal(a, b) && equal(b, c) && equal(c, d) && angle == 90.f)
            return true;
        else
            return false;
    }

    bool Prostokat::validate(float a, float b, float c, float d, float angle) {
        if (equal(a, b) && equal(c, d) && angle == 90.f)
            return true;
        else
            return false;
    }
}
