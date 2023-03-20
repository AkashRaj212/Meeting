package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.user;
import server.DBconnection;

public class updateUsersDetails {

	public void updateUsers(user client) {
		
		
		DBconnection.getInstance();
		PreparedStatement pt;
		try {
			pt = DBconnection.dbConnection.prepareStatement("update user_in set peerId = ?,roomId = ? , videoId = ? where sessionId = ? ");
			pt.setString(1, client.getPeerId());
			pt.setString(2, client.getRoomId());
			pt.setString(3, client.getVideoId());
			pt.setString(4, client.getSessionId());
			int results = pt.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
	
		
		
	}
	
	
	
	
}
