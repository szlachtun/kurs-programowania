#pragma once

#include <string>
#include <utility>

namespace Zadanie {
    class Figura {
    public:
        explicit Figura(std::string figureName);

        virtual float area() = 0;

        virtual float length() = 0;

        std::string getFigureName();

    private:
        std::string mFigureName{};
    };

    class Czworokat : public Figura {
    public:
        Czworokat(float a, float b, float c, float d, float angle, std::string figureName);

        float area() override;

        float length() override;

        static bool equal(float a, float b);

    private:
        float mA{0};
        float mB{0};
        float mC{0};
        float mD{0};
        float mAngle{0.f};
    };

    class Romb : public Czworokat {
    public:
        Romb(float a, float angle) : Czworokat(a, a, a, a, angle, "romb") {}

        float area() override { return Czworokat::area(); }

        float length() override { return Czworokat::length(); }

        static bool validate(float a, float b, float c, float d, float angle);
    };

    class Kwadrat : public Czworokat {
    public:
        explicit Kwadrat(float a) : Czworokat(a, a, a, a, 90.f, "kwadrat") {}

        float area() override { return Czworokat::area(); }

        float length() override { return Czworokat::length(); }

        static bool validate(float a, float b, float c, float d, float angle);
    };

    class Prostokat : public Czworokat {
    public:
        Prostokat(float a, float b) : Czworokat(a, a, b, b, 90.f, "prostokąt") {}

        float area() override { return Czworokat::area(); }

        float length() override { return Czworokat::length(); }

        static bool validate(float a, float b, float c, float d, float angle);
    };
}
