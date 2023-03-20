package Model;

import client.Messages;

public class sendMsgToEveryOne extends Thread{

	String roomId;
	Messages msg;
	@Override
	public void run() {
		System.out.print("a");
		userInList.currentlyLoggedInUserList.forEach((SessionId, user) -> {
			
			if(user.getRoomId().equals(roomId) && !(SessionId.equals(msg.getSenderSessionId()))) {
				
				user.getMyMessages().add(msg);
				
			}
			
		});
		
	}
	public sendMsgToEveryOne(String roomId, Messages msg) {
		super();
		this.roomId = roomId;
		this.msg = msg;
	}
	
	
}
