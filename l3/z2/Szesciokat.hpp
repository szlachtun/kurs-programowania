#pragma once

#include "Czworokat.hpp"

namespace Zadanie {
    class Szesciokat : public Figura {
    public:
        explicit Szesciokat(float a, std::string figureName = "sześciokąt");

        float area() override;

        float length() override;

        static bool validate(float a);

    private:
        float mA{0.f};
    };
}
