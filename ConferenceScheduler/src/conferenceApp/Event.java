package conferenceApp;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Serializable {	
	private static final long serialVersionUID = 1L;
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
	
	public String getTimeOfDay() {
		return timeOfDay;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getEventID() {
		return eventID;
	}

}
