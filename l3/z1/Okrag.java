public class Okrag extends Figura{

    Okrag(float r) {
        this.mR = r;
        this.mFiugreName = "okrąg";
    }

    @Override
    public float area() {
        return (float)(2 * Math.PI * (mR * mR));
    }

    @Override
    public float length() {
        return (float)(2 * Math.PI * mR);
    }

    public String getFigureName() {
        return mFigureName;
    }
    static boolean validate(float r) {
        return r > 0;
    }

    private float mR = 0.f;
    private String mFigureName = "";
}
