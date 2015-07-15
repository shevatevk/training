package hu.neuron.java.common.service;

import hu.neuron.java.common.vo.UserVO;

public interface UserService {

	UserVO findUserByName(String name) throws Exception;

	void registrationUser(UserVO userVO) throws Exception;
}
