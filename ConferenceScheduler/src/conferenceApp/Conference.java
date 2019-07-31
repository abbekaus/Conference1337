package conferenceApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.LoginScreen;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Conference extends Application implements Constants{
	
	private static ArrayList<Event> amEvents;
	private static ArrayList<Event> pmEvents;
	private static ArrayList<Event> lunchEvents;
	private Map<String,Attendee> attendees;
	private Attendee currentAttendee;
	
	public Conference() {
		amEvents = new ArrayList<Event>();
		pmEvents = new ArrayList<Event>();
		lunchEvents = new ArrayList<Event>();
		attendees = new HashMap<String,Attendee>();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	private void loadEvents(String eventFile) throws IOException, ParseException {

		File file = new File(eventFile);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		Event event;
		while ((st = br.readLine()) != null) {
			String[] lectureDetails = st.split(",");
			event = new Event();
			event.setEventID(lectureDetails[0]);
			event.setDescription(lectureDetails[1]);
			event.setStartTime(new SimpleDateFormat("HH:mm").parse(lectureDetails[2]));
			event.setEndTime(new SimpleDateFormat("HH:mm").parse(lectureDetails[3]));
			if (event.getEndTime()
					.compareTo(new SimpleDateFormat("HH:mm").parse("12:00")) < 0) {
				event.setTimeOfDay("am");
				amEvents.add(event);
			} else if (event.getStartTime()
					.compareTo(new SimpleDateFormat("HH:mm").parse("13:00")) >= 0) {
				event.setTimeOfDay("pm");
				pmEvents.add(event);
			} else {
				event.setTimeOfDay("lunch");
				lunchEvents.add(event);
			}
			
		}
		br.close();

	}
	
	
	public void addAttendee(Attendee att) {
		currentAttendee = att;
		attendees.put(att.getEmail(),att);
		printAllAttendees();
	}
	
	
	public ArrayList<Event> getAMEvents() {
		return amEvents;
	}
	
	public ArrayList<Event> getPMEvents(){
		return pmEvents;
	}
	
	public ArrayList<Event> getLunchEvents(){
		return lunchEvents;
	}
	
	
	public boolean alreadyAttending(Attendee att) {
		boolean isAttending = false;

		for (String emailID : attendees.keySet()) {
			if (attendees.get(emailID).getEmail().equals(att.getEmail())) {
				isAttending = true;
			}
		}
		return isAttending;
	}
	
	public void removeAttendee(Attendee att) {
		
		attendees.remove(att.getEmail());
		System.out.println("After removal: " + attendees.size());
		
	}
	
	 @Override
	    public void start(Stage primaryStage) {
		 	Conference conf = new Conference();
		 	try {
				conf.loadEvents(eventFile);
			//	interact();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
							e.printStackTrace();
			}	
	        LoginScreen logScr = new LoginScreen(conf);
	        logScr.drawScreen(primaryStage);
	    }
	    
	 public Attendee getCurrentAttendee() {
		 return currentAttendee;
	 }
	 
	 public void printAllAttendees() {
		 if(attendees.size() >0) {
			 Iterator it = attendees.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        System.out.println(pair.getKey() + " = " + pair.getValue());
			        it.remove(); // avoids a ConcurrentModificationException
			    }
		 }
	 }


	
}
