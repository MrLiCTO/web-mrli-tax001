package com.shilong.nsfw.role.action;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.shilong.core.action.BaseAction;
import com.shilong.core.constant.Constant;
import com.shilong.core.util.QueryHelper;
import com.shilong.nsfw.role.entity.Role;
import com.shilong.nsfw.role.entity.RolePrivilege;
import com.shilong.nsfw.role.entity.RolePrivilegeId;
import com.shilong.nsfw.role.service.RoleService;

public class RoleAction extends BaseAction {
	private static final long serialVersionUID = -5997217145880191215L;

	@Resource
	private RoleService roleService;

	private Role role;
	private String[] privilegeIds;
	
	private String strName;
	

	// 列表页面
	public String listUI() throws Exception {
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		//roleList = roleService.findObjects();
		
		QueryHelper qh = new QueryHelper(Role.class, "r");

		if (role != null) {

			role.setName(URLDecoder.decode(role.getName(), "utf-8"));

			qh.addCondition(" r.name like ?", "%" + role.getName() + "%");

		}

		// qh.addOrderByProperty(" u.createTime ", QueryHelper.ORDER_BY_DESC);

		pageResult = roleService.getPageResult(qh, getPageNo(), getPageSize());

		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		
		strName=role.getName();
		role=new Role();
		
		return "addUI";
	}

	// 保存新增
	public String add() {
		
		if (privilegeIds != null) {
			Set<RolePrivilege> set=new HashSet<RolePrivilege>();
			for (int i = 0; i < privilegeIds.length; i++) {
				set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
			}
			role.setRolePrivileges(set);
		}
		

		roleService.save(role);

		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		if (role != null && role.getRoleId() != null) {
			strName=role.getName();
			role = roleService.findObjectById(role.getRoleId());
			if(role.getRolePrivileges()!=null){
				int i=0;
				privilegeIds=new String[role.getRolePrivileges().size()];
				for(RolePrivilege rp:role.getRolePrivileges()){
					privilegeIds[i]=rp.getId().getCode();
					i++;
				}
			}
		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {

		if (privilegeIds != null) {
			Set<RolePrivilege> set=new HashSet<RolePrivilege>();
			for (int i = 0; i < privilegeIds.length; i++) {
				set.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
			}
			role.setRolePrivileges(set);
		}
		roleService.update(role);

		return "list";
	}

	// 删除
	public String delete() {
		strName=role.getName();
		if (role != null && role.getRoleId() != null) {
			roleService.delete(role.getRoleId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		strName=role.getName();
		if (selectedRow != null) {
			for (String id : selectedRow) {
				roleService.delete(id);
			}
		}
		return "list";
	}

	// *******************************************
	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

}
