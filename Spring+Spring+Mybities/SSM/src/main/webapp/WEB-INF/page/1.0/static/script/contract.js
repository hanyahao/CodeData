
var Mgmt = {
	init: function(){
		this.navInit();
	},
	navInit: function(){
		//加载左侧栏
		$('#Nav_Contract').click(function(e){
			JUI.menu.load('/contract/leftmenu', function(){
				JUI.menu.expandAll();
				var $el = $('#JUI_Menu a.jui-loadpage:eq(0)').trigger('click');
			});
		});

		$('#Password').click(function(){
			JUI.loadConfirm('/contract/reqmodifypw', '修改密码', 450, 170, function(){
				var params = JUI.getFormData('#JUI_Dialog_Form');
				if(!params.passwordNow.length){
					JUI.dialogTips('请输入当前密码');
				}else if(!params.passwordNew.length){
					JUI.dialogTips('请输入新密码');
				}else if(!/^.{6,255}$/.test(params.passwordNew)){
					JUI.dialogTips('新密码请输入6-255位任意字符');
				}else if(params.passwordNew != params.passwordNewAgain){
					JUI.dialogTips('两次输入的新密码不相同');
				}else{
					JUI.dialogButton();
					JUI.dialogTips('正在处理...', true);
					params.passwordNow = md5(params.passwordNow);
					params.passwordNew = md5(params.passwordNew);
					params.passwordNewAgain = md5(params.passwordNewAgain);
					$.ajax({
						type: 'POST',
						url: '/contract/modifypw',
						dataType: 'json',
						data: params,
						success: function(data, ts, jqXHR){
							if(/^ok$/i.test(data.result)){
								JUI.dialogTips('修改密码成功');
								setTimeout(function(){
									JUI.dialogButton(true);
									JUI.close();
								}, 1000);
							}else{
								JUI.dialogButton(true);
								JUI.dialogTips(data.message);
							}
						},
						error: function(){
							JUI.dialogButton(true);
							JUI.dialogTips('修改密码失败，请重试');
						}
					});
				}
			});
		});

		$('#Quit').click(function(){
			JUI.confirm('确实要退出吗？', '退出系统', function(){
				JUI.dialogButton();
				JUI.dialogTips('正在退出，请稍后...', true);
				window.location.href='/contract/logout';
			});
		});
	}
};


