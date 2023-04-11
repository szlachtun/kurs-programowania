public class Romb extends Czworokat {
    public Romb(float a, float b, float c, float d, float angle) {
        super(a, b, c, d, angle, "romb");
    }

    static boolean validate(float a, float b, float c, float d, float angle) {
        return a == b && b == c && c == d && 0.f < angle && angle < 90.f;
    }
}
