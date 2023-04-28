import java.util.ArrayList;

public class WierszTrojkataPascala {
	private ArrayList<Integer> line_nums = new ArrayList<Integer>();

	WierszTrojkataPascala(int n) {
		for (int i = 0; i <= n; i++) {
			line_nums.add(factorial(n) / (factorial(i) * factorial(n - i)));
		}
	}

	public int factorial(int n) {
		try {
			assert (n >= 0);
			if (n == 0) {
				return 1;
			} else {
				int result = 1;
				for (int i = n; i >= 1; i--) {
					result *= i;
				}
				return result;
			}
		} catch (AssertionError ex) {
			throw new FactorialNegativeNumberException();
		}
	}

	public int wspolczynnik(int m) throws WrongElementIndexException {
		try {
			return line_nums.get(m);
		} catch (IndexOutOfBoundsException ex) {
			throw new WrongElementIndexException();
		}
	}
}
