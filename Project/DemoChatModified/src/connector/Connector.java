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

import javafx.application.Platform;
import controller.Controller;
import model.communication.CPackage;
import model.communication.Request;
import model.sendmodel.Message;
import model.sendmodel.Person;
import model.sendmodel.Room;
import model.sendmodel.RoomPersonModel;

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
				 messageAction(pack.getRequest());
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
	
	private void  messageAction(Request request){
		switch (request.getName()) {
		case ADD:
			addMessageAction(request);
			break;

		default:
			break;
		}
	}

	private void addMessageAction(Request request) {
		Message message = (Message) request.getContent();
		Controller.getInstance().inputMessage(message);
	}

	private void accountAction(Request request) {
		switch (request.getName()) {
		case GET:
			getAccountAction(request);
			break;
		case UPDATE:
			accountUpdateAction(request);
			break;
		default:
			break;
		}
	}

	private void accountUpdateAction(Request request) {
		boolean result = (boolean) request.getContent();
		Controller.getInstance().inputUpdateAccountResult(result);
	}

	private void friendAction(Request request) {
		switch (request.getName()) {
		case GET:
			getFriendAction(request);
			break;
		case ADD:
			addFriendAction(request);
			break;
		case REMOVE:
			removeFriendAction(request);
			break;
		case FIND:
			findFriendAction(request);
			break;
		case ACCEPT:
			//TODO
			acceptFriendAction(request);
			break;
		case CONFORM:
			//TODO
			confirmFriendAction(request);
			break;
		case DELINE:
			//TODO
			delineFriendAcction(request);
		default:
			break;
		}
	}

	private void delineFriendAcction(Request request) {
		Person person =(Person) request.getContent();
		Platform.runLater(()->Controller.getInstance().inputDelineAddFriend(person.getName()));
	}

	private void acceptFriendAction(Request request) {
		Person person =(Person) request.getContent();
		Platform.runLater(()->Controller.getInstance().inputNewFriend(person));
	}

	private void removeFriendAction(Request request) {
		int id = (int) request.getContent();
		Platform.runLater(()->Controller.getInstance().setRemoveFriend(id));
	}

	private void confirmFriendAction(Request request) {
		Person person = (Person) request.getContent();
		Platform.runLater(()->Controller.getInstance().inputRequestConfirmFriend(person));
	}

	private void addFriendAction(Request request) {
		boolean result = (boolean) request.getContent();
		Platform.runLater(()->Controller.getInstance().inputRequestAddFriendResult(result));
	}

	private void roomAction(Request request) {
		switch (request.getName()) {
		case GET:
			getRoomAction(request);
			break;
		case ADD:
			addRoomAction(request);
			break;
		case UPDATE:
			updateRoom(request);
			break;
		case EXIT:
			exitRoomAction(request);
			break;
		case ADDMEMBER:
			addMemberRoom(request);
			break;
		default:
			break;
		}
	}

	private void addMemberRoom(Request request) {
		RoomPersonModel roomPersonModel = (RoomPersonModel) request.getContent();
		Controller.getInstance().inputAddMemberToARoom(roomPersonModel.getPerson(), roomPersonModel.getRoom().getId());
	}

	private void updateRoom(Request request) {
		Room room = (Room) request.getContent();
		Controller.getInstance().inputUpdateRoom(room);
	}
	private void exitRoomAction(Request request) {
		RoomPersonModel roomPersonModel = (RoomPersonModel) request.getContent();
		if(roomPersonModel.getPerson().getId()==Controller.getInstance().getUserId())
			Platform.runLater(()->Controller.getInstance().inputKickoutRoom(roomPersonModel.getRoom().getId()));
		else Platform.runLater(()->Controller.getInstance().inputNotifyKickOut(roomPersonModel.getRoom().getName(), roomPersonModel.getPerson().getName()));
	}

	private void addRoomAction(Request request) {
		Room room =(Room) request.getContent();
		if(room.getId()==-1)
			Platform.runLater(()->Controller.getInstance().inputCreateRoomFalse());
		else
		Platform.runLater(()->Controller.getInstance().inputNewRoomRuntime(room));
	}

	private void authenticAction(Request request) {
		switch (request.getName()) {
		case LOGIN:
			loginAuthenticAction(request);
			break;
		case SIGNUP:
			signupAuthenticAction(request);
			break;
		case LOGOUT:
			logoutAuthenticAction(request);
			break;
		default:
			break;
		}
	}

	private void signupAuthenticAction(Request request) {
		boolean result = (boolean) request.getContent();
		if(result)
			Controller.getInstance().inputVerifyAccountSuccessful();
		else Controller.getInstance().inputVerifyAccountFailure();
	}

	private void logoutAuthenticAction(Request request) {
		boolean reusult = (boolean) request.getContent();
		if (reusult)
			Controller.getInstance().inputLogoutSuccessfull();
	}

	private void loginAuthenticAction(Request request) {
		boolean reusult = (boolean) request.getContent();
		if (reusult)
			Controller.getInstance().sendRequestUser();
		else
			Controller.getInstance().inputAuthenticAccountFailure();
	}

	private void getAccountAction(Request request) {
		Person person = (Person) request.getContent();
		Controller.getInstance().inputUserFromDatabase(person);
		Controller.getInstance().sendRequestFriend();
	}

	private void getRoomAction(Request request) {
		@SuppressWarnings("unchecked")
		ArrayList<Room> arrayList = (ArrayList<Room>) request.getContent();
		for (Room room : arrayList)
			Controller.getInstance().inputNewRoom(room);
		Controller.getInstance().inputAuthenticAccountSuccessful();
	}
	
	private void getFriendAction(Request request){
		@SuppressWarnings("unchecked")
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