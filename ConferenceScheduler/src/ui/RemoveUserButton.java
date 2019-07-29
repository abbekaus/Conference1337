package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class RemoveUserButton extends Button{
	
	public HBox createButton() {
		Button removeUserButton = new Button("Remove me from the conference");
		HBox removeUserHbButton = new HBox(10);
		removeUserHbButton.setAlignment(Pos.BOTTOM_RIGHT);
		removeUserHbButton.getChildren().add(removeUserButton);
		return removeUserHbButton;
	}
}
