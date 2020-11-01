package Library_Tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import connection.ConnectionString;
import connection.ServerConnection;

public class Connection_Test {

	//Test get connection string
	@Test
	public void getConnectonString()
	{
		ConnectionString connectionString = new ConnectionString();
		
		//Check not null for url, username, password
		assertNotNull(connectionString.getUrl());
		assertNotNull(connectionString.getUsername());
		assertNotNull(connectionString.getPassword());
		
	}
	
	//Test get connection string
	@Test
	public void getServerConnection()
	{
		ServerConnection serverConnection = new ServerConnection();
		
		//Check not null for port
		assertNotNull(serverConnection.getPort());
	}
}
