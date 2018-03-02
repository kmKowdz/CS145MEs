import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Calendar;
import java.util.TimeZone;
//import java.util.Date;


public class Client {
	public static void main(String args[]) {
		try {

			Scanner input = new Scanner(System.in);
			String msg = "";

			System.out.println("Client tries to connect to the server...");

			Socket socket = new Socket("127.0.0.1",8888);
			MyConnection conn = new MyConnection(socket);
			System.out.println(conn.getMessage());
			System.out.print("Enter Message: ");
			msg = input.nextLine();

			conn.sendMessage(msg.substring(11));
			System.out.println(conn.getMessage());

			System.out.print("Enter Message: ");
			msg = input.nextLine();
			conn.sendMessage(msg);
			System.out.println(conn.getMessage());
			
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}