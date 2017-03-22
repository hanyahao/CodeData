package com.weizhuo.bs.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.weizhuo.bs.core.common.Page;
import com.weizhuo.db.dao.SupportExceptionLogMapper;


import org.apache.commons.lang.StringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SystemService {

    @Resource(name = "supportExceptionLogMapper")
    private SupportExceptionLogMapper exceptionLogMapper;
    


	
	
	public List<Map<String, Object>> queryExcepLog(Page page, Map<String, Object> param){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Long count = 0L;
//		List<Map<String, Object>> list = exceptionLogMapper.queryExcepLog(param);
//		Long count = exceptionLogMapper.queryExcepLogCount(param);
		page.setCount(count);
		return list;
	}
	

	

	

	
}
