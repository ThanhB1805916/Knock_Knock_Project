import java.util.HashMap;

public final class Factory {

	private static final HashMap<Class<?>, Class<?>> products = new HashMap<Class<?>, Class<?>>();
	
	private Factory() {
	}
	
	private static final Factory Instance = new Factory();

	public static Factory getInstance() {
		return Instance;
	}

	public <T, V> void assign(Class<T> TFrom, Class<V> TTo) {
		products.put(TFrom, TTo);
	}

	@SuppressWarnings("unchecked")
	public <T> T of(Class<T> Type) {
		T instance = null;
		try {
			instance = (T) products.get(Type).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
