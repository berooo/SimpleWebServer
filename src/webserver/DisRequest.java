package webserver;

import java.io.IOException;
import java.io.InputStream;

public class DisRequest {
	
	InputStream input;
	
	public DisRequest(InputStream input) {
		this.input=input;
	}
	
	public String getUrl() {
		
		String rcontent=null,str=null;
		StringBuffer request=new StringBuffer();
		byte[] buffer=new byte[2048];
		int  i=0;
		
		try {
			//读取内容并存到Buffer数组中，并返回读取的字节数
			i=input.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			i=-1;
		}
		
		for(int k=0;k<i;k++) {
			request.append((char)buffer[k]);
		}
		
		rcontent=request.toString();
		String result=getFilename(rcontent);
		return result;
		
	}
	//获取文件名
	public String getFilename(String content) {
		int a,b;
		a=content.indexOf(' ');
		if(a!=-1) {
			
			b=content.indexOf('?',a+1);
			if(b==-1)b=content.indexOf(' ',a+1);
			return content.substring(a+2,b);
		}
		return null; 
	}
}
