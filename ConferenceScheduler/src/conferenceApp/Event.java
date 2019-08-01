package conferenceApp;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Serializable {	
	private String eventID;
	private String description;
	private Date startTime;
	private Date endTime;
	private String timeOfDay;


	public Event() {	
	}
	
	public Event(String eventDetails) throws ParseException {
		String[] lectureDetails = eventDetails.split(",");
		eventID = lectureDetails[0];
		description = lectureDetails[1];
		startTime = new SimpleDateFormat("HH:mm").parse(lectureDetails[2]);
		endTime= new SimpleDateFormat("HH:mm").parse(lectureDetails[3]);
		if (endTime
				.compareTo(new SimpleDateFormat("HH:mm").parse("12:00")) < 0) {
			timeOfDay = "am";
			
		} else if (startTime
				.compareTo(new SimpleDateFormat("HH:mm").parse("13:00")) >= 0) {
			timeOfDay = "pm";
		} else {
			timeOfDay = "lunch";
		}
	}
	
	public String printEvent() {
		return eventID + "," + description + "," + startTime +"," + endTime + "," + timeOfDay; 
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
