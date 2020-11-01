package Models;
import java.io.Serializable;


public class Message implements Serializable {
	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private User sender;
	private FileInfo content;
	private String sendDate;
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public FileInfo getContent() {
		return content;
	}
	public void setContent(FileInfo content) {
		this.content = content;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	
}
