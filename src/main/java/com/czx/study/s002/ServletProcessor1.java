package com.czx.study.s002;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletProcessor1 {

	
	
	public void process(Request request,Response response){
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/")+1);
		URLClassLoader loader = null;
		
		File classPath = new File(Constants.WEB_ROOT);
		URLStreamHandler streamHandler = null;
		URL[] urls = new URL[1];
		 try {
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString() ;
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
		 } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		Class myClass = null;
		try {
			loader.loadClass(servletName);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Servlet servlet =null;
		try {
			servlet = (Servlet)myClass.newInstance();
			servlet.service((ServletRequest)request, (ServletResponse)response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
