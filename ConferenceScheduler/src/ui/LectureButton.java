package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class LectureButton extends Button {
	
	public Button createButton() {
		Button lectureButton = new Button("Choose lectures");
        HBox lectureHbBtn = new HBox(10);
        lectureHbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        lectureHbBtn.getChildren().add(lectureButton);
		return lectureButton;
	}
}
