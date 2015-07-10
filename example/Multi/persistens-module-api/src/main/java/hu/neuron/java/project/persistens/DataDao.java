package hu.neuron.java.project.persistens;

import java.util.Set;

public interface DataDao {

	Long save(DataVO dataVO);

	void update(DataVO dataVO);

	void delete(Long id);

	DataVO findById(Long id);

	Set<DataVO> findAll();

}
