package com.crawler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public class App 
{
	static String html;
	static String dbUrl="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String username="root";
	static String password="root";
	static String urlString="http://118.190.174.130:3101/lostFound/20";
    public static void main( String[] args )
    {
    	SaveToDB saveToDB=new SaveToDB();
    	Map<String, Object> map=saveToDB.connectDB(username, password, dbUrl);
    	Statement stmt=(Statement) map.get("stmt");
    	Connection conn=(Connection) map.get("conn");
    	for(int i=0;i<10000;i+=20) {
    		System.out.println("即将插入第"+i+"条数据");
	    	GetHtml getHtml =new GetHtml();
	    	try {
				html = getHtml.getHtml(urlString);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        StringToJson stringToJson = new StringToJson();
	        JSONArray jsonArray= stringToJson.stringToJson(html);
	        try {
				saveToDB.excuteSQL(stmt, jsonArray);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	saveToDB.closeDB(stmt, conn);
    	System.out.println("执行完毕");
    }
    
    
}
