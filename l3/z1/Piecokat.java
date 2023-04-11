public class Piecokat extends Figura{
    Piecokat(float a) {
        this.mA = a;
        this.mFigureName = "pięciokąt";
    }

    @Override
    public float area() {
        return (float)(5 * Math.pow(mA, 2) * (Math.cos(Math.PI / 5.0) / Math.sin(Math.PI / 5.0)) / 4);
    }

    @Override
    public float length() {
        return 5 * mA;
    }

    static boolean validate(float a) {
        return a > 0;
    }

    public String getFigureName() {
        return mFigureName;
    }


    private float mA = 0.f;

    private String mFigureName = "";
}
