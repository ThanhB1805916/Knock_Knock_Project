package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import handler.HandlerFactory;
import model.communication.CPackage;
import model.sendmodel.Person;

public class ClientImp implements Client {

	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Person person;

	@Override
	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public Person getPerson() {
		return person;
	}
	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructors
	// --------------------------------------------------------------------------------------------------------------------------------------------

	//Use for test case
	public ClientImp() {
	}
	
	public ClientImp(Socket socket) {
		try {
			this.socket = socket;
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Client constructor Error");
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public void run() {
		connect();
	}

	@Override
	public void connect() {
		// When still receive message
		CPackage CPackage = receive();

		while (CPackage != null) {
			// Create Handler to handle client request
			HandlerFactory.getInstance().createHandler(this, CPackage);
			// Receive new package
			CPackage = receive();
		}
	}

	@Override
	public void close() {
		try {
			socket.close();
			// Remove this client from clientList
			if (this.getPerson() != null)
				Server.getInstance().getAuthorizedClientList().remove(this.getPerson().getId());
		} catch (IOException e) {
			System.out.println("Close Error");
			e.printStackTrace();
		}
	}

	@Override
	public void send(CPackage CPackage) {
		try {
			output.reset();
			output.writeObject(CPackage);
			output.flush();
		} catch (IOException e) {
			System.out.println("Send Error");
			e.printStackTrace();
		}
	}

	@Override
	public CPackage receive() {
		try {
			Object obj = input.readObject();
			CPackage CPackage = (CPackage) obj;
			return CPackage;
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Client receive Error");
			close();
			e.printStackTrace();
		}
		return null;
	}
}
