package hu.neuron.java.service.converter;

import hu.neuron.java.core.entity.Role;
import hu.neuron.java.core.entity.User;
import hu.neuron.java.service.vo.RoleVO;
import hu.neuron.java.service.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

	public static UserVO toVO(User dto) {
		if (dto == null) {
			return null;
		}
		UserVO vo = new UserVO();
		vo.setId(dto.getId());
		vo.setPassword(dto.getPassword());
		vo.setUserName(dto.getUserName());

		ArrayList<RoleVO> vos = new ArrayList<RoleVO>();

		for (Role role : dto.getRoles()) {
			vos.add(RoleConverter.toVO(role));
		}

		vo.setRoles(vos);
		return vo;
	}

	public static List<UserVO> toVO(List<User> dtos) {
		if (dtos == null) {
			return null;
		}
		List<UserVO> vos = new ArrayList<UserVO>();
		for (User dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public static User toEntity(UserVO vo) {
		if (vo == null) {
			return null;
		}
		User dto = new User();
		dto.setId(vo.getId());
		dto.setPassword(vo.getPassword());
		dto.setUserName(vo.getUserName());

		if (vo.getRoles() != null) {
			ArrayList<Role> vos = new ArrayList<Role>();
			for (RoleVO role : vo.getRoles()) {
				vos.add(RoleConverter.toEntity(role));
			}

			dto.setRoles(vos);
		}
		return dto;
	}

	public static List<User> toEntity(List<UserVO> vos) {
		if (vos == null) {
			return null;
		}
		List<User> dtos = new ArrayList<User>();
		for (UserVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}
}
