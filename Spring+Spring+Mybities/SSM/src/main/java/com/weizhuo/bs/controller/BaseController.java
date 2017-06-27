package com.weizhuo.bs.controller;

import com.google.gson.Gson;
import com.weizhuo.bs.core.common.Page;
import com.weizhuo.bs.service.BaseService;
import com.weizhuo.bs.util.StringUtil;
import com.weizhuo.db.dao.SupportExceptionLogMapper;

import com.weizhuo.db.model.SupportExceptionLog;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected Map<String, Object> resultMap;

    @Resource(name="baseService")
    BaseService baseService;
    
    @Resource
    private SupportExceptionLogMapper exceptionLogMapper;
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        
        if (resultMap!=null){
        	resultMap.clear();
        }
    }
    
    protected Map<String, Object> getFail(String code, String message){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", code);
        resultMap.put("message", message);
    	return resultMap;
    }
    
    protected Map<String, Object> getOk(){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("result", "OK");
    	resultMap.put("message", "success");
        resultMap.put("code", "00000");
    	return resultMap;
    }
    
    @SuppressWarnings("rawtypes")
    public void setPageInfo(Page page) throws Exception{
		resultMap.put("pageNo", page.getPageNo());
		resultMap.put("pageSize", page.getPageSize());
		resultMap.put("count", page.getCount());
		resultMap.put("totalPage", page.getTotalPage());
		if(page.getRecords() != null){
			resultMap.put("totalResult", page.getRecords().size());
			resultMap.put("list", page.getRecords());
		}
	}
    
    public void saveExcepLog(String module, String method, HttpServletRequest request, Exception ex){
    	
		baseService.saveExceptionLog(request, module, StringUtil.getExceptionStackTrace(ex));
    }

}
