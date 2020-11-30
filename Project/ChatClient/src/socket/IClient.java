package socket;

import model.communication.CPackage;

public interface IClient extends Runnable {

	public void connect();

	public void close();

	public void send(CPackage CPackage);

	public CPackage receive();

}
