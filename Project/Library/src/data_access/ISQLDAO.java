package data_access;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

//Common interface for data access objects

public interface ISQLDAO {
	public List<HashMap<String, Object>> convertResultSetToList(ResultSet resultSet);

	public CallableStatement createCallableStatement(Connection connection, String query, Object[] parameters);

	public List<HashMap<String, Object>> executeQuery(String query, Object[] parameters);

	public int executeNonQuery(String query, Object[] parameters);

	public Object executeScalar(String query, Object[] parameters);
}
