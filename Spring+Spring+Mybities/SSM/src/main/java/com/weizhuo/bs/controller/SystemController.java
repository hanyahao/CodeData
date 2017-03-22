package com.weizhuo.bs.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.weizhuo.bs.core.common.Constants;
import com.weizhuo.bs.core.common.Message;
import com.weizhuo.bs.core.common.Page;
import com.weizhuo.bs.core.rules.Required;
import com.weizhuo.bs.core.rules.Rule;
import com.weizhuo.bs.core.rules.Validator;
import com.weizhuo.bs.service.SystemService;
import com.weizhuo.bs.util.StringUtil;
import com.weizhuo.db.dao.SupportExceptionLogMapper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;











import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("system")
public class SystemController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(SystemController.class);

    @Resource(name="systemService")
    private SystemService systemService;
    @Resource
    private SupportExceptionLogMapper exceptionLogMapper;
    
	/**
	 * 跳转到主页
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET) 
	public String home(HttpServletRequest request) throws Exception{
    	try {
    		return "system/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	/**
	 * 跳转到异常日志界面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "exceplogpage", method = RequestMethod.GET) 
	public String excepLogPage(HttpServletRequest request) throws Exception{
    	try {
    		return "log/exception_log";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 异常日志查询
	 * @param request
	 * @return
	 */
	@ResponseBody
	@SuppressWarnings("rawtypes")
    @RequestMapping(value = "queryexplog", method = RequestMethod.GET)
	public Map queryexplog(HttpServletRequest request){
		String keywords = request.getParameter("keywords");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			Page page = new Page(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			param.put("keywords", keywords.split(" "));
			if(StringUtils.isNotBlank(startDate)){
				param.put("startDate", startDate);
			}
			if(StringUtils.isNotBlank(endDate)){
				param.put("endDate", endDate );
			}
			param.put("page", page);
			List<Map<String, Object>> list = systemService.queryExcepLog(page, param);
			resultMap = this.getOk();
			resultMap.put("list", list);
			this.setPageInfo(page);
		} catch (Exception e) {
			this.saveExcepLog("system", "queryexplog", request, e);
			String msg = StringUtil.getExceptionStackTrace(e);
			logger.error(msg);
			resultMap = this.getFail("E0000", "msg.exception");
		
		}
		return resultMap;
	}
	
	

    /**
     * CC0043   第三方应用请求打开格口
     *
     * 储物柜 --> 控制中心
     *
     * 将储物柜信息还原到初始化之前的状态
     *
     * @param   request     request
     * @return  resultMap
     *
     * 参数说明：
     * terminalNumber       终端代码
     * boxId                格口代码
     */
	
//    @SuppressWarnings("rawtypes")
//    @RequestMapping(value = "OpenBox", method = RequestMethod.GET)
//    @ResponseBody
//    /** CC0043  第三方应用(OSM)请求开箱 **/
//    public Map openBox(HttpServletRequest request) {
//        try {
//            String terminalNumber = request.getParameter("terminalNumber");
//            String boxNumber = request.getParameter("boxNumber");
//
//            Validator v = new Validator();
//            // 储物柜编号，必须，正则
//            v.add("terminalNumber", terminalNumber, "terminal.param.number",
//                    new Rule[]{new Required()});
//            // 格口号
//            v.add("boxNumber", boxNumber, "box.param.number",
//                    new Rule[]{new Required()});
//
//            String vResult = v.validateResult();
//            if (StringUtils.isNotBlank(vResult)) {
//                resultMap = this.getFail("F0001", vResult);
//            } else {
//                Map<String, Object> result = terminalService.openBox(terminalNumber, boxNumber);
//
//                if (result == null) {
//                    resultMap = this.getFail("E0000", Message.get("terminal.openboxError"));
//                }
//                else {
//                    resultMap = result;
//                }
//            }
//        } catch (Exception e) {
//            String msg = StringUtil.getExceptionStackTrace(e);
//            logger.error(msg);
//            this.baseService.saveExceptionLog(request, Constants.SYSTEM_MODULE_TERMINAL,
//                    Constants.SERVICE_TERMINAL, msg);
//            resultMap = this.getFail("F0000", Message.get("msg.exception"));
//        }
//        return resultMap;
//    }


}
