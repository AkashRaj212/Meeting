package Model;

import java.util.ArrayList;
import java.util.HashMap;

import client.user;

public class userInList {

	public static HashMap<String, user> currentlyLoggedInUserList =new HashMap<>();
	
	
	public void userIn(String sessionId,user client) {
		currentlyLoggedInUserList.put(sessionId,client);
		addUserDetails addUser = new addUserDetails();
		addUser.addUser(client);
	}


	public static HashMap<String, user> getCurrentlyLoggedInUser() {
		return currentlyLoggedInUserList;
	}


	public static void setCurrentlyLoggedInUser(HashMap<String, user> currentlyLoggedInUser) {
		userInList.currentlyLoggedInUserList = currentlyLoggedInUser;
	}
	
	public void userLoggedInAgain(String sessionId,String peerId,String videoId,String roomId) {
		
		user currentlyLoggedInUser = currentlyLoggedInUserList.get(sessionId);
		
				currentlyLoggedInUser.setPeerId(peerId);
		currentlyLoggedInUser.setVideoId(videoId);
		currentlyLoggedInUser.setRoomId(roomId);
	    updateUsersDetails updateUser = new updateUsersDetails();
	    
	    updateUser.updateUsers(currentlyLoggedInUser); 
	}
	
	
}
