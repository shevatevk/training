package hu.neuron.java.project.business;

import java.util.Set;

public interface BusinessO {

	Long save(DataBVO dataVO);

	void update(DataBVO dataVO);

	void delete(Long id);

	DataBVO findById(Long id);

	Set<DataBVO> findAll();
}
