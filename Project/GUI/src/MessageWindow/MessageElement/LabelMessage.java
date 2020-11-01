package MessageWindow.MessageElement;


import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabelMessage extends Label {
	public LabelMessage(String content, boolean flag) {
		super();
		super.setText(content);
		super.setWrapText(true);
		super.setFont(Font.font("System",FontWeight.NORMAL,18));
		super.setMaxWidth(560);
		super.setMinHeight(0);
		if(flag)
			super.setStyle("-fx-padding:5 10 5 10;-fx-background-color:#74b9ff33;-fx-border-width:1;-fx-border-radius:30; -fx-background-radius:30;-fx-border-color:#353b48");
		else
			super.setStyle("-fx-padding:5 10 5 10;-fx-background-color:white;-fx-border-width:1;-fx-border-radius:30; -fx-background-radius:30;-fx-border-color:#353b48");
	}
}
