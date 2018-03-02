import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {
	public static void main(String args[]) {
		try {

			System.out.println("Starting server...");
			ServerSocket ssocket = new ServerSocket(8888);
			Date date = new Date();
			boolean flag = true;

			System.out.println("Waiting for connections...");
			Socket socket = ssocket.accept();
			MyConnection conn = new MyConnection(socket);
			System.out.println("Client connected!");
			conn.sendMessage("Client connected!");
			
			while(flag){
				String client_msg = conn.getMessage();
				if (client_msg.length() > 10 && client_msg.substring(0,10).equals("MY NAME IS")){
					//REPLY for MY NAME IS message
					conn.sendMessage("Server: Hello " + client_msg.substring(11) + "!");
				} else {
					switch (client_msg) {
						case "TIME":
							//REPLY for TIME message
							conn.sendMessage("Server: The time now is " + date.toString());
							break;
						case "JOKE TIME":
							//REPLY for JOKE TIME message
							String joke = "P1: Knock knock&P2: Who is there?&P1: Ako maba&P2: Ako maba who?&P1: Hahahaha!, mabaho ka LOL!";
							conn.sendMessage(joke);
							break;
						case "QUIT":
							//REPLY for QUIT message
							conn.sendMessage("Server: Goodbye!");
							System.out.println("Client disconnected...");
							System.out.println("Server closed...");
							flag = false;
							break;
						default:
							//REPLY for ANY message
							conn.sendMessage("Server: I can't understand what you're saying");
							break;
					}
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