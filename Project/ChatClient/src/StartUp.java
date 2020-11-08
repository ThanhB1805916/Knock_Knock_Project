import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import model.communication.*;
import model.sendmodel.*;
import socket.Client;

public class StartUp {

	public static void main(String[] args) {

//		Thread A = new Thread(()->{
//			send();
//		});
//		
//		Thread B = new Thread(()->{
//			receive();
//		});
//				
//		A.start();
//		B.start();
//		
//		try {
//			A.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Client client = new Client();
//		LoginModel model = new LoginModel("admin", "1234");
//		
//		CPackage CPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.LOGIN, model));
//		client.send(CPackage);
//		boolean success = (boolean)client.receive().getRequest().getContent();
//		
//		CPackage = new CPackage(Type.ACCOUNT, new Request(Name.GET, Name.GET));
//		client.send(CPackage);
//		Person person = (Person)client.receive().getRequest().getContent();
//		System.out.println(person.getName());
		
		
		Person person = new Person(0, "User250", "1234", "User 50", true, "0111126987", null, null);
	
		CPackage CPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.SIGNUP, person));
		client.send(CPackage);
		
		boolean success = (boolean)client.receive().getRequest().getContent();
		
		System.out.println(success);
		
		if(success)
		{
			LoginModel model = new LoginModel("User250", "1234");
			 CPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.LOGIN, model));
			client.send(CPackage);
			success = (boolean)client.receive().getRequest().getContent();
			System.out.println(success);
			
			CPackage = new CPackage(Type.ACCOUNT, new Request(Name.GET, Name.GET));
			client.send(CPackage);
			Person person1 = (Person)client.receive().getRequest().getContent();
			System.out.println(person1.getName());
		}
	}
	
	
	public static void send()
	{

		Client client = new Client();
//		LoginModel model = new LoginModel("admin", "1234");
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
//			
//			System.out.println("\n\n");
			
//			CPackage = new CPackage(Type.FRIEND, new Request(Name.GET, Name.GET));
//			client.send(CPackage);
//			@SuppressWarnings("unchecked")
//			List<Person> friends = (List<Person>)client.receive().getRequest().getContent();
//			for (Person person2 : friends) {
//				System.out.println(person2.getName());
//			}
			
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
			int i = 0;
			while(true)
			{
				FileInfo file = new FileInfo();
				file.setName("Hallo"+i++);
				Message model1 = new Message(person, rooms.get(0), file, false, LocalDateTime.now());
				
				CPackage = new CPackage(Type.MESSAGE, new Request(Name.ADD, model1));
				client.send(CPackage);
				
				try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			
		}
	}
	
	public static void receive()
	{
		Client client = new Client();
		LoginModel model = new LoginModel("admin", "1234");

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
			
			while (true) {
				Message msg = (Message)client.receive().getRequest().getContent();
				
				System.out.println("Receive "+msg.getContent().getName());
			}
		}
	}
}
