import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.communication.*;
import model.sendmodel.*;
import socket.Client;

public class StartUp {

	public static void main(String[] args) {

		Thread A = new Thread(() -> {
			send();
		});

		Thread B = new Thread(() -> {
			receive();
		});

		A.start();
		B.start();

		try {
			A.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void send() {

		Client client = new Client();
		LoginModel model = new LoginModel("nguyetnga", "1234");

		CPackage CPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.LOGIN, model));
		client.send(CPackage);
		boolean success = (boolean) client.receive().getRequest().getContent();

		if (success) {

			System.out.println("Success");
			CPackage = new CPackage(Type.ACCOUNT, new Request(Name.GET, Name.GET));
			client.send(CPackage);
			Person person = (Person) client.receive().getRequest().getContent();
			System.out.println(person.getName());
			List<Person> members = new ArrayList<Person>();
			members.add(person);
			members.add(new Person(1, "admin", "1233", "AAAA", false, "", LocalDate.now(), new FileInfo()));
			Room room = new Room(1, "P1", LocalDateTime.now(), null, members);
			int i = 0;
			while (true) {
				FileInfo file = new FileInfo();
				file.setName("Hallo" + i++);
				 room.setAvatar(file);
				Message msg = new Message(1, person, room, file, false, LocalDateTime.now());
				CPackage = new CPackage(Type.MESSAGE, new Request(Name.ADD, msg));
				client.send(CPackage);

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static void receive() {
		Client client = new Client();
		LoginModel model = new LoginModel("admin", "1234");

		CPackage CPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.LOGIN, model));
		client.send(CPackage);
		boolean success = (boolean) client.receive().getRequest().getContent();

		if (success) {

			System.out.println("Success");
			CPackage = new CPackage(Type.ACCOUNT, new Request(Name.GET, Name.GET));
			client.send(CPackage);
			Person person = (Person) client.receive().getRequest().getContent();
			System.out.println(person.getName());

			System.out.println("\n\n");
			
			while (true) {
				Message msg = (Message) client.receive().getRequest().getContent();
				System.out.println("Receive " + msg.getContent().getName());
			}
		}
	}
}
