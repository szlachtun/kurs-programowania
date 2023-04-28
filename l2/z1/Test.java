public class Test {
	public static void main(String args[]) {
		try {
			if (Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[0]) > 12) {
				System.out.println(args[0] + " - Nieprawidłowy numer wiersza");
				System.exit(0);
			}
		} catch (NumberFormatException ex) {
			System.out.println(args[0] + " - nieprawidłowa dana");
			System.exit(0);
		}

		WierszTrojkataPascala line_nums = new WierszTrojkataPascala(Integer.parseInt(args[0]));

		for (int i = 1; i < args.length; i++) {
			try {
				try {
					int selected_num = line_nums.wspolczynnik(Integer.parseInt(args[i]));
					System.out.println(args[i] + " - " + selected_num);
				} catch (FactorialNegativeNumberException ex){
					System.out.println(args[i] + " - liczba spoza zakresu");
				} 
			} catch (WrongElementIndexException ex) {
				System.out.println(args[i] + " - liczba spoza zakresu");
			} catch (NumberFormatException ex) {
				System.out.println(args[i] + " - nieprawidłowa dana");
			}
		}

	}
}
