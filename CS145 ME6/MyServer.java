import java.io.*;
import java.net.*;
import java.util.Date;

public class MyServer {
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
			//conn.sendMessage("");
			
			File filename = new File("index.html");
			InputStream is = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);

			String client_msg = conn.getMessage();
			if(client_msg.length() > 2 && client_msg.substring(0,3).equals("GET")) {
					
				//REPLY for GET message
				conn.sendMessage("HTTP/1.1 200 OK");
				conn.sendMessage("Connection close");
				conn.sendMessage("Date: Mon, 11 Aug 2018 12:00:00 GMT");
				conn.sendMessage("Server: Apache/1.3.0 (Unix)");
				conn.sendMessage("Last-Modified: Mon, 16 Aug 2018 12:00:00 GMT");
				conn.sendMessage("Content-Length: 49");
				conn.sendMessage("Content-Type: text/html");
				conn.sendMessage("");
				
				String msg = "";
				while(msg!=null){
					conn.sendMessage(in.readLine());
				}
				in.close();

				conn.sendMessage("");
				conn.sendMessage("");			
			}

			socket.close();

		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}	
}