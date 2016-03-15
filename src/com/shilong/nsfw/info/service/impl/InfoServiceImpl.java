package com.shilong.nsfw.info.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shilong.core.service.impl.BaseserviceImpl;
import com.shilong.nsfw.info.dao.InfoDao;
import com.shilong.nsfw.info.entity.Info;
import com.shilong.nsfw.info.service.InfoService;
@Service("infoService")
public class InfoServiceImpl extends BaseserviceImpl<Info> implements InfoService{
	
	private InfoDao infoDao;
	
	
	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
	
	
	
	
	
//	@Resource
//	private InfoDao infoDao;
//	
//	@Override
//	public void save(Info info) {
//		infoDao.save(info);
//		
//	}
//
//	@Override
//	public void delete(Serializable id) {
//		infoDao.delete(id);
//		
//	}
//
//	@Override
//	public void update(Info info) {
//		infoDao.update(info);
//		
//	}
//
//	@Override
//	public Info findObjectById(Serializable id) {
//		return infoDao.findObjectById(id);
//	}
//
//	@Override
//	public List<Info> findObjects() {
//		return infoDao.findObjects();
//	}

}
