package conferenceApp;

import java.util.Date;

public class Event {	
	private String eventID;
	private String description;
	private Date startTime;
	private Date endTime;
	private String timeOfDay;

	public Event(String eventID, String desc) {
		this.eventID = eventID;
		this.description = desc;
	
	}
	public Event() {
		
	}
	
	public String getTimeOfDay() {
		return timeOfDay;
	}
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	
	public String toString() {
		return eventID + ": "+ description;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
}
