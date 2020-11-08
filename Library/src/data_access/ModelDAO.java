package data_access;

public interface ModelDAO<T> {

	T get(int id);
	//Query for get
	String getQuery();

	boolean add(T T);
	//Query for add
	String addQuery();
	
	boolean update(T T);
	//Query for update
	String updateQuery();
}
