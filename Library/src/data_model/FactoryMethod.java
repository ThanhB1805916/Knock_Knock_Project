package data_model;

import java.util.HashMap;

public interface FactoryMethod<T> {
	T createT(HashMap<String, Object> parameters);
}
