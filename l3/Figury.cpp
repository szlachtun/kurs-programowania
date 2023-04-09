#include "Figury.hpp"
#include <cmath>

namespace Zadanie {

    Czworokat::Czworokat(float a, float b, float c, float d, float angle)
        : mA(a)
        , mB(b)
        , mC(c)
        , mD(d)
        , mAngle(angle)
    {
    }
    float Czworokat::field()
    {
        if (mA == mB && mC == mD) {
            return mA * mC * std::sin(mAngle);
        } 
        else
            throw std::logic_error("");
    }

    float Czworokat::length()
    {
        return mA + mB + mC + mD;
    }

    
}

