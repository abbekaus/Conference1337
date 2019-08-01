package conferenceApp;

import java.io.Serializable;

public class Attendee implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String emailID;
	private Event amEvent;
	private Event pmEvent;
	private Event lunchOption;
	

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return emailID;
	}
	public void setEmail(String email) {
		this.emailID = email;
	}
	
	public void signUp(Event ev) {
		if(ev.getTimeOfDay().equals("am")) {
			amEvent = ev;
		} else if(ev.getTimeOfDay().equals("pm")) {
			pmEvent = ev;
		} else {
			lunchOption = ev;
		}
	}

}
