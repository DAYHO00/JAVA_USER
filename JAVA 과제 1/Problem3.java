package study;

public class Problem3 {
	public static void main(String[] args) {
		String S = "XXXXOXXXX";
		int len, num;
		len = S.length();
		while (true) {
			num = (int) (len * Math.random());
			String S1 = S.substring(num, num + 1);
			if (S1.equals("O")) {
				System.out.println("random number is " + num + ", hit");
				break;
			} else
				System.out.println("random number is " + num + ", miss");
		}

	}
}
