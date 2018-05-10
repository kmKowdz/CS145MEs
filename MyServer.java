import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MyServer {
	public static void main(String args[]) {
		try {

			System.out.println("Starting server...");
			System.out.println("Waiting for connections...");

			ServerSocket ssocket = new ServerSocket(8888);
			Socket socket = ssocket.accept();
			MyConnection conn = new MyConnection(socket);
			boolean flag = true;
			String client_msg = "";
			String path = "";
			String up_one_dir = "";

			System.out.println("Client connected!");
			conn.sendMessage("Server: Client connected!");

			while(true){
				client_msg = conn.getMessage();
				path = System.getProperty("user.dir");
				System.out.println("Client message: " + client_msg);
				if(client_msg.equals("PWD")){
					conn.sendMessage("pwd");
					conn.sendMessage("Server: " + path);						
				}else if(client_msg.substring(0,2).equals("CD")){
					conn.sendMessage("cd");
					if (client_msg.equals("CD ..")){
						StringTokenizer tokenizer = new StringTokenizer(path, "\\");
						ArrayList<String> parts = new ArrayList<String>();
						int j = 0;
						while (tokenizer.hasMoreTokens()) {
							parts.add(tokenizer.nextToken());
						 	j++;
						}
						for (int i = 0; i < j-1; i++) {
							up_one_dir = up_one_dir + (parts.get(i)) + "\\";
						}
						up_one_dir = up_one_dir.substring(0, up_one_dir.length()-1);
						System.setProperty("user.dir", up_one_dir);
						conn.sendMessage("Server: " + up_one_dir);
					}else if(client_msg.substring(3,4).equals("\"")){
						File file = new File(path + "\\" + client_msg.substring(4, client_msg.length()-1));
						if (file.exists() && file.isDirectory()){
							System.setProperty("user.dir", path + "\\" + client_msg.substring(4, client_msg.length()-1));
							conn.sendMessage("Server: " + System.getProperty("user.dir"));
						} else {
							conn.sendMessage("Server: The directory does not exist.");
						}
					}else{
						conn.sendMessage("Server: Wrong input. (e.g. CD .. or CD \"Directory\")");
					}
				}else if(client_msg.equals("LS")){
					File file = new File(path);
					File[] list = file.listFiles();
					conn.sendMessage("ls");
					for (int i = 0; i < list.length; i++) {
						if (list[i].isFile()) {
							conn.sendMessage("Server: [File] " + list[i].getName());
					   	} else if (list[i].isDirectory()) {
					   		conn.sendMessage("Server: [Directory] " + list[i].getName());
					   	}
					}
					conn.sendMessage("");
				}else if(client_msg.length() > 5 && client_msg.substring(0,3).equals("GET")){
					conn.sendMessage("get");
					if (client_msg.substring(3,5).equals(" \"")){
						String filename = client_msg.substring(5,client_msg.length()-1);
						File file = new File(path + "\\" + filename);
						if (file.exists() && file.isFile() && file.getName().endsWith(".txt")){
							conn.sendMessage("OK");
							conn.sendMessage(filename);

							InputStream is = new FileInputStream(filename);
							InputStreamReader isr = new InputStreamReader(is);
							BufferedReader in = new BufferedReader(isr);
							
							String msg = "";
							while((msg = in.readLine()) != null ){
								conn.sendMessage(msg);
							}
							conn.sendMessage("end-of-file");
							in.close();
							conn.sendMessage("Server: File successfully copied.");
						} else {
							conn.sendMessage("Server: The file you are trying to access does not exist.");
						}
					}else{
						conn.sendMessage("Server: Wrong input. (e.g. GET \"Filename.extension\")");
					}
				}else if(client_msg.equals("QUIT")){
					System.out.println("Client disconnected.");
					System.out.println("Server closed.");
					conn.sendMessage("quit");
					conn.sendMessage("Server: Goodbye!");
					break;
				}else{
					conn.sendMessage("");
					conn.sendMessage("Server: Sorry, I can't understand what you're saying");
				}
				System.out.println("Response sent.");
			}

			socket.close();
		} catch (Exception e) {
			System.out.println("Something bad happened!");
			System.out.println(e);
			e.printStackTrace();
		}
	}	
}