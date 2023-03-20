package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import client.user;
import server.DBconnection;

public class addUserDetails {

	
	public void addUser(user client) {
		DBconnection.getInstance();
		PreparedStatement pt;
		try {
			pt = DBconnection.dbConnection.prepareStatement("insert into user_in values(?,?,?,?,?)");
			pt.setString(1, client.getSessionId());
			pt.setString(2, client.getPeerId());
			pt.setString(3, client.getUsername());
			pt.setString(4, client.getRoomId());
			pt.setString(5, client.getVideoId());
			
			int results = pt.executeUpdate();
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
