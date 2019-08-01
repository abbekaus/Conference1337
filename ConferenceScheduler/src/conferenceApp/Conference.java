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
			System.out.println("Attendee class not found");
			c.printStackTrace();
			return;
		}
		System.out.println(attendees.toString());
	}

	public void addAttendee(Attendee att) {
		currentAttendee = att;
		attendees.put(att.getEmail(), att);
		
		try {
			FileOutputStream fileOut = new FileOutputStream(attendeeFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(attendees);
			System.out.printf(attendees.toString());
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}

		

	
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
		System.out.println("After removal: " + attendees.size());

	}

	@Override
	public void start(Stage primaryStage) {
		Conference conf = new Conference();
		try {
			conf.loadEvents();

			// interact();
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
		
	}

		

}
