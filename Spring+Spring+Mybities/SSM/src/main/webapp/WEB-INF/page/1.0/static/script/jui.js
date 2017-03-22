(function(window){
	var _JUI = {
		//常用规则
		rules: {
			username	: '^[a-z0-9\._-]{3,30}$',
			domain		: '^[a-z0-9]{1,}([-\.]{1}[a-z0-9]{1,}){0,}[\.]{1}[a-z]{1,4}$',
			email		: '^[a-z0-9_\.]{1,}@[a-z0-9]{1,}([-\.]{1}[a-z0-9]{1,}){0,}[\.]{1}[a-z]{1,4}$',
			password	: '^.{4,}$',
			chinese		: '^[\u4e00-\u9fa5]{0,}$',
			wchars		: '^[^\x00-\xff]{0,}$',
			phone		: '^(([0-9]{3,4}[-]{1})?[0-9]{7,8})?$',
			cellphone	: '^(1[34578][0-9]{9})?$',
			zipcode		: '^([1-9][0-9]{5})?$',
			integer		: '^([-]?[0-9]+)?$',
			number		: '^([-]?[0-9]{1,}([.][0-9]{1,})?)?$',
			idcard		: '^([1-9]{1}[0-9]{14}([0-9]{2}[0-9x])?)?$',
			nickname	: '^[A-Za-z0-9\_\u4e00-\u9fa5]{1,50}$'
		},
		_page: {},  //页面的一些尺寸
		//初始化
		init: function(){
			//this.getPageSize();
			this.navActive();
			this.dropMenu.init();
			this.menu.init();
			this.loadPageInit();
			this.dialogInit();
			this.fixPage();
		},
		//正则验证字符串
		regMatch: function(str, pattern){
			var re = new RegExp(pattern, 'gi');
			return re.test(str);
		},
		//字符串(yyyy-mm-dd)转时间
		strToDate: function(str){
			var strs = str.split('-');
			var year = parseInt(strs[0]),
				month = parseInt(strs[1]) - 1,
				day = parseInt(strs[2]);
			return new Date(year, month, day);
		},
		// 日期转字符串 日期对象 输出格式支持yyyy-MM-dd HH:mm:ss SSS
		dateToStr: function(date, pattern){
			date = date || new Date();
			var ys = date.getFullYear();
			var Ms = date.getMonth() + 1;
			var ds = date.getDate();
			var hs = date.getHours();
			var ms = date.getMinutes();
			var ss = date.getSeconds();
			var Ss = date.getTime() % 1e3;
			var dateStr = ys + "-";
			if (Ms < 10) {
				dateStr += "0";
			}
			dateStr += Ms + "-";
			if (ds < 10) {
				dateStr += "0";
			}
			dateStr += ds + " ";
			if (hs < 10) {
				dateStr += "0";
			}
			dateStr += hs + ":";
			if (ms < 10) {
				dateStr += "0";
			}
			dateStr += ms + ":";
			if (ss < 10) {
				dateStr += "0";
			}
			dateStr += ss;
			dateStr += " ";
			if (Ss < 100) {
				dateStr += "0";
			}
			if (Ss < 10) {
				dateStr += "0";
			}
			dateStr += Ss;

			var formatSymbols = "yMdHmsS";
			if (pattern == null || pattern == undefined) {
				pattern = 'yyyy-MM-dd HH:mm:ss SSS';
			}
			// 标记存入数组
			var cs = formatSymbols.split("");
			// 格式存入数组
			var fs = pattern.split("");
			// 构造数组
			var ds = dateStr.split("");
			// 标志年月日的结束下标
			var y = 3;
			var M = 6;
			var d = 9;
			var H = 12;
			var m = 15;
			var s = 18;
			var S = 22;
			// 逐位替换年月日时分秒和毫秒
			for (var i = fs.length - 1; i > -1; i--) {
				switch (fs[i]) {
					case cs[0]:
						fs[i] = ds[y--];
						break;

					case cs[1]:
						fs[i] = ds[M--];
						break;

					case cs[2]:
						fs[i] = ds[d--];
						break;

					case cs[3]:
						fs[i] = ds[H--];
						break;

					case cs[4]:
						fs[i] = ds[m--];
						break;

					case cs[5]:
						fs[i] = ds[s--];
						break;

					case cs[6]:
						fs[i] = ds[S--];
						break;
				}
			}
			return fs.join('');
		},
		//获取表单参数
		getFormData: function(obj){
			var $form = $(obj);
			var data = {};
			$form.find(':input[name]').each(function(){
				var ipttype = $(this).attr('type'),
					iptname = $(this).attr('name'),
					iptvalue = $(this).val();
				if(ipttype == 'checkbox' || ipttype == 'radio'){
					if($(this).prop('checked')){
						data[iptname] = (typeof data[iptname] == 'undefined' || data[iptname] == '') ? iptvalue : data[iptname] + ',' + iptvalue;
					}
					else{
						if(typeof data[iptname] == 'undefined')
							data[iptname] = '';
					}
				}else{
					data[iptname] = typeof data[iptname] == 'undefined' ? iptvalue : data[iptname] + ',' + iptvalue;
				}
			});
			return data;
		},
		//获取URL参数
		getUrlParam: function(key){
			var url = window.location.search.substring(1);
			var par = url.split('&');
			for(var i=0; i<par.length; i++){
				var code = par[i].split('=');
				if(key==code[0]) return code[1];
			}
			return false;
		},
		//获取cookie的值
		getCookie: function(key){
			var arrstr = document.cookie.split('; ');
			for(var i=0; i<arrstr.length; i++){
				var obj = arrstr[i].split('=');
				if(obj[0] == key)
					return unescape(obj[1]);
			}
			return '';
		},
		getPageSize: function(){
			this._page.winWidth = $(window).width();
			this._page.winHeight = $(window).height();
			this._page.docWidth = $(document).width();
			this._page.docHeight = $(document).height();
			this._page.bodyWidth = $(document.body).width();
			this._page.bodyHeight = $(document.body).height();
			this._page.$bodyWidth = $(document.body).find('.body').width();
			this._page.$bodyHeight = $(document.body).find('.body').height();
			this._page.barHeight = $('div.nav-bar').outerHeight();
			this._page.footerHeight = $('div.footer').outerHeight();
		},
		//修复一些CSS无法实现的页面问题
		//优化
		fixPage: function(){
			var _this = this;
			if(!this._page.winWidth){
				$(window).resize(function(){
					$('div.menu').height(0);
					$('div.footer').css('position', 'absolute');
					_this.getPageSize();
					var h, position = 'static';
					if(_this._page.winHeight > (_this._page.bodyHeight + _this._page.footerHeight))
						position = 'fixed';
					$('div.footer').css('position', position);
					_this.getPageSize();
					h = _this._page.docHeight - _this._page.barHeight - _this._page.footerHeight;
					$('div.menu').height(h);
				});
			}
			$('div.menu').height(0);
			$('div.footer').css('position', 'absolute');
			this.getPageSize();
			var h, position = 'static';
			if(this._page.winHeight > (this._page.bodyHeight + this._page.footerHeight))
				position = 'fixed';
			$('div.footer').css('position', position);
			this.getPageSize();
			h = this._page.docHeight - this._page.barHeight - this._page.footerHeight;
			$('div.menu').height(h);
		},
		//导航栏菜单点击激活
		navActive: function(){
			$('.nav-bar .nav>li>a').click(function(){
				if(!$(this).hasClass('drop-down')){
					$('.nav-bar .nav>li>a').removeClass('nav-active');
					$(this).addClass('nav-active');
				}
			});
		},
		dropMenu: {
			init: function(){
				var _this = this;
				$('.drop-down').each(function(){
					var width = $(this).parent().innerWidth();
					$(this).next('.drop-menu').width(width-2);
				});
				$(document).on('click', '.drop-down', function(event){
					event.preventDefault();
					_this.hide();
					_this.show(this);
					return false;
				});
				$(document).click(function(){
					_this.hide();
				});
			},
			show: function(obj){
				var $obj = $(obj);
				if($obj.hasClass('drop-active')){
					this.hide();
				}else{
					if($obj.next('.drop-menu').length > 0)
						$obj.addClass('drop-active').next('.drop-menu').slideDown(200);
					else
						this.hide();
				}
			},
			hide: function(){
				$('.drop-menu').hide();
				$('.drop-active').removeClass('drop-active');
			}
		},
		//左侧菜单
		menu: {
			status: 0,
			minSize: 48,
			maxSize: 200,
			init: function(){
				var _this = this;
				this.menuInit();
				this.groupInit();
				$(document).click(function(){
					if(!_this.status){
						_this.shrink($('#JUI_Menu .menu-active'));
					}
				});
			},
			menuInit: function(){
				var _this = this;
				$('#JUI_Menu .menu-control a').off('click').on('click', function(e){
					e.stopPropagation();
					var $obj = $(this).parent().parent();
					$obj.stop();
					if(_this.status){
						$obj.find('.menu-active a.menu-group-head').trigger('click');
						_this.narrow($obj);
					}else{
						_this.shrink($('#JUI_Menu .menu-active'));
						_this.wide($obj);
					}
				});
			},
			groupInit: function(){
				var _this = this;
				$('#JUI_Menu .menu-group-head').off('click').on('click', function(e){
					e.stopPropagation();
					var $menugroup = $(this).parent();
					$menugroup.children('ul').stop();
					if($menugroup.hasClass('menu-active')){
						_this.shrink($menugroup);
					}else{
						_this.expand($menugroup);
					}
				});
			},
			load: function(url, fn){
				if(!document.getElementById('JUI_Menu')){
					$('div.body').prepend('<div id="JUI_Menu" class="menu"><div class="menu-control"><a href="javascript:;" class="menu-open"></a><a href="javascript:;" class="menu-close"></a></div></div>');
					$('div.menu').height(_JUI._page.docHeight - _JUI._page.barHeight - _JUI._page.footerHeight);
					this.menuInit();
				}
				$.ajax({
					type: 'GET',
					url: url,
					dataType: 'html',
					success: function(data){
						$('#JUI_Menu div.menu-group').remove();
						$('#JUI_Menu').append(data);
						_JUI.menu.groupInit();
						if(typeof fn == 'function')
							fn();
					},
					error: function(){
						_JUI.alert('载入菜单失败');
					}
				});
			},
			destory: function(){
				this.status = 0;
				$('#JUI_Menu').remove();
			},
			expandAll: function(){
				var _this = this;
				if(!this.status){
					this.wide(function(){
						$('#JUI_Menu .menu-group').each(function(){
							if(!$(this).hasClass('menu-active'))
								_this.expand($(this));
						});
					});
				}else{
					$('#JUI_Menu .menu-group').each(function(){
						if(!$(this).hasClass('menu-active')){
							$(this).children('a.menu-group-head').trigger('click');
						}
					});
				}
			},
			wide: function($obj){
				this.status = 1;
				var fn;
				if(typeof $obj != 'object'){
					fn = $obj;
					$obj = $('#JUI_Menu');
				}
				$obj.find('a.menu-open').hide();
				$obj.animate({
					width: this.maxSize
				}, 200, function(){
					$('#JUI_Menu .menu-group').css('overflow', 'visible');
					if(typeof fn == 'function')
						fn();
				});
			},
			narrow: function($obj){
				this.status = 0;
				var fn;
				if(typeof $obj != 'object'){
					fn = $obj;
					$obj = $('#JUI_Menu');
				}
				$obj.find('a.menu-open').show();
				$obj.animate({
					width: this.minSize
				}, 200, function(){
					$('#JUI_Menu .menu-group').css('overflow', 'hidden');
					if(typeof fn == 'function')
						fn();
				});
			},
			expand: function($obj){
				if(this.status){
					$obj.children('ul').slideDown(200, function(){
						$obj.addClass('menu-active');
					});
				}else{
					this.shrink($obj.siblings('.menu-group'));
					var objpos = $obj.offset();
					$obj.children('ul').addClass('menu-float').css({
						top: objpos.top,
						left: objpos.left + 48
					}).fadeIn(200, function(){
						$obj.addClass('menu-active');
					});
				}
			},
			shrink: function($obj){
				if(this.status){
					$obj.children('ul').slideUp(200, function(){
						$obj.removeClass('menu-active');
					});
				}else{
					$obj.each(function(){
						$(this).children('ul').fadeOut(200, function(){
							$(this).parent().removeClass('menu-active');
							$(this).removeClass('menu-float');
						});
					});
				}
			}
		},
		//遮罩
		overlay: {
			show: function(speed, alpha){
				$('body').css('overflow', 'hidden');
				if(!document.getElementById('JUI_Overlay'))
					$('body').append('<div id="JUI_Overlay" style="display:none"></div>');
				speed = typeof speed == 'number' ? speed : 200;
				if(speed < 0)
					speed = 0;
				alpha = typeof alpha == 'number' ? alpha : 0.3;
				if(alpha > 1 || alpha < 0)
					alpha = 0.3;
				$('#JUI_Overlay').show().stop().animate({opacity:alpha}, speed);
			},
			hide: function(speed){
				speed = typeof speed == 'number' ? speed : 200;
				if(speed < 0)
					speed = 0;
				$('#JUI_Overlay').stop().animate({opacity:0}, speed, function(){
					$(this).hide();
					$('body').css('overflow', 'auto');
				});
			}
		},
		//loading
		loading: {
			show: function(speed, fn){
				if(!document.getElementById('JUI_Loading'))
					$('body').append('<div id="JUI_Loading"></div>');
				speed = typeof speed == 'number' ? speed : 200;
				_JUI.close(true);
				_JUI.overlay.show(speed);
				$('#JUI_Loading').fadeIn(speed, function(){
					if(typeof fn == 'function') {
						fn();
					}
				});
			},
			hide: function(speed, fn){
				speed = typeof speed == 'number' ? speed : 200;
				$('#JUI_Loading').fadeOut(speed, function(){
					if(typeof fn == 'function') {
						fn();
					}
				});
				_JUI.overlay.hide(speed);
			},
			hideAnimation: function(){
				$('#JUI_Loading').hide();
			}
		},
		//工作区载入页面初始化
		loadPageInit: function(){
			var _this = this;
			$(document).on('click', '.jui-loadpage', function(event){
				event.preventDefault();
				event.stopPropagation();
				var url = $(this).attr('href');
				var fn = $(this).attr('data-fn');
				_this.loadPage(url, function(){
					if(!_this.menu.status)
						_this.menu.shrink($('#JUI_Menu .menu-active'));
					_this.loading.hide();
					if(fn){
						eval(fn +'();');
					}
				});
				return false;
			});
		},
		//工作区载入页面(HTML片段)
		loadPage: function(url, fn){
			var _this = this;
			this.loading.show(200, function(){
				$.ajax({
					type: 'GET',
					url: url,
					dataType: 'html',
					success: function(data){
						$('#JUI_Main').html(data);
						_this.fixPage();
						_this.loading.hide(200, function(){
							if(typeof fn == 'function')
								fn();
						});
					},
					error: function(){
						_this.loading.hideAnimation();
						_this.alert('载入页面错误');
					}
				});
			});
		},
		//dialog私有变量
		_dialog: {
			timer: null,
			canClose: true,
			fadeSpeed: 200
		},
		//dialog初始化
		dialogInit: function(){
			var _this = this;
			$(document).keydown(function(event){
				if(event.keyCode == 27)
					_this.close();
			});
		},
		//打开对话框
		dialog: function(options){
			var _this = this;
			//dialog默认参数
			var defaults = {
				content: '',
				width: 400,
				height: 68,
				title: '提示',
				scroll: 0,
				okButton: '确定',
				okAvailable: 1,
				cancelButton: '取消',
				cancelAvailable: 0,
				onBegin: null,
				onComplete: null,
				onEnd: null,
				onOK: function(){return true;},
				onCancel: function(){return true;}
			};
			var opts = $.extend(defaults, options);
			this.onComplete = typeof opts.onComplete == 'function' ? opts.onComplete : null;
			this.onEnd = typeof opts.onEnd == 'function' ? opts.onEnd : null;

			if(typeof opts.onBegin == 'function')  //触发开始载入
				opts.onBegin();

			//载入基本框架
			if(!document.getElementById('JUI_Dialog')){
				$('body').append('<div id="JUI_Dialog"><div id="JUI_Dialog_Title" class="jui-dialog-title"><span></span><a href="javascript:;">&times;</a></div><div class="jui-dialog-container"></div><div class="jui-dialog-buttons"><span></span></div></div>');
				$('#JUI_Dialog').jDrag({container:'#JUI_Overlay', handle:'#JUI_Dialog_Title'});
			}else{
				$('#JUI_Dialog .jui-dialog-container').html('');
				$('#JUI_Dialog .jui-dialog-buttons').html('<span></span>');
			}
			this.overlay.show(this._dialog.fadeSpeed);
			this.loading.hideAnimation();
			$('#JUI_Dialog').fadeIn(this._dialog.fadeSpeed);

			//填充内容、设置样式、绑定事件
			$('#JUI_Dialog_Title span').html(opts.title);
			$('#JUI_Dialog_Title a').click(function(){
				_this.close();
			});
			if(!opts.okButton && !opts.cancelButton)
				$('#JUI_Dialog .jui-dialog-buttons').hide();
			$('#JUI_Dialog .jui-dialog-container').height(opts.height).html(opts.content);
			if(opts.scroll)
				$('#JUI_Dialog .jui-dialog-container').addClass('jui-dialog-scroll');
			if(!this._dialog.canClose){
				$('#JUI_Dialog_Title a').hide();
			}else{
				$('#JUI_Dialog_Title a').show();
			}
			if(opts.okAvailable)
				$('#JUI_Dialog .jui-dialog-buttons').append('<button class="jui-dialog-ok">'+opts.okButton+'</button>');
			if(opts.cancelAvailable)
				$('#JUI_Dialog .jui-dialog-buttons').append('<button class="jui-dialog-cancel">'+opts.cancelButton+'</button>');
			var winWidth = this._page.winWidth,
				winHeight = this._page.winHeight,
				titleHeight = $('#JUI_Dialog_Title').outerHeight(),
				btnHeight = $('#JUI_Dialog .jui-dialog-buttons').outerHeight(),
				scrollTop = $(window).scrollTop();
			var dialogTop = (winHeight - opts.height - titleHeight - btnHeight) / 2 + scrollTop,
				dialogHeight = opts.height + titleHeight + btnHeight;
			if(dialogTop < 0){
				$('#JUI_Dialog .jui-dialog-container').height(winHeight - titleHeight - btnHeight);
				dialogHeight = winHeight;
				dialogTop = 0;
			}
			$('#JUI_Dialog').css({
				width: opts.width,
				height: dialogHeight,
				left: (winWidth - opts.width) / 2,
				top: dialogTop
			});

			//绑定按钮事件
			$('#JUI_Dialog .jui-dialog-buttons button:first').focus();
			$('#JUI_Dialog button.jui-dialog-ok').click(function(){
				if(opts.onOK())
					_this.close();
			});
			$('#JUI_Dialog button.jui-dialog-cancel').click(function(){
				if(opts.onCancel())
					_this.close();
			});

			if(typeof opts.onComplete == 'function')  //载入完成触发
				opts.onComplete();
		},
		//对话框的提示
		dialogTips: function(msg, keep, delay){
			if($('#JUI_Dialog').is(':visible')){
				if(typeof delay != 'number')
					delay = 2000;
				clearTimeout(this._dialog.timer);
				$('#JUI_Dialog .jui-dialog-buttons span').text(msg);
				if(!keep)
					this._dialog.timer = setTimeout(function(){
						$('#JUI_Dialog .jui-dialog-buttons span').text('');
					}, delay);
			}
		},
		//禁用启用对话框按钮
		dialogButton: function(state){
			if(state)
				$('#JUI_Dialog .jui-dialog-buttons button').prop('disabled', false);
			else
				$('#JUI_Dialog .jui-dialog-buttons button').prop('disabled', true);
		},
		//提示浮窗
		alert: function(str, title, fn){
			var opts;
			if(typeof title == 'string')
				opts = {content:'<span class="txt">'+str+'</span>', title:title};
			else
				opts = {content:'<span class="txt">'+str+'</span>'};
			if(typeof fn == 'function')
				opts.onOK = fn;
			this.dialog(opts);
		},
		//确认浮窗
		confirm: function(str, title, fn){
			var opts;
			if(typeof title == 'string'){
				opts = {
					cancelAvailable: 1,
					content: '<span class="txt">'+str+'</span>',
					title: title,
					onOK: fn
				};
			}else{
				opts = {
					cancelAvailable: 1,
					content: '<span class="txt">'+str+'</span>',
					onOK: fn
				};
			}
			this.dialog(opts);
		},
		//载入浮窗表单
		loadConfirm: function(url, title, width, height, fn){
			var opts = {
				cancelAvailable: 1,
				content: '<div class="loading"></div>',
				title: title,
				width: width,
				height: height,
				onOK: fn,
				onComplete: function(){
					$.ajax({
						type: 'GET',
						url: url,
						dataType: 'html',
						success: function(data){
							$('#JUI_Dialog .jui-dialog-container').html(data);
						},
						error: function(){
							$('#JUI_Dialog .jui-dialog-container').html('<div class="load-failed"></div>');
							JUI.dialogTips('页面载入失败', true);
							$('#JUI_Dialog button.jui-dialog-ok').unbind("click").click(function(){
								JUI.close();
							});
						}
					});
				}
			};
			this.dialog(opts);
		},
		//关闭对话框
		close: function(box){
			if(this._dialog.canClose){
				if($('#JUI_Dialog').is(':visible')){
					if(box){
						$('#JUI_Dialog').hide(0, function(){
							$('#JUI_Dialog .jui-dialog-container').html('').removeClass('jui-dialog-scroll');
							$('#JUI_Dialog .jui-dialog-buttons').html('<span></span>');
						});
					}else{
						this.overlay.hide(this._dialog.fadeSpeed);
						$('#JUI_Dialog').fadeOut(this._dialog.fadeSpeed, function(){
							$('#JUI_Dialog .jui-dialog-container').html('').removeClass('jui-dialog-scroll');
							$('#JUI_Dialog .jui-dialog-buttons').html('<span></span>');
						});
					}
					if(typeof this.onEnd == 'function') this.onEnd();
				}
			}
		},
		//tabs
		tabs: {
			init: function(obj){
				if(typeof obj == 'undefined')
					obj = 'ul.main-tabs';
				$obj = $(obj);
				$obj.children('li').each(function(i){
					$(this).click(function(){
						$(this).addClass('active').siblings().removeClass('active');
						$obj.next('ul.main-tabs-content').children('li').css('display','none');
						$obj.next('ul.main-tabs-content').children('li').eq(i).css('display','block');
					});
				});
				$obj.children('li:first').trigger('click');
			}
		},
		// tableList默认配置
		tableListDefaults: {
			method: 'GET',
			url: '',
			form: 'div.main-widgets form.main-widgets-form',
			list: 'div.main-widgets div.main-list',
			pages: 'div.main-widgets div.main-list-pages',
			params:{},
			rootProperty: 'records',				// 数据节点，默认值records
			statusProperty: 'status',				// 状态参数，默认值status
			statusCheckExp: /^0|ok|true|success$/i,	// 状态正则校验，默认值/^true|0|success$/i
			messageProperty: 'message',				// 消息参数，默认值message
			totalProperty: 'total',					// 总记录数，默认值total
			limitParam: 'pagesize',					// 分页参数，默认值pagesize，默认大小1
			pageParam: 'page',						// 页码参数，默认值page，默认大小1
			transdata: null, //转换数据函数
			checkdata: null, //查询表单验证函数
			onload: null //列表载入完成后执行的函数
		},
		//载入列表
		tableList: function(options){
			this.$form = null;
			this.$list = null;
			this.$pages = null;
			this.params = {};
			var defaults = _JUI.tableListDefaults;

			this.init = function(){
				this.opts = $.extend(true, {}, defaults, options);
				this.opts.params[this.opts.limitParam] = this.opts.params[this.opts.limitParam] || 20;
				this.opts.params[this.opts.pageParam] = this.opts.params[this.opts.pageParam] || 1;
				this.setObject();
				this.bindEvent();
			};
			this.setObject = function(){
				if(this.opts.form)
					this.$form = $(this.opts.form);
				if(this.opts.list)
					this.$list = $(this.opts.list);
				if(this.opts.pages)
					this.$pages = $(this.opts.pages);
			};
			this.bindEvent = function(){
				var _this = this;
				if(this.$form.length > 0){
					this.$form.get(0).submit = false;
					this.$form.find('.jui-date').JUICalendar();
					this.$form.find('button.submit').click(function(event){
						event.preventDefault();
						if(typeof _this.checkdata == 'function'){
							if(_this.checkdata())
								_this.load();
						}else{
							_this.load();
						}
					});
				}
				this.checkAll();
			};
			this.getParams = function(){
				var result = {};
				result[this.opts.limitParam] = this.opts.params[this.opts.limitParam];
				result[this.opts.pageParam] = this.opts.params[this.opts.pageParam];
				if(this.$form.length > 0){
					this.$form.find(':input[name]').each(function(){
						result[$(this).attr('name')] = $.trim($(this).val());
					});
				}
				return result;
			};
			this.shade = function(tp){
				if(tp){
					this.$list.stop().animate({opacity:1}, 0);
				}else{
					this.$list.stop().animate({opacity:0.5}, 0);
				}
			};
			this.load = function(pageno){
				var _this = this;
				this.setObject();
				if(pageno){
					this.params[this.opts.pageParam] = pageno;
				}else{
					this.params = this.getParams();
				}
				this.shade();
				$.ajax({
					type: _this.opts.method,
					url: _this.opts.url.toString(),
					dataType: 'json',
					data: _this.params,
					success: function(data){
						_this.shade(1);
						if(_this.opts.statusCheckExp.test(data[_this.opts.statusProperty])){
							var list = data[_this.opts.rootProperty] || [];
							if(typeof _this.opts.transdata == 'function')
								list = _this.opts.transdata(list);
							_this.show(list, {page:data[_this.opts.pageParam],total:data[_this.opts.totalProperty]});
						}else{
							_JUI.alert(data[_this.opts.messageProperty], '载入数据');
						}
					},
					error: function(){
						_this.shade(1);
						_JUI.alert('载入数据失败', '载入数据');
					}
				});
			};
			this.reload = function(){
				this.load(this.params[this.opts.pageParam]);
			};
			this.show = function(records, pageset){
				this.$list.find('tr:not(.list-head)').remove();
				var code = '';
				for(var i=0; i<records.length; i++){
					code += '<tr>';
					for(var j=0; j<records[i].length; j++){
						if(this.$list.find('tr.list-head th').eq(j).hasClass('list-center'))
							code += '<td class="list-center">' + records[i][j] + '</td>';
						else if(this.$list.find('tr.list-head th').eq(j).hasClass('list-right'))
							code += '<td class="list-right">' + records[i][j] + '</td>';
						else
							code += '<td class="list-left">' + records[i][j] + '</td>';
					}
					code += '</tr>';
				}
				this.$list.find('tr.list-head').after(code);
				if(typeof this.opts.onload == 'function')
					this.opts.onload(this);
				this.pagination(pageset);
				_JUI.fixPage();
			};
			this.pagination = function(pageset){
				if(this.$pages.length > 0){
					var _this = this;
					this.$pages.html('');
					var startpage, endpage, maxpage;
					var total = pageset.total,
						pageno = pageset.page;
					if(total < 1){
						startpage = 1;
						endpage = 1;
						maxpage = 1;
					}else{
						maxpage = Math.ceil(total/this.opts.params[this.opts.limitParam]);
						startpage = pageno - 4;
						endpage = pageno + 4;
						if(startpage < 1){
							startpage = 1;
							endpage = 9;
						}
						if(endpage > maxpage){
							endpage = maxpage;
							startpage = maxpage - 8;
							if(startpage < 1) startpage = 1;
						}
					}
					if(pageno > maxpage) pageno = maxpage;
					var htmlcode;
					if(pageno == 1){
						htmlcode = '<span class="page-none">上一页</span>';
					}else{
						htmlcode = '<a href="javascript:;" pageno="'+(pageno-1)+'">上一页</a>';
					}
					if(startpage == 2){
						htmlcode += '<a href="javascript:;" pageno="1">1</a>';
					}else if(startpage > 2){
						htmlcode += '<a href="javascript:;" pageno="1">1</a><span class="page-more">..</span>';
					}
					for(var i=startpage; i<=endpage; i++){
						if(pageno == i){
							htmlcode += '<span>'+i+'</span>';
						}else{
							htmlcode += '<a href="javascript:;" pageno="'+i+'">'+i+'</a>';
						}
					}
					if(endpage == (maxpage-1)){
						htmlcode += '<a href="javascript:;" pageno="'+maxpage+'">'+maxpage+'</a>';
					}else if(endpage < maxpage){
						htmlcode += '<span class="page-more">..</span><a href="javascript:;" pageno="'+maxpage+'">'+maxpage+'</a>';
					}
					if(pageno == maxpage){
						htmlcode += '<span class="page-none">下一页</span>';
					}else{
						htmlcode += '<a href="javascript:;" pageno="'+(pageno+1)+'">下一页</a>';
					}
					this.$pages.html(htmlcode);
					_JUI.fixPage();
					this.$pages.find('a').click(function(){
						var p = parseInt($(this).attr('pageno'));
						_this.load(p);
					});
				}
			};
			this.checkAll = function(){
				var _this = this;
				this.$list.find('.list-head :checkbox.checkall').click(function(){
					var status = $(this).prop('checked');
					_this.$list.find('td :checkbox').prop('checked', status);
				});
			};
			this.init();
		},
		//获取表单数据
		getFormData: function(formobj){
			var $form = $(formobj);
			var data = {};
			$form.find(':input[name]').each(function(){
				var iptname = $(this).attr('name');
				if(iptname == '')
					return true;
				if(typeof data[iptname] == 'undefined')
					data[iptname] = '';
			});
			$form.find(':input[name]').each(function(){
				var iptname = $(this).attr('name');
				if(iptname == '')
					return true;
				if($(this).is(':radio')){
					if($(this).prop('checked'))
						data[iptname] = $(this).val();
				}else if($(this).is(':checkbox')){
					if($(this).prop('checked'))
						data[iptname] += data[iptname] == '' ? $(this).val() : ', ' + $(this).val();
				}else if($(this).is('textarea')){
					data[iptname] = $(this).val();
				}else{
					data[iptname] = $.trim($(this).val());
				}
			});
			return data;
		}
	};
	window.JUI = window._JUI = _JUI;
	$(function(){
		_JUI.init();
	})
})(window);

