package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import client.Messages;
import client.user;
import server.DBconnection;

public class LoadMsg extends Thread{

	user currentlyLoggedIn;

	public LoadMsg(user currentlyLoggedIn) {
		super();
		this.currentlyLoggedIn = currentlyLoggedIn;
	}
	
	@Override
	public void run() {
		
		DBconnection.getInstance();
		PreparedStatement pt;
		try {
			pt = DBconnection.dbConnection.prepareStatement("select * from chat_messages where senderSessionId = ? or recieverSessionId = ? or recieverSessionId = ?");
			pt.setString(1, currentlyLoggedIn.getSessionId());
			pt.setString(2, currentlyLoggedIn.getSessionId());
			pt.setString(3, "everyone");
			
			ResultSet resultset = pt.executeQuery();
			while(resultset.next()) {
				Messages msg = new Messages(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getString(5),resultset.getString(6),resultset.getString(7));
			    currentlyLoggedIn.getMyMessages().add(msg);
			   
			}
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
}
