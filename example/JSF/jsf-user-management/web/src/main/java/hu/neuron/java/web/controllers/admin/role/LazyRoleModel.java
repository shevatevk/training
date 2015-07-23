package hu.neuron.java.web.controllers.admin.role;

import java.util.List;
import java.util.Map;

import hu.neuron.java.service.UserService;
import hu.neuron.java.service.vo.RoleVO;
import hu.neuron.java.service.vo.UserVO;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyRoleModel extends LazyDataModel<RoleVO> {

	private List<RoleVO> visibleRoleList;
	private UserService userService;

	public LazyRoleModel(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public RoleVO getRowData(String rowkey) {
		if (visibleRoleList != null || rowkey != null) {
			for (RoleVO roleVO : visibleRoleList) {
				if (roleVO.getId().toString().equals(rowkey)) {
					return roleVO;
				}
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(RoleVO roleVO) {
		if (roleVO == null) {
			return null;
		}
		return roleVO.getId();
	}

	@Override
	public List<RoleVO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "name";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		visibleRoleList = userService.getRoles(first / pageSize, pageSize,
				sortField, dir, filter, filterColumnName);

		int dataSize = userService.getRoleCount();

		this.setRowCount(dataSize);

		return visibleRoleList;

	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
