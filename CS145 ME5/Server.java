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
				if (client_msg.length() > 9 && client_msg.substring(0,10).equals("MY NAME IS")){
					//REPLY for MY NAME IS message
					System.out.println("GREETINGS sent.");
					conn.sendMessage("Server: Hello " + client_msg.substring(11) + "!");
				} else if(client_msg.length() > 2 && client_msg.substring(0,3).equals("GET")) {
					//REPLY for GET message
					File fp = new File(client_msg.substring(4)); 
					if (fp.isFile()) {
						InputStream is = new FileInputStream(fp);
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader in = new BufferedReader(isr);

						conn.sendMessage("Server: The file exist.");
						conn.sendMessage("Server: Contents of the file are as follows...");
						System.out.println("Notification sent to the client.");

						String msg = "";
						while (msg != null) {
							msg = in.readLine();
							conn.sendMessage(msg);
						}
						in.close();
					} else {
						conn.sendMessage("Server: The file requested does not exist.");
						System.out.println("Notification sent to the client.");
					}
				} else {
					switch (client_msg) {
						case "TIME": //REPLY for TIME message
							System.out.println("TIME request sent.");
							conn.sendMessage("Server: The time now is " + date.toString());
							break;
						case "JOKE TIME": //REPLY for JOKE TIME message
							System.out.println("JOKE request sent.");
							String joke = "P1: Knock knock&P2: Who is there?&P1: Ako maba&P2: Ako maba who?&P1: Hahahaha!, mabaho ka LOL!";
							conn.sendMessage(joke);
							break;
						case "QUIT": //REPLY for QUIT message
							conn.sendMessage("Server: Goodbye!");
							System.out.println("Client disconnected.");
							System.out.println("Server closed.");
							flag = false;
							break;
						case "LS": //REPLY for LS message
							System.out.println("LIST OF FILE request sent.");
							String server_msg = "";
							String path = System.getProperty("user.dir");
							System.out.println(path);
							File directory = new File(path);
							String list[] = directory.list();
							String list_length = Integer.toString(list.length);
							for (int i = 0; i < list.length; i++) {
								server_msg = server_msg + list[i] + "&";
							}
							conn.sendMessage(server_msg);
							break;
						default: //REPLY for ANY message
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