//获取鼠标坐标
jQuery.getMousePosition = function(e){
	var posx = 0;
	var posy = 0;
	if(!e) var e = window.event;
	if(e.pageX || e.pageY){
		posx = e.pageX;
		posy = e.pageY;
	}else if(e.clientX || e.clientY){
		posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
		posy = e.clientY + document.body.scrollTop  + document.documentElement.scrollTop;
	}
	return {'x':posx, 'y':posy};
};

//jQuery拖动插件
;(function($){
	var jDragObj = null;
	var jDragContainer = '';
	var jDragMouseX, jDragMouseY, jDragElemTop, jDragElemLeft;
	var jDragOnDrop = null;
	var jDragOnMove = null;
	$.jDragSetPos = function(event){
		var pos = $.getMousePosition(event);
		var X = (pos.x - jDragMouseX);
		var Y = (pos.y - jDragMouseY);
		if(jDragContainer != '' && $(jDragContainer).length == 1){
			var conpos = $(jDragContainer).position();
			var minleft = conpos.left;
			minleft += isNaN(parseInt($(jDragContainer).css('margin-left'))) ? 0 : parseInt($(jDragContainer).css('margin-left'));
			minleft += isNaN(parseInt($(jDragContainer).css('border-left-width'))) ? 0 : parseInt($(jDragContainer).css('border-left-width'));
			var maxleft = minleft + $(jDragContainer).innerWidth() - $(jDragObj).outerWidth();
			var mintop = conpos.top;
			mintop += isNaN(parseInt($(jDragContainer).css('margin-top'))) ? 0 : parseInt($(jDragContainer).css('margin-top'));
			mintop += isNaN(parseInt($(jDragContainer).css('border-top-width'))) ? 0 : parseInt($(jDragContainer).css('border-top-width'));
			var maxtop = mintop + $(jDragContainer).innerHeight() - $(jDragObj).outerHeight();
			var newY = jDragElemTop + Y;
			var newX =jDragElemLeft + X;
			if(newX < minleft) newX = minleft;
			if(newX > maxleft) newX = maxleft;
			if(newY < mintop) newY = mintop;
			if(newY > maxtop) newY = maxtop;
			$(jDragObj).css("top", newY);
			$(jDragObj).css("left", newX);
		}else{
			$(jDragObj).css("top", (jDragElemTop + Y));
			$(jDragObj).css("left", (jDragElemLeft + X));
		}
	};
	$(document).mousemove(function(event){
		event.stopPropagation();
		if(jDragObj != null){
			$.jDragSetPos(event);
			if(typeof(jDragOnMove) == 'function') jDragOnMove();
			return false;
		}
	});
	$(document).mouseup(function(event){
		event.stopPropagation();
		if(jDragObj != null){
			jDragObj = null;
			if(typeof(jDragOnDrop) == 'function') jDragOnDrop();
			return false;
		}
	});
	$.fn.extend({
		jDrag: function(options){
			var defaults = {
				container	: '',
				handle		: '',
				cursor		: 'move',
				ondrag		: null,
				onmove		: null,
				ondrop		: null
			};
			var opts = $.extend(defaults, options);
			return this.each(function(){
				//$(this).css('z-index', '10000');
				var $handle = (opts.handle == '') ? $(this) : $(this).find(opts.handle);
				$handle.css('cursor', opts.cursor);
				var dragobj = this;
				$handle.mousedown(function(event){
					event.stopPropagation();
					jDragObj = dragobj;
					jDragContainer = opts.container;
					$(dragobj).css('position','absolute');
					var pos = $.getMousePosition(event);
					jDragMouseX = pos.x;
					jDragMouseY = pos.y;
					jDragElemTop  = dragobj.offsetTop;
					jDragElemLeft = dragobj.offsetLeft;
					$.jDragSetPos(event);
					if(typeof(opts.ondrop) == 'function') jDragOnDrop = opts.ondrop;
					if(typeof(opts.onmove) == 'function') jDragOnMove = opts.onmove;
					if(typeof(opts.ondrag) == 'function') opts.ondrag();
					return false;
				});
			});
		}
	});
})(jQuery);

