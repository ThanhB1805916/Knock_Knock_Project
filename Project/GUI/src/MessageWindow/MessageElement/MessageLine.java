package MessageWindow.MessageElement;

import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MessageLine extends AnchorPane{
	private LabelMessage message;
	private HBox hBox;
	public MessageLine(String content, boolean flag) {
		hBox = new HBox();
		message = new LabelMessage(content, flag);
		if(flag) {
			hBox.setAlignment(Pos.BASELINE_RIGHT);
			AnchorPane.setRightAnchor(hBox, 20.0);
			AnchorPane.setLeftAnchor(hBox, 0.0);
		}
		else {
			hBox.setAlignment(Pos.BASELINE_LEFT);
			AnchorPane.setRightAnchor(hBox, 0.0);
			AnchorPane.setLeftAnchor(hBox, 30.0);
		}
		AnchorPane.setBottomAnchor(hBox, 0.0);
		hBox.getChildren().add(message);
		super.getChildren().add(hBox);
		super.widthProperty().addListener(func->{
			if(message.getText().length()>44)
				message.setPrefWidth(super.getWidth()*70/100);
			else if(message.getText().length()<=44 && message.getText().length()>=35) {
				if(super.getWidth()<650)
					message.setPrefWidth(super.getWidth()*70/100);
			}
		});
	}
}