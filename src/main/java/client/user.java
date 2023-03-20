package client;

import java.util.ArrayList;

import com.mysql.cj.protocol.Message;

import Model.msgList;
import Model.sendMsgToEveryOne;
import Model.userInList;

public class user {
    String sessionId;
    String peerId;
	String username;
	String roomId;
	String videoId;
	
	public user(String sessionId, String peerId, String username, String roomId, String videoId) {
		super();
		this.sessionId = sessionId;
		this.peerId = peerId;
		this.username = username;
		this.roomId = roomId;
		this.videoId = videoId;
	}

	ArrayList<Messages> myMessages = new ArrayList<>();

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getPeerId() {
		return peerId;
	}

	public void setPeerId(String peerId) {
		this.peerId = peerId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getVideoId() {
		return videoId;
	}

	@Override
	public String toString() {
		return "user [sessionId=" + sessionId + ", peerId=" + peerId + ", username=" + username + ", roomId=" + roomId
				+ ", videoId=" + videoId + "]";
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public ArrayList<Messages> getMyMessages() {
		return myMessages;
	}

	public void setMyMessages(ArrayList<Messages> myMessages) {
		this.myMessages = myMessages;
	}
 
	public void composeMsg(Messages msg){
		myMessages.add(msg);
		msgList msgList = new msgList();
		msgList.addmsg(msg);
		if(msg.getRecieverId().equals("everyone")) {
			System.out.print("s");
			sendMsgToEveryOne sendMsgToEveryOne = new sendMsgToEveryOne(roomId, msg);
			sendMsgToEveryOne.start();
			System.out.println(msg);
			
		}else {
			
			userInList.getCurrentlyLoggedInUser().get(msg.getRecieverId()).getMyMessages().add(msg);
			
		}
	}

	
	
	
	
}
