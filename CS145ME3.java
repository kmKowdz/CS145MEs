import java.util.Scanner;
import java.util.Random;

public class CS145ME3 {
	public static void main(String args[]) {
		
		int hp = 100;
		Random r = new Random();
		int chance,regen;
		String areThereZombies = "Y";

		while(areThereZombies.equals("Y")){

			System.out.println();
			System.out.println("The zombie has " + hp + " hp. What will you do?");
			System.out.println("A. Shoot it");
			System.out.println("B. Grenade it");
			System.out.println("C. Decapitate it");
			System.out.print("Enter choice: ");
			String choice = "";
			Scanner input = new Scanner(System.in);
			choice = input.nextLine();		

			if (choice.equals("A")) {

				System.out.println();
				System.out.println("You shoot the zombie. ");
				regen = r.nextInt(20)+1;
				hp = hp - 10;
				
				System.out.println("It has " + hp + " hp left.");
				hp = hp + regen;

				if (hp > 100){
					hp = 100;
				}

				if(hp <= 0){
					System.out.println("You WIN!");
					areThereZombies = "N";
				} else {
					System.out.println("Oh no! It begins to regenerate. It has " + hp + " hp now.");
					continue;
				}		
								
			} else if (choice.equals("B")) {

				System.out.println();
				System.out.println("You toss a grenade. ");
				chance = r.nextInt(100) + 1;
				
				if(chance < 20) {
					System.out.println("You missed!");
					continue;
				} else {
					hp = hp - 30;
					System.out.println("You hit it! It has " + hp + " hp left.");
					if(hp <= 0){
						System.out.println("You WIN!");
						areThereZombies = "N";
					} else {
						regen = r.nextInt(20)+1;
						hp = hp + regen;

						if (hp > 100){
							hp = 100;
						}

						System.out.println("Oh no! It begins to regenerate. It has " + hp + " hp now.");
						continue;
					}
				}

			} else if (choice.equals("C")){

				System.out.println();
				System.out.println("You choose to cut off the head of the zombie.");
				chance = r.nextInt(100) + 1;

				if(chance <= 50) {
					System.out.println("Oh God! You missed!");
					System.out.println("You're turned into a zombie now.");
					System.out.println("You LOSE!");
					areThereZombies = "N";
				} else {
					hp = hp - hp;
					System.out.println("You hit it. It has " + hp + " hp left.");
					System.out.println("You WIN!");
					areThereZombies = "N";
				}

			} else {

				System.out.println("Invalid input.");

			}
		}

	}
}
