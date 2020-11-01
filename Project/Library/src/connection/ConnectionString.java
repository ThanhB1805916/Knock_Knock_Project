package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConnectionString {
	private String url;
	private String username;
	private String password;

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------- Constructor
	// --------------------------------------------------------------------------------------------------------------------------------------------

	// Create connection string
	public ConnectionString() {

		// File config.Properties
		Properties prop = new Properties();

		try {
			InputStream input = new FileInputStream(LibraryPath.Path + "commons\\configs.properties");
			
			// load a properties file
			prop.load(input);

			url = (String) prop.get("url");
			username = (String) prop.get("username");
			password = (String) prop.get("password");

			input.close();
			prop.clear();

		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------------
	// ----------------------------------------------------------- Getters - Setters
	// --------------------------------------------------------------------------------------------------------------------------------------------

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
