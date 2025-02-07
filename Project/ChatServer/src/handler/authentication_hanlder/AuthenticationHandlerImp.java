package handler.authentication_hanlder;

import java.io.File;
import java.time.LocalDate;

import data_access.person_access.PersonDAO;
import handler.Handler;
import model.communication.*;
import model.converter.PersonConverter;
import model.sendmodel.FileInfo;
import model.sendmodel.LoginModel;
import model.sendmodel.Person;
import socket.Client;

public class AuthenticationHandlerImp extends Handler implements AuthenticationHandler {

	private PersonDAO dao;
	private PersonConverter converter;
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public AuthenticationHandlerImp(Client client, PersonDAO dao, PersonConverter converter) {
		super(client);
		this.dao = dao;
		this.converter = converter;
	}

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
				responseCommandType = new Request(Name.LOGOUT, logout((Person)request.getContent()));
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
		boolean success = false;
		if (username != null && username.isEmpty() == false) {
			success = dao.getByUsername(username) != null;
		}
		return success;
	}

	@Override
	public boolean existPhonenumber(String phonenumber) {
		boolean success = false;
		if (phonenumber != null && phonenumber.isEmpty() == false) {
			success = dao.getByPhonenumber(phonenumber) != null;
		}
		return success;
	}

	@Override
	public boolean signup(Person person) {

		boolean success = false;
		if (setupUser(person)) {
			// If user name and phone number not exist
			if (person.isValid() && existUsername(person.getUsername()) == false
					&& existPhonenumber(person.getPhonenumber()) == false && dao.add(converter.revert(person))) {
				// Add to database
				success = true;
			} else {
				// Remove directory
				setupUserUndo(person);
			}
		}

		return success;
	}

	// Create folder for storing user's avatar
	// Folder's name is user name
	@Override
	public boolean setupUser(Person person) {
		try {
			if (person.getUsername().isEmpty() == false) {
				String folderName = person.getUsername();

				// If don't have date of birth
				if (person.getDateofbirth() == null)
					person.setDateofbirth(LocalDate.now());

				// Create folder for user and avatar
				File userDir = new File("sources/users/" + folderName + "/avatars");
				userDir.mkdirs();

				FileInfo avatar = person.getAvatar();
				if (avatar == null) {
					// Avatar will be default
					avatar = new FileInfo("sources/default/avatars/default_avatar.png");
					person.setAvatar(avatar);
				}

				// Create avatar in folder
				avatar.getFile("sources/users/" + folderName + "/avatars");
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean setupUserUndo(Person person) {
		String folderName = person.getUsername();
		return deleteFolder("sources/users/" + folderName + "/avatars") && deleteFolder("sources/users/" + folderName);
	}

	@Override
	public boolean deleteFolder(String path) {

		boolean success = false;

		try {
			if (path.isEmpty() == false) {
				File userDir = new File(path);

				// Delete all childFiles
				String[] childFiles = userDir.list();
				if (childFiles != null) {
					for (String s : childFiles) {
						File currentFile = new File(userDir.getPath(), s);
						currentFile.delete();
					}
				}

				// Delete folder
				success = userDir.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Login

	@Override
	public boolean login(LoginModel model) {

		boolean exist = false;
		if (model.isValid()) {

			// Get person from database
			Person person = converter.convert(dao.getByUsername(model.getUsername()));
			// Match password
			if (person != null && model.getPassword().equals(person.getPassword())
					&& authorizedClientList.containsKey(person.getId()) == false) {
				// Save login user
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
		if(person != null &&  person.getId() == client.getPerson().getId())
		{
			// Keep connection
			// Remove person
			authorizedClientList.remove(client.getPerson().getId());
			client.setPerson(null);
			return true;
		}
		return false;
	}
}
