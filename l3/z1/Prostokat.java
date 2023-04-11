public class Prostokat extends Czworokat {
    public Prostokat(float a, float b, float c, float d, float angle) {
        super(a, b, c, d, angle, "prostokąt");
    }

    static boolean validate(float a, float b, float c, float d, float angle) {
        return a == b && c == d && angle == 90.f;
    }
}
