package study;

public class Problem1 {
	public static void main(String[] args) {
		int a, sum, i;
		a = 1;
		sum = 0;
		for (i = 0; i < 10; i++) {
			System.out.println("a" + i + "=" + a);
			sum += a;
			a <<= 1;
			a |= 1;
		}
		System.out.println("Sum:" + sum);
	}
}

