package handler.authentication_hanlder;

import java.io.File;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import communication_standard.manage_type.EManageType;
import communication_standard.manage_type.type.EAuthentication;
import converter.PersonConverter;
import data_access.person_access.IPersonDAO;
import data_model.PersonTable;
import handler.Handler;
import model.LoginModel;
import model.Person;
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

			CommandType commandType = null;

			switch (command) {

			case SIGNUP:
				commandType = new CommandType(EAuthentication.SIGNUP, signup((Person) request.getContent()));
				break;

			case LOGIN:
				commandType = new CommandType(EAuthentication.LOGIN, login((LoginModel) request.getContent()));
				break;

			case LOGOUT:
				logout(request.getContent());
				break;
			}

			// Send back to client
			if (commandType != null)
				packAndSend(commandType);
			else
				System.out.println("Not valid authentication commandType");
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
	public boolean signup(Person person) {

		boolean success = false;

		if (person.isValid()) {

			// If user name and phone number not exist
			if (existUsername(person.getUsername()) == false && existPhonenumber(person.getPhonenumber()) == false) {
				PersonConverter converter = new PersonConverter();

				// Convert to personTable
				PersonTable personTable = converter.revert(person);

				// Add to database
				if (dao.add(personTable)) {
					success = true;

					// Set up new user
					setupUser(person);
				}
			}
		}

		return success;
	}

	// Create folder for storing user's avatar
	// Folder's name is user name
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
	public boolean login(LoginModel model) {

		boolean exist = false;
		if (model.isValid()) {

			// Get person from database
			PersonTable personTable = dao.getByUsername(model.getUsername());

			Person person = null;

			//Match password
			if (personTable != null && model.getPassword().equals(personTable.getPassword())) {

				PersonConverter converter = new PersonConverter();

				person = converter.convert(personTable);

				// Save login client
				authorizedClient_List.put(client, person);

				exist = true;

			}

		}

		return exist;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Logout

	@Override
	public boolean logout(Object command) {
		// Keep connection
		// Remove user
		Server.getInstance().getAuthorizedClient_List().remove(client);
		return false;
	}

	@Override
	public boolean existUsername(String username) {
		return dao.getByUsername(username) != null;
	}

	@Override
	public boolean existPhonenumber(String phonenumber) {
		return dao.getByPhonenumber(phonenumber) != null;
	}
}
