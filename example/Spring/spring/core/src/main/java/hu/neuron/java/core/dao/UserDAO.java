package hu.neuron.java.core.dao;

import hu.neuron.java.common.vo.UserVO;
import hu.neuron.java.core.dto.UserDTO;

public interface UserDAO extends BaseDAO<UserDTO, UserVO> {

	UserDTO findUserByName(String name);

}
