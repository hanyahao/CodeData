
var expLogList = [];
var ExpLog = {
	listInit: function(){
		var _this = this;
		var list = new JUI.tableList({
			url: '/system/queryexplog',
			
			transdata: _this.transList
		});
		list.load();
	},

	transList: function(data){
		var result = [];
		expLogList = data;
		for(var i=0; i<data.length; i++){
			result.push([
				data[i].module,
				data[i].method,
				'<div class="wpnormal">' + data[i].parameter + '</div>',
				'<div class="wpnormal">' + data[i].content + '</div>',
				data[i].create_datetime,
				'<a class="jui-view" href="javascript:;" onClick="logDetailInit('+i+')" title="详情">详情</a>'
			]);
		}
		return result;
	}
};


function logDetailInit(i){
	var htmlString = '';
	htmlString += '<div class="p30">';
	htmlString += '<form id="addAllowedIp" class="Simple-Form">';
	htmlString += '<div class="rows"><label class="w100">模块  </label><label class="w600">'+expLogList[i].module+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">方法  </label><label class="w600">'+expLogList[i].method+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">日期  </label><label class="w600">'+expLogList[i].create_datetime+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">参数  </br></label><label class="w600">'+expLogList[i].parameter+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">内容  </br></label><label class="w600">'+expLogList[i].content.replace(/[\n]/ig, '<br />')+'</label></div>';
	htmlString += '</form>';
	htmlString += '</div>';
	var opts = {
			content : htmlString,
			title : '异常日志详情',
			height : 600,
			width : 800,
			scroll :1
		};
	_JUI.dialog(opts);
}

var websocklist = [];
var Websock = {
		listInit: function(){
			var _this = this;
			var list = new JUI.tableList({
				url: '/system/websocket',
				
				transdata: _this.transList
			});
			list.load();
		},

		transList: function(data){
			var result = [];
			expLogList = data;
			for(var i=0; i<data.length; i++){
				result.push([
					data[i].terminal_number,
					data[i].cmd_code,
					data[i].cmd_serial_no,
					'<div class="wpnormal">' + data[i].cmd_raw_txt + '</div>',
					data[i].create_datetime

				]);
			}
			return result;
		}
};

function websock(i){
	var htmlString = '';
	htmlString += '<div class="p30">';
	htmlString += '<form id="addAllowedIp" class="Simple-Form">';
	htmlString += '<div class="rows"><label class="w100">终端编号  </label><label class="w600">'+websocklist[i].terminal_number+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">命令码 </label><label class="w600">'+websocklist[i].cmd_code+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">命令发序列号  </label><label class="w600">'+websocklist[i].cmd_serial_no+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">完整请求信息  </br></label><label class="w600">'+websocklist[i].cmd_raw_txt+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">创建时间  </br></label><label class="w600">'+websocklist[i].create_datetime(/[\n]/ig, '<br />')+'</label></div>';
	htmlString += '</form>';
	htmlString += '</div>';
	var opts = {
			content : htmlString,
			title : 'WebSock详情',
			height : 600,
			width : 800,
			scroll :1
		};
	_JUI.dialog(opts);
}

var terminallist = [];
var Terminal = {
		listInit: function(){
			var _this = this;
			var list = new JUI.tableList({
				url: '/system/terminal',
				
				transdata: _this.transList
			});
			list.load();
		},

		transList: function(data){
			var result = [];
			expLogList = data;
			for(var i=0; i<data.length; i++){
				result.push([
					data[i].terminal_number,
					data[i].cpu_load,
					data[i].memory_load,
					'<div class="wpnormal">' + data[i].device_info + '</div>',
					data[i].report_time,
					data[i].create_datetime
					
				]);
			}
			return result;
		}
};

function terminalstatus(i){
	var htmlString = '';
	htmlString += '<div class="p30">';
	htmlString += '<form id="addAllowedIp" class="Simple-Form">';
	htmlString += '<div class="rows"><label class="w100">终端编号  </label><label class="w600">'+websocklist[i].module+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">命令码 </label><label class="w600">'+websocklist[i].method+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">命令发序列号  </label><label class="w600">'+websocklist[i].create_datetime+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">完整请求信息  </br></label><label class="w600">'+websocklist[i].parameter+'</label></div>';
	htmlString += '<div class="rows"><label class="w100">创建时间  </br></label><label class="w600">'+websocklist[i].content.replace(/[\n]/ig, '<br />')+'</label></div>';
	htmlString += '</form>';
	htmlString += '</div>';
	var opts = {
			content : htmlString,
			title : '终端状态详情',
			height : 600,
			width : 800,
			scroll :1
		};
	_JUI.dialog(opts);
}
 