//日期控件
var JUIDatePicker = {
	seldate		: null,
	usrdate		: null,
	weekstart	: null,
	lastday		: null,
	dateipt		: null,
	midyear	: new Date().getFullYear(),
	on_selyear	: false,
	on_selmonth	: false,
	pos			: {left:0, top:0},
	getInfo : function(){
		var tmp_month = new Date(this.seldate.getFullYear(), this.seldate.getMonth());
		this.weekstart = 1 - tmp_month.getDay();
		tmp_month.setMonth(tmp_month.getMonth() + 1);
		tmp_month.setDate(tmp_month.getDate() - 1);
		this.lastday = tmp_month.getDate();
	},
	popUp : function(ipt, obj){
		var $ipt = $(ipt);
		if($ipt.length > 0){
			if(!document.getElementById('JUI_DatePicker')){
				var newdiv = document.createElement("div");
				newdiv.id = "JUI_DatePicker";
				newdiv.style.display = "none";
				document.body.appendChild(newdiv);
			}
			spdt = $ipt.val().split('-');
			if(spdt.length == 3){
				if(isNaN(spdt[0]) || isNaN(spdt[1]) || isNaN(spdt[2])){
					this.seldate = new Date();
					this.usrdate = null;
				}else{
					this.seldate = new Date(parseInt(spdt[0]), parseInt(spdt[1])-1, parseInt(spdt[2]));
					this.usrdate = this.seldate;
					this.midyear = (parseInt(spdt[0]) < 5) ? 5 : parseInt(spdt[0]);
				}
			}else{
				this.seldate = new Date();
				this.usrdate = null;
			}
			this.dateipt = ipt;
			this.pos = $(obj).offset();
			this.Show();
		}
	},
	Show : function(){
		var _this = this;
		this.getInfo();
		var code = '<div class="calendar"><div class="calendar_bar"><div class="calendar_year">'+this.seldate.getFullYear()+'&#x5E74;</div><div class="calendar_month">'+(this.seldate.getMonth()+1)+'&#x6708;</div><div class="calendar_close"></div></div><div class="week"><table border="0" cellspacing="0" cellpadding="0"><tr><td><div class="week_t week_sun">日</div></td><td><div class="week_t">一</div></td><td><div class="week_t">二</div></td><td><div class="week_t">三</div></td><td><div class="week_t">四</div></td><td><div class="week_t">五</div></td><td><div class="week_t">六</div></td></tr>';
		while(this.weekstart <= this.lastday){
			var selClass;
			for(var i=0; i<7; i++){
				if(i == 0) code += '<tr>';
				if(this.weekstart < 1){
					code += '<td>&nbsp;</td>';
				}else{
					if(this.weekstart > this.lastday){
						code += '<td>&nbsp;</td>';
					}else{
						if(this.usrdate == null){
							selClass = 'week_d_n';
						}else{
							if(this.seldate.getFullYear() == this.usrdate.getFullYear() && this.seldate.getMonth() == this.usrdate.getMonth()){
								if(this.weekstart == this.usrdate.getDate())
									selClass = 'week_d_s';
								else
									selClass = 'week_d_n';
							}else{
								selClass = 'week_d_n';
							}
						}
						if(this.seldate.getFullYear() == new Date().getFullYear() && this.seldate.getMonth() == new Date().getMonth()){
							if(this.weekstart == new Date().getDate())
								selClass += ' week_tdy';
							else if(i == 0)
								selClass += ' week_sun';
						}else{
							if(i == 0) selClass += ' week_sun';
						}
						code += '<td><div class="week_d '+selClass+'" onclick="JUIDatePicker.setDate('+this.seldate.getFullYear()+','+(this.seldate.getMonth()+1)+','+this.weekstart+')">'+this.weekstart+'</div></td>';
					}
				}
				this.weekstart++;
				if(i == 6) code += '</tr>';
			}
		}
		code += '</table></div>';
		code += '<div class="btns"><input type="button" class="setclean" value="清除" /><input type="button" class="settoday" value="今天" /></div>';
		code += '</div><div id="JUI_DatePicker_selyear" class="calendar_selyear" style="display:none"></div><div id="JUI_DatePicker_selmonth" class="calendar_selmonth" style="display:none"></div>';
		$('#JUI_DatePicker').html(code);
		$('#JUI_DatePicker').css(this.pos);
		$('#JUI_DatePicker').fadeIn(100);
		$('#JUI_DatePicker').click(function(event){
			event.stopPropagation();
		});
		$('#JUI_DatePicker .calendar_close').click(function(){
			_this.Close();
		});
		$('#JUI_DatePicker .setclean').click(function(event){
			event.stopPropagation();
			$(_this.dateipt).val('');
			_this.Close();
		});
		$('#JUI_DatePicker .settoday').click(function(event){
			var today = new Date();
			_this.setDate(today.getFullYear(), today.getMonth()+1, today.getDate());
		});
		$('#JUI_DatePicker .calendar_year').click(function(event){
			event.stopPropagation();
			_this.selYear();
			return false;
		});
		$('#JUI_DatePicker .calendar_month').click(function(event){
			event.stopPropagation();
			_this.selMonth();
			return false;
		});
	},
	setDate : function(y, m, d){
		var mon = (m < 10) ? '0'+m : m.toString();
		var day = (d < 10) ? '0'+d : d.toString();
		$(this.dateipt).val(arguments[0]+"-"+mon+"-"+day);
		this.Close();
	},
	selYear : function(onsel){
		var _this = this;
		if(typeof(onsel) == 'undefined') onsel = false;
		var code = '<div class="calendar_sel year_up">-</div>';
		var start_year, end_year;
		if(this.midyear < 5){
			start_year = 0;
			end_year = 10;
		}else{
			start_year = this.midyear - 5;
			end_year = this.midyear + 5;
		}
		for(var i=start_year; i<=end_year; i++){
			code += (i == this.seldate.getFullYear()) ? '<div class="calendar_sel year_sel" style="font-weight:bold" yearnum="'+i+'">'+i+'</div>' : '<div class="calendar_sel year_sel" yearnum="'+i+'">'+i+'</div>';
		}
		code += '<div class="calendar_sel year_down">+</div>';
		if(onsel){
			$('#JUI_DatePicker_selyear').html(code);
		}else{
			if(this.on_selmonth){
				$('#JUI_DatePicker_selyear').html(code).show();
				$('#JUI_DatePicker_selmonth').hide();
				this.on_selyear = true;
				this.on_selmonth = false;
			}else{
				if(this.on_selyear){
					$('#JUI_DatePicker_selyear').hide();
					this.on_selyear = false;
				}else{
					$('#JUI_DatePicker_selyear').html(code).show();
					this.on_selyear = true;
				}
			}
		}
		$('#JUI_DatePicker .year_up').click(function(event){
			event.stopPropagation();
			_this.chgYear(-1);
			return false;
		});
		$('#JUI_DatePicker .year_down').click(function(event){
			event.stopPropagation();
			_this.chgYear(-2);
			return false;
		});
		$('#JUI_DatePicker .year_sel').click(function(event){
			event.stopPropagation();
			var yearnum = parseInt($(this).attr('yearnum'));
			_this.chgYear(yearnum);
			return false;
		});
		return false;
	},
	chgYear : function(num){
		if(num < 0){
			if(num == -1)
				this.midyear -= 2;
			else
				this.midyear += 2;
			this.selYear(event, true);
		}else{
			this.seldate = new Date(num, this.seldate.getMonth());
			this.on_selyear = false;
			this.Show();
		}
		return false;
	},
	selMonth : function(onsel){
		var _this = this;
		if(typeof(onsel) == 'undefined') onsel = false;
		var code = '';
		for(var i=1; i<=12; i++){
			code += (i == this.seldate.getMonth()+1) ? '<div class="calendar_sel" style="font-weight:bold" monnum="'+i+'">'+i+'</div>' : '<div class="calendar_sel" monnum="'+i+'">'+i+'</div>';
		}
		if(onsel){
			$('#JUI_DatePicker_selmonth').html(code);
		}else{
			if(this.on_selyear){
				$('#JUI_DatePicker_selmonth').html(code).show();
				$('#JUI_DatePicker_selyear').hide();
				this.on_selyear = false;
				this.on_selmonth = true;
			}else{
				if(this.on_selmonth){
					$('#JUI_DatePicker_selmonth').hide();
					this.on_selmonth = false;
				}else{
					$('#JUI_DatePicker_selmonth').html(code).show();
					this.on_selmonth = true;
				}
			}
		}
		$('#JUI_DatePicker_selmonth .calendar_sel').click(function(event){
			event.stopPropagation();
			var monnum = parseInt($(this).attr('monnum'));
			_this.chgMonth(monnum);
		});
		return false;
	},
	chgMonth : function(num){
		this.seldate = new Date(this.seldate.getFullYear(), num-1);
		this.on_selmonth = false;
		this.Show();
		return false;
	},
	Close : function(){
		this.on_selyear = false;
		this.on_selmonth = false;
		$('#JUI_DatePicker_selyear').hide();
		$('#JUI_DatePicker_selmonth').hide();
		$('#JUI_DatePicker').fadeOut(100);
	}
};
$(document).click(function(){
	JUIDatePicker.Close();
});

