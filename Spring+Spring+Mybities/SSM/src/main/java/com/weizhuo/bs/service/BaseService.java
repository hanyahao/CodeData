package com.weizhuo.bs.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.weizhuo.db.dao.SupportExceptionLogMapper;
import com.weizhuo.db.model.SupportExceptionLog;

import org.apache.commons.lang.StringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BaseService {

    @Resource(name = "supportExceptionLogMapper")
    private SupportExceptionLogMapper exceptionLogMapper;

    /**
     * 保存异常日志
     *
     * @param request 请求，可空
     * @param module  发生异常的模块名
     * @param service 发生异常的Service，可空
     * @param content 异常详细信息
     * @return ExceptionWithBLOBs
     */
    public SupportExceptionLog saveExceptionLog(HttpServletRequest request, String module,  String content) {
    	SupportExceptionLog log = new SupportExceptionLog();
        Gson gson = new Gson();
        try {
            if (null != request) {
                Map<String, Object> param = new HashMap<>();
                param.put("parameter", request.getParameterMap());
                param.put("protocol", request.getProtocol());
                param.put("cookies", request.getCookies());
                param.put("url", request.getHeader("referer"));
                log.setParameters(gson.toJson(param));
            }

            log.setModule(module);
            
            log.setContent(content);
            log.setCreateDatetime(new Date());
            exceptionLogMapper.insertSelective(log);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return log;
    }

}
