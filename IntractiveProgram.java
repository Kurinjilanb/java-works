import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class IntractiveProgram {
	static String name;
	static int sec, digits;
	static int num;
	static int dnumber;
	static int range;
	static String autoPilot;
	static int attempts = 0, score = 0, fail = 0;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		IntractiveProgram in = new IntractiveProgram();

		try {

			in.play();

		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void play() throws InterruptedException {

		Random r = new Random();
		char option = 0;

		System.out.printf("\n Enter the Name : ");
		name = sc.nextLine();

		System.out.printf("\n Enter the Digits : ");
		digits = sc.nextInt();

		System.out.printf("\n Enter the Seconds : ");
		sec = sc.nextInt();

		System.out.printf("\n Auto pilot mode [Yes/No]: ");
		autoPilot = sc.next().toLowerCase();

		if (autoPilot.equals("yes")) {
			range = FindDigits(digits);
			autoPilotMOde();

		}
		else {

			// calling FindDigits function to set the range to generate random number (if digits count 5 function will return 10000)
			range = FindDigits(digits);

			do {
				// Counting Number of attempts
				attempts++;

				// Generating Random numbers with starting range and ending range(if digit count 5 starting range 10000 to 99999)
				num = r.nextInt(range / 10, range - 1);

				System.out.printf("\n I will be shown %d for %d seconds, then I will be prompted for to enter the number. ", num, sec);
				Thread.sleep(sec * 1000);

				// Calling Function to clear the screen
				clears();

				System.out.printf("\n\n Please enter displayed Number here : ");
				dnumber = sc.nextInt();
				clears();

				if (dnumber == num) {
					score++;
				}
				else {
					fail++;
				}

				System.out.printf("\n Do You Want to Continue ? [Yes/No] : ");
				option = sc.next().charAt(0);
			}
			while (option == 'Y' || option == 'y');

			clears();

			System.out.printf("\n Player Name    : %s", name);
			System.out.printf("\n No of Attempts : %d", attempts);
			System.out.printf("\n Total Scores   : %d", score);
			System.out.printf("\n       Failed   : %d", fail);
		}

	}

	private static int FindDigits(int n) {

		int num = 1;
		while (n > 0) {
			num *= 10;
			n--;
		}
		return num;

	}

	// function to clear terminal
	public static void clears() {

		try {

			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//function to call auto pilot mode
	public static void autoPilotMOde() throws InterruptedException {
		byte count = 0;
		boolean check = true;
		Random r = new Random();

		do {

			// Counting Number of attempts
			attempts++;
			// Generating Random numbers with starting range and ending range(if digit count 5 starting range 10000 to 99999)
			num = r.nextInt(range / 10, range - 1);

			System.out.printf("\n I will be shown %d for %d seconds, then I will be prompted for to enter the number. ", num, sec);

			// Possible to throw InterruptedException hence function declared withInterruptedException
			Thread.sleep(sec * 1000);
			// Calling Function to clear the screen
			clears();

			System.out.printf("\n\n Please enter displayed Number here : ");
			dnumber = sc.nextInt();
			clears();

			if (dnumber == num) {
				count++;
			} else {
				fail++;

			}
			if (count == 3) {
				
					digits += 1;
					count = 0;
					range = FindDigits(digits);
					clears();
			}
			else if (fail == 3) {

				System.out.printf("\n Do You Want to Continue ? [Yes/No] : ");
				char m = sc.next().charAt(0);

				if (m == 'n'||m =='N') {
					check = false;
				}
				else {
					digits -= 1;
					fail = 0;
					range = FindDigits(digits);
					clears();
				}

			}
		} while (check);
	}

}