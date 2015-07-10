package hu.neuron.java.project.business.impl;

import hu.neuron.java.project.business.BusinessO;
import hu.neuron.java.project.business.DataBVO;
import hu.neuron.java.project.persistens.DataDao;
import hu.neuron.java.project.persistens.DataVO;
import hu.neuron.java.project.persistent.impl.DataDaoImpl;

import java.util.HashSet;
import java.util.Set;

public class BusinessOImpl implements BusinessO {

	@Override
	public Long save(DataBVO dataBVO) {
		DataDao dao = new DataDaoImpl();
		DataVO dataVO = Mapper.dataBVoToDataVo(dataBVO);
		return dao.save(dataVO);
	}

	@Override
	public void update(DataBVO dataBVO) {
		DataDao dao = new DataDaoImpl();
		DataVO dataVO = Mapper.dataBVoToDataVo(dataBVO);
		dao.update(dataVO);

	}

	@Override
	public void delete(Long id) {
		DataDao dao = new DataDaoImpl();
		dao.delete(id);
	}

	@Override
	public DataBVO findById(Long id) {
		DataDao dao = new DataDaoImpl();
		DataVO dataVO = dao.findById(id);
		return Mapper.dataVoToDataBVo(dataVO);
	}

	@Override
	public Set<DataBVO> findAll() {
		Set<DataBVO> rv = new HashSet<>();
		DataDao dao = new DataDaoImpl();
		Set<DataVO> set = dao.findAll();

		for (DataVO dataVO : set) {
			rv.add(Mapper.dataVoToDataBVo(dataVO));
		}

		return rv;
	}

}
