import java.util.ArrayList;

public class LiczbyPierwsze {
	private ArrayList<Integer> prime_nums = new ArrayList<Integer>();

	LiczbyPierwsze(int n) {
		prime_nums.add(2);
		for (int i = 2; i <= n; i++) {
			boolean is_ok = true;

			for (Integer divider : prime_nums) {
				if (i % divider == 0) {
					is_ok = false;
				}
			}

			if (is_ok){
				prime_nums.add(i);
			}
		}
	}
	
	public int liczba(int m){
		return prime_nums.get(m);
	}
}
