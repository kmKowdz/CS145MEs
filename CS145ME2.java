import java.util.Scanner;
import java.util.Random;

public class CS145ME2 {
	public static void main(String args[]) {

		System.out.print("Enter month: ");
		String month = "";
		Scanner input = new Scanner(System.in);
		month = input.nextLine();

		System.out.print("Enter day: ");
		int day = 0;
		day = Integer.parseInt(input.nextLine());
		
		if (month.toUpperCase().equals("JAN")){
			if (day < 20) {
				System.out.println("Capricorn");
			} else if (day <= 31) {
				System.out.println("Aquarius");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("FEB")){
			if (day < 19) {
				System.out.println("Aquarius");
			} else if (day <= 29){
				System.out.println("Pisces");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("MAR")){
			if (day < 21) {
				System.out.println("Pisces");
			} else if (day <= 31){
				System.out.println("Aries");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("APR")){
			if (day < 20) {
				System.out.println("Aries");
			} else if (day <= 30) {
				System.out.println("Taurus");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("MAY")){
			if (day < 21) {
				System.out.println("Taurus");
			} else if (day <= 31) {
				System.out.println("Gemini");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("JUN")){
			if (day < 21) {
				System.out.println("Gemini");
			} else if (day <= 30) {
				System.out.println("Cancer");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("JUL")){
			if (day < 23) {
				System.out.println("Cancer");
			} else if (day <= 31){
				System.out.println("Leo");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("AUG")){
			if (day < 23) {
				System.out.println("Leo");
			} else if (day <= 31) {
				System.out.println("Virgo");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("SEPT")){
			if (day < 23) {
				System.out.println("Virgo");
			} else if (day <= 30) {
				System.out.println("Libra");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("OCT")){
			if (day < 23) {
				System.out.println("Libra");
			} else if (day <= 31) {
				System.out.println("Scorpio");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("NOV")){
			if (day < 22) {
				System.out.println("Scorpio");
			} else if (day <= 30) {
				System.out.println("Sagittarius");
			} else {
				System.out.println("Invalid input");
			}
		} else if (month.toUpperCase().equals("DEC")){
			if (day < 22) {
				System.out.println("Sagittarius");
			} else if (day <= 31) {
				System.out.println("Capricorn");
			} else {
				System.out.println("Invalid input");
			}
		} else {
			System.out.println("Invalid input");
		}
	}
}
