package com.czx.study.s001;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Administrator
 *
 */
public class HttpServer {

	public static final String WEB_ROOT =
			    System.getProperty("user.dir") + File.separator  + "webroot";

	// shutdown command
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	private static boolean shutDown = false;
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}
	
	
	public void await(){
		int port = 8090;
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			while(!shutDown){
				Socket socket = server.accept();
				InputStream input = socket.getInputStream();
				OutputStream output = socket.getOutputStream();
				
				Request request = new Request(input);
				request.parse();
				
				Response response = new Response();
				response.setOutput(output);
				response.setRequest(request);
				response.sendStaticResource();
				
				// Close the socket
		        socket.close();
	
		        //check if the previous URI is a shutdown command
	//		     shutDown = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
