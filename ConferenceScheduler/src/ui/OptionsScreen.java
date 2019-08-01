package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import conferenceApp.*;

public class OptionsScreen {
		private Attendee currentAttendee;
		private Conference conference;
		
	public OptionsScreen(Conference conf, Attendee att) {
		this.currentAttendee = att;
		this.conference = conf;
	}

	public void drawScreen() {
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Choose action:");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        Button lectureButton = new Button("Choose Events");
        HBox lectureHbBtn = new HBox(10);
        lectureHbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        lectureHbBtn.getChildren().add(lectureButton);
        grid.add(lectureButton, 0, 1);
        Button removeUserButton = new Button("Remove me from the conference");
		HBox removeUserHbButton = new HBox(10);
		removeUserHbButton.setAlignment(Pos.BOTTOM_RIGHT);
		removeUserHbButton.getChildren().add(removeUserButton);
        grid.add(removeUserButton, 0, 3);

    	Stage stage = new Stage();
        stage.setTitle("Main menu");
        stage.setScene(new Scene(grid, 450, 450));
        stage.show();
               
        lectureButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	LectureScreen lectScr = new LectureScreen(conference, currentAttendee);
            	lectScr.drawScreen();           	
            }
        });

        removeUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	conference.removeAttendee(currentAttendee);
            	conference.saveAttendees();
            }
        });
	}
}
