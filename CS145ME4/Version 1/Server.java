import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {
	public static void main(String args[]) {
		try {

			System.out.println("Starting server...");
			ServerSocket ssocket = new ServerSocket(8888);
			Date date = new Date();

			System.out.println("Waiting for connections...");
			Socket socket = ssocket.accept();
			MyConnection conn = new MyConnection(socket);
			System.out.println("Client connected!");
			conn.sendMessage("Client connected!");
			conn.sendMessage("Server: Hello " + conn.getMessage() + "!");
			conn.sendMessage("Server: The time now is " + date.toString());

			socket.close();
			
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}	
}