package conferenceApp;

import java.io.Serializable;

public class Attendee implements Serializable{
	
	private String name;
	private String emailID;
	private Event amEvent;
	private Event pmEvent;
	private Event lunchOption;
	

	public Event getAmEvent() {
		return amEvent;
	}
	public void setAmEvent(Event amEvent) {
		this.amEvent = amEvent;
	}
	public Event getPmEvent() {
		return pmEvent;
	}
	public void setPmEvent(Event pmEvent) {
		this.pmEvent = pmEvent;
	}
	public Event getLunchOption() {
		return lunchOption;
	}
	public void setLunchOption(Event lunchOption) {
		this.lunchOption = lunchOption;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return emailID;
	}
	public void setEmail(String email) {
		this.emailID = email;
	}
	
	public void addEvent(Event ev) {
		if(ev.getTimeOfDay().equals("am")) {
			amEvent = ev;
		} else if(ev.getTimeOfDay().equals("pm")) {
			pmEvent = ev;
		} else {
			lunchOption = ev;
		}
	}
	
	public void printAttendee() {
		
		System.out.println(name + ", " + emailID + " has chosen: ");
		System.out.println("Before lunch: " + amEvent);
		System.out.println("After lunch: " + pmEvent);
		System.out.println("Lunch option: " + lunchOption);
		System.out.println("---------------------------------"); 
	}

}
