package com.shilong.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


public class TestLog {
	@Test
	public void testLog() throws Exception {
		Log log=LogFactory.getLog(getClass());
		try {
			int i=1/0;
		} catch (Exception e) {
			log.error(e.getMessage());
			//e.printStackTrace();
		}
		
		int id=1;
		int count=20;
		log.debug("debug级别日志。。。。。。。。。。。。");
		log.warn("warn级别日志。。。。。。。。。。。。");
		log.info("info级别日志。。。。。。。。。。。。");
		log.error("error级别日志。。。。。。。。。。。。"+"id="+id+"...count="+count);
		log.fatal("fatal级别日志。。。。。。。。。。。。"+"id="+id+"...count="+count);
	}
}
