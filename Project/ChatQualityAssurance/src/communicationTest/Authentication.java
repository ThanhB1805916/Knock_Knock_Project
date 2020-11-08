
package communicationTest;

import java.io.IOException;
import java.net.Socket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.communication.CPackage;
import model.communication.Name;
import model.communication.Request;
import model.communication.Type;
import model.sendmodel.LoginModel;
import socket.ClientImp;
import socket.Client;
import socket.Server;

public class Authentication {

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
	public void login() {
		LoginModel model = new LoginModel("admin", "1234");

		Assert.assertTrue(setUpLogin(model));
	}

	@Test
	public void username_isNull_when_login() {
		LoginModel model = new LoginModel(null, "1234");

		Assert.assertFalse(setUpLogin(model));
	}

	@Test
	public void username_isEmpty_when_login() {
		LoginModel model = new LoginModel("", "1234");

		Assert.assertFalse(setUpLogin(model));
	}

	@Test
	public void password_isNull_when_login() {
		LoginModel model = new LoginModel("admin", null);

		Assert.assertFalse(setUpLogin(model));
	}

	@Test
	public void password_isEmpty_when_login() {
		LoginModel model = new LoginModel("admin", "");

		Assert.assertFalse(setUpLogin(model));
	}

	@Test
	public void usernameAndPassword_isNull_when_login() {
		LoginModel model = new LoginModel(null, null);

		Assert.assertFalse(setUpLogin(model));
	}

	@Test
	public void usernameAndPassword_isEmpty_when_login() {
		LoginModel model = new LoginModel("", "");

		Assert.assertFalse(setUpLogin(model));
	}
}
