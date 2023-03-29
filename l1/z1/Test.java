public class Test {

	public static void main(String args[]) {
		try {
			if (Integer.parseInt(args[0]) < 2) {
				System.out.println(args[0] + " - Nieprawidłowy zakres");
			}
		} catch (NumberFormatException ex) {
			System.out.println(args[0] + " - nieprawidłowa dana");
		} finally {
			System.exit(0);
		}

		LiczbyPierwsze prime_list = new LiczbyPierwsze(Integer.parseInt(args[0]));

		for (int i = 1; i < args.length; i++) {
			try {
				int selected_num = prime_list.liczba(Integer.parseInt(args[i]));
				System.out.println(args[i] + " - " + selected_num);
			} catch (IndexOutOfBoundsException ex) {
				System.out.println(args[i] + " - liczba spoza zakresu");
			} catch (NumberFormatException ex) {
				System.out.println(args[i] + " - nieprawidłowa dana");
			}
		}
	}
}
