import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import util.Constants;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conference implements Constants{
	
	private ArrayList<Event> events;
	private Map<String,Attendee> attendees;
	
	public Conference() {
		events = new ArrayList<Event>();
		attendees = new HashMap<String,Attendee>();
	}
	
	public static void main(String[] args) {
		Conference conf = new Conference();
		conf.host();		
	}
	
	private void host() {
		try {
			loadEvents(eventFile);
			interact();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
		}	

	}
	
	private void loadEvents(String eventFile) throws IOException, ParseException {
		
		File file = new File(eventFile); 		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 	  
		String st;
		Event lecture;
	    while ((st = br.readLine()) != null) {
	    	String[] lectureDetails = st.split(",");
		    lecture = new Event();
		    lecture.setEventID(lectureDetails[0]);
		    lecture.setDescription(lectureDetails[1]);
		    lecture.setStartTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(lectureDetails[2]));
		    lecture.setEndTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(lectureDetails[3]));
		    events.add(lecture);
		} 
	}
	
	private void interact() throws ParseException {
		while(attendees.size()<5){
			
			
			Scanner scan = new Scanner(System.in);
			Attendee att = new Attendee();
			System.out.println("Enter your name:");
			att.setName(scan.nextLine());
			System.out.println("Enter yout email:");
			att.setEmail(scan.nextLine());
			
			
			if(alreadyAttending(att)) {
				System.out.println("Already attending");
				chooseLectures(attendees.get(att.getEmail()));
			} else {	
				chooseLectures(att);
				addAttendee(att);
			}
			
		
			System.out.println("---------------------");
			printAllAttendees();
		}
		
	}
	
	
	private void addAttendee(Attendee att) {
		System.out.println("Welcome to this conference " + att.getName());
		attendees.put(att.getEmail(),att);	
	}
	
	private void chooseLectures(Attendee att) throws ParseException {
		att.clearSchedule();
		printAllAttendees();
		Scanner scan = new Scanner(System.in);
		System.out.println("Choose one am activity:");
		for(int i = 0;i<events.size();i++) {
			if(events.get(i).getEndTime().compareTo(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("2019-08-16 12:00:00")) < 0){
				System.out.println(i + ". " + events.get(i).getEventID() + ": " + events.get(i).getDescription());
			}
		}
		att.addEvent(events.get(scan.nextInt()));
		System.out.println("Choose one pm activity:");
		for(int i = 0;i<events.size();i++) {
			if(events.get(i).getStartTime().compareTo(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("2019-08-16 13:00:00")) >= 0){
				System.out.println(i + ". " + events.get(i).getEventID() + ": " + events.get(i).getDescription());
			}
		}
		att.addEvent(events.get(scan.nextInt()));
		System.out.println("Choose which lunch alternative you want, Vegan, Fish or Chicken:");
		String str = scan.next();
		Event lunch = events.get(6);
		lunch.setDescription(str);
		att.addEvent(lunch);
		System.out.println("Hi " + att.getName() + ". Welcome to this years conference. You have chosen the following schedule:");
		att.printEnlistedEvents();
	}
	
	private boolean alreadyAttending(Attendee att) {
		boolean isAttending = false;

		for (String emailID : attendees.keySet()) {
			if (attendees.get(emailID).getEmail().equals(att.getEmail())) {
				isAttending = true;
			}
		}
		return isAttending;
	}

	private void printAllAttendees() {
		for (String emailID : attendees.keySet()) {
			attendees.get(emailID).printEnlistedEvents();
		}
		
	}
	
	private void removeAttendee(Attendee att) {
		attendees.remove(att.getEmail());
	}

	
}
