package data_access;

public interface ModelDAO<T> {

	T get(int id);

	boolean add(T T);

	boolean update(T T);
}
