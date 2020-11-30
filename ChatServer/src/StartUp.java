import socket.Server;

public class StartUp {
	public static void main(String[] args) {
		Server.getInstance().connect();
		System.out.println("Hello You");
	}
}
