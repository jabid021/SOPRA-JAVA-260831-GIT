package quest.dao;

import java.util.List;

public interface IDAO<T,K> {

	
	public List<T> findAll();
	public T findById(K id);
	public T save(T obj);
	public void deleteById(K id);
	public void delete(T obj);
}
