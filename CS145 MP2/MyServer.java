import java.io.*;
import java.net.*;
import java.util.Date;

public class MyServer {

	public static void main(String args[]) {
		try {
			
			ServerSocket server = new ServerSocket(8888);
			Socket socket;
			MyConnection conn;
			Date today = new Date();
			String client_msg = "";
			boolean flag = true;

			while (true) {
	        	System.out.println("Server: Waiting for connection...");
	        	socket = server.accept();
	        	conn = new MyConnection(socket);
	        	System.out.println("Server: Client connected.");
	        	
        		while (true){
        			client_msg = conn.getMessage();
        			System.out.println("Client: " + client_msg);
	       
	        		if (client_msg != null){
	        			sendResponse(client_msg, conn, today);
	        		} else {
	        			break;
	        		}
        		}
	        }

		} catch (Exception e) {
			System.out.println("Server: Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void sendResponse(String client_msg, MyConnection conn, Date today) {
		if (client_msg.toUpperCase().startsWith("GET ")){
			sendMessage(client_msg, conn, today);
		}
	}

	public static void sendMessage(String client_msg, MyConnection conn, Date today) {
		File filename = new File(getFileName(client_msg));
		if (filename.exists()){
			sendFile(filename, conn, today);
		} else {
			sendErrorMsg(conn, today);
		}
	}

	public static String getFileName(String client_msg) {
		String trimmed_msg = client_msg.substring(5,client_msg.length()-9);
		return trimmed_msg;
	}

	public static void sendFile(File filename, MyConnection conn, Date today) {
		conn.sendMessage("HTTP/1.1 200 OK");
		conn.sendMessage("Connection: closed");
		conn.sendMessage("Date: " + today);
		conn.sendMessage("Server: Apache/1.3.0 (Unix)");
		conn.sendMessage("Last-Modified: " + today);
		conn.sendMessage("Content-Length: " + filename.length());
		
		if(filename.getName().endsWith(".jpg")){
			conn.sendMessage("Content-Type: image/jpeg");
			conn.sendMessage("");
			conn.sendBytes(filename);
		}else if(filename.getName().endsWith(".css")){
			conn.sendMessage("Content-Type: text/css");
			conn.sendMessage("");
			conn.sendBytes(filename);
		}else{
			conn.sendMessage("Content-Type: text/html");
			conn.sendMessage("");
			readFile(filename, conn);
		}

		conn.sendMessage("");
		conn.sendMessage("");
		conn.getMessage();
	}

	public static void sendErrorMsg(MyConnection conn, Date today) {
		File filename = new File("error.html");

		conn.sendMessage("HTTP/1.1 404 Not Found");
		conn.sendMessage("Connection closed");
		conn.sendMessage("Date: " + today);
		conn.sendMessage("Server: Apache/1.3.0 (Unix)");
		conn.sendMessage("Last-Modified: " + today);
		conn.sendMessage("Content-Length: " + filename.length());
		conn.sendMessage("Content-Type: text/html");
		conn.sendMessage("");

		readFile(filename, conn);

		conn.sendMessage("");
		conn.sendMessage("");
	}

	public static void readFile(File filename, MyConnection conn) {
		try {
			FileReader file = new FileReader(filename);
			BufferedReader bFileReader = new BufferedReader(file);

			String line = "";
			while((line = bFileReader.readLine()) != null ){
				conn.sendMessage(line);
			}

			bFileReader.close();
		} catch (Exception e) {
			System.out.println("Server: Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}