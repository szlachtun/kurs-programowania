public class Szesciokat extends Figura{
    Szesciokat(float a) {
        this.mA = a;
        this.mFigureName = "sześciokąt";
    }

    @Override
    public float area() {
        return (float)((3 * Math.pow(mA, 2) * Math.sqrt(3)) / 2);
    }

    @Override
    public float length() {
        return 6 * mA;
    }

    static boolean validate(float a) {
        return a > 0;
    }

    public String getFigureName() {
        return mFigureName;
    }


    private final float mA;

    private final String mFigureName;
}
