import java.lang.Math;

public class Czworokat extends Figura {
    public Czworokat(float a, float b, float c, float d, float angle, String figureName) {
        this.mA = a;
        this.mB = b;
        this.mC = c;
        this.mD = d;
        this.mAngle = angle;
        this.mFigureName = figureName;
    }

    @Override
    public float area() {
        if (mA == mB && mC == mD) {
            return (float) (mA * mC * Math.sin(Math.PI * mAngle / 180));
        } else {
            throw new ArithmeticException("To nie czworokąt\n");
        }

    }

    @Override
    public float length() {
        return mA + mB + mC + mD;
    }

    public String getFigureName() {
        return mFigureName;
    }

    private float mA = 0.f;
    private float mB = 0.f;
    private float mC = 0.f;
    private float mD = 0.f;
    private float mAngle = 0.f;
    private String mFigureName = "";
}