//日期控件 for jQuery
;(function($){
	$.fn.extend({
		JUICalendar: function (options) {
			return this.each(function(){
				$(this).attr('readonly', 'readonly');
				$(this).click(function(event){
					event.stopPropagation();
					JUIDatePicker.popUp(this, this);
				});
			});
		}
	});
})(jQuery);

//MD5函数
(function ($) {
	'use strict';
	function safe_add(x, y) {
		var lsw = (x & 0xFFFF) + (y & 0xFFFF),
			msw = (x >> 16) + (y >> 16) + (lsw >> 16);
		return (msw << 16) | (lsw & 0xFFFF);
	}
	function bit_rol(num, cnt) {
		return (num << cnt) | (num >>> (32 - cnt));
	}
	function md5_cmn(q, a, b, x, s, t) {
		return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s), b);
	}
	function md5_ff(a, b, c, d, x, s, t) {
		return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
	}
	function md5_gg(a, b, c, d, x, s, t) {
		return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
	}
	function md5_hh(a, b, c, d, x, s, t) {
		return md5_cmn(b ^ c ^ d, a, b, x, s, t);
	}
	function md5_ii(a, b, c, d, x, s, t) {
		return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
	}
	function binl_md5(x, len) {
		/* append padding */
		x[len >> 5] |= 0x80 << (len % 32);
		x[(((len + 64) >>> 9) << 4) + 14] = len;

		var i, olda, oldb, oldc, oldd,
			a =  1732584193,
			b = -271733879,
			c = -1732584194,
			d =  271733878;

		for (i = 0; i < x.length; i += 16) {
			olda = a;
			oldb = b;
			oldc = c;
			oldd = d;

			a = md5_ff(a, b, c, d, x[i],       7, -680876936);
			d = md5_ff(d, a, b, c, x[i +  1], 12, -389564586);
			c = md5_ff(c, d, a, b, x[i +  2], 17,  606105819);
			b = md5_ff(b, c, d, a, x[i +  3], 22, -1044525330);
			a = md5_ff(a, b, c, d, x[i +  4],  7, -176418897);
			d = md5_ff(d, a, b, c, x[i +  5], 12,  1200080426);
			c = md5_ff(c, d, a, b, x[i +  6], 17, -1473231341);
			b = md5_ff(b, c, d, a, x[i +  7], 22, -45705983);
			a = md5_ff(a, b, c, d, x[i +  8],  7,  1770035416);
			d = md5_ff(d, a, b, c, x[i +  9], 12, -1958414417);
			c = md5_ff(c, d, a, b, x[i + 10], 17, -42063);
			b = md5_ff(b, c, d, a, x[i + 11], 22, -1990404162);
			a = md5_ff(a, b, c, d, x[i + 12],  7,  1804603682);
			d = md5_ff(d, a, b, c, x[i + 13], 12, -40341101);
			c = md5_ff(c, d, a, b, x[i + 14], 17, -1502002290);
			b = md5_ff(b, c, d, a, x[i + 15], 22,  1236535329);

			a = md5_gg(a, b, c, d, x[i +  1],  5, -165796510);
			d = md5_gg(d, a, b, c, x[i +  6],  9, -1069501632);
			c = md5_gg(c, d, a, b, x[i + 11], 14,  643717713);
			b = md5_gg(b, c, d, a, x[i],      20, -373897302);
			a = md5_gg(a, b, c, d, x[i +  5],  5, -701558691);
			d = md5_gg(d, a, b, c, x[i + 10],  9,  38016083);
			c = md5_gg(c, d, a, b, x[i + 15], 14, -660478335);
			b = md5_gg(b, c, d, a, x[i +  4], 20, -405537848);
			a = md5_gg(a, b, c, d, x[i +  9],  5,  568446438);
			d = md5_gg(d, a, b, c, x[i + 14],  9, -1019803690);
			c = md5_gg(c, d, a, b, x[i +  3], 14, -187363961);
			b = md5_gg(b, c, d, a, x[i +  8], 20,  1163531501);
			a = md5_gg(a, b, c, d, x[i + 13],  5, -1444681467);
			d = md5_gg(d, a, b, c, x[i +  2],  9, -51403784);
			c = md5_gg(c, d, a, b, x[i +  7], 14,  1735328473);
			b = md5_gg(b, c, d, a, x[i + 12], 20, -1926607734);

			a = md5_hh(a, b, c, d, x[i +  5],  4, -378558);
			d = md5_hh(d, a, b, c, x[i +  8], 11, -2022574463);
			c = md5_hh(c, d, a, b, x[i + 11], 16,  1839030562);
			b = md5_hh(b, c, d, a, x[i + 14], 23, -35309556);
			a = md5_hh(a, b, c, d, x[i +  1],  4, -1530992060);
			d = md5_hh(d, a, b, c, x[i +  4], 11,  1272893353);
			c = md5_hh(c, d, a, b, x[i +  7], 16, -155497632);
			b = md5_hh(b, c, d, a, x[i + 10], 23, -1094730640);
			a = md5_hh(a, b, c, d, x[i + 13],  4,  681279174);
			d = md5_hh(d, a, b, c, x[i],      11, -358537222);
			c = md5_hh(c, d, a, b, x[i +  3], 16, -722521979);
			b = md5_hh(b, c, d, a, x[i +  6], 23,  76029189);
			a = md5_hh(a, b, c, d, x[i +  9],  4, -640364487);
			d = md5_hh(d, a, b, c, x[i + 12], 11, -421815835);
			c = md5_hh(c, d, a, b, x[i + 15], 16,  530742520);
			b = md5_hh(b, c, d, a, x[i +  2], 23, -995338651);

			a = md5_ii(a, b, c, d, x[i],       6, -198630844);
			d = md5_ii(d, a, b, c, x[i +  7], 10,  1126891415);
			c = md5_ii(c, d, a, b, x[i + 14], 15, -1416354905);
			b = md5_ii(b, c, d, a, x[i +  5], 21, -57434055);
			a = md5_ii(a, b, c, d, x[i + 12],  6,  1700485571);
			d = md5_ii(d, a, b, c, x[i +  3], 10, -1894986606);
			c = md5_ii(c, d, a, b, x[i + 10], 15, -1051523);
			b = md5_ii(b, c, d, a, x[i +  1], 21, -2054922799);
			a = md5_ii(a, b, c, d, x[i +  8],  6,  1873313359);
			d = md5_ii(d, a, b, c, x[i + 15], 10, -30611744);
			c = md5_ii(c, d, a, b, x[i +  6], 15, -1560198380);
			b = md5_ii(b, c, d, a, x[i + 13], 21,  1309151649);
			a = md5_ii(a, b, c, d, x[i +  4],  6, -145523070);
			d = md5_ii(d, a, b, c, x[i + 11], 10, -1120210379);
			c = md5_ii(c, d, a, b, x[i +  2], 15,  718787259);
			b = md5_ii(b, c, d, a, x[i +  9], 21, -343485551);

			a = safe_add(a, olda);
			b = safe_add(b, oldb);
			c = safe_add(c, oldc);
			d = safe_add(d, oldd);
		}
		return [a, b, c, d];
	}
	function binl2rstr(input) {
		var i,
			output = '';
		for (i = 0; i < input.length * 32; i += 8) {
			output += String.fromCharCode((input[i >> 5] >>> (i % 32)) & 0xFF);
		}
		return output;
	}
	function rstr2binl(input) {
		var i,
			output = [];
		output[(input.length >> 2) - 1] = undefined;
		for (i = 0; i < output.length; i += 1) {
			output[i] = 0;
		}
		for (i = 0; i < input.length * 8; i += 8) {
			output[i >> 5] |= (input.charCodeAt(i / 8) & 0xFF) << (i % 32);
		}
		return output;
	}
	function rstr_md5(s) {
		return binl2rstr(binl_md5(rstr2binl(s), s.length * 8));
	}
	function rstr_hmac_md5(key, data) {
		var i,
			bkey = rstr2binl(key),
			ipad = [],
			opad = [],
			hash;
		ipad[15] = opad[15] = undefined;
		if (bkey.length > 16) {
			bkey = binl_md5(bkey, key.length * 8);
		}
		for (i = 0; i < 16; i += 1) {
			ipad[i] = bkey[i] ^ 0x36363636;
			opad[i] = bkey[i] ^ 0x5C5C5C5C;
		}
		hash = binl_md5(ipad.concat(rstr2binl(data)), 512 + data.length * 8);
		return binl2rstr(binl_md5(opad.concat(hash), 512 + 128));
	}
	function rstr2hex(input) {
		var hex_tab = '0123456789abcdef',
			output = '',
			x,
			i;
		for (i = 0; i < input.length; i += 1) {
			x = input.charCodeAt(i);
			output += hex_tab.charAt((x >>> 4) & 0x0F) +
				hex_tab.charAt(x & 0x0F);
		}
		return output;
	}
	function str2rstr_utf8(input) {
		return unescape(encodeURIComponent(input));
	}
	function raw_md5(s) {
		return rstr_md5(str2rstr_utf8(s));
	}
	function hex_md5(s) {
		return rstr2hex(raw_md5(s));
	}
	function raw_hmac_md5(k, d) {
		return rstr_hmac_md5(str2rstr_utf8(k), str2rstr_utf8(d));
	}
	function hex_hmac_md5(k, d) {
		return rstr2hex(raw_hmac_md5(k, d));
	}
	function md5(string, key, raw) {
		if (!key) {
			if (!raw) {
				return hex_md5(string);
			}
			return raw_md5(string);
		}
		if (!raw) {
			return hex_hmac_md5(key, string);
		}
		return raw_hmac_md5(key, string);
	}

	if (typeof define === 'function' && define.amd) {
		define(function () {
			return md5;
		});
	} else {
		$.md5 = md5;
	}
}(this));

$.ajaxSetup({
	cache: false,
	contentType: 'application/x-www-form-urlencoded;charset=utf-8'
});