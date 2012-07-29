<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>404错误页面提示</title>
    <script language="JavaScript" type="text/javascript" src=""></script>
    <script language="JavaScript" type="text/javascript" src=""></script>
    <script language="JavaScript" type="text/javascript" src=""></script>

  </head>
  
  <body>
  	<div style="text-align:center;">
  		<br>
  		<img align="middle" src="../image/imageError/error404.jpg" />
  		<br>
  		<br>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	您好这是404错误，可能是您的操作有误，请重新尝试，
    	<br>如果还未能解决，请联系我们!	
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	</div>
  </body>
</html>
