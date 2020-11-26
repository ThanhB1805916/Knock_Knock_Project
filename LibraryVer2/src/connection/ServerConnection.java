package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ServerConnection {

	private int port;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public ServerConnection() {

		// File config.Properties
		Properties prop = new Properties();

		try {
			InputStream input = new FileInputStream(LibraryPath.Path + "commons\\configs.properties");

			// load a properties file
			prop.load(input);

			setPort(Integer.parseInt((String) prop.get("port")));

			input.close();
			prop.clear();

		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Getters - Setters
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
