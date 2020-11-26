package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerConnection {

	private String IP;
	private int port;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public ServerConnection() {

		// File config.Properties
		Properties prop = new Properties();

		try {
			InputStream input = new FileInputStream("commons\\configs.properties");

			// load a properties file
			prop.load(input);

			setIP((String) prop.get("IP"));

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

	public String getIP() {
		return IP;
	}

	public void setIP(String IP) {
		this.IP = IP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
