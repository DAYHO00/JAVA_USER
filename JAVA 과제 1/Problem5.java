package study;
import java.util.Scanner;

public class Problem5  {

	public static void choice() {
		System.out.println("--------------------------------------------------------------");
		System.out.println("1. Deposit | 2. Withdrawal | 3. Balance | 4. Exit");
		System.out.println("--------------------------------------------------------------");
	}

	public static long Deposit(long money) {
		long i;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Deposit money : ");
		i = scanner.nextLong();
		return money + i;
	}
	
	public static long Withdrawal(long money) {
		long j;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Withdrawal money : ");
		j = scanner.nextLong();
		if (money >= j)
			return money - j;
		else
		{
			System.out.println("!! Insufficient Balance !!");
			System.out.println("Shortage amount : "+ (j - money));
			return money;
		}
	}
	
	public static void Balance(long money) {
		System.out.println("Balance : " +  money);
	}
	
	public static void Exit()
	{
		System.out.println("\nExit Program");
	}

	public static void main(String[] args) {

		System.out.print("Create your account number : ");
		Scanner scanner = new Scanner(System.in);
		long count = scanner.nextLong();
		
		int k; 
		long money = 0;
		boolean flag = true;
		while(flag)
		{
			choice();
			System.out.print("Input : ");
			k = scanner.nextInt();
			switch(k)
			{
				case 1:
						money = Deposit(money);
						break;
				case 2:
						money = Withdrawal(money);
						break;
				case 3:
						Balance(money);
						break;
				case 4:
						Exit();
						flag = false;
						break;
				default : System.out.println("Error! Check this your choice!!"); break;
			}
		}
	}

}
