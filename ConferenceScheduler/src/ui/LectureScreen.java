package ui;

import java.util.ArrayList;

import conferenceApp.Attendee;
import conferenceApp.Conference;
import conferenceApp.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		Text scenetitle = new Text("Choose Lectures:");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		ArrayList<Event> events = conference.getEvents();
		int nbrOfEvents = events.size();
		for (int i = 0; i < nbrOfEvents; i++) {
			Event currentEvent = events.get(i);
			Label label = new Label(currentEvent.getEventID() + ": " + currentEvent.getDescription());
			grid.add(label, 0, i+1);
			Button button = new Button("Choose lecture");
			HBox hbBtn = new HBox(10);
			hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			hbBtn.getChildren().add(button);
			grid.add(button, 1, i+1);

			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					currentAttendee.addEvent(currentEvent);
					currentAttendee.printEnlistedEvents();
				}
			});
		}

		Stage stage = new Stage();
		stage.setTitle("My New Stage Title");
		stage.setScene(new Scene(grid, 450, 450));
		stage.show();

	}

}
