package handler.manage_account_handler;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import communication_standard.manage_type.EManageType;
import communication_standard.manage_type.type.EManageAccount;
import converter.PersonConverter;
import data_access.SQLDAO;
import data_access.person_access.PersonDAO;
import data_model.PersonTable;
import handler.Handler;
import model.Person;
import socket.IClient;

public class ManageAccountHandler extends Handler {

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public ManageAccountHandler(IClient client) {
		super(client);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------- Handle Request
	@Override
	public void handleRequest(CommandType request) {
		if (request.isValid()) {

			EManageAccount command = (EManageAccount) request.getCommand();

			switch (command) {
			case VIEWACCOUNT:
				ViewAccount();
				break;

			case EDITACCOUNT:
				EditAccount((Person) request.getContent());
				break;

			default:
				break;
			}

		}

	}

	private void EditAccount(Person person) {

		boolean success = false;

		if (person.isValid()) {
			// Cap nhat thong tin nguoi dung
			PersonConverter converter = new PersonConverter();
			PersonTable personTable = converter.revert(person);

			PersonDAO dao = new PersonDAO(new SQLDAO());

			if (dao.update(personTable)) {
				success = true;
			}

		}

		CommandType commandType = new CommandType(EManageAccount.EDITACCOUNT, success);
		packAndSend(commandType);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------ Pack And Send
	@Override
	public void packAndSend(CommandType commandType) {
		if (commandType.isValid()) {
			CommunicationPackage CPackage = new CommunicationPackage(EManageType.MANAGEACCOUNT, commandType);
			send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Get Account
	private void ViewAccount() {
		
		Person person = authorizedClient_List.get(client);

		CommandType commandType = new CommandType(EManageAccount.VIEWACCOUNT, person);
		packAndSend(commandType);
	}
}
