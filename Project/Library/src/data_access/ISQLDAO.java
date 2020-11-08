package data_access;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

//Common interface for data access objects

public interface ISQLDAO {

	/*
	 * Read all data in a result set and put in hash map list Pair<Column, Field>
	 * Example: Pair<id, 1> 
	 * 			Pair<id, 2>
	 */
	public List<HashMap<String, Object>> convertResultSetToList(ResultSet resultSet);

	/*
	 * Add parameters to query
	 * Return a executable client just need to call execute
	 */
	public CallableStatement createCallableStatement(Connection connection, String query, Object[] parameters);

	/*
	 * Use for query that return a set of data like SELECT *
	 */
	//su dung cho SELECT *
	public List<HashMap<String, Object>> executeQuery(String query, Object[] parameters);

	/*
	 * Use for query that return row effected like DELETE, UPDATE
	  su dung cho DELETE, UPDATE
	 */
	public int executeNonQuery(String query, Object[] parameters);

	/*
	 * Use when return only 1 column and 1 row like SELECT COUNT (*)
	  su dung cho SELECT COUNT (*)
	 */
	public Object executeScalar(String query, Object[] parameters);
}
