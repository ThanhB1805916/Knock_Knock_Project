package main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/chatroom/FrameChat.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("My App");
			stage.setMinHeight(537.4);
			stage.setMinWidth(500);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
