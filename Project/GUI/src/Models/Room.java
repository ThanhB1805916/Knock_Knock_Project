package Models;
import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {
	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private int _id;
	private String _name;
	private ArrayList<Message> _messageList;
	private ArrayList<User> _userList;
	private FileInfo _avatar;

	// ---------------------------------------------------------------------------
	// ------------------------------- Setter - Getter
	// ---------------------------
	// ---------------------------------------------------------------------------

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public FileInfo get_avatar() {
		return _avatar;
	}

	public void set_avatar(FileInfo _avatar) {
		this._avatar = _avatar;
	}
	
	public ArrayList<Message> get_messageList() {
		return _messageList;
	}

	public void set_messageList(ArrayList<Message> _messageList) {
		this._messageList = _messageList;
	}

	public ArrayList<User> get_userList() {
		return _userList;
	}

	public void set_userList(ArrayList<User> _userList) {
		this._userList = _userList;
	}

	public Room() {
		_id = 0;
		_name = "";
		_messageList = new ArrayList<Message>();
		_userList = new ArrayList<User>();
	}
	
	//Phong moi
	//Tao phong co id, ten, danh sach thanh vien, hinh dai dien
	public Room(int id, String name, FileInfo avatar, ArrayList<User> userList) {
		_id = id;
		_name = name;
		set_avatar(avatar);
		_messageList = new ArrayList<Message>();
		_userList = userList;
	}
	
	//Phong da co san
	//Tao phong co id, ten, danh sach thanh vien, hinh dai dien
	public Room(int id, String name, ArrayList<Message> messageList, ArrayList<User> userList) {
		_id = id;
		_name = name;
		_messageList = messageList;
		_userList = userList;
	}


}
