package model.converter;

import java.util.List;

// Interface for converting type T to type V and reverting back
public interface Converter<T, V> {

	public V convert(T T);

	public List<V> convert(List<T> Ts);

	public T revert(V V);

	public List<T> revert(List<V> Vs);
}
