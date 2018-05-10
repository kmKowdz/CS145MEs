import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
	public static void main(String args[]) {
		try {
			System.out.println("Client tries to connect to the server...");
			Socket socket = new Socket("127.0.0.1",8888);
			MyConnection conn = new MyConnection(socket);
			System.out.println(conn.getMessage());
			Scanner input = new Scanner(System.in);
			String msg = "";
			String server_msg = "";
			boolean flag = true;

			while(true){
				msg = "";
				System.out.println();
				System.out.print("Enter Message: ");
				msg = input.nextLine();
				
				if(msg != null){
					conn.sendMessage(msg);
					server_msg = conn.getMessage();
					if(server_msg.equals("pwd")){
						System.out.println(conn.getMessage());
					}else if(server_msg.equals("cd")){
						System.out.println(conn.getMessage());
					}else if(server_msg.equals("ls")){
						while(true){
							server_msg = conn.getMessage();
							if (server_msg.equals("")){
								break;
							}
							System.out.println(server_msg);
						}
					}else if(server_msg.equals("get")){
						
						server_msg = conn.getMessage();
						if (server_msg.equals("OK")){
							File file = new File(conn.getMessage());
							if(!file.exists()){ 
								OutputStream os = new FileOutputStream(file);
								OutputStreamWriter osr = new OutputStreamWriter(os);
								PrintWriter loc_file = new PrintWriter(osr);

								while (true) {
									server_msg = conn.getMessage();
									if (server_msg.equals("end-of-file")){
										break;
									}
									loc_file.println(server_msg);
									loc_file.flush();
								}
								loc_file.close();
								System.out.println(conn.getMessage());
							} else {
								System.out.println("The file already exists. Process aborted.");
							}
						} else {
							System.out.println(server_msg);
						}
					}else if(server_msg.equals("quit")){
						System.out.println(conn.getMessage());
						break;
					}else{
						System.out.println(conn.getMessage());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}