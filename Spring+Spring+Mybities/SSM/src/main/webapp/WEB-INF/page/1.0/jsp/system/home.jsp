<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>管理平台</title>
	<link href="${pageContext.request.contextPath}/resource/static/css/select2.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/resource/static/css/jui.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/resource/static/css/platform.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/static/script/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/static/script/jquery.validate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/static/script/jquery.validate.additional-methods.osm.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/static/script/jquery.validate.messages_zh.js"></script>
	<%--<script type="text/javascript" src="${pageContext.request.contextPath}/resource/static/script/select2.js"></script>--%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/static/script/select2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/static/script/select2_locale_zh-CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/static/script/jui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/static/script/commu.js"></script>
</head>
<body>
	<div class="nav-bar">
		
		<div class="title l">&emsp;&emsp;通讯服务</div>
		<ul id="main_nav" class="nav l">
			<li>
				<a id="Nav_Exceplog" href="exceplogpage" class="tico tico-book jui-loadpage">异常日志</a>
			</li>
			<li>
				<a id="Nav_WebSock" href="websockpage" class="tico tico-book jui-loadpage">WebSock命令</a>
			</li>
			<li>
				<a id="Nav_TerminalStatus" href="terminalpage" class="tico tico-book jui-loadpage">终端状态</a>
			</li>
		</ul>

		<ul class="nav r">
			<li>
				<a href="javascript:;" class="tico16 tico16-user-w drop-down">${login_account_info["username"] }</a>
				<ul class="drop-menu">
					<%--<li><a href="/account/reqaccountinfo" class="tico12 tico12-account jui-loadpage">账号详情</a></li>--%>
					<!-- <li><a id="Password" href="javascript:;" class="tico12 tico12-set">修改密码</a></li> -->
					<li><a id="Quit" href="javascript:;" class="tico12 tico12-quit">退出系统</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<div class="body">
		<div class="main-fix">
			<div id="JUI_Main" class="main">
				<!-- Main -->
				<div style="padding:10px;color:#999;">加载中...</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<!--
		<span>关于我们</span>
		<span>|</span>
		<span>联系方式</span>
		<span>|</span>
		 -->
		<span>Copyright &copy;2017 All Rights Reserved</span>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		//全局的ajax访问，处理ajax清求时sesion超时
		$.ajaxSetup({
			cache: false,
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			complete: function(xhr, ts){
				var sessionstatus = xhr.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，
				if(sessionstatus == "timeout"){
					//如果超时就处理 ，指定要跳转的页面
					var session_timeout_text = "操作超时, 即将跳转到登录页...";
					JUI.alert(session_timeout_text);
					setTimeout("window.location.replace('/');", 2400);
				}
			}
		});
		JUI.tableListDefaults['rootProperty'] = 'list';
		JUI.tableListDefaults['statusProperty'] = 'result';
		JUI.tableListDefaults['totalProperty'] = 'count';
		JUI.tableListDefaults['limitParam'] = 'pageSize';
		JUI.tableListDefaults['pageParam'] = 'pageNo';
		// 开始jQuery.validator调试模式，正式发布时取消
		// 重写submitHandler时无效
		$.validator.setDefaults({
			debug: true,
			ignore: '.ignore',
			errorPlacement: function(error, element){
				//console.log([error, element]);
				if(element.is('input[type=checkbox], input[type=radio]')){
					error.insertAfter(element.parents('.main-form-rows').find('label:last'));
				}else{
					error.insertAfter(element);
				}
			}
		});
		// 加载导航栏第一项
		$('#main_nav').find('li:eq(0) a').trigger('click');
		/*
		JUI.menu.load('/admin/loadmenu?pid=${pid}', function(){
			JUI.menu.expandAll();
			//JUI.loadPage('${url}');
			$('#JUI_Menu').find('.menu-group:eq(0) ul li:eq(0) a').trigger('click'); // 加载菜单第一项
		});
		*/
	});
</script>
</html>