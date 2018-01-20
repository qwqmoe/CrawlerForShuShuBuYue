package com.crawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.mysql.cj.xdevapi.JsonArray;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SaveToDB {
    Map<String, Object> map=new HashMap<String, Object>();
	public Map<String, Object> connectDB(String username,String password,String url) {
		Connection conn = null;
        Statement stmt = null;
            try {
                // 注册 JDBC 驱动
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 打开链接
	            System.out.println("连接数据库...");
	            conn = DriverManager.getConnection(url,username,password);
	            stmt = conn.createStatement();
	            //删除表
	            String dropTableSQl="DROP TABLE IF EXISTS info;";
	            stmt.execute(dropTableSQl);
	            //创建表
	            String createTabelSql="CREATE TABLE info"+
	            					"(id INTEGER NOT NULL AUTO_INCREMENT,"+
	            					"lostDate VARCHAR(255),"+
	            					"wechat VARCHAR(255),"+
	            					"title VARCHAR(255),"+
	            					"article VARCHAR(255),"+
	            					"question VARCHAR(255),"+
	            					"PRIMARY KEY(id))";
	            stmt.executeUpdate(createTabelSql);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            map.put("stmt", stmt);
            map.put("conn", conn);
        return map;
	}
	
	public void excuteSQL(Statement stmt, JSONArray jsonArray) throws SQLException {
		for(int i=0;i<jsonArray.size();i++) {
			JSONObject jsonObject=(JSONObject) jsonArray.get(i);
			Iterator it=jsonObject.keys();
			String lostDate=(String) jsonObject.get("lostDate");
			String wechat=(String) jsonObject.get("wechat");
			String title=(String) jsonObject.get("title");
			String article=(String) jsonObject.get("article");
			String question=(String) jsonObject.get("question");
            //插入数据
            String insertSql="INSERT INTO info(id,lostDate,wechat,title,article,question) VALUES("+null+",'"+lostDate+"','"+wechat+"','"+
            				title+"','"+article+"','"+question+"')";
            stmt.executeUpdate(insertSql);
		}
	}
	public void closeDB(Statement stmt,Connection conn) {
		try{
            if(stmt!=null) stmt.close();
        }catch(SQLException se2){
        }
        try{
            if(conn!=null) conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
	}
	
//
//	public void save(String username,String password,String url,JSONArray jsonArray) {
//		Connection conn = null;
//        Statement stmt = null;
//        try{
//            // 注册 JDBC 驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            // 打开链接
//            System.out.println("连接数据库...");
//            conn = DriverManager.getConnection(url,username,password);
//            stmt = conn.createStatement();
//            
//            //删除表
//            String dropTableSQl="DROP TABLE IF EXISTS info;";
//            stmt.execute(dropTableSQl);
//            
//            //创建表
//            String createTabelSql="CREATE TABLE info"+
//            					"(id INTEGER NOT NULL AUTO_INCREMENT,"+
//            					"lostDate VARCHAR(255),"+
//            					"wechat VARCHAR(255),"+
//            					"title VARCHAR(255),"+
//            					"article VARCHAR(255),"+
//            					"question VARCHAR(255),"+
//            					"PRIMARY KEY(id))";
//            stmt.executeUpdate(createTabelSql);
//            
//    		for(int i=0;i<jsonArray.size();i++) {
//				JSONObject jsonObject=(JSONObject) jsonArray.get(i);
//				Iterator it=jsonObject.keys();
//				//while(it.hasNext()) {
//					//String key=it.next().toString();
//					//System.out.print(""+key);
//					//System.out.println(":"+jsonObject.get(key));
//				
//				String lostDate=(String) jsonObject.get("lostDate");
//				String wechat=(String) jsonObject.get("wechat");
//				String title=(String) jsonObject.get("title");
//				String article=(String) jsonObject.get("article");
//				String question=(String) jsonObject.get("question");
//		            //插入数据
//		            String insertSql="INSERT INTO info(id,lostDate,wechat,title,article,question) VALUES("+null+",'"+lostDate+"','"+wechat+"','"+
//		            				title+"','"+article+"','"+question+"')";
//		            stmt.executeUpdate(insertSql);
//				//}
//    		}
//
//            // 完成后关闭
//            stmt.close();
//            conn.close();
//        }catch(SQLException se){
//            // 处理 JDBC 错误
//            se.printStackTrace();
//        }catch(Exception e){
//            // 处理 Class.forName 错误
//            e.printStackTrace();
//        }finally{
//            // 关闭资源
//            try{
//                if(stmt!=null) stmt.close();
//            }catch(SQLException se2){
//            }// 什么都不做
//            try{
//                if(conn!=null) conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }
//        }
//        System.out.println("关闭数据库!");
//	}
	
}
