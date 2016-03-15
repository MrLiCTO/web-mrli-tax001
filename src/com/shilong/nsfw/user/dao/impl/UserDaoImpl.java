package com.shilong.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.shilong.core.dao.impl.BaseDaoImpl;
import com.shilong.nsfw.user.dao.UserDao;
import com.shilong.nsfw.user.entity.User;
import com.shilong.nsfw.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User>implements UserDao {

	@Override
	public List<User> getUserByAccountAndId(String account, String id) {
		String hql = "from User where account=?";
		if (StringUtils.isNotBlank(id)) {
			hql += " and id!=?";
		}
		Query q = getSession().createQuery(hql);
		q.setParameter(0, account);
		if (StringUtils.isNotBlank(id)) {
			q.setParameter(1, id);
		}
		List<User> list = q.list();
		return list;
	}

	@Override
	public void deleteUserRoleByUserId(Serializable id) {
		getSession().createQuery("delete from UserRole where id.userId=?").setParameter(0, id).executeUpdate();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);

	}


	@Override
	public List<UserRole> findUserRolesByUserId(String id) {

		List<UserRole> list=getSession().createQuery("from UserRole where id.userId=?").setParameter(0, id).list();
		
		for(UserRole ur:list){
			ur.getId().getRole().getRolePrivileges();
		}
		
		return list;
	}

	@Override
	public List<User> findUserByAccountAndPassword(String account, String password) {

		return getSession()//
				.createQuery("from User where account=? and password=?")//
				.setParameter(0, account)//
				.setParameter(1, password)//
				.list();
	}

}
