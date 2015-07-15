package hu.neuron.java.core.dao;

import java.util.List;

public interface BaseDAO<T, K> {

	public Long save(T dto) throws Exception;

	public void update(T dto) throws Exception;

	public void delete(Long id) throws Exception;

	public T find(Long id) throws Exception;

	public List<T> findAll() throws Exception;

	
	public K toVO(T dto);

	public List<K> toVO(List<T> dtos);

	public T toDTO(K vo);

	public List<T> toDTO(List<K> vos);

}
