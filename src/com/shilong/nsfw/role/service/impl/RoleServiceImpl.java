package com.shilong.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shilong.core.service.impl.BaseserviceImpl;
import com.shilong.nsfw.role.dao.RoleDao;
import com.shilong.nsfw.role.entity.Role;
import com.shilong.nsfw.role.service.RoleService;
@Service("roleService")
public class RoleServiceImpl extends BaseserviceImpl<Role> implements RoleService {
	
	
	private RoleDao roleDao;
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}
	
	
	@Override
	public void update(Role t) {
		roleDao.deletePrivilegeByRoleId(t.getRoleId());
		super.update(t);
	}

}
