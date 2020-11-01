package data_access;

public interface IModelDAO<T> {

	T get(int id);

	boolean add(T T);

	boolean update(T T);
}
