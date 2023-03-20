package client;

import java.util.Date;

public class Messages {

	String senderSessionId;
	String username;
	String message;
	String time;
	String roomId;
	String recievername;
	
	public String getSenderSessionId() {
		return senderSessionId;
	}
	public void setSenderSessionId(String userSessionId) {
		this.senderSessionId = userSessionId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRecievername() {
		return recievername;
	}
	public void setRecievername(String recievername) {
		this.recievername = recievername;
	}
	public String getRecieverId() {
		return recieverId;
	}
	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}
	String recieverId;
	public Messages(String userSessionId, String username, String message, String time, String roomId,
			String recievername, String recieverId) {
		super();
		this.senderSessionId = userSessionId;
		this.username = username;
		this.message = message;
		this.time = time;
		this.roomId = roomId;
		this.recievername = recievername;
		this.recieverId = recieverId;
	}
	@Override
	public String toString() {
		return "{\"senderSessionId\":\"" + senderSessionId + "\",\"username\":\"" + username + "\",\"message\":\"" + message
				+ "\",\"time\":\"" + time + "\",\"roomId\":\"" + roomId + "\",\"recievername\":\"" + recievername + "\",\"recieverId\":\""
				+ recieverId + "\"}";
	}
    
    
	
	
	
	
}