/****模板类型****/
var MgmtTemplateType = {
	listInit: function(){
		var _this = this;
		var list = new JUI.tableList({
			method: 'POST',
			url: '/contract/querytt',
			transdata: _this.transList
		});
		list.load();
	},	
	transList: function(data){
		var result = [];
		for(var i=0; i<data.length; i++){
			result.push([
				data[i].name,
				transType(data[i].type),
				data[i].description,
				'<a class="jui-del" href="javascript:;" onClick="delTemplateType('+data[i].id+')" title="删除">删除</a>'
			]);
		}
		return result;
	},
	addInit: function(){
		var $form = $('#JUI_Main form.main-form');
		$form.validate({
			errorElement: 'span',
			submitHandler: function(form){
				$form.find('button.submit').prop('disabled', 'disabled');
				var params, url, method;
				params = JUI.getFormData(form);
				url = $form.attr('action');
				method = $form.attr('method');
				
				$.ajax({
					type: method,
					url: url,
					dataType: 'json',
					data: params,
					success: function(data, ts, jqXHR){
						$form.find('button.submit').prop('disabled', false);
						if(/^ok$/i.test(data.result)){
							JUI.alert('添加成功', '', function(){
								JUI.loadPage('/contract/reqttmgmt', function(){ MgmtTemplateType.listInit(); });
							});
						}else{
							JUI.alert(data.message);
						}
					},
					error: function(xhr, ts, errorThrown){
						$form.find('button.submit').prop('disabled', false);
						JUI.alert(ts || '请求失败');
					}
				});
			},
			rules:{
				name: {
					required: true,
					maxlength: 45
				},
				type: {
					required: true,
					maxlength: 255
				},
				productNum: {
					maxlength: 11
				},
				description: {
					maxlength: 255
				}
			}
		});

		$form.find('button.cancel').click(function(e){
			e.preventDefault();
			JUI.loadPage('/contract/reqttmgmt', function(){ MgmtTemplateType.listInit(); });
		});
	},
	
};
function delTemplateType(id){
	$.ajax({
		type: "POST",
		url: "/contract/deltt",
		dataType: 'json',
		data: {id:id},
		success: function(data){
			if(data.result=="OK"){
				JUI.alert('删除成功', '删除模板类型', function(){
					JUI.loadPage('/contract/reqttmgmt', function(){ MgmtTemplateType.listInit(); });
					return true;
				});
			}else{
				JUI.alert(data.message);
			}
		},
		error: function(){
			$(_this).prop('disabled', false);
			JUI.alert('操作失败');
		}
	});	
}
/****模板****/
var MgmtTemplate = {
	listInit: function(){
		var _this = this;
		var list = new JUI.tableList({
			method: 'POST',
			url: '/contract/querytemplate',
			transdata: _this.transList
		});
		list.load();
	},	
	transList: function(data){
		var result = [];
		for(var i=0; i<data.length; i++){
			result.push([
				data[i].name,
				data[i].description,
				transType(data[i].type), 
				'<a class="jui-edit jui-loadpage" href="/contract/reqtemplateedit?id=' + data[i].id + '" title="编辑">编辑</a>'+
				'<a class="jui-del ml10" href="javascript:;" onClick=delTemplate('+data[i].id+') title="删除">删除</a>'+
				' <a class="ml10" href="exporttemplate?id='+data[i].id+'">下载 </a>'
			]);
		}
		return result;
	},
	ttChooseInit: function(){
		var $form = $('#JUI_Main form.main-form');
		$form.validate({
			errorElement: 'span',
			submitHandler: function(form){
				$form.find('button.submit').prop('disabled', 'disabled');
				var templateTypeId = $("#templateTypeId").val();
				JUI.loadPage('/contract/reqtemplateadd?id='+templateTypeId, function(){ MgmtTemplate.addInit(); });
			},
			rules:{
				templateTypeId: {
					required: true
				}
			}
		});

		$form.find('button.cancel').click(function(e){
			e.preventDefault();
			JUI.loadPage('/contract/reqtemplatemgmt', function(){ MgmtTemplate.listInit(); });
		});
	},
	addInit: function(){
		var $form = $('#JUI_Main form.main-form');
		$form.validate({
			errorElement: 'span',
			submitHandler: function(form){
				$form.find('button.submit').prop('disabled', 'disabled');
				var params, url, method;
				params = JUI.getFormData(form);
				url = $form.attr('action');
				method = $form.attr('method');
				params.content = CKEDITOR.instances.content.getData();
				$.ajax({
					type: method,
					url: url,
					dataType: 'json',
					data: params,
					success: function(data, ts, jqXHR){
						$form.find('button.submit').prop('disabled', false);
						if(/^ok$/i.test(data.result)){
							JUI.alert('添加成功', '', function(){
								JUI.loadPage('/contract/reqtemplatemgmt', function(){ MgmtTemplate.listInit(); });
							});
						}else{
							JUI.alert(data.message);
						}
					},
					error: function(xhr, ts, errorThrown){
						$form.find('button.submit').prop('disabled', false);
						JUI.alert(ts || '请求失败');
					}
				});
			},
			rules:{
				type:{
					required:true
				},
				name: {
					required: true,
					maxlength: 45
				},
				description: {
					maxlength: 255
				}
			}
		});

		$form.find('button.cancel').click(function(e){
			e.preventDefault();
			JUI.loadPage('/contract/reqtemplatemgmt', function(){ MgmtTemplate.listInit(); });
		});
	},
	
	editInit: function(){
		var $form = $('#JUI_Main form.main-form');
		$form.validate({
			errorElement: 'span',
			submitHandler: function(form){
				$form.find('button.submit').prop('disabled', 'disabled');
				var params, url, method;
				params = JUI.getFormData(form);
				url = $form.attr('action');
				method = $form.attr('method');
				params.content = CKEDITOR.instances.content.getData();
				$.ajax({
					type: method,
					url: url,
					dataType: 'json',
					data: params,
					success: function(data, ts, jqXHR){
						$form.find('button.submit').prop('disabled', false);
						if(/^ok$/i.test(data.result)){
							JUI.alert('编辑成功', '', function(){
								JUI.loadPage('/contract/reqtemplatemgmt', function(){ MgmtTemplate.listInit(); });
							});
						}else{
							JUI.alert(data.message);
						}
					},
					error: function(xhr, ts, errorThrown){
						$form.find('button.submit').prop('disabled', false);
						JUI.alert(ts || '请求失败');
					}
				});
			},
			rules:{
				type:{
					required:true
				}
				name: {
					required: true,
					maxlength: 45
				},
				description: {
					maxlength: 255
				}
			}
		});

		$form.find('button.cancel').click(function(e){
			e.preventDefault();
			JUI.loadPage('/contract/reqtemplatemgmt', function(){ MgmtTemplate.listInit(); });
		});
	},
	
};
// 删除模板
function delTemplate(id){
	$.ajax({
		type: "POST",
		url: "/contract/deltemplate",
		dataType: 'json',
		data: {id:id},
		success: function(data){
			if(data.result=="OK"){
				JUI.alert('删除成功', '删除模板', function(){
					JUI.loadPage('/contract/reqtemplatemgmt', function(){ MgmtTemplate.listInit(); });
					return true;
				});
			}else{
				JUI.alert(data.message);
			}
		},
		error: function(){
			$(_this).prop('disabled', false);
			JUI.alert('操作失败');
		}
	});	
}

