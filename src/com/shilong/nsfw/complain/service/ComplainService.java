package com.shilong.nsfw.complain.service;

import java.util.List;
import java.util.Map;

import com.shilong.core.service.BaseService;
import com.shilong.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {
	//自动受理投诉
	
	public void autoDeal();

	public List<Map> getAnnualStatisticDataByYear(int year);
}
