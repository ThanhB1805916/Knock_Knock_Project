package socket;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import connection.ServerConnection;
import model.sendmodel.Person;

//Singleton
public class Server implements Runnable {

	private ServerSocket serverSocket;

	private HashMap<IClient, Person> authorizedClient_List;

	// The only Instance
	private static Server Instance = new Server();

	public HashMap<IClient, Person> getAuthorizedClient_List() {
		return authorizedClient_List;
	}

	public static Server getInstance() {
		return Instance;
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	private Server() {
		try {
			// Get port to create socket
			ServerConnection serverConenction = new ServerConnection();
			serverSocket = new ServerSocket(serverConenction.getPort());

			// Initialize hashmap
			authorizedClient_List = new HashMap<>();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Functions
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Start server
	@Override
	public void run() {
		connect();
	}

	public void connect() {
		System.out.println("Listening... ");
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();

				// Record connected socket
//				recordLog(clientSocket.toString());
				System.out.println("Client connected: " + clientSocket.toString());

				IClient client = new Client(clientSocket);

				Thread receiver = new Thread(client);
				receiver.start();
			} catch (IOException e) {
				System.out.println("Connect Error");
				e.printStackTrace();
				close();
				break;
			}
		}
	}

	public void recordLog(String connectedSocket) {
		try {
			FileWriter myWriter = new FileWriter("sources//Logs//connectedSocket_Log.txt", true);

			// Get current date time
			Date curDate = new Date();
			// Create format
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			myWriter.write(connectedSocket + " at " + formatter.format(curDate) + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("RecordLog an error has occurred.");
			e.printStackTrace();
		}
	}

	// Close connection to server
	public void close() {
		try {
			// Close server socket
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Close Error");
			e.printStackTrace();
		}
	}
}
