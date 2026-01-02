package study;

public class Problem2 {
	public static void main(String[] args) {
		int i, j, k;
		int num = 0;
		for (i = 16; i > 0; i -= 2) {

			for (j = 0; j < num; j++)
				System.out.print(" ");

			for (k = 0; k < i; k++)
				System.out.print("*");
			System.out.println();
			num++;
		}
		for (i = 2; i <= 16; i += 2) {

			for (j = num - 1; j > 0; j--)
				System.out.print(" ");

			for (k = 0; k < i; k++)
				System.out.print("*");
			System.out.println();
			num--;
		}

	}

}

