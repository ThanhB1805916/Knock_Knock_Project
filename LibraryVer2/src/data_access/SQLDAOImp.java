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
import data_model.ModelTable;

public class SQLDAOImp implements SQLDAO {

	private ConnectionString connectionString;

	public SQLDAOImp(ConnectionString connectionString) {
		this.connectionString = connectionString;
	}

	@Override
	public List<HashMap<String, Object>> convertResultSetToList(ResultSet resultSet) {

		List<HashMap<String, Object>> dataTable = new ArrayList<HashMap<String, Object>>();

		try {
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
				dataTable.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dataTable;

	}

	@Override
	public CallableStatement createCallableStatement(Connection connection, String query, Object[] parameters) {

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
			e.printStackTrace();
		}

		return callableStatement;
	}

	@Override
	public <T extends ModelTable<T>> List<T> executeQuery(Class<T> currentClass, String query, Object[] parameters) {
		List<T> modelList = null;
		try {
			// Try connect to database
			Connection connection = DriverManager.getConnection(connectionString.getUrl(),
					connectionString.getUsername(), connectionString.getPassword());

			// Get callableSatement
			CallableStatement callableStatement = createCallableStatement(connection, query, parameters);
			ResultSet resultSet = callableStatement.executeQuery();

			// If have value
			if (resultSet.isBeforeFirst()) {
				List<HashMap<String, Object>> dataTable = convertResultSetToList(resultSet);
				for (HashMap<String, Object> row : dataTable) {
					try {
						T model = currentClass.newInstance();
						model.fillData(row);
						modelList = new ArrayList<>();
						modelList.add(model);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

			resultSet.close();
			callableStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return modelList;
	}

	@Override
	public int executeNonQuery(String query, Object[] parameters) {

		// Store effected rows
		int cntRow = 0;
		try {
			// Try connect to database
			Connection connection = DriverManager.getConnection(connectionString.getUrl(),
					connectionString.getUsername(), connectionString.getPassword());

			CallableStatement callableStatement = createCallableStatement(connection, query, parameters);
			cntRow = callableStatement.executeUpdate();

			callableStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cntRow;
	}

	@Override
	public Object executeScalar(String query, Object[] parameters) {
		Object output = null;
		try {
			// Try connect to database
			Connection connection = DriverManager.getConnection(connectionString.getUrl(),
					connectionString.getUsername(), connectionString.getPassword());

			CallableStatement callableStatement = createCallableStatement(connection, query, parameters);
			ResultSet resultSet = callableStatement.executeQuery();

			// Get the only value
			resultSet.next();
			output = resultSet.getObject(1);

			resultSet.close();
			callableStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}

}
