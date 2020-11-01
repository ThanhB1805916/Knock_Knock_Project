package handler.authentication_hanlder;

import communication_standard.model.LoginModel;
import communication_standard.model.Person;

public interface IAuthenticationHandler {

	void signup(Person person);
	
	void setupUser(Person person);

	void login(LoginModel model);

	void logout(Object command);

}