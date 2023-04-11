#pragma once
#include "Czworokat.hpp"

namespace Zadanie {

    class Okrag : public Figura {
    public:
        explicit Okrag(float r);

        float area() override;

        float length() override;

        static bool validate(float r);

    private:
        float mR{0};
    };
}
