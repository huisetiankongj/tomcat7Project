package com.czx.study.s002;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse{

	private final int BUFFER_SIZE = 1024;
	
	private Request request;
	private OutputStream output;
	private PrintWriter writer;
	
	public Response(OutputStream output) {
	    this.output = output;
	}
	
	public void setRequest(Request request) {
		this.request = request;
	}

	public void sendStaticResource() throws IOException{
		byte[] buffer = new byte[BUFFER_SIZE];
		File file =  new File(Constants.WEB_ROOT,request.getUri());
		FileInputStream fis =null;
		try {
			fis = new FileInputStream(file);
			if(file.exists()){
				int ch = fis.read(buffer);
				while(ch!=-1){
					output.write(buffer);
					ch = fis.read(buffer);
				}
				
			}
		} catch (FileNotFoundException e) {
			String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
				        "Content-Type: text/html\r\n" +
				        "Content-Length: 23\r\n" +
				        "\r\n" +
				        "<h1>File Not Found</h1>";
			output.write(errorMessage.getBytes());
		}finally{
			output.flush();
			output.close();
			if (fis!=null)
		        fis.close();
		}
		
	}


	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public PrintWriter getWriter() throws IOException {
		PrintWriter writer = new PrintWriter(output,true);
		return writer;
	}

	public void setCharacterEncoding(String charset) {
		// TODO Auto-generated method stub
		
	}

	public void setContentLength(int len) {
		// TODO Auto-generated method stub
		
	}

	public void setContentType(String type) {
		// TODO Auto-generated method stub
		
	}

	public void setBufferSize(int size) {
		// TODO Auto-generated method stub
		
	}

	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub
		
	}

	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

}
