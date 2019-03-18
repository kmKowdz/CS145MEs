import java.io.*;
import java.net.*;

// this class simplifies the connection setup
// see note below on how to use

public class MyConnection {
	Socket s;
	BufferedReader in;
	PrintWriter out;
	
	public MyConnection(Socket s) {
		try {
			this.s = s;
			this.in = new BufferedReader(
				new InputStreamReader(
				s.getInputStream()));
			this.out = new PrintWriter(
					new OutputStreamWriter(
					s.getOutputStream()));
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public boolean sendMessage(String msg) {
		try {
			this.out.println(msg);
			this.out.flush();
			return true;
		} catch (Exception e) {
			System.out.println("Something bad happend!");
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	
	public String getMessage() {
		try {
			String msg = "";
			msg = this.in.readLine();
			return msg;
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}
	
	// how to use:
	
	// 1. setup Socket as usual
	// Server: Socket socket = ssocket.accept();
	// Client: Socket socket = new Socket("127.0.0.1", 8888);
	
	// 2. Initialize MyConnection 
	// all commands now same for both client and server
	// MyConnection conn = new MyConnection(socket);
	
	// 3. To send a message (talk):
	// conn.sendMessage("my message");
	
	// 4. To receive a message (listen):
	// String msg = "";
	// msg = conn.getMessage();  
	// note: When client is talking, server must be listening
	//       When server is talking, client must be listening
	// note: program will pause on conn.getMessage() until a message does arrive
	//       from the other party
	
	
}








