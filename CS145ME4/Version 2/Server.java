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
			
			//REPLY for MY NAME IS message
			conn.sendMessage("Server: Hello " + conn.getMessage() + "!");
			
			//REPLY for TIME message
			if(conn.getMessage().equals("TIME")){
				conn.sendMessage("Server: The time now is " + date.toString());
			} else {
				conn.sendMessage("Wrong input");
			}

			//REPLY for JOKE TIME message
			if(conn.getMessage().equals("JOKE TIME")){
				String joke = "P1: Knock knock&P2: Who is there?&P1: Ako maba&P2: Ako maba who?&P1: Hahahaha!, mabaho ka LOL!";
				conn.sendMessage(joke);
			} else {
				conn.sendMessage("Wrong input");
			}

			//REPLY for QUIT message
			if(conn.getMessage().equals("QUIT")){
				conn.sendMessage("Server: Goodbye!");
				System.out.println("Client disconnected...");
				System.out.println("Server closed...");
				socket.close();
			} else {
				conn.sendMessage("Wrong input");
			}

			//REPLY for ANY message
				conn.sendMessage("Server: I can't understand what you're saying");

			
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}	
}