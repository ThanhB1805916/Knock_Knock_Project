package sender.send_authentication_handler;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import communication_standard.manage_type.EManageType;
import communication_standard.manage_type.type.EAuthentication;
import communication_standard.model.LoginModel;
import communication_standard.model.Person;
import sender.SendHandler;
import socket.IClient;

// This class will send command to server
public class SendAuthenticationHandler extends SendHandler implements ISendAuthenticationHandler {

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public SendAuthenticationHandler(IClient client) {
		super(client);
		// TODO Auto-generated constructor stub
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------- Send Package To Server

	@Override
	public void packAndSend(CommandType commandType) {
		if (commandType.isValid()) {
			CommunicationPackage CPackage = new CommunicationPackage(EManageType.AUTHENTICATION, commandType);
			send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Signup

	@Override
	public boolean signup(Person person) {

		boolean sended = false;

		if (person.isValid()) {

			CommandType commandType = new CommandType(EAuthentication.SIGNUP, person);

			packAndSend(commandType);

			sended = true;
		}

		return sended;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Login

	@Override
	public boolean login(String username, String password) {

		LoginModel model = new LoginModel(username, password);

		boolean success = false;

		if (model.isValid()) {

			CommandType commandType = new CommandType(EAuthentication.LOGIN, model);

			// Send to server
			packAndSend(commandType);

			// Receive from server
			success = (boolean) receiveAndUnPack(client.receive());
		}

		System.out.println(success);

		return success;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Logout

	@Override
	public void logout() {
	}

}
