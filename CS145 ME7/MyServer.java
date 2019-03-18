import java.io.*;
import java.net.*;

public class MyServer {
	public static void main(String args[]) {
		try {

			System.out.println("Starting server...");
			ServerSocket ssocket = new ServerSocket(8888);

			System.out.println("Waiting for connection...");
			Socket socket = ssocket.accept();
			MyConnection conn = new MyConnection(socket);
			System.out.println("Client connected!");

			String client_msg = conn.getMessage();

			//REPLY for GET message
			if(client_msg.length() > 2 && client_msg.substring(0,3).equals("GET")) {
				
				String trimmed_msg = client_msg.substring(5,client_msg.length()-9);
				File filename = new File(trimmed_msg);

				if(filename.exists()){
					
					conn.sendMessage("HTTP/1.1 200 OK");
					conn.sendMessage("Connection closed");
					conn.sendMessage("Date: Thu, 19 Apr 2018 12:00:00 GMT");
					conn.sendMessage("Server: Apache/1.3.0 (Unix)");
					conn.sendMessage("Last-Modified: Thu, 19 Apr 2018 12:00:00 GMT");
					conn.sendMessage("Content-Length: 50");
					conn.sendMessage("Content-Type: text/html");
					conn.sendMessage("");

					InputStream is = new FileInputStream(filename);
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader in = new BufferedReader(isr);

					String msg = "";
					while(msg!=null){
						conn.sendMessage(in.readLine());
					}
					in.close();

					conn.sendMessage("");
					conn.sendMessage("");

				} else {

					conn.sendMessage("HTTP/1.1 404 Not Found");
					conn.sendMessage("Connection closed");
					conn.sendMessage("Date: Thu, 19 Apr 2018 12:00:00 GMT");
					conn.sendMessage("Server: Apache/1.3.0 (Unix)");
					conn.sendMessage("Last-Modified: Thu, 19 Apr 2018 12:00:00 GMT");
					conn.sendMessage("Content-Length: 50");
					conn.sendMessage("Content-Type: text/html");

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