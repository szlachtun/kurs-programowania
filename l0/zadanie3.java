public class zadanie3 {
	public static int div(int number) {
		for (int divider = number - 1; divider > 0; divider--) {
			if (number % divider == 0) {
				return divider;
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			try {
				int n = Integer.parseInt(args[i]);
				System.out.println(div(n));
			} catch (NumberFormatException ex) {
				System.out.println(args[i] + "nie jest liczba całkowita");
			}
		}
	}

}
