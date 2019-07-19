import java.util.Date;

public class Event {	
	protected String eventID;
	protected String description;
	protected Date startTime;
	protected Date endTime;
	protected String eventType;
	
	public Event(String eventID) {
		this.eventID = eventID;
	
	}
	public Event() {
		
	}
	
	public String toString() {
		return eventID;
	}
}
