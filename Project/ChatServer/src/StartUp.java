import socket.Server;


public class StartUp {

	public static void main(String[] args) {

//		Server.getInstance().connect();	
		String str1 = "Hello World";
		String str2 = "Hello World";
		
		System.out.println(str1 == str2);
		System.out.println(str1.equals(str2));
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
	}
}
