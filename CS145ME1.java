import java.util.Scanner;
import java.util.Random;

public class CS145ME1 {
	public static void main(String args[]) {

		System.out.print("Player 1: ");
		String Player1 = "";
		Scanner choice = new Scanner(System.in);
		Player1 = choice.nextLine();

		System.out.print("Player 2: ");
		String Player2 = "";
		Player2 = choice.nextLine();

		if (Player1.equals("scissors")){
			if (Player2.equals("paper")) {
				System.out.println("Player 1 wins");			
			} else if (Player2.equals("rock")) {
				System.out.println("Player 2 wins");
			} else {
				System.out.println("It's a tie");	
			}
		} else if (Player1.equals("rock")){
			if (Player2.equals("scissors")) {
				System.out.println("Player 1 wins");			
			} else if (Player2.equals("paper")) {
				System.out.println("Player 2 wins");
			} else {
				System.out.println("It's a tie");	
			}
		} else if (Player1.equals("paper")){
			if (Player2.equals("rock")) {
				System.out.println("Player 1 wins");			
			} else if (Player2.equals("scissors")) {
				System.out.println("Player 2 wins");
			} else {
				System.out.println("It's a tie");	
			}
		} else {
			System.out.println("Invalid move.");	
		}

	}
}
