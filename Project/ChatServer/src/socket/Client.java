package socket;

import model.communication.CPackage;
import model.sendmodel.Person;

public interface Client extends Runnable {
	
	public void setPerson(Person person);
	
	public Person getPerson();
	
	public void connect();

	public void close();

	public void send(CPackage CPackage);

	public CPackage receive();

}
