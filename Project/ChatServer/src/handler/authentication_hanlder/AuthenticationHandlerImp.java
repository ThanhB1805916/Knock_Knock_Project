package handler.authentication_hanlder;

import java.io.File;

import data_access.person_access.PersonDAO;
import data_model.PersonTable;
import handler.Handler;
import model.communication.*;
import model.converter.PersonConverter;
import model.sendmodel.LoginModel;
import model.sendmodel.Person;
import socket.Client;

public class AuthenticationHandlerImp extends Handler implements AuthenticationHandler {

	private PersonDAO dao;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public AuthenticationHandlerImp(Client client, PersonDAO dao) {
		super(client);
		this.dao = dao;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Handle Request
	@Override
	public void handleRequest(Request request) {
		if (request.isValid()) {
			Request responseCommandType = null;

			Name command = request.getName();
			switch (command) {

			case SIGNUP:
				responseCommandType = new Request(Name.SIGNUP, signup((Person) request.getContent()));
				break;

			case LOGIN:
				responseCommandType = new Request(Name.LOGIN, login((LoginModel) request.getContent()));
				break;

			case LOGOUT:
				responseCommandType = new Request(Name.LOGOUT, (Person) request.getContent());
				break;

			default:
				responseCommandType = new Request(null, null);
				break;
			}

			// Send back to client
			packAndSend(responseCommandType);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------ Pack And Send
	@Override
	public void packAndSend(Request request) {
		if (request.isValid()) {
			CPackage CPackage = new CPackage(Type.AUTHENTICATION, request);
			send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Signup

	@Override
	public boolean existUsername(String username) {
		return dao.getByUsername(username) != null;
	}

	@Override
	public boolean existPhonenumber(String phonenumber) {
		return dao.getByPhonenumber(phonenumber) != null;
	}

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

			// Match password
			if (personTable != null && model.getPassword().equals(personTable.getPassword())) {

				PersonConverter converter = new PersonConverter();

				person = converter.convert(personTable);

				// Save login client
				client.setPerson(person);
				authorizedClientList.put(person.getId(), client);

				exist = true;
			}
		}

		return exist;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Logout

	@Override
	public boolean logout(Person person) {
		// Keep connection
		// Remove person
		Person curPerson = client.getPerson();
		if (curPerson.equals(person)) {
			authorizedClientList.remove(person.getId());
			return true;
		}
		return false;
	}
}
