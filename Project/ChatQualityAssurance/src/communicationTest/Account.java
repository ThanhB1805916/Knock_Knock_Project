package communicationTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.sendmodel.FileInfo;
import model.sendmodel.LoginModel;
import model.sendmodel.Person;
import socket.ClientImp;
import socket.Client;
import socket.Server;

public class Account {
	Client client;

	@Before
	public void setUp() {
		Thread server = new Thread(Server.getInstance());
		server.start();
		try {
			client = new ClientImp(new Socket("127.100.100.100", 9999));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean setUpLogin(LoginModel model) {
		CPackage CPackage = new CPackage(Type.AUTHENTICATION, new Request(Name.LOGIN, model));
		client.send(CPackage);

		boolean success = (boolean) client.receive().unpack();

		return success;
	}

	@Test
	public void getAccount_afterLogin() {
		LoginModel model = new LoginModel("admin", "1234");
		if (setUpLogin(model)) {
			CPackage CPackage = new CPackage(Type.ACCOUNT, new Request(Name.GET, Name.GET));
			client.send(CPackage);
			Person personvalid = new Person(1, "admin", "1234", "Administrator", true, "0000000000",
					LocalDate.of(2020, 10, 04), new FileInfo("sources/default/avatars/default_avatar.png"));
			
			Person person = (Person)client.receive().unpack();
			
			assertNotNull(person);
			System.out.println(person.equals(personvalid));
//			assertTrue(person.equals(personvalid));
			
		}
	}
}
