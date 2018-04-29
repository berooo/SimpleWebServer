package webserver;

import java.io.*;
import java.net.*;


public class WebServer {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ServerSocket server=null;
		Socket s=null;
		
		try {
			server=new ServerSocket(8080);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			
			try {
				s=server.accept();
				OutputStream output=s.getOutputStream();
				InputStream input=s.getInputStream();
				
				//����������Ϣ
				DisRequest response=new DisRequest(input);
				String filename=response.getUrl();
				
				//������Ӧ������Ϣ
				DisResponse respons=new DisResponse(output,filename);
				respons.response();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
