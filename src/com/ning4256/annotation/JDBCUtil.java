package com.ning4256.annotation;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class JDBCUtil {
	public static String DeriverClass;
	public static String url;
	public static String user;
	public static String pass;
	
	//读取jdbc.xml文件，把数据放入数据库配置信息变量
	
	static{
		SAXReader loader = new SAXReader();
		Document document;
		try {
			//利用类加载器去获得文件绝对路径
			String urlPath = JDBCUtil.class.getClassLoader().getResource("jdbc.xml").getPath();
//			System.out.println(urlPath);
			document = loader.read(new File(urlPath));
			DeriverClass=document.selectSingleNode("/jdbc-config/named-config[@name='myjdbc']/property[@name='driverClass']").getText();
			url=document.selectSingleNode("/jdbc-config/named-config[@name='myjdbc']/property[@name='jdbcUrl']").getText();
			user=document.selectSingleNode("/jdbc-config/named-config[@name='myjdbc']/property[@name='user']").getText();
			pass=document.selectSingleNode("/jdbc-config/named-config[@name='myjdbc']/property[@name='password']").getText();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static Connection connection = null;
	public static Connection getConnection(){
		
		try {
			Class.forName(DeriverClass);
			connection = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	public static void close(Connection con, PreparedStatement state, ResultSet set) throws SQLException {
		connection.close();
		
	}
	
	
}