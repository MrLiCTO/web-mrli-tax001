package com.shilong.nsfw.role.dao;

import com.shilong.core.dao.BaseDao;
import com.shilong.nsfw.role.entity.Role;

public interface RoleDao extends BaseDao<Role> {

	public void deletePrivilegeByRoleId(String roleId); 

}
