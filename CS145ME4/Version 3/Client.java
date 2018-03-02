import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {
	public static void main(String args[]) {
		try {

			Scanner input = new Scanner(System.in);
			boolean flag = true;

			System.out.println("Client tries to connect to the server...");

			Socket socket = new Socket("127.0.0.1",8888);
			MyConnection conn = new MyConnection(socket);
			System.out.println(conn.getMessage());

			while(flag){
				String msg = "";
				System.out.print("Enter Message: ");
				msg = input.nextLine();

				if(msg.equals("JOKE TIME")){
					conn.sendMessage(msg);
					String joke = conn.getMessage();
					StringTokenizer joke_tokenizer = new StringTokenizer(joke, "&");
					while (joke_tokenizer.hasMoreElements()) {
						System.out.println("Server " + joke_tokenizer.nextElement());
					}
				} else if (msg.equals("QUIT")){
					conn.sendMessage(msg);
					System.out.println(conn.getMessage());
					flag = false;
				} else {
					conn.sendMessage(msg);
					System.out.println(conn.getMessage());
				}
			}

			socket.close();

		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}