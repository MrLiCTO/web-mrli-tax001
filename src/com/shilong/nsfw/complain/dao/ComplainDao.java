package com.shilong.nsfw.complain.dao;

import java.util.List;

import com.shilong.core.dao.BaseDao;
import com.shilong.nsfw.complain.entity.Complain;

public interface ComplainDao extends BaseDao<Complain> {

	public List<Object[]> getAnnualStatisticDataByYear(int year);

}
