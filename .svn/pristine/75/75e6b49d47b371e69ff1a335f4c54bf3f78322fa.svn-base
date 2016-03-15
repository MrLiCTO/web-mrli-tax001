package com.shilong.nsfw.complain.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shilong.core.service.impl.BaseserviceImpl;
import com.shilong.core.util.QueryHelper;
import com.shilong.nsfw.complain.dao.ComplainDao;
import com.shilong.nsfw.complain.entity.Complain;
import com.shilong.nsfw.complain.service.ComplainService;
@Service("complainService")
public class ComplainServiceImpl extends BaseserviceImpl<Complain>implements ComplainService {
	private ComplainDao complainDao;
	
	@Resource
	public void setComplainDao(ComplainDao complainDao) {
		super.setBaseDao(complainDao);
		this.complainDao = complainDao;
	}

	@Override
	public void autoDeal() {
		QueryHelper qh=new QueryHelper(Complain.class,"c");
		
		
		
		qh.addCondition("c.state=?",Complain.COMPLAIN_STATE_UNDONE);
		
		Calendar cd=Calendar.getInstance();
		
		cd.set(Calendar.DAY_OF_MONTH, 1);
		
		cd.set(Calendar.HOUR_OF_DAY, 0);
		
		cd.set(Calendar.MINUTE, 0);
		
		
		cd.set(Calendar.SECOND, 0);
		
		
		
		
		
		qh.addCondition("c.compTime<?",cd.getTime());
		
		
		List<Complain> list=this.findObjects(qh);
		
		if(list!=null&&list.size()>0){
			for(Complain comp:list){
				comp.setState(Complain.COMPLAIN_STATE_INVALID);
				this.update(comp);
			}
		}
		
	}

	@Override
	public List<Map> getAnnualStatisticDataByYear(int year) {
		List<Map> resList=new ArrayList<Map>();
		
		List<Object[]> list=complainDao.getAnnualStatisticDataByYear(year);
		
		if(list!=null&&list.size()>0){
			int curYear=Calendar.getInstance().get(Calendar.YEAR);
			int curMonth=Calendar.getInstance().get(Calendar.MONTH)+1;
			boolean isCurYear=(curYear==year);
			int temMonth=0;
			Map<String,Object> map;
			for(Object[] obj:list){
				temMonth=Integer.valueOf(obj[0]+"");
				map=new HashMap<String,Object>();
				map.put("label",temMonth+"月");
				if(isCurYear){
					if(temMonth>curMonth){
						
						map.put("value","");
					}else{
						map.put("value",obj[1]==null?"0":obj[1]);
					}
				}else{
					map.put("value",obj[1]==null?"0":obj[1]);
				}
				resList.add(map);
			}
		}
		return resList;
	}
}
