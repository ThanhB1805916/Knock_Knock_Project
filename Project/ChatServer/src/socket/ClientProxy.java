package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import communication_standard.CommunicationPackage;
import handler.HandlerFactory;

public class ClientProxy implements IClient {

	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructors
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public ClientProxy(Socket socket) {
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
		CommunicationPackage CPackage = receive();

		while (CPackage != null) {
			// Create Hanlder to handle client request
			HandlerFactory.getInstance().createHanlder(this, CPackage);
			// Receive new package
			CPackage = receive();
		}
	}

	@Override
	public void close() {
		try {
			socket.close();
			// Remove this client from clientList
			Server.getInstance().getAuthorizedClient_List().remove(this);
		} catch (IOException e) {
			System.out.println("Close Error");
			e.printStackTrace();
		}
	}

	@Override
	public void send(CommunicationPackage CPackage) {
		try {
			output.writeObject(CPackage);
			output.flush();
		} catch (IOException e) {
			System.out.println("Send Error");
			e.printStackTrace();
		}
	}

	@Override
	public CommunicationPackage receive() {
		try {
			Object obj = input.readObject();
			CommunicationPackage CPackage = (CommunicationPackage) obj;
			return CPackage;
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Client receive Error");
			close();
			e.printStackTrace();
		}
		return null;
	}
}
