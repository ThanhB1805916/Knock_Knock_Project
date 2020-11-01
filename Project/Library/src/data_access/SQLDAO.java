package data_access;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import connection.ConnectionString;

public class SQLDAO implements ISQLDAO {

	// Convert a resultset to a hash list
	@Override
	public List<HashMap<String, Object>> convertResultSetToList(ResultSet resultSet) {

		// Create a list store columns and their values
		List<HashMap<String, Object>> resultSetList = new ArrayList<HashMap<String, Object>>();

		try {
			// Get result metadata
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			// Get the number of columns
			int colSize = resultSetMetaData.getColumnCount();

			// Read resultSet
			while (resultSet.next()) {
				// Each row is a hashmap<column, value>
				HashMap<String, Object> row = new HashMap<String, Object>(colSize);

				// Read each columns put in hash
				for (int i = 1; i <= colSize; i++) {
					row.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
				}

				// Add row to list
				resultSetList.add(row);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultSetList;

	}

	// Create a callable statement base on connection, query and parameters
	@Override
	public CallableStatement createCallableStatement(Connection connection, String query, Object[] parameters) {

		// Create callablestatement
		CallableStatement callableStatement = null;
		try {
			callableStatement = connection.prepareCall(query);
			// Set parameters
			if (parameters != null) {
				for (int i = 1; i <= parameters.length; i++) {
					if (parameters[i - 1] != null) {
						callableStatement.setString(i, parameters[i - 1].toString());
					} else {
						callableStatement.setString(i, null);
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return callableStatement;
	}

	// ExecuteQuery return a list of hashmap<column, values> by query and parameters
	// Use for SELECT query
	@Override
	public List<HashMap<String, Object>> executeQuery(String query, Object[] parameters) {

		// Create a connection String
		ConnectionString connectionString = new ConnectionString();

		// Create a list
		List<HashMap<String, Object>> resultSetList = null;

		try {
			// Try connect to database
			Connection connection = DriverManager.getConnection(connectionString.getUrl(),
					connectionString.getUsername(), connectionString.getPassword());

			// Get callableSatement
			CallableStatement callableStatement = createCallableStatement(connection, query, parameters);

			// Create result set
			ResultSet resultSet = callableStatement.executeQuery();

			// Convert to hash list
			resultSetList = convertResultSetToList(resultSet);

			// Close result
			resultSet.close();

			// Close statement
			callableStatement.close();

			// Close connection
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultSetList;
	}

	// ExecuteNonQuery return numbers of rows effect
	// Use for DELETE, UPDATE
	@Override
	public int executeNonQuery(String query, Object[] parameters) {
		// Create a connection String
		ConnectionString connectionString = new ConnectionString();

		// Store effected rows
		int cnt = 0;

		try {
			// Try connect to database
			Connection connection = DriverManager.getConnection(connectionString.getUrl(),
					connectionString.getUsername(), connectionString.getPassword());

			// Get callableSatement
			CallableStatement callableStatement = createCallableStatement(connection, query, parameters);

			// Get effected rows
			cnt = callableStatement.executeUpdate();

			// Close statement
			callableStatement.close();

			// Close connection
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cnt;
	}

	// ExecuteScalar return 1 object
	// Use for SELECT COUNT (*)
	@Override
	public Object executeScalar(String query, Object[] parameters) {
		// Create a connection String
		ConnectionString connectionString = new ConnectionString();

		// Store 1 output value
		Object output = null;

		try {
			// Try connect to database
			Connection connection = DriverManager.getConnection(connectionString.getUrl(),
					connectionString.getUsername(), connectionString.getPassword());

			// Get callableSatement
			CallableStatement callableStatement = createCallableStatement(connection, query, parameters);

			// Create result set
			ResultSet resultSet = callableStatement.executeQuery();

			// Go to first row
			resultSet.next();

			// Get the object
			output = resultSet.getObject(1);

			// Close result
			resultSet.close();

			// Close statement
			callableStatement.close();

			// Close connection
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;
	}

}
