package sender.send_manage_account_handler;

import communication_standard.CommandType;
import communication_standard.CommunicationPackage;
import communication_standard.manage_type.EManageType;
import communication_standard.manage_type.type.EManageAccount;
import communication_standard.model.Person;
import sender.SendHandler;
import socket.Client;

public class SendManageAccountHandler extends SendHandler {

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public SendManageAccountHandler(Client client) {
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
			CommunicationPackage CPackage = new CommunicationPackage(EManageType.MANAGEACCOUNT, commandType);
			send(CPackage);
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- View Account

	public Person ViewAccount() {

		CommandType commandType = new CommandType(EManageAccount.VIEWACCOUNT, EManageAccount.VIEWACCOUNT);

		packAndSend(commandType);

		Person person = (Person) receiveAndUnPack(client.receive());

		return person;
	}

}
