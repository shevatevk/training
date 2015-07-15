package hu.neuron.java.project.business.impl;

import hu.neuron.java.project.business.BusinessO;
import hu.neuron.java.project.business.DataBVO;
import hu.neuron.java.project.persistens.DataDao;
import hu.neuron.java.project.persistens.DataVO;
import hu.neuron.java.project.persistent.impl.DAOFactory;
import hu.neuron.java.project.persistent.impl.DataDaoImpl;

import java.util.HashSet;
import java.util.Set;

public class BusinessOImpl implements BusinessO {

	@Override
	public Long save(DataBVO dataBVO) {
		DAOFactory instance = DAOFactory.getInstance();
		Long rv = null;
		try {
			instance.beginConnectionScope();
			try {
				instance.beginTransaction();

				DataDao dao = null;
				dao = instance.getDataDaoImpl();
				DataVO dataVO = Mapper.dataBVoToDataVo(dataBVO);
				rv = dao.save(dataVO);
				instance.endTransaction();
			} catch (Exception ex) {
				ex.printStackTrace();
				instance.abortTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				instance.endConnectionScope();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rv;
	}

	@Override
	public void update(DataBVO dataBVO) {
		DAOFactory instance = DAOFactory.getInstance();
		try {
			instance.beginConnectionScope();
			try {
				instance.beginTransaction();
				DataDaoImpl dao = instance.getDataDaoImpl();
				DataVO dataVO = Mapper.dataBVoToDataVo(dataBVO);

				dao.update(dataVO);

				instance.endTransaction();
			} catch (Exception ex) {
				ex.printStackTrace();
				instance.abortTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				instance.endConnectionScope();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void delete(Long id) {
		DAOFactory instance = DAOFactory.getInstance();
		try {
			instance.beginConnectionScope();
			try {
				instance.beginTransaction();
				DataDaoImpl dao = instance.getDataDaoImpl();

				dao.delete(id);

				instance.endTransaction();
			} catch (Exception ex) {
				ex.printStackTrace();
				instance.abortTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				instance.endConnectionScope();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public DataBVO findById(Long id) {
		DataVO dataVO = null;
		DAOFactory instance = DAOFactory.getInstance();
		try {
			instance.beginConnectionScope();
			try {
				instance.beginTransaction();
				DataDaoImpl dao = instance.getDataDaoImpl();

				dataVO = dao.findById(id);

				instance.endTransaction();
			} catch (Exception ex) {
				ex.printStackTrace();
				instance.abortTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				instance.endConnectionScope();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Mapper.dataVoToDataBVo(dataVO);
	}

	@Override
	public Set<DataBVO> findAll() {
		Set<DataBVO> rv = new HashSet<>();
		DAOFactory instance = DAOFactory.getInstance();
		try {
			instance.beginConnectionScope();
			try {
				instance.beginTransaction();
				DataDaoImpl dao = instance.getDataDaoImpl();

				Set<DataVO> set = dao.findAll();

				for (DataVO dataVO : set) {
					rv.add(Mapper.dataVoToDataBVo(dataVO));
				}

				instance.endTransaction();

			} catch (Exception ex) {
				ex.printStackTrace();
				instance.abortTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				instance.endConnectionScope();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rv;
	}

}
