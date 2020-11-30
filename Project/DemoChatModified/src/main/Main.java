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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/login.fxml"));	
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("Knock Knock");
			stage.setResizable(false);
/*		
			Person person = new Person(123,"firefly","password","Tâm", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh3.jpg"));
			Controller.getInstance().inputUserFromDatabase(person);
	
			ArrayList<Person> lists = new ArrayList<Person>();

			Person person1 = new Person(1,"firefly","password","Nguyen Hong Nhi", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh1.jpg"));
			Person person2 = new Person(2,"firefly","password","Tran Nhut An", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh2.jpg"));
			Person person3 = new Person(3,"firefly","password","Quynh Bich Tram", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh3.jpg"));
			Person person4 = new Person(7,"firefly","password","Do hong anh", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/anhdong.gif"));
			Person person12 = new Person(11,"firefly","password","Nguyen Hong Nhi", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh1.jpg"));
			Person person22 = new Person(12,"firefly","password","Tran Nhut An", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh2.jpg"));
			Person person32 = new Person(31,"firefly","password","Quynh Bich Tram", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh3.jpg"));
			Person person42 = new Person(71,"firefly","password","Do hong anh", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/anhdong.gif"));
			Person person11 = new Person(21,"firefly","password","Nguyen Hong Nhi", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh1.jpg"));
			Person person21 = new Person(22,"firefly","password","Tran Nhut An", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh2.jpg"));
			Person person31 = new Person(23,"firefly","password","Quynh Bich Tram", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/demo/anh3.jpg"));
			Person person41 = new Person(27,"firefly","password","Do hong anh", true,"0987070422",LocalDate.of(2000, 7, 9), new FileInfo("E:/anhdong.gif"));
			lists.add(person1);
			lists.add(person1);
			lists.add(person2);
			lists.add(person3);
			lists.add(person4);
			lists.add(person12);
			lists.add(person12);
			lists.add(person32);
			lists.add(person31);
			lists.add(person41);
			lists.add(person31);
			lists.add(person11);
			lists.add(person22);
			lists.add(person21);
			lists.add(person42);
			
			
			Controller.getInstance().inputFriendList(lists);
			
			Room room = new Room(12365,"Loveroom", new FileInfo("E:/demo/anh6.jpg"),lists);
			Message message = new Message(person1, room,new FileInfo("Chào tất cả các bạn") , false, LocalDateTime.now());
			Message message1 = new Message(person3, room,new FileInfo("Chào cái gì mà chào") , false, LocalDateTime.now());
			room.getMessages().add(message);
			room.getMessages().add(message1);
			Controller.getInstance().inputNewRoom(room);
			*/
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
