import communication_standard.model.LoginModel;
import sender.send_authentication_handler.SendAuthenticationHandler;
import socket.Client;

public class StartUp {

	public static void main(String[] args) {

		Client client = new Client();
		LoginModel model = new LoginModel("admin", "1234");

		SendAuthenticationHandler handler = new SendAuthenticationHandler(client);

		boolean success = handler.login(model.getUsername(), model.getPassword());

		if (success) {

			System.out.println("Success");}
//			
//			SendManageAccountHandler ahandler = new SendManageAccountHandler(client);
//
//			User user = new User(ahandler.ViewAccount());
//			
//			FileInfo avatar = user.getPerson().getAvatar();		
//			try {
//				FileOutputStream stream = new FileOutputStream("sources//avatars//"+ avatar.getName());
//				stream.write(avatar.getData());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			Object a = user;
//		}
	}
}
