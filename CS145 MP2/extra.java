
MyConnection conn = new MyConnection(socket);
			Date today = new Date();
			String client_msg = "";
//System.out.println("Waiting for connection...");

System.out.println("response-------");

	            //REPLY for GET message
				//if(client_msg.length() > 2 && client_msg.substring(0,3).equals("GET")) {
	

				//client_msg = conn.getMessage();
	        	//System.out.println(client_msg);

				String trimmed_msg = client_msg.substring(5,client_msg.length()-9);
				File filename = new File(trimmed_msg);

				if(filename.exists()){
					if (filename.getName().endsWith(".html")){
						//this is for html part
		            	conn.sendMessage("HTTP/1.1 200 OK");
						conn.sendMessage("Connection closed");
						conn.sendMessage("Date: " + today);
						conn.sendMessage("Server: Apache/1.3.0 (Unix)");
						conn.sendMessage("Last-Modified: " + today);
						conn.sendMessage("Content-Length: " + filename.length());
						conn.sendMessage("Content-Type: text/html");
						conn.sendMessage("");

						//print html part
		            	System.out.println("HTTP/1.1 200 OK");
						System.out.println("Connection closed");
						System.out.println("Date: " + today);
						System.out.println("Server: Apache/1.3.0 (Unix)");
						System.out.println("Last-Modified: " + today);
						System.out.println("Content-Length: " + filename.length());
						System.out.println("Content-Type: text/html");
						System.out.println("");


						InputStream is = new FileInputStream(filename);
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader in = new BufferedReader(isr);

						String msg = "";
						while((msg = in.readLine()) != null ){
							conn.sendMessage(msg);
							System.out.println(msg);
						}
						in.close();

						conn.sendMessage("");
						conn.sendMessage("");
					} else if (filename.getName().endsWith(".css")){
						//this is for html part
		            	conn.sendMessage("HTTP/1.1 200 OK");
						conn.sendMessage("Connection closed");
						conn.sendMessage("Date: " + today);
						conn.sendMessage("Server: Apache/1.3.0 (Unix)");
						conn.sendMessage("Last-Modified: " + today);
						conn.sendMessage("Content-Length: " + filename.length());
						conn.sendMessage("Content-Type: text/html");
						conn.sendMessage("");

						InputStream is = new FileInputStream(filename);
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader in = new BufferedReader(isr);

						String msg = "";
						while((msg = in.readLine()) != null ){
							conn.sendMessage(msg);
						}
						in.close();

						conn.sendMessage("");
						conn.sendMessage("");
					} else {
						break;
					}
					//for image:
					// use byte array; create a method for getBytes() and sendBytes() hint: available sya online , data inputstreamreader
		        } else {
		        	conn.sendMessage("HTTP/1.1 404 Not Found");
					conn.sendMessage("Connection closed");
					conn.sendMessage("Date: " + today);
					conn.sendMessage("Server: Apache/1.3.0 (Unix)");

					System.out.println("HTTP/1.1 404 Not Found");
					System.out.println("Connection closed");
					System.out.println("Date: " + today);
					System.out.println("Server: Apache/1.3.0 (Unix)");
	        	}