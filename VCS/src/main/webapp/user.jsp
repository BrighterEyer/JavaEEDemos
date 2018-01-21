<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head id="Head1">
<%@ include file="path.jsp"%>
<title>用户列表</title>
<script type="text/javascript"
	src="${demoPath}static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${demoPath}static/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${demoPath}static/js/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${demoPath}static/js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${demoPath}static/css/default.css" />
<%-- <script type="text/javascript" src='${demoPath}static/js/outlook2.js'> </script> --%>
<script type="text/javascript"
	src='${demoPath}static/js/publicmethod.js'>
	
</script>
<style type="text/css">
.cs-navi-tab {
	padding: 5px;
}

.cs-navi-tab:hover {
	padding: 5px;
	text-decoration: none;
	font-size: 16px;
	color: black;
}
</style>
<script type="text/javascript">
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	//关闭登录窗口
	function closePwd() {
		$('#w').window('close');
	}
	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}

		$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(
				msg) {
			msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$newpass.val('');
			$rePass.val('');
			close();
		})

	}

	$(function() {
		openPwd();
		$('#editpass').click(function() {
			$('#w').window('open');
		});
		$('#btnEp').click(function() {
			serverLogin();
		})
		$('#btnCancel').click(function() {
			closePwd();
		})
		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

				if (r) {
					location.href = '/ajax/loginout.ashx';
				}
			});
		})
	});
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="${demoPath}static/image/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px;
        background: url(${demoPath}static/image/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float: right; padding-right: 20px;" class="head">欢迎
			用户登录 <a id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 16px;"><img
			src="${demoPath}static/image/blocks.gif" width="20" height="20"
			align="absmiddle" />VCS后台管理</span>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer">By 天源迪科信息有限公司</div>
	</div>
	<div region="west" hide="true" split="true" title="导航菜单"
		style="width: 180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<div title="系统管理">
				<a href="javascript:void(0);" src="user/userList.html"
					iconCls="icon-user" class="cs-navi-tab">用户管理</a>
				</p>
				<a href="javascript:void(0);" src="role/roleList.html"
					class="cs-navi-tab">角色管理</a>
				</p>
				<a href="javascript:void(0);" src="permission/permissonList.html"
					class="cs-navi-tab">权限设置</a>
				</p>
				<a href="javascript:void(0);" src="function/functionList.html"
					class="cs-navi-tab">资源管理</a>
				</p>
				<a href="javascript:void(0);" src="system/log.html"
					class="cs-navi-tab">系统日志</a>
				</p>
			</div>
			<div title="商户管理">
				<a href="javascript:void(0);" src="user/userList.html"
					class="cs-navi-tab">商户管理</a>
				</p>
				<a href="javascript:void(0);" src="role/roleList.html"
					class="cs-navi-tab">商户管理</a>
				</p>
				<a href="javascript:void(0);" src="permission/permissonList.html"
					class="cs-navi-tab">商户管理</a>
				</p>
				<a href="javascript:void(0);" src="function/functionList.html"
					class="cs-navi-tab">商户管理</a>
				</p>
				<a href="javascript:void(0);" src="system/log.html"
					class="cs-navi-tab">商户管理</a>
				</p>
			</div>
		</div>
	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用"
				style="padding: 20px; overflow: hidden; color: red;">
				<h1 style="font-size: 24px;">* 作者：天源迪科信息有限公司</h1>
				<h1 style="font-size: 24px;">
					* BLOG: <a style="font-size: 24px; color: green;"
						href="https://github.com/liuren">天源迪科信息有限公司</a>
				</h1>
				<h1 style="font-size: 24px;">* 技术支持QQ：393993507</h1>
			</div>
		</div>
	</div>
	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a id="btnCancel"
					class="easyui-linkbutton" icon="icon-cancel"
					href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
</html>