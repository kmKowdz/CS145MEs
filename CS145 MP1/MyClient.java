import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MyClient {
	public static void main(String args[]) {
		try {

			Scanner input = new Scanner(System.in);
			boolean flag = true;
			String server_path = "";
			String client_path = System.getProperty("user.dir");

			System.out.println("Client tries to connect to the server...");

			Socket socket = new Socket("127.0.0.1",8888);
			MyConnection conn = new MyConnection(socket);
			System.out.println(conn.getMessage());

			while(flag){
				String msg = "";
				System.out.println();
				System.out.print("Enter Message: ");
				msg = input.nextLine();

				if (msg.length() > 2 && msg.substring(0,3).equals("GET")) {
					conn.sendMessage(msg);
					conn.sendMessage(server_path);
					conn.sendMessage(client_path);
					System.out.println(conn.getMessage());
				} else if (msg.length() > 2 && msg.substring(0,2).equals("CD")) {
					System.out.println();
					conn.sendMessage(msg);
					String status = conn.getMessage();
					if (status.equals("OK")) {
						System.out.print(conn.getMessage());
						server_path = conn.getMessage();
						System.out.println(server_path);
					} else {
						System.out.println(status);
					}
				} else if (msg.equals("CD ..")) {
					conn.sendMessage(msg);
					System.out.println(conn.getMessage());
					server_path = conn.getMessage();
				} else if (msg.equals("LS")) {
					System.out.println();
					conn.sendMessage(msg);
					String server_msg = conn.getMessage();
					StringTokenizer list_tokenizer = new StringTokenizer(server_msg, "&");
					while (list_tokenizer.hasMoreElements()) {
						System.out.println(list_tokenizer.nextElement());
					}					
				} else if (msg.equals("QUIT")){
					System.out.println();
					conn.sendMessage(msg);
					System.out.println(conn.getMessage());
					flag = false;
				} else if (msg.equals("PWD")){
					System.out.println();
					conn.sendMessage(msg);
					server_path = conn.getMessage();
					System.out.println(conn.getMessage());
				} else {
					System.out.println();
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