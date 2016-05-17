package com.czx.study.s002;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {

	public static void main(String[] args) {
		int port = 8090;
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			while(true){
				Socket socket = null;
				InputStream input = null;
				OutputStream output = null;
				socket = server.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				
				Request request = new Request(input);
				request.parse();
				
				Response response = new Response(output);
				response.setRequest(request);
				
				String uri = request.getUri();
				if(uri.contains("servlet")){
					ServletProcessor1 processor = new ServletProcessor1();
					processor.process(request, response);
				}else{
					StaticResourceProcessor processor = new StaticResourceProcessor();
					processor.process(request, response);
				}
				socket.close();
			}
		} catch (Exception e) {
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
