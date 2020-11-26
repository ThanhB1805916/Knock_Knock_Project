package data_access;
import java.util.HashMap;

public final class DAOFactoryVer2 {
	private static final HashMap<Class<?>, Class<?>> products = new HashMap<Class<?>, Class<?>>(); 
	
	public <T, V> void Assign(Class<T> TFrom,Class<V> TTo) {
		products.put(TFrom, TTo);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> Type)
	{
		T instance = null;
		try {
			instance = (T)products.get(Type).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
