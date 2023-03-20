package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import client.Messages;
import client.user;
import server.DBconnection;

public class LoadUser {
	
public void LoadcurrentUser(String sessionId) {
	
	
		DBconnection.getInstance();
		PreparedStatement pt;
		try {
			pt = DBconnection.dbConnection.prepareStatement("select * from user_in where sessionId = ?");
			pt.setString(1, sessionId);
		
			ResultSet resultset = pt.executeQuery();
			if(resultset.next()) {
				
				user currentlyLoggedIn = new user(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getString(5));
				userInList.getCurrentlyLoggedInUser().put(sessionId, currentlyLoggedIn);
				
				LoadMsg loadMsg = new LoadMsg(currentlyLoggedIn);
				loadMsg.start();
			}
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
	
}

}
