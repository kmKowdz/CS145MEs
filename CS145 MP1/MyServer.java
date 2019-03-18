import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MyServer {
	public static void main(String args[]) {
		try {

			System.out.println("Starting server...");
			ServerSocket ssocket = new ServerSocket(8888);
			boolean flag = true;
			String path = "";

			System.out.println("Waiting for connections...");
			Socket socket = ssocket.accept();
			MyConnection conn = new MyConnection(socket);
			System.out.println("Client connected!");
			conn.sendMessage("Client connected!");
			
			while(flag){
				String client_msg = conn.getMessage();
				if (client_msg.length() > 2 && client_msg.substring(0,3).equals("GET")) {
					System.out.println("Request received.");
					String filename = client_msg.substring(5,client_msg.length()-1);
					path = conn.getMessage();
					String client_path = conn.getMessage();
					File src_file = new File(path + "\\" + filename);
					File dest_file = new File(client_path + "\\" + filename);

					//check if the file exists at the server
					if(src_file.exists() && src_file.isFile()){
						if (src_file.getName().endsWith(".txt")) {
							//check if the file exists at client side
							if(dest_file.exists() && dest_file.isFile()){
								conn.sendMessage("Server: The destination already has a file named \"" + dest_file.getName() +"\".");
								System.out.println("Response sent.");
							} else {
								InputStream is = null;
								OutputStream os = null;
								try {
								   	is = new FileInputStream(src_file);
								   	os = new FileOutputStream(dest_file);
								   	byte[] buffer = new byte[1024];
								   	int length;
								   	while ((length = is.read(buffer)) > 0) {
								      	os.write(buffer, 0, length);
								   	}
								} finally {
								   	is.close();
								   	os.close();
								}
								conn.sendMessage("Server: The file you requested has been succesfully copied.");
								System.out.println("Response sent.");
							}
						} else {
							conn.sendMessage("Server: The file you requested is not a text file.");
							System.out.println("Response sent.");
						}
					} else {
						conn.sendMessage("Server: The file you requested does not exist.");
						System.out.println("Response sent.");
					}
				} else if (client_msg.length() > 2 && client_msg.substring(0,2).equals("CD")){
					//REPLY for CD message
					System.out.println("Request received.");
					if(client_msg.substring(3,4).equals("\"") && client_msg.substring(client_msg.length()-1).equals("\"")) {
						path = System.getProperty("user.dir");
						File file = new File(path + "\\" + client_msg.substring(4, client_msg.length()-1));
						if(client_msg.length() > 4 ) {
							if (file.exists() && file.isDirectory()){
								conn.sendMessage("OK");
								System.setProperty("user.dir", path + "\\" + client_msg.substring(4, client_msg.length()-1));
								conn.sendMessage("Server: ");
								conn.sendMessage(path + "\\" + client_msg.substring(4, client_msg.length()-1));
								System.out.println("Response sent.");
							} else {
								conn.sendMessage("Server: The directory does not exist.");
								System.out.println("Response sent.");
							}
						} else {
							conn.sendMessage("Server: Wrong input.");
							System.out.println("Response sent.");
						}
					} else if (client_msg.equals("CD ..")) {
						System.out.println("Request received.");
						path = System.getProperty("user.dir");
						StringTokenizer tokenizer = new StringTokenizer(path, "\\");
						ArrayList<String> parts = new ArrayList<String>();
						int j = 0;
						while (tokenizer.hasMoreTokens()) {
							parts.add(tokenizer.nextToken());
							j++;
						}
						path = "";
						for (int i = 0; i < j-1; i++) {
							path = path + (parts.get(i)) + "\\";
						}
						path = path.substring(0, path.length()-1);
						System.setProperty("user.dir", path);
						path = System.getProperty("user.dir");
						conn.sendMessage("Server: " + path);
						conn.sendMessage(path);
						System.out.println("Response sent.");
					} else {
						conn.sendMessage("Server: Wrong input.");
						System.out.println("Response sent.");
					}
				} else {
					switch (client_msg) {
						
						case "PWD": //REPLY for PWD message
							System.out.println("Request received.");
							String server_dir = System.getProperty("user.dir");
							conn.sendMessage(server_dir);
							conn.sendMessage("Server: " + server_dir);
							System.out.println("Response sent.");
							break;
						case "LS": //REPLY for LS message
							System.out.println("Request received.");
							String server_msg = "";
							path = System.getProperty("user.dir");
							File file = new File(path);
							File[] list = file.listFiles();
							for (int i = 0; i < list.length; i++) {
								if (list[i].isFile()) {
						        	server_msg = server_msg + "Server: [File] " + list[i].getName() + "&";
						     	} else if (list[i].isDirectory()) {
						        	server_msg = server_msg + "Server: [Directory] " + list[i].getName() + "&";
						      	}
							}
							conn.sendMessage(server_msg);
							System.out.println("Response sent.");
							break;
						case "QUIT": //REPLY for QUIT message
							conn.sendMessage("Server: Goodbye!");
							System.out.println("Client disconnected.");
							System.out.println("Server closed.");
							flag = false;
							break;
						default: //REPLY for ANY message
							conn.sendMessage("Server: I can't understand what you're saying");
							System.out.println("Response sent.");
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