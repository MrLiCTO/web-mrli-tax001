package com.shilong.nsfw.complain.dao.impl;

import java.util.List;

import com.shilong.core.dao.impl.BaseDaoImpl;
import com.shilong.nsfw.complain.dao.ComplainDao;
import com.shilong.nsfw.complain.entity.Complain;

public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

	@Override
	public List<Object[]> getAnnualStatisticDataByYear(int year) {
		StringBuffer sb=new StringBuffer();
		sb.append("select imonth,count(comp_id) ")//
		.append("from tmonth left join complain on imonth=month(comp_time) ")//
		.append("and year(comp_time)=? ")//
		.append("group by imonth order by imonth");
		
		
		
		return getSession().createSQLQuery(sb.toString()).setParameter(0,year).list();
	}

}
