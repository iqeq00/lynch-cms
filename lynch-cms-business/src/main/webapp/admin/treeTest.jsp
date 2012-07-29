<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
		<base href="<%=basePath%>">
		<title>标准后台页面</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>res/common/js/jquery-easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>res/common/js/jquery-easyui/themes/icon.css"/>
		<script type="text/javascript" src="<%=basePath%>res/common/js/jquery-easyui/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>res/common/js/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>res/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#tt2").tree({  
    				url:'admin/tree_data.json',
    				onLoadError:function(){
    					alert("222");
    				}
				});  
			});
		</script>
		
	</head>

	<body>
	
		<div style="width:150px;height:500px;">
		    <ul id="tt2"></ul>
		</div>
		
	</body>
</html>
