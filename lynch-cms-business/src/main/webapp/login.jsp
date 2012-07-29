<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<base href="<%=basePath%>">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>res/common/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>res/common/js/jquery-easyui/themes/icon.css"/>
	<script type="text/javascript" src="<%=basePath%>res/common/js/jquery-easyui/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>res/common/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>res/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<!-- 
	<script type="text/javascript">
		$(function(){
			$('#loginDialog').dialog({  
				title:"CMS登录",
				closable:false,
				width:300,
				height:250,
			    modal:true,
			    buttons:[{
					text:'登录',
					handler:function(){
						$.post("j_spring_security_check",$("#loginForm").serializeArray(),
							function(data){
								$('#loginDialog').dialog('close');
								$.messager.show({
									title:'提示信息',
									msg:'恭喜您已成功登录'
								});
								$('#indexInfo').html(data);
						});
					}
				},{
					text:'重置',
					handler:function(){
						
					}
				}]
			});  
		});
		function refreshCaptcha() {
			$('#captchaImg').hide().attr('src','<c:url value="/jcaptcha.jpg"/>' + '?'+ Math.floor(Math.random() * 100)).fadeIn();
		}
	</script>
	 -->
	 <script type="text/javascript">
	 	function refreshCaptcha() {
			$('#captchaImg').hide().attr('src','<c:url value="/jcaptcha.jpg"/>' + '?'+ Math.floor(Math.random() * 100)).fadeIn();
		}
	 </script>
	</head>

	<body>
		<!-- 
		<div id="indexInfo">
			<div id="loginDialog">
				${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
				<form id="loginForm" method="post">
					<table >
						<tr>
							<td>用户名:</td>
							<td><input type='text' name='j_username'></td>
						</tr>
						<tr>
							<td>密&nbsp;&nbsp;码:</td>
							<td><input type='password' name='j_password'></td>
						</tr>
						<tr>
							<td>验证码:</td>
							<td>
								<input type='text' name='j_captcha' class="required" size='5' style="width:48px;height:20px;"/>
							</td>
						</tr>
						<tr>
							<td>
								<img id="captchaImg" src="<c:url value="/jcaptcha.jpg" />" style="width:96px;height:50px;"/>
							</td>
							<td>
								<a href="javascript:refreshCaptcha()" style="text-decoration:none">看不清楚换一张</a>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" name="_spring_security_remember_me" />两周内记住我
							</td>
						</tr>
					</table>
				</form>
			</div> 
		</div>
		 -->
		 
		<form id="loginForm" action="j_spring_security_check" method="post">
			<table >
				<tr>
					<td>用户名:</td>
					<td><input type='text' name='j_username'></td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;码:</td>
					<td><input type='password' name='j_password'></td>
				</tr>
				<tr>
					<td>验证码:</td>
					<td>
						<input type='text' name='j_captcha' size='5' style="width:48px;height:20px;"/>
					</td>
				</tr>
				<tr>
					<td>
						<img id="captchaImg" src="<c:url value="/jcaptcha.jpg" />" style="width:96px;height:50px;"/>
					</td>
					<td>
						<a href="javascript:refreshCaptcha()" style="text-decoration:none">看不清楚换一张</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="_spring_security_remember_me" />两周内记住我
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="提交" />
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</form>
		 
	</body>
</html>
