package hu.neuron.java.core.dao.impl;

import hu.neuron.java.common.vo.RoleVO;
import hu.neuron.java.core.dao.RoleDAO;
import hu.neuron.java.core.dto.RoleDTO;
import hu.neuron.java.core.dto.RoleDTO.RoleDTOMapper;

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
public class RoleDAOImpl implements RoleDAO {
	private static final Logger logger = Logger.getLogger(RoleDAOImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Long save(final RoleDTO dto) throws Exception {
		final String INSERT_SQL = "insert into role (name) values(?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL,
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, dto.getName());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	@Override
	public void update(RoleDTO dto) throws Exception {
		this.jdbcTemplate.update("update role set name = ?,  where id = ?",
				dto.getName(), dto.getId());

	}

	@Override
	public void delete(Long id) throws Exception {
		this.jdbcTemplate.update("delete from role s where id = ?", id);

	}

	@Override
	public RoleDTO find(Long id) throws Exception {
		try {
			return this.jdbcTemplate.queryForObject(
					"select id,name from role where id=?", new Object[] { id },
					new RoleDTOMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<RoleDTO> findAll() throws Exception {
		return this.jdbcTemplate.query("select id, name from role",
				new RoleDTOMapper());
	}

	@Override
	public List<RoleDTO> findRolesByUserId(Long id) {
		return this.jdbcTemplate
				.query("select r.id, r.name from role r, user_role_sw sw where sw.user_id = ? and sw.role_id = r.id",
						new Object[] { id }, new RoleDTOMapper());
	}

	@Override
	public void addRoleToUser(Long roleId, Long userId) {
		this.jdbcTemplate.update(
				"insert into user_role_sw (user_id,role_id) values(?,?)",
				userId, roleId);

	}

	@Override
	public void removeRoleFromUser(Long roleId, Long userId) {
		this.jdbcTemplate.update(
				"delete from  user_role_sw where user_id =? and role_id=?",
				userId, roleId);

	}

	@Override
	public RoleDTO findRoleByName(String name) {
		try {
			return this.jdbcTemplate.queryForObject(
					"select id,name from role where name=?",
					new Object[] { name }, new RoleDTOMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public RoleVO toVO(RoleDTO dto) {
		if (dto == null) {
			return null;
		}
		RoleVO vo = new RoleVO();
		vo.setId(dto.getId());
		vo.setName(dto.getName());
		return vo;
	}

	public List<RoleVO> toVO(List<RoleDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<RoleVO> vos = new ArrayList<RoleVO>();
		for (RoleDTO dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public RoleDTO toDTO(RoleVO vo) {
		if (vo == null) {
			return null;
		}
		RoleDTO dto = new RoleDTO();
		dto.setId(vo.getId());
		dto.setName(vo.getName());
		return dto;
	}

	public List<RoleDTO> toDTO(List<RoleVO> vos) {
		if (vos == null) {
			return null;
		}
		List<RoleDTO> dtos = new ArrayList<RoleDTO>();
		for (RoleVO vo : vos) {
			dtos.add(toDTO(vo));
		}
		return dtos;
	}

}
