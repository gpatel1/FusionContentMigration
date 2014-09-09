package com.discovery.restapi.helper;

import java.sql.*;

public class DatabaseTasks {

	protected static String dbUrl=null;
	public static Connection conn = null;
	public static String WhereToRun = null;
	public static String dbusername = null;
	public static String dbpassword = null;

		public static Connection GetDBConnection(String dbname){
					WhereToRun =UtilMethods.GetValueFromPropFile("selected_environment");
					if(WhereToRun.contains("qa")){
						dbUrl = "jdbc:mysql://"+WhereToRun+".dp.discovery.com:3306/"+dbname;
						dbusername=UtilMethods.GetValueFromPropFile("qa_db_username");
						dbpassword=UtilMethods.GetValueFromPropFile("qa_db_password");
					}
					else if (WhereToRun.contains("staging")){
						dbUrl = "jdbc:mysql://"+WhereToRun+".dp.discovery.com:3306/"+dbname;
						dbusername=UtilMethods.GetValueFromPropFile("staging_db_username");
						dbpassword=UtilMethods.GetValueFromPropFile("staging_db_password");
					}
					else if (WhereToRun.contains("production")){
						dbUrl = "jdbc:mysql://"+WhereToRun+".dp.discovery.com:3306/"+dbname;
						dbusername=UtilMethods.GetValueFromPropFile("prod_db_username");
						dbpassword=UtilMethods.GetValueFromPropFile("prod_db_password");
					}
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						//conn = DriverManager.getConnection (dbUrl,"qa","92nao2901");
						conn = DriverManager.getConnection (dbUrl,dbusername,dbpassword);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return conn;			
		}
}