package ui;

import conferenceApp.Attendee;
import conferenceApp.Conference;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginScreen {
		private Conference conference;
	
	public LoginScreen(Conference conf) {
		this.conference = conf;
	}
	
	public void drawScreen(Stage primaryStage) {

		primaryStage.setTitle("Hello World!");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userNameLabel = new Label("User Name:");
        grid.add(userNameLabel, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label userEmailLabel = new Label("Email:");
        grid.add(userEmailLabel, 0, 2);

        TextField userEmailField = new TextField();
        grid.add(userEmailField, 1, 2);
        Button signInButton = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(signInButton);
        grid.add(hbBtn, 1, 4);
        primaryStage.setScene(scene);
       
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String userName = userTextField.getText();
            	String userEmail = userEmailField.getText();
                if(userName.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), 
                    "Form Error!", "Please enter your name");
                    return;
                }
                if(userEmail.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), 
                    "Form Error!", "Please enter your email id");
                    return;
                }
                Attendee newAttendee = new Attendee();
                newAttendee.setName(userName);
                newAttendee.setEmail(userEmail);
                conference.addAttendee(newAttendee);
                OptionsScreen optScr = new OptionsScreen(conference, conference.getCurrentAttendee());
                optScr.drawScreen();
            }
        });
        primaryStage.show();
	}
	
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
