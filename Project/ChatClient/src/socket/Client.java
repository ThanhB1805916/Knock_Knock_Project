
package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.communication.CPackage;
import connection.ServerConnection;
import model.sendmodel.Person;

public class Client implements IClient {

	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Person Person;

	public Person getPerson() {
		return Person;
	}

	public void setPerson(Person viewPerson) {
		Person = viewPerson;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructors
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public Client() {
		try {
			ServerConnection serverConenction = new ServerConnection();
			socket = new Socket(serverConenction.getIP(), serverConenction.getPort());
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("Client constructor error");
			e.printStackTrace();
		}
	}
	
	public Client(String IP, int port)
	{
		try {
			socket = new Socket(IP, port);
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("Client constructor error");
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
			// Create Hanlder to handle client request
//			HandlerFactory.getInstance().createHanlder(this, CPackage);
			// Receive new package
			CPackage = receive();
		}
	}

	@Override
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("Close Error");
			e.printStackTrace();
		}
	}

	@Override
	public void send(CPackage CPackage) {
		try {
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
			System.out.println("Receive Error");
			e.printStackTrace();
		}
		return null;
	}
}
