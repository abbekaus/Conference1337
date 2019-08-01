package conferenceApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.stage.Stage;
import ui.LoginScreen;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conference extends Application implements Constants {

	private static ArrayList<Event> amEvents;
	private static ArrayList<Event> pmEvents;
	private static ArrayList<Event> lunchEvents;
	private Map<String, Attendee> attendees;
	private Attendee currentAttendee;

	public Conference() {
		amEvents = new ArrayList<Event>();
		pmEvents = new ArrayList<Event>();
		lunchEvents = new ArrayList<Event>();
		attendees = new HashMap<String, Attendee>();
	}

	public ArrayList<Event> getAMEvents() {
		return amEvents;
	}

	public ArrayList<Event> getPMEvents() {
		return pmEvents;
	}

	public ArrayList<Event> getLunchEvents() {
		return lunchEvents;
	}

	public boolean alreadyAttending(Attendee att) {
		return attendees.containsKey(att.getEmail());
	}

	public void removeAttendee(Attendee att) {
		attendees.remove(att.getEmail());
	}

	public Attendee getCurrentAttendee() {
		return currentAttendee;
	}

	public int getNbrOfAttendees() {
		return attendees.size();
	}

	public Attendee getAttendee(String userEmail) {
		return attendees.get(userEmail);
	}
	
	public void addAttendee(Attendee att) {
		currentAttendee = att;
		attendees.put(att.getEmail(), att);
	}
	
	/**
	 * Loads the events for the conference from the Events file.
	 * @throws IOException
	 * @throws ParseException
	 */
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
	
	/**
	 * Loads the attendees already registered to the conference from the Attendees file.
	 */
	private void loadAttendees() {
		try {
			FileInputStream fileIn = new FileInputStream(attendeeFile);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			attendees = (HashMap) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}
	
	/**
	 * Saves the attendees that are currently registered to the Attendees file.
	 */
	public void saveAttendees() {
		try {
			FileOutputStream fileOut = new FileOutputStream(attendeeFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(attendees);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Conference conf = new Conference();
		try {
			conf.loadEvents();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		conf.loadAttendees();
		LoginScreen logScr = new LoginScreen(conf);
		logScr.drawScreen(primaryStage);
	}

}
