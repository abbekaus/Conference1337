package ui;

import java.util.ArrayList;

import conferenceApp.Attendee;
import conferenceApp.Conference;
import conferenceApp.Event;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LectureScreen {

	private Attendee currentAttendee;
	private Conference conference;

	public LectureScreen(Conference conf, Attendee att) {
		this.currentAttendee = att;
		this.conference = conf;
	}
	
	public void drawScreen() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text amTitle = new Text("Choose AM Lecture:");
		amTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(amTitle, 0, 0, 2, 1);
		addOptions(conference.getAMEvents(), grid, 1);
		Text pmTitle = new Text("Choose PM Lecture:");
		pmTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(pmTitle, 0, 4, 2, 1);
		addOptions(conference.getPMEvents(), grid, 5);
		Text lunchTitle = new Text("Choose Lunch Alternative:");
		lunchTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(lunchTitle, 0, 8, 2, 1);
		addOptions(conference.getLunchEvents(), grid, 9);
		
		Stage stage = new Stage();
		stage.setTitle("My New Stage Title");
		stage.setScene(new Scene(grid, 450, 450));
		stage.show();
	}
	
	private void addOptions(ArrayList<Event> events, GridPane grid, int offset) {
		int nbrOfEvents = events.size();
		final ToggleGroup toggleGroup = new ToggleGroup();
		for (int i = 0; i < nbrOfEvents; i++) {
			Event currentEvent = events.get(i);
			Label label = new Label(currentEvent.getEventID() + ": " + currentEvent.getDescription());
			grid.add(label, 0, i+offset);
			RadioButton button = new RadioButton();
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(button);
			grid.add(button, 1, i+offset);
			button.setToggleGroup(toggleGroup);
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					currentAttendee.addEvent(currentEvent);
				
				}
			});
		}
		

	}

}
