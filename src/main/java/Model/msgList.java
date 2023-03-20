package Model;

import java.util.ArrayList;
import java.util.List;

import client.Messages;

public class msgList {

	public static List<Messages>  MessagesList = new ArrayList<>();
	
	public void addmsg(Messages msg) {
		
		MessagesList.add(msg);
		addmsgDetails addmsgDetails = new addmsgDetails();
		addmsgDetails.addmsgToDatabase(msg);
	}
	
	
}
