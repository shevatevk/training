package hu.neuron.java.service.converter;

import hu.neuron.java.core.entity.Role;
import hu.neuron.java.service.vo.RoleVO;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter {
	public static RoleVO toVO(Role dto) {
		if (dto == null) {
			return null;
		}
		RoleVO vo = new RoleVO();
		vo.setId(dto.getId());
		vo.setName(dto.getName());
		return vo;
	}

	public static List<RoleVO> toVO(List<Role> dtos) {
		if (dtos == null) {
			return null;
		}
		List<RoleVO> vos = new ArrayList<RoleVO>();
		for (Role dto : dtos) {
			vos.add(toVO(dto));
		}
		return vos;
	}

	public static Role toEntity(RoleVO vo) {
		if (vo == null) {
			return null;
		}
		Role dto = new Role();
		dto.setId(vo.getId());
		dto.setName(vo.getName());
		return dto;
	}

	public static List<Role> toEntity(List<RoleVO> vos) {
		if (vos == null) {
			return null;
		}
		List<Role> dtos = new ArrayList<Role>();
		for (RoleVO vo : vos) {
			dtos.add(toEntity(vo));
		}
		return dtos;
	}

}
