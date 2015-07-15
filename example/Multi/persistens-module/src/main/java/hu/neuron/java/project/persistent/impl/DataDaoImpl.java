package hu.neuron.java.project.persistent.impl;

import hu.neuron.java.project.persistens.DataDao;
import hu.neuron.java.project.persistens.DataVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DataDaoImpl implements DataDao {

	private static HashMap<Long, DataVO> dbMock = new HashMap<>();
	private Connection connection;

	private DataDaoImpl() {

	}

	public DataDaoImpl(Connection connection) {
		this.connection = connection;

	}

	@Override
	public Long save(DataVO dataVO) {

		Long rv = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO DATA( data1,data2,data3 ) VALUES ( ?,?,? ) ";
			statement = connection.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, dataVO.getData1());
			statement.setString(2, dataVO.getData2());
			statement.setString(3, dataVO.getData3());
			statement.executeUpdate();

			rs = statement.getGeneratedKeys();

			if (rs.next()) {
				rv = rs.getLong(1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rv;
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
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Set<DataVO> rv = new HashSet<DataVO>();
		try {

			String sql = "select * from DATA";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String data1 = resultSet.getString("data1");
				String data2 = resultSet.getString("data2");
				String data3 = resultSet.getString("data3");
				rv.add(new DataVO(id, data1, data2, data3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return rv;
	}

}
