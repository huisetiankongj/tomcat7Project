package com.czx.study.s001;

import java.io.IOException;
import java.io.InputStream;

public class Request {
	
	private InputStream input;
	
	private String uri;
	public Request(){
		
	}
	
	public Request(InputStream input){
		this.input = input;
	}
	
	public void parse(){
		System.out.println("-----------------");
		StringBuilder request = new StringBuilder();
		byte[] buffer = new byte[2048];
		int i;
		try {
			i = input.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i=-1;
		}
		
		for (int j=0; j<i; j++) {
	      request.append((char) buffer[j]);
	    }
		
		System.out.print(request.toString());
		uri = parseUri(request.toString());
	}
	
	private String parseUri(String requestString){
		int index1, index2;
	    index1 = requestString.indexOf(' ');
	    if (index1 != -1) {
	      index2 = requestString.indexOf(' ', index1 + 1);
	      if (index2 > index1)
	        return requestString.substring(index1 + 1, index2);
	    }
	    return null;
	}

	public String getUri() {
		return uri;
	}

	
}
