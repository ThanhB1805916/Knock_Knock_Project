package connection;

public final class LibraryPath {
	
	//The current Path of system to the project + \\Library\\
	public static String Path = System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf('\\')) + "\\Library\\";

	private LibraryPath() {
	}
}
