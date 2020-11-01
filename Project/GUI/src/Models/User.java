package Models;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;

	private int _id;
	private String _name;
	private String _phone;
	private String _dateofbirth;
	private boolean _male;
	private String _username;
	private String _password;
	private FileInfo _avatar;
	private ArrayList<User> _friends;

	// ---------------------------------------------------------------------------
	// ------------------------------- Setter - Getter
	// ---------------------------
	// ---------------------------------------------------------------------------

	public int get_id() {
		return _id;
	}

	public String get_phone() {
		return _phone;
	}

	public void set_phone(String _phone) {
		this._phone = _phone;
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

	public String get_dateofbirth() {
		return _dateofbirth;
	}

	public void set_dateofbirth(String _dateofbirth) {
		this._dateofbirth = _dateofbirth;
	}

	public boolean is_male() {
		return _male;
	}

	public void set_male(boolean _male) {
		this._male = _male;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public FileInfo get_avatar() {
		return _avatar;
	}

	public void set_avatar(FileInfo _avatar) {
		this._avatar = _avatar;
	}

	public ArrayList<User> get_friends() {
		return _friends;
	}

	public void set_friends(ArrayList<User> _friends) {
		this._friends = _friends;
	}

	public User() {
		_id = 0;
		_name = "";
		_dateofbirth = "";
		_male = false;
		_username = "";
		_phone = new String();
		_password = "";
		_avatar = new FileInfo();
		_friends = new ArrayList<User>();
	}

	// Ham xay dung tao nguoi dung moi
	public User(int id, String name, String dateofbirth,String phone, boolean male,
			String username, String password, FileInfo avatar) {
		_id = id;
		_name = name;
		_dateofbirth = dateofbirth;
		_male = male;
		_username = username;
		_phone = phone;
		_password = password;
		_avatar = avatar;
		_friends = new ArrayList<User>();
	}

	// Ham xay dung full para
	public User(int id, String name, String dateofbirth, String phone, boolean male,
			String username, String password, FileInfo avatar,
			ArrayList<User> friends) {
		_id = id;
		_name = name;
		_dateofbirth = dateofbirth;
		_male = male;
		_phone = phone;
		_username = username;
		_password = password;
		_avatar = avatar;
		_friends = friends;
	}
	
	//ham xay dung khong avatar
	public User(int id, String name, String dateofbirth,String phone, boolean male,
			String username, String password) {
		_id = id;
		_name = name;
		_dateofbirth = dateofbirth;
		_male = male;
		_phone = phone;
		_username = username;
		_password = password;
		_avatar = new FileInfo();
		_friends = new ArrayList<User>();
	}
	public int get_year() {
		return Integer.parseInt(_dateofbirth.substring(0,4));
	}
	
	public int get_month() {
		return Integer.parseInt(_dateofbirth.substring(5,7));
	}
	
	public int get_day() {
		return Integer.parseInt(_dateofbirth.substring(8));
	}
}
