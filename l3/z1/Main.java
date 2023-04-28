import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        if (args.length <= 1)
            throw new IllegalArgumentException("Zbyt mało argumentów");

        ArrayList<Float> val = new ArrayList<>();
        for (int i = 1; i < args.length; ++i) {
            val.add(Float.parseFloat(args[i]));
        }

        Figura figura = null;


        if (Objects.equals(args[0], "o") && val.size() == 1) {
            if (Okrag.validate(val.get(0)))
                figura = new Okrag(val.get(0));
            else throw new IllegalArgumentException("Niewiadomy okrąg");
        } else if (Objects.equals(args[0], "c") && val.size() == 5) {
            if (Prostokat.validate(val.get(0), val.get(1), val.get(2), val.get(3), val.get(4))) {
                figura = new Prostokat(val.get(0), val.get(1), val.get(2), val.get(3), val.get(4));
            }
            else if (Romb.validate(val.get(0), val.get(1), val.get(2), val.get(3), val.get(4)))
                figura = new Romb(val.get(0), val.get(1), val.get(2), val.get(3), val.get(4));
            else if (Kwadrat.validate(val.get(0), val.get(1), val.get(2), val.get(3), val.get(4)))
                figura = new Kwadrat(val.get(0), val.get(1), val.get(2), val.get(3), val.get(4));
            else throw new IllegalArgumentException("Niewiadomy czworokąt");
        } else if (Objects.equals(args[0], "p") && val.size() == 1) {
            if (Piecokat.validate(val.get(0)))
                figura = new Piecokat(val.get(0));
            else throw new IllegalArgumentException("Niewiadomy pięciokąt");
        } else if (Objects.equals(args[0], "s") && val.size() == 1) {
            if (Szesciokat.validate(val.get(0)))
                figura = new Szesciokat(val.get(0));
            else throw new IllegalArgumentException("Niewiadomy sześciokąt");
        }

        if (figura != null) {
            System.out.println("Nazwa figury: " + figura.getFigureName());
            System.out.println("Pole figury: " + figura.area());
            System.out.println("Obwód figury: " + figura.length());
        }
    }
}
