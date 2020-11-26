package data_model;

import java.util.HashMap;

public interface ModelTable<T> {
	T fillData(HashMap<String, Object> parameters);
}
