package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.protocol.Message;

import client.Messages;
import client.user;
import server.DBconnection;

public class addmsgDetails {

	public void addmsgToDatabase(Messages msg) {
		DBconnection.getInstance();
		PreparedStatement pt;
		try {
			pt = DBconnection.dbConnection.prepareStatement("insert into chat_messages values(?,?,?,?,?,?,?)");
			pt.setString(1, msg.getSenderSessionId());
			pt.setString(2, msg.getUsername());
			pt.setString(3, msg.getMessage());
			pt.setString(4, msg.getTime());
			pt.setString(5, msg.getRoomId());
			pt.setString(6,msg.getRecievername());
			pt.setString(7, msg.getRecieverId());
			
			
			int results = pt.executeUpdate();
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
