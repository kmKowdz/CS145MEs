import java.io.*;
import java.net.*;

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
			System.out.println("Server: Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String msg) {
		try {
			this.out.println(msg);
			this.out.flush();
		} catch (Exception e) {
			System.out.println("Server: Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
			//return false;
		}
	}

	public void sendBytes(File file) {
		try {
			
			BufferedInputStream bInputStream = new BufferedInputStream(new FileInputStream(file));
	        OutputStream output = this.s.getOutputStream();

			byte[] bytes = new byte[4096];
            int bytesRead;

            while ((bytesRead = bInputStream.read(bytes)) != -1) {
        		output.write(bytes, 0, bytesRead);
        		output.flush();
            }
		} catch (Exception e) {
			System.out.println("Server: Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public String getMessage() {
		try {
			String msg = "";
			msg = this.in.readLine();
			return msg;
		} catch (Exception e) {
			System.out.println("Server: Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}

	
}








