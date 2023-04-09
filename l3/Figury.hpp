#pragma once
#include <stdexcept>

namespace Zadanie {
class Figura
{
public:
    virtual float field() = 0;
    virtual float length() = 0;
};

class Czworokat: public Figura
{
public:
    Czworokat(float a, float b, float c, float d, float angle);

    float field();
    float length();

private:
    float mAngle {0.f};
    float mA {0.f};
    float mB {0.f};
    float mC {0.f};
    float mD {0.f};
};

class Romb: public Czworokat
{
public:
    Romb(float a, float b, float c, float d, float angle) : Czworokat(a, b, c, d, angle) {}

    float field() {return Czworokat::field();}
    float length() {return Czworokat::length();}
};

class Kwadrat: public Czworokat
{
public:
    Kwadrat(float a, float b, float c, float d, float angle);
private:
    float mAngle {0.f};
    float mA {0.f};
    float mB {0.f};
    float mC {0.f};
    float mD {0.f};
};

class Prostokat: public Czworokat
{
public:
    Prostokat(float a, float b, float c, float d, float angle);
private:
    float mAngle {0.f};
    float mA {0.f};
    float mB {0.f};
    float mC {0.f};
    float mD {0.f};
};



}
