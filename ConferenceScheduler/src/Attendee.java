import java.util.HashMap;
import java.util.Map;


public class Attendee {
	
	protected String name;
	protected String emailID;
	protected String lunchOption;
	protected Map<String,Event> enlistedEvents;
	
	public Attendee() {
		enlistedEvents = new HashMap<String,Event>();
	}
	
	
	public void addEvent(Event ev) {
		enlistedEvents.put(ev.eventID, ev);
	}
	
	public void removeEvent(Event ev) {	
		enlistedEvents.remove(ev.eventID);
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
}
