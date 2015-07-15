package hu.neuron.java.project.persistent.impl;

import java.sql.SQLException;

import hu.neuron.java.project.persistens.DataVO;
import junit.framework.TestCase;

public class DataDaoImplTest extends TestCase {

	public void testSave() throws SQLException {
		DataDaoImpl daoImpl = new DataDaoImpl(ConnectionFactory.getConnection());
		DataVO dataVO = new DataVO(null, "data1", "data2", "data3");
		daoImpl.save(dataVO);
	}

	public void testUpdate() throws SQLException {
		DataDaoImpl daoImpl = new DataDaoImpl(ConnectionFactory.getConnection());
		DataVO dataVO = new DataVO(null, "data1", "data2", "data3");
		daoImpl.save(dataVO);
		dataVO = daoImpl.findById(0l);
		System.out.println(dataVO);
		DataVO newDataVO = new DataVO(0l, "data11", "data22", "data33");
		daoImpl.save(newDataVO);
		System.out.println(newDataVO);
	}

	public void testDelete() throws SQLException {
		DataDaoImpl daoImpl = new DataDaoImpl(ConnectionFactory.getConnection());
		DataVO dataVO = new DataVO(null, "data1", "data2", "data3");
		daoImpl.save(dataVO);
		daoImpl.delete(dataVO.getId());
	}

	public void testFindById() throws SQLException {
		DataDaoImpl daoImpl = new DataDaoImpl(ConnectionFactory.getConnection());
		DataVO dataVO = new DataVO(null, "data1", "data2", "data3");
		daoImpl.findById(dataVO.getId());
	}

	public void testFindAll() throws SQLException {
		DataDaoImpl daoImpl = new DataDaoImpl(ConnectionFactory.getConnection());
		DataVO dataVO = new DataVO(null, "data1", "data2", "data3");
		daoImpl.save(dataVO);
		daoImpl.findAll();
	}

}
