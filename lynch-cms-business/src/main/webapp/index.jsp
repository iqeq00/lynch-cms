<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>欢迎页面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body>
		这是首页，欢迎您,<sec:authentication property="name"/><br/>
		${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }<br>
		
		
		<sec:authorize url="/admin/**" >
			<a href="admin/main.jsp">admin的main页面</a><br>
		</sec:authorize>
		<sec:authorize url="/index/**" >
			<a href="index/testIndex.jsp">前台用户</a><br>
		</sec:authorize>
		<a href="j_spring_security_logout">推出系统</a>
		
		
		<!--
		
			<a href="admin/adminAdd.jsp">添加admin</a><br>
			<a href="admin/adminListUrl.jspx?page=1">admin列表Url方式</a><br>
			<a href="admin/adminListForm.jspx">admin列表Forml方式</a><br>
		 
		 -->
	</body>
</html>
