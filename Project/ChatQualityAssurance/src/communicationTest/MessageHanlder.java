//package communicationTest;
//
//import java.io.IOException;
//import java.net.Socket;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import model.communication.CPackage;
//import model.communication.Name;
//import model.communication.Request;
//import model.communication.Type;
//import model.sendmodel.FileInfo;
//import model.sendmodel.LoginModel;
//import model.sendmodel.Message;
//import model.sendmodel.Person;
//import model.sendmodel.Room;
//import socket.Client;
//import socket.ClientImp;
//import socket.Server;
//
//public class MessageHanlder {
//
//	Client clientSend;
//	Client clientReceive;
//
//	@Before
//	public void setUp() {
//		
//		Thread server = new Thread(()->{
//			Server.getInstance().connect();
//		});
//		server.start();
//		
//		Socket send = null;
//		Socket receive = null;
//		try {
//			send = new Socket("127.100.100.100", 9999);
//			receive = new Socket("127.100.100.10", 9999);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		clientSend = new ClientImp(send);
//		clientReceive = new ClientImp(receive);
//	}
//
//	public void sendSetUp() {
//		LoginModel model = new LoginModel("user2", "1234");
//		CPackage CPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.LOGIN, model));
//		clientSend.send(CPackage);
//		
//		boolean success = (boolean) clientSend.receive().getRequest().getContent();
//
//		if (success) {
//			CPackage = new CPackage(Type.ACCOUNT, new Request(Name.GET, Name.GET));
//			clientSend.send(CPackage);
//			Person person = (Person) clientSend.receive().getRequest().getContent();
//			System.out.println(person.getName());
//
//			CPackage = new CPackage(Type.ROOM, new Request(Name.GET, Name.GET));
//			clientSend.send(CPackage);
//			System.out.println("\n Room \n");
//			@SuppressWarnings("unchecked")
//			List<Room> rooms = (List<Room>) clientSend.receive().getRequest().getContent();
//
//			for (Room room : rooms) {
//				System.out.println(room.getName());
//				for (Person mem : room.getMembers()) {
//					System.out.println(mem.getName());
//				}
//
//				System.out.println("\n");
//			}
//
//			FileInfo file = new FileInfo();
//			file.setName("Hallo");
//			Message model1 = new Message(1, person, rooms.get(0), file, false, null);
//
//			CPackage = new CPackage(Type.MESSAGE, new Request(Name.ADD, model1));
//			clientSend.send(CPackage);
//
//		}
//	}
//
//	@Test
//	public void receive() {
//		LoginModel model = new LoginModel("admin", "1234");
//
//		CPackage CPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.LOGIN, model));
//		clientReceive.send(CPackage);
//		boolean success = (boolean) clientReceive.receive().getRequest().getContent();
//
//		if (success) {
//			CPackage = new CPackage(Type.ACCOUNT, new Request(Name.GET, Name.GET));
//			clientReceive.send(CPackage);
//			Person person = (Person) clientReceive.receive().getRequest().getContent();
//			System.out.println(person.getName()+"AHAHAH");
//
//			System.out.println("\n\n");
//			
//			Thread a = new Thread(()->{
//				sendSetUp();
//			});
//			a.start();
//			
//			Message msg = (Message) clientReceive.receive().getRequest().getContent();
//			System.out.println(msg.getContent().getName());
//			
//			Assert.assertTrue(msg.getContent().getName().equals("Hallo"));
//		}
//	}
//
//}
