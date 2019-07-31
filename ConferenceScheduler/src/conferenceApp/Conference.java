package conferenceApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

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
	
	
	private void loadEvents() throws IOException, ParseException {

		File file = new File(eventFile);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		Event event;
		while ((st = br.readLine()) != null) {
			event = new Event(st);
			if (event.getEndTime().compareTo(new SimpleDateFormat("HH:mm").parse("12:00")) < 0) {
				amEvents.add(event);
			} else if (event.getStartTime().compareTo(new SimpleDateFormat("HH:mm").parse("13:00")) >= 0) {
				pmEvents.add(event);
			} else {
				lunchEvents.add(event);
			}
		}
		br.close();

	}
	
	private void loadAttendees() {
		Attendee att = null;
		boolean cont = true;
		try {

			FileInputStream fileIn = new FileInputStream(attendeeFile);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			while (cont) {
				att = (Attendee) in.readObject();
				if (att != null)
					attendees.put(att.getEmail(), att);
				else
					cont = false;
			}
			
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Attendee class not found");
			c.printStackTrace();
			return;
		}
		System.out.println();
	}

	
	
	public void addAttendee(Attendee att) {
		currentAttendee = att;
		attendees.put(att.getEmail(),att);
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
		return attendees.containsKey(att.getEmail());
		
	}
	
	public void removeAttendee(Attendee att) {
		
		attendees.remove(att.getEmail());
		System.out.println("After removal: " + attendees.size());
		
	}
	
	 @Override
	    public void start(Stage primaryStage) {
		 	Conference conf = new Conference();
		 	try {
				conf.loadEvents();
				
			//	interact();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
							e.printStackTrace();
			}
		 	conf.loadAttendees();
	        LoginScreen logScr = new LoginScreen(conf);
	        logScr.drawScreen(primaryStage);
	    }
	    
	 public Attendee getCurrentAttendee() {
		 return currentAttendee;
	 }
	 

	 public int getNbrOfAttendees() {
			return attendees.size();
		}

	@Override
	public void stop() throws IOException {
		System.out.println("Stage is closing");
		Attendee att1 = new Attendee();
		Attendee att2 = new Attendee();
		att1.setName("heja");
		att1.setEmail("heja11");
		attendees.put("hej", att1);
		att2.setName("aaaaaa");
		att2.setEmail("hsssssss");
		attendees.put("gggg", att2);
		System.out.println(attendees.size());
		FileOutputStream fileOut = new FileOutputStream(attendeeFile);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.flush();
		attendees.forEach((String s, Attendee a) -> {
			a.printAttendee();
			try {
				Attendee att = (Attendee) a;
				out.writeObject(att);
				System.out.printf("Serialized data is saved");
			} catch (IOException i) {
				i.printStackTrace();
			}

		});
		
		
		out.close();
		fileOut.close();

	}
	
}
