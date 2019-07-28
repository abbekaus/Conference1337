import java.util.HashMap;
import java.util.Map;


public class Attendee {
	
	private String name;
	private String emailID;
	private Map<String,Event> enlistedEvents;
	
	public Attendee() {
		enlistedEvents = new HashMap<String,Event>();
	}
	
	
	public void addEvent(Event ev) {
		enlistedEvents.put(ev.getEventID(), ev);
	}
	
	public void removeEvent(Event ev) {	
		enlistedEvents.remove(ev.getEventID());
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
	
	public String printAttendee() {
		return name + ", " + emailID + " has chosen: " + enlistedEvents.toString();
	}
	
	public void printEnlistedEvents() {
		System.out.println(enlistedEvents.toString());
		
	}
	
	public void clearSchedule() {
		enlistedEvents.clear();
	}
}
