#pragma once
#include "Czworokat.hpp"

namespace Zadanie {

    class Piecokat : public Figura {
    public:
        explicit Piecokat(float a);

        float area() override;

        float length() override;

        static bool validate(float a);

    private:
        float mA{0.f};
    };
}
