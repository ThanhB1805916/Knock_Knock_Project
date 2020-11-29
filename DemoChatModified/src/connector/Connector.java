package connector;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

import javax.security.auth.callback.ConfirmationCallback;

import controller.Controller;
import model.communication.CPackage;
import model.communication.Request;
import model.sendmodel.Person;
import model.sendmodel.Room;

public class Connector {

	/*----------------------------------------------------------------------------------
	 *
	 *----------------------------------->   ATTRIBUTES    <-----------------------------
	 *
	 *-----------------------------------------------------------------------------------*/
	private Socket socket;

	private ObjectInputStream input;
	private ObjectOutputStream output;

	private Properties properties;
	private Thread streamThread, soldier;

	private Queue<Object> conveyor = new LinkedList<Object>();

	private boolean status = false;
	private boolean isDoing = false;
	{
		try {
			properties = new Properties();
			properties.load(new FileReader("commons\\configs.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*----------------------------------------------------------------------------------
	 *
	 *----------------------------------->  	CONSTRUCTOR    <----------------------------
	 *
	 *-----------------------------------------------------------------------------------*/
	public Connector() {
		System.out.println(getIP() + ": " + getPort());
		try {
			socket = new Socket(getIP(), getPort());
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.err.println("Connect fail!");
			e.printStackTrace();
		}
	}

	/*----------------------------------------------------------------------------------
	 *
	 *----------------------------------->   METHODS    <--------------------------------
	 *
	 *-----------------------------------------------------------------------------------*/
	public void start() {
		this.status = true;
		streamThread = new Thread(() -> {
			inputStreamFromServer();
		});
		streamThread.start();
	}

	public void stop() {
		status = false;
	}

	public boolean sendCPackage(CPackage pack) {
		try {
			output.writeObject(pack);
			output.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	/*----------------------------------------------------------------------------------
	 *
	 *----------------------------------->     MODULES     <-----------------------------
	 *
	 *-----------------------------------------------------------------------------------*/
	private void inputStreamFromServer() {
		while (status) {
			try {
				Object data = input.readObject();
				conveyor.add(data);
				wakeUp();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				status = false;
				break;
			}
		}
		try {
			input.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getIP() {
		return properties.get("IP").toString();
	}

	private int getPort() {
		return Integer.parseInt(properties.get("port").toString());
	}

	private void wakeUp() {
		soldier = new Thread(() -> {
			callWakeUp();
		});
		soldier.start();
	}

	private void callWakeUp() {
		if (!isDoing) {
			isDoing = !isDoing;
			takeAndDistribute();
		}
	}

	private void takeAndDistribute() {
		while (conveyor.peek() != null) {
			CPackage pack = (CPackage) conveyor.poll();
			switch (pack.getType()) {
			case ACCOUNT:
				accountAction(pack.getRequest());
				break;
			case AUTHENTICATION:
				authenticAction(pack.getRequest());
				break;
			case FRIEND:
				friendAction(pack.getRequest());
				break;
			case MESSAGE:
				// messageAction();
				break;
			case ROOM:
				roomAction(pack.getRequest());
				break;
			default:
				break;
			}
		}
		isDoing = false;
	}

	private void accountAction(Request request) {
		switch (request.getName()) {
		case SIGNUP:
			// TODO
			break;
		case LOGIN:
			// TODO
			break;
		case LOGOUT:
			// TODO
			break;
		case GET:
			getAccountAction(request);
			break;
		case ADD:
			// TODO
			break;
		case UPDATE:
			accountUpdateAction(request);
			break;
		case REMOVE:
			// TODO
			break;
		case EXIT:
			// TODO
			break;
		case FIND:
			// TODO
			break;
		}
	}

	private void accountUpdateAction(Request request) {
		boolean result = (boolean) request.getContent();
		Controller.getInstance().updateAccountResult(result);
	}

	private void friendAction(Request request) {
		switch (request.getName()) {
		case SIGNUP:
			// TODO
			break;
		case LOGIN:
			// TODO
			break;
		case LOGOUT:
			// TODO
			break;
		case GET:
			getFriendAction(request);
			break;
		case ADD:
			addFriendAction(request);
			break;
		case UPDATE:
			// TODO
			break;
		case REMOVE:
			// TODO
			break;
		case EXIT:
			// TODO
			break;
		case FIND:
			findFriendAction(request);
			break;
		case ACCEPT:
//			acceptFriendAction(request);
			break;
		case CONFORM:
			confirmFriendAction(request);
			break;
		}
	}

	private void confirmFriendAction(Request request) {
		Person person = (Person) request.getContent();
		Controller.getInstance().requestConfirmFriend(person);
	}

	private void addFriendAction(Request request) {
		boolean result = (boolean) request.getContent();
		Controller.getInstance().requestAddFriendResult(result);
	}

	private void roomAction(Request request) {
		switch (request.getName()) {
		case SIGNUP:
			// TODO
			break;
		case LOGIN:
			// TODO
			break;
		case LOGOUT:
			// TODO
			break;
		case GET:
			getRoomAction(request);
			break;
		case ADD:
			// TODO
			break;
		case UPDATE:
			// TODO
			break;
		case REMOVE:
			// TODO
			break;
		case EXIT:
			// TODO
			break;
		case FIND:
			// TODO
			break;
		}
	}

	private void authenticAction(Request request) {
		boolean reusult = (boolean) request.getContent();
		if (reusult) {
			Controller.getInstance().sendRequestUser();
		} else
			Controller.getInstance().authenticAccountFailure();
	}

	private void getAccountAction(Request request) {
		Person person = (Person) request.getContent();
		System.out.println(person.getName());
		Controller.getInstance().inputUserFromDatabase(person);
		Controller.getInstance().sendRequestFriend();
	}

	private void getRoomAction(Request request) {
		ArrayList<Room> arrayList = (ArrayList<Room>) request.getContent();
		for (Room room : arrayList)
			Controller.getInstance().inputNewRoom(room);
		Controller.getInstance().authenticAccountSuccessful();
	}
	
	private void getFriendAction(Request request){
		ArrayList<Person> arrayList = (ArrayList<Person>) request.getContent();
		Controller.getInstance().inputFriendList(arrayList);
		Controller.getInstance().sendRequestRoom();
	}
	
	private void findFriendAction(Request request){
		Person person = (Person) request.getContent();
		ArrayList<Person> list = new ArrayList<Person>();
		if(person!=null)
			list.add(person);
		Controller.getInstance().inputSearchPerson(list);
	}
}