package hu.neuron.java.project.persistent.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import hu.neuron.java.project.persistens.DataDao;
import hu.neuron.java.project.persistens.DataVO;

public class DataDaoImpl implements DataDao {

	private static HashMap<Long, DataVO> dbMock = new HashMap<>();

	public DataDaoImpl() {

	}

	@Override
	public Long save(DataVO dataVO) {
		Long id = Integer.valueOf(dbMock.size()).longValue();
		dbMock.put(id, dataVO);
		return id;
	}

	@Override
	public void update(DataVO dataVO) {
		DataVO old = dbMock.get(dataVO.getId());
		old.setData1(dataVO.getData1());
		old.setData2(dataVO.getData2());
		old.setData3(dataVO.getData3());

	}

	@Override
	public void delete(Long id) {
		dbMock.remove(id);

	}

	@Override
	public DataVO findById(Long id) {
		return dbMock.get(id);
	}

	@Override
	public Set<DataVO> findAll() {
		return new HashSet<>(dbMock.values());
	}

}