function exportTemplate(id){
	$.ajax({
		type: "POST",
		url: "/contract/exporttemplate",
		dataType: 'json',
		data: {id:id},
		success: function(data){
			if(data.result=="OK"){
				JUI.alert('导出成功', '导出模板', function(){
					return true;
				});
			}else{
				JUI.alert(data.message);
			}
		},
		error: function(){
			$(_this).prop('disabled', false);
			JUI.alert('操作失败');
		}
	});	
}
/***************合同**************/
var MgmtContract={
	listInit: function(){
		var _this = this;
		var list = new JUI.tableList({
			method: 'POST',
			url: '/contract/querycontract',
			transdata: _this.transList
		});
		list.load();
	},	
	transList: function(data){
		var result = [];
		for(var i=0; i<data.length; i++){
			var optHtml = '';
			if(data[i].status == '1'){
				optHtml = '<a class="jui-loadpage ml10" href="/contract/requploadsc?id=' + data[i].id + '" title="上传扫描件">上传扫描件</a>'
						+ '<a class="ml10" href="javaScript:;" onClick="invalidcontract('+data[i].id+')" title="作废">作废</a>';
			}
			optHtml += ' <a class="ml10" href="exportcc?id='+data[i].id+'">下载 </a>'
			result.push([
			    data[i].number,
				data[i].name,
				data[i].templateName,
				transType(data[i].type), 
				transContractStatus(data[i].status),
				JUI.dateToStr(new Date(data[i].createDatetime), 'yyyy-MM-dd HH:mm:ss'),
				optHtml
			]);
		}
		return result;
	},
	templateChooseInit: function(){
		var $form = $('#JUI_Main form.main-form');
		$form.validate({
			errorElement: 'span',
			submitHandler: function(form){
				$form.find('button.submit').prop('disabled', 'disabled');
				var templateId = $("#templateId").val();
				JUI.loadPage('/contract/reqcontractadd?templateId='+templateId, function(){ MgmtContract.addInit(); });
			},
			rules:{
				templateId: {
					required: true
				}
			}
		});

		$form.find('button.cancel').click(function(e){
			e.preventDefault();
			JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
		});
	},
	addInit: function(){
		var $form = $('#JUI_Main form.main-form');
		$form.validate({
			errorElement: 'span',
			submitHandler: function(form){
				$form.find('button.submit').prop('disabled', 'disabled');
				var params, url, method;
				params = JUI.getFormData(form);
				url = $form.attr('action');
				method = $form.attr('method');
				$.ajax({
					type: method,
					url: url,
					dataType: 'json',
					data: params,
					success: function(data, ts, jqXHR){
						$form.find('button.submit').prop('disabled', false);
						if(/^ok$/i.test(data.result)){
							JUI.alert('添加成功', '', function(){
								JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
							});
						}else{
							JUI.alert(data.message);
						}
					},
					error: function(xhr, ts, errorThrown){
						$form.find('button.submit').prop('disabled', false);
						JUI.alert(ts || '请求失败');
					}
				});
			},
			rules:{
				name: {
					required: true,
					maxlength: 45
				},
				bname:{
					required:true
				},
				idCard:{
					required:true,
					idcard: true
				},
				totalPrice:{
					required:true,
					number:true
				}
			}
		});

		$form.find('button.cancel').click(function(e){
			e.preventDefault();
			JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
		});
	},
	addtgInit: function(){
		var $form = $('#JUI_Main form.main-form');
		$form.validate({
			errorElement: 'span',
			submitHandler: function(form){
				$form.find('button.submit').prop('disabled', 'disabled');
				var params, url, method;
				params = JUI.getFormData(form);
				url = $form.attr('action');
				method = $form.attr('method');
				$.ajax({
					type: method,
					url: url,
					dataType: 'json',
					data: params,
					success: function(data, ts, jqXHR){
						$form.find('button.submit').prop('disabled', false);
						if(/^ok$/i.test(data.result)){
							JUI.alert('添加成功', '', function(){
								JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
							});
						}else{
							JUI.alert(data.message);
						}
					},
					error: function(xhr, ts, errorThrown){
						$form.find('button.submit').prop('disabled', false);
						JUI.alert(ts || '请求失败');
					}
				});
			},
			rules:{
				name: {
					required: true,
					maxlength: 45
				},
				bname:{
					required:true
				}
			}
		});

		$form.find('button.cancel').click(function(e){
			e.preventDefault();
			JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
		});
	},
	adddlInit: function(){
		var $form = $('#JUI_Main form.main-form');
		$form.validate({
			errorElement: 'span',
			submitHandler: function(form){
				$form.find('button.submit').prop('disabled', 'disabled');
				var params, url, method;
				params = JUI.getFormData(form);
				url = $form.attr('action');
				method = $form.attr('method');
				$.ajax({
					type: method,
					url: url,
					dataType: 'json',
					data: params,
					success: function(data, ts, jqXHR){
						$form.find('button.submit').prop('disabled', false);
						if(/^ok$/i.test(data.result)){
							JUI.alert('添加成功', '', function(){
								JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
							});
						}else{
							JUI.alert(data.message);
						}
					},
					error: function(xhr, ts, errorThrown){
						$form.find('button.submit').prop('disabled', false);
						JUI.alert(ts || '请求失败');
					}
				});
			},
			rules:{
				name: {
					required: true,
					maxlength: 45
				},
				bname:{
					required:true
				},
				idCard:{
					required:true,
					idcard: true
				},
				domicile:{
					required:true
				},
				company:{
					required:true
				},
				address:{
					required:true
				},
				province:{
					required:true
				},
				startDay:{
					required:true,
					dateISO:true
				},
				endDay:{
					required:true,
					dateISO:true
				}
			}
		});

		$form.find('button.cancel').click(function(e){
			e.preventDefault();
			JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
		});
	},
	uploadscInit: function(){
		var $form = $('#JUI_Main form.main-form');
		var self = this;

		// 上传
		$form.validate({
			errorElement: 'span',
			submitHandler: function (form) {
				$('#logs').hide();
				$form.find('button.submit').prop('disabled', 'disabled');

				var _html5endable = true;
				try{
					!!FileReader || !!FormData;
				}catch(e){ _html5endable = false; }

				if(!_html5endable){
					if(!self.frameid){
						self.frameid = 'uploadify_' + new Date().getTime();
						self.initIframe(self.frameid);
						$form.attr('target', self.frameid);
						$form.append('<input type="hidden" name="type" value="jsonp" />')
							.append('<input type="hidden" name="frameid" value="' + self.frameid + '" />');
					}
					form.submit();
				}else{
					var url = $form.attr('action');
					var $id = $form.find('input[name="id"]');
					var $el = $form.find('input[type=file]');
					var file = $el.get(0).files[0];

					var formData = new FormData();
					formData.append($el.attr('name'), file);
					formData.append($id.attr('name'), $id.val());

					var xhr = new XMLHttpRequest();
					xhr.open('POST', url, true);
					//xhr.setRequestHeader('Content-Type', 'multipart/form-data;');
					xhr.upload.onprogress = function (e) {
						// 进度更新事件
						//console.log(['upload.onprogress', e.loaded, e.total]);
					};
					xhr.onload = function (e) {
						if (this.status == 200) {
							var data;
							try{
								data = eval( '(' + this.responseText + ')');
							}catch(err){
								data = '{message:["Eval object string error!"], result:"fail", error_code:"000000"}';
							}
							
							if(/^ok$/i.test(data.result)){
								JUI.alert('上传成功', '', function(){
									JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
								});
							}else{
								JUI.alert(data.message);
							}
						} else {
							self.uploadCallback('{message:[' + this.statusText + '], result:"fail", error_code:"000000"}');
						}
					};
					xhr.send(formData);
				}

			},
			rules: {
				file: {
					required: true
				}
			}
		});

		$form.find('button.cancel').click(function(e){
			e.preventDefault();
			JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
		});
	},
};

