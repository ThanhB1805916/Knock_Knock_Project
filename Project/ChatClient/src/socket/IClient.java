package socket;

import communication_standard.CommunicationPackage;

public interface IClient extends Runnable {

	public void connect();

	public void close();

	public void send(CommunicationPackage CPackage);

	public CommunicationPackage receive();

}
