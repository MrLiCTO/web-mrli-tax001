package com.shilong.nsfw.role.dao.impl;

import com.shilong.core.dao.impl.BaseDaoImpl;
import com.shilong.nsfw.role.dao.RoleDao;
import com.shilong.nsfw.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void deletePrivilegeByRoleId(String roleId) {
		getSession().createQuery("delete from RolePrivilege where id.role.roleId=?").setParameter(0, roleId).executeUpdate();
		
	}

	

}