// 导出全部合同清单
function exportac(){
	$.ajax({
		type: "POST",
		url: "/contract/exportac",
		dataType: 'json',
		success: function(data){
			if(data.result=="OK"){
				JUI.alert('导出成功', '导出合同清单', function(){
					return true;
				});
			}else{
				JUI.alert(data.message);
			}
		},
		error: function(){
			$(_this).prop('disabled', false);
			JUI.alert('操作失败');
		}
	});	
}

function invalidcontract(id){
	JUI.alert('确定要作废吗','',function temp(){
		$.ajax({
			type: "POST",
			url: "/contract/invalidcontract",
			dataType: 'json',
			data:{id:id},
			success: function(data){
				if(data.result=="OK"){
					JUI.loadPage('/contract/reqcontractmgmt', function(){ MgmtContract.listInit(); });
				}else{
					JUI.alert(data.message);
				}
			},
			error: function(){
				$(_this).prop('disabled', false);
				JUI.alert('操作失败');
			}
		});	
		return true;
	});
	
}

function transContractStatus(status){
	var str = '';
	if(status === 1){
		str = '新建';
	}else if(status === 2){
		str = '归档';
	}else if(status === -1){
		str = '作废';
	}else{
		str = '未知';
	}
	return str;
}

function transType(type){
	var str = '';
	if(type == 'xs'){
		str = '销售合同';
	}else if(type == 'dl'){
		str = '代理合同';
	}else if(type == 'tg'){
		str = '托管合同';
	}
	return str;
}

$(function(){
	Mgmt.init();
});
