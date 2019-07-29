package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class LunchButton extends Button {
	
	public HBox createButton() {
		Button lunchButton = new Button("Choose lectures");
        HBox lunchHbBtn = new HBox(10);
        lunchHbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        lunchHbBtn.getChildren().add(lunchButton);
		return lunchHbBtn;
	}
}
