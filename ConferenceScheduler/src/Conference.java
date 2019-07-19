import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import util.Constants;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Conference implements Constants{
	
	private ArrayList<Event> events;
	private ArrayList<Attendee> attendees;
	
	public Conference() {
		events = new ArrayList<Event>();
		attendees = new ArrayList<Attendee>();
	}
	
	public static void main(String[] args) {
		Conference conf = new Conference();
		conf.host();
		
		
		
	}
	
	private void host() {
		try {
			loadEvents(eventFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
		}
//		printEvents();
		try {
			interact();
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
		    lecture.eventID = lectureDetails[0];
		    lecture.description = lectureDetails[1];
		    lecture.startTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(lectureDetails[2]);
		    lecture.endTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(lectureDetails[3]);
		    events.add(lecture);
		} 
	}
	
	private void interact() throws ParseException {
		while(attendees.size()<1){
			Scanner scan = new Scanner(System.in);
			Attendee att = new Attendee();
			System.out.println("Enter your name:");
			att.name = scan.nextLine();
			System.out.println("Enter yout email:");
			att.emailID = scan.nextLine();
			System.out.println("Choose one am activity:");
			for(int i = 0;i<events.size();i++) {
				if(events.get(i).endTime.compareTo(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("2019-08-16 12:00:00")) < 0){
					System.out.println(i + ". " + events.get(i).eventID + ": " + events.get(i).description);
				}
			}
			att.enlistedEvents.put("am", new Event(scan.nextLine()));
			System.out.println("Choose which lunch alternative you want, Vegan, Fish or Chicken:");
			att.enlistedEvents.put("lunch",new Event(scan.nextLine()));
			System.out.println("Choose one pm activity:");
			for(int i = 0;i<events.size();i++) {
				if(events.get(i).startTime.compareTo(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("2019-08-16 13:00:00")) >= 0){
					System.out.println(i + ". " + events.get(i).eventID + ": " + events.get(i).description);
				}
			}
			att.enlistedEvents.put("pm", new Event(scan.nextLine()));
			System.out.println("Hi " + att.name + ". Welcome to this years conference. You have chosen the following schedule:");
			System.out.println(att.enlistedEvents.get("am").toString());
			System.out.println(att.enlistedEvents.get("lunch").toString());
			System.out.println(att.enlistedEvents.get("pm").toString());
			attendees.add(att);
		}
		
	}
	
	private void printEvents(){
		int eventsLength = events.size();
		for(int i = 0; i< eventsLength;i++) {
			events.get(i).toString();
		}
	}
	
}
