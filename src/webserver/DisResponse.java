package webserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DisResponse {
	
	//响应并请求处理信息
	public OutputStream output;
	public String filename;
	private static final int BUFFER_SIZE=1024;
	
	public DisResponse(OutputStream output,String filename) {
		
		this.output=output;
		this.filename=filename;
	}
	
	public void response() throws IOException{
		
		//获取当前的工作目录
		String path=System.getProperty("user.dir");
		byte[] buffer=new byte[BUFFER_SIZE];
		int ch;
		FileInputStream f=null;
		
		if(path!=null&&filename!=null) {
			File file=new File(path,filename);
			String str="";
			/*必须添加响应头，否则无法以html格式显示内容*/
			if(file.exists()) {
				f=new FileInputStream(file);
				str="HTTP/1.1 200 ok \r\n"+
				"Content-Type:text/html\r\n"+
						"\r\n";
				output.write(str.getBytes());
				ch=f.read(buffer);
				while(ch!=-1) {
					output.write(buffer,0,ch);
					ch=f.read(buffer, 0, BUFFER_SIZE);
				}
				
			}
			else {
				str = "HTTP/1.1 404 File Not Found \r\n" +  
						"Content-Type: text/html\r\n" +  
						"Content-Length: 100\r\n" +  
						"\r\n" +  
					"<h1>404 File Not Found!</h1>"; 
				output.write(str.getBytes());
			}
		}
		output.close();
	}
}
