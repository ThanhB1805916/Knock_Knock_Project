package sender.send_authentication_handler;

import communication_standard.model.Person;

public interface ISendAuthenticationHandler {

	boolean signup(Person person);

	boolean login(String username, String password);

	void logout();

}