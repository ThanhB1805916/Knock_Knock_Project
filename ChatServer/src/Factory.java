import java.util.HashMap;

public final class Factory {
private static final HashMap<Class<?>, Class<?>> products = new HashMap<Class<?>, Class<?>>(); 
	
	private Factory() {}

	public static <T, V> void Assign(Class<T> TFrom,Class<V> TTo) {
		products.put(TFrom, TTo);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(Class<T> Type)
	{
		T instance = null;
		try {
			@SuppressWarnings("deprecation")
			Object a= products.get(Type).newInstance();
			instance = (T)products.get(Type).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
