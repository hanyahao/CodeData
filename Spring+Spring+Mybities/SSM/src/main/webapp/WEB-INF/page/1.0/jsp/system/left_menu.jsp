<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>  
<div class="menu-group">
	<a href="javascript:;" class="menu-group-head">
		<span class="ico24 ico24-box"></span>
		<span class="menu-name">日志管理</span>
		<span class="menu-arrow"></span>
	</a>
	<ul>
		<li><a href="/contract/reqttmgmt" class="jui-loadpage" data-fn="MgmtTemplateType.listInit">异常日志</a></li>
		<li><a href="/contract/reqtemplatemgmt" class="jui-loadpage" data-fn="MgmtTemplate.listInit">命令日志</a></li>
	</ul>
</div>