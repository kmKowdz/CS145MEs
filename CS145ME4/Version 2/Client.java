import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {
	public static void main(String args[]) {
		try {

			Scanner input = new Scanner(System.in);
			String msg = "";

			System.out.println("Client tries to connect to the server...");

			Socket socket = new Socket("127.0.0.1",8888);
			MyConnection conn = new MyConnection(socket);
			System.out.println(conn.getMessage());
			
			//send MY NAME IS message
			System.out.print("Enter Message: ");
			msg = input.nextLine();
			if (msg.length() > 9 && msg.substring(0,10).equals("MY NAME IS")){
					conn.sendMessage(msg.substring(11));
					conn.sendMessage(msg);
					System.out.println(conn.getMessage());
			} else {
				System.out.println("Wrong input");
			}
			
			//send TIME message
			System.out.print("Enter Message: ");
			msg = input.nextLine();
			conn.sendMessage(msg);
			System.out.println(conn.getMessage());
			
			//send JOKE TIME message
			System.out.print("Enter Message: ");
			msg = input.nextLine();
			conn.sendMessage(msg);
			String joke = conn.getMessage();
			StringTokenizer joke_tokenizer = new StringTokenizer(joke, "&");
			while (joke_tokenizer.hasMoreElements()) {
				System.out.println("Server " + joke_tokenizer.nextElement());
			}

			//send any message
			System.out.print("Enter Message: ");
			msg = input.nextLine();
			conn.sendMessage(msg);
			System.out.println(conn.getMessage());

			//send QUIT message
			System.out.print("Enter Message: ");
			msg = input.nextLine();
			conn.sendMessage(msg);
			System.out.println(conn.getMessage());

			socket.close();

		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}