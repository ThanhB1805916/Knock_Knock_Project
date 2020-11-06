import java.util.List;

import model.communication.*;
import model.sendmodel.*;
import socket.Client;

public class StartUp {

	public static void main(String[] args) {

		Client client = new Client();
		LoginModel model = new LoginModel("user2", "1234");

		CPackage CPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.LOGIN, model));
		client.send(CPackage);
		boolean success = (boolean)client.receive().getRequest().getContent();

		if (success) {

			System.out.println("Success");
			CPackage = new CPackage(Type.ACCOUNT, new Request(Name.GET, Name.GET));
			client.send(CPackage);
			Person person = (Person)client.receive().getRequest().getContent();
			System.out.println(person.getName());
			
			System.out.println("\n\n");
			
			CPackage = new CPackage(Type.FRIEND, new Request(Name.GET, Name.GET));
			client.send(CPackage);
			@SuppressWarnings("unchecked")
			List<Person> friends = (List<Person>)client.receive().getRequest().getContent();
			for (Person person2 : friends) {
				System.out.println(person2.getName());
			}
			
			CPackage = new CPackage(Type.ROOM, new Request(Name.GET, Name.GET));
			client.send(CPackage);
			System.out.println("\n Room \n");
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>)client.receive().getRequest().getContent();
			
			for (Room room : rooms) {
				System.out.println(room.getName());
				for (Person mem : room.getMembers()) {
					System.out.println(mem.getName());
				}
				
				System.out.println("\n");
			}
		}
			
//		}
	}
}
