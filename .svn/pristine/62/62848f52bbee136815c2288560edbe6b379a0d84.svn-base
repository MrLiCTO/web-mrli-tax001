package com.shilong.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import com.shilong.core.dao.BaseDao;
import com.shilong.nsfw.user.entity.User;
import com.shilong.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {
	public List<User> getUserByAccountAndId(String account, String id);

	public void deleteUserRoleByUserId(Serializable id);

	public void saveUserRole(UserRole userRole);

	public List<UserRole> findUserRolesByUserId(String id);

	public List<User> findUserByAccountAndPassword(String account, String password);    
}
