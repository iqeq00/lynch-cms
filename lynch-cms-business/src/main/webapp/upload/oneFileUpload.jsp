<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<base href="<%=basePath%>">
	
	<title>My JSP 'oneFileUpload.jsp' starting page</title>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	</head>
	
	<body>
		This is my onefileUpload page.
		<br>
		<form method="POST" action="admin/oneFileUpload.jspx"
			enctype="multipart/form-data">
			<input type="text" name="name" /> <input type="file" name="file" />
			<input type="submit" />
		</form>
	</body>
</html>
