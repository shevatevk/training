package hu.neuron.java.core.dao;

import hu.neuron.java.common.vo.RoleVO;
import hu.neuron.java.core.dto.RoleDTO;

import java.util.List;

public interface RoleDAO extends BaseDAO<RoleDTO, RoleVO> {

	List<RoleDTO> findRolesByUserId(Long id);

	void addRoleToUser(Long roleId, Long userId);

	void removeRoleFromUser(Long roleId, Long userId);

	RoleDTO findRoleByName(String name);
}
