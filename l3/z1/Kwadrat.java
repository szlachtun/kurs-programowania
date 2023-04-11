public class Kwadrat extends Czworokat {

    public Kwadrat(float a, float b, float c, float d, float angle) {
        super(a, b, c, d, angle, "kwadrat");
    }

    static boolean validate(float a, float b, float c, float d, float angle) {
        return a == b && b == c && c == d && angle == 90.f;
    }
}