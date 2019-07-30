package conferenceApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;

import javafx.stage.Stage;

import ui.LoginScreen;

import util.Constants;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Conference extends Application implements Constants{
	
	private static ArrayList<Event> events;
	private Map<String,Attendee> attendees;
	private Attendee currentAttendee;
	
	public Conference() {
		events = new ArrayList<Event>();
		attendees = new HashMap<String,Attendee>();
	}
	
	public static void main(String[] args) {
		
		
		launch(args);
	}
	
	private void host() {
		

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
	/*
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

		}
		
	}*/
	
	
	public void addAttendee(Attendee att) {
		currentAttendee = att;
		attendees.put(att.getEmail(),att);
		System.out.println("After login: " + attendees.size());
	}
	
	/*
	private void chooseLectures(Attendee att) throws ParseException {
		att.clearSchedule();
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
	}*/
	
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
	 
	 public ArrayList<Event> getEvents(){
		 return events;
	 }

	    

	
}
