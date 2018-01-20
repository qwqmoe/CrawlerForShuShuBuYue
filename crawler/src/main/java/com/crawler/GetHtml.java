package com.crawler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
/**
 * 输入链接爬取网页代码
 * @author Dream
 *
 */
public class GetHtml {

	public String getHtml(String urlString) throws IOException {
		URL url = new URL(urlString);  
        // 打开连接  
        URLConnection con = url.openConnection();  
		InputStream inputStream=con.getInputStream();
		// 1K的数据缓冲  
        byte[] bs = new byte[1024];  
        // 读取到的数据长度  
        int len;  
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        while ((len = inputStream.read(bs)) != -1) {  
            outputStream.write(bs, 0, len);  
         } 
        outputStream.close();
        byte date[]=outputStream.toByteArray();
        String html=new String(date,"utf-8");
		return html;
	}
	
}
