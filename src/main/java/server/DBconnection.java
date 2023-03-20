package server;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	public static Connection dbConnection = null;
	private static DBconnection single_instance = null;

	private DBconnection(){

	}

	
	public static DBconnection getInstance() {
		if (single_instance == null) {
			single_instance = new DBconnection();
			getDbConnection();

		}

		return single_instance;

	}
	
	public static Connection getDbConnection() {
		if(dbConnection==null) {
			
		try {
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zoom", "root", "root");
				return dbConnection;
				
			} catch (Exception ex) {
			
				System.out.println(ex.getMessage());
				
			}
		}
			return dbConnection;
		
	}
	
	
	
	
}
