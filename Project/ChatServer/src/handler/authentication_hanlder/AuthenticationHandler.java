package handler.authentication_hanlder;

import java.io.File;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import communication_standard.converter.PersonConverter;
import communication_standard.manage_type.EManageType;
import communication_standard.manage_type.type.EAuthentication;
import communication_standard.model.LoginModel;
import communication_standard.model.Person;
import data_access.person_access.IPersonDAO;
import data_model.PersonTable;
import handler.Handler;
import socket.IClient;
import socket.Server;

public class AuthenticationHandler extends Handler implements IAuthenticationHandler {

	private IPersonDAO dao;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public AuthenticationHandler(IClient client, IPersonDAO dao) {
		super(client);
		this.dao = dao;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Handle Request
	@Override
	public void handleRequest(CommandType request) {
		if (request.isValid()) {

			EAuthentication command = (EAuthentication) request.getCommand();

			switch (command) {

			case SIGNUP:
				signup((Person) request.getContent());
				break;

			case LOGIN:
				login((LoginModel) request.getContent());
				break;

			case LOGOUT:
				logout(request.getContent());
				break;
			}
		}

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------ Pack And Send
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
	public void signup(Person person) {

		boolean success = false;

		if (person.isValid()) {

			boolean existUsername = dao.existUsername(person.getUsername());
			boolean existPhonenumber = dao.existPhonenumber(person.getPhonenumber());

			// If username not exist
			if (existUsername == false && existPhonenumber == false) {
				PersonConverter converter = new PersonConverter();

				// Convert to personTable
				PersonTable personTable = converter.revert(person);

				// Add to database
				if (dao.add(personTable)) {
					success = true;

					// Set up new user
					setupUser(person);
				}
				System.out.println(success);
			}
		}

		// Send back to client
		CommandType commandType = new CommandType(EAuthentication.SIGNUP, success);
		packAndSend(commandType);
	}

	// Create folder for storing user's avatar
	// Folder's name is username
	@Override
	public void setupUser(Person person) {

		boolean success = false;

		if (person.isValid()) {
			String folderName = person.getUsername();

			// Folder stored in sources/users/
			File userDir = new File("sources/users/" + folderName);
			if (userDir.mkdir())
				success = true;

			// Create avatars folder
			userDir = new File("sources/users/" + folderName + "/avatars");
			if (userDir.mkdir())
				success = true;

		}

		if (success) {
			System.out.println(person.getUsername() + "'s folder created");
		} else {
			System.out.println("Can't create " + person.getUsername() + "'s folder, folder exist");
		}

	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Login

	@Override
	public void login(LoginModel model) {

		if (model.isValid()) {

			// Get person from database
			PersonTable personTable = dao.get(model.getUsername(), model.getPassword());

			Person person = null;

			boolean exist = false;

			if (personTable != null) {
				PersonConverter converter = new PersonConverter();

				person = converter.convert(personTable);

				//Save login client
				authorizedClient_List.put(client, person);
				
				exist = true;
			}

			// Send back to client
			CommandType commandType = new CommandType(EAuthentication.LOGIN, exist);
			packAndSend(commandType);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Logout

	@Override
	public void logout(Object command) {
		// Keep connection
		// Remove user
		Server.getInstance().getAuthorizedClient_List().remove(client);
	}
}
