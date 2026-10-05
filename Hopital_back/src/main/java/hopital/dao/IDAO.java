package hopital.dao;

import java.util.List;

public interface IDAO<T,K> {

	String urlBdd = "jdbc:mysql://localhost:3306/hopital";
	String loginBdd = "root";
	String passwordBdd = "root";
	
	public List<T> findAll();
	public T findById(K id);
	public T insert(T obj);
	public T update(T obj);
	public void deleteById(K id);
	
}
