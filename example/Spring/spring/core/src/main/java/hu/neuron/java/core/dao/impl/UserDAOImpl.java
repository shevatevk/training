package hu.neuron.java.core.dao.impl;

import hu.neuron.java.common.vo.UserVO;
import hu.neuron.java.core.dao.RoleDAO;
import hu.neuron.java.core.dao.UserDAO;
import hu.neuron.java.core.dto.RoleDTO;
import hu.neuron.java.core.dto.UserDTO;
import hu.neuron.java.core.dto.UserDTO.UserDTOMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Autowired
	RoleDAO roleDAO;

	@Override
	public Long save(final UserDTO dto) throws Exception {
		final String INSERT_SQL = "insert into user (name,password) values(?,?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, dto.getUserName());
				ps.setString(2, dto.getPassword());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(UserDTO dto) throws Exception {
		this.jdbcTemplate.update(
				"update user set name = ?, password = ? where id = ?",
				dto.getUserName(), dto.getPassword(), dto.getId());

	}

	@Override
	public void delete(Long id) throws Exception {
		this.jdbcTemplate.update("delete from user s where id = ?", id);

	}

	@Override
	public UserDTO find(Long id) throws Exception {
		try {
			return this.jdbcTemplate.queryForObject(
					"select id ,name, password from user where id=?",
					new Object[] { id }, new UserDTOMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<UserDTO> findAll() throws Exception {
		return this.jdbcTemplate.query("select id,name, password from user",
				new UserDTOMapper());
	}

	@Override
	public UserDTO findUserByName(String name) {
		try {
			return this.jdbcTemplate.queryForObject(
					"select id ,name, password from user where name=?",
					new Object[] { name }, new UserDTOMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public UserVO toVO(UserDTO dto) {
		if (dto == null) {
			return null;
		}
		UserVO vo = new UserVO();
		vo.setId(dto.getId());
		vo.setPassword(dto.getPassword());
		vo.setUserName(dto.getUserName());
		List<RoleDTO> roles = roleDAO.findRolesByUserId(dto.getId());
		vo.setRoles(roleDAO.toVO(roles));
		return vo;
	}

	public List<UserVO> toVO(List<UserDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<UserVO> vos = new ArrayList<UserVO>();
		for (UserDTO dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public UserDTO toDTO(UserVO vo) {
		if (vo == null) {
			return null;
		}
		UserDTO dto = new UserDTO();
		dto.setId(vo.getId());
		dto.setPassword(vo.getPassword());
		dto.setUserName(vo.getUserName());
		return dto;
	}

	public List<UserDTO> toDTO(List<UserVO> vos) {
		if (vos == null) {
			return null;
		}
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for (UserVO vo : vos) {
			dtos.add(toDTO(vo));
		}
		return dtos;
	}
}
