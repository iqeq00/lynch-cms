<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
		<script type="text/javascript" src="<%=basePath%>res/common/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			$.ajaxSetup({cache:false});
			$(function(){
				var west = $('body').layout('panel','west').panel({
					onCollapse:function(){
						alert('collapse');
					}
				});
				
				//构建异步树
				$("#tree").tree({  
					url:'admin/adminAsyncTree.jspx',
					//url:'admin/tree_data.json',
					onClick:function(node){
						//alert(node.attributes.url);
						setMain(node.text,node.attributes.url);
					}
				});  
				
				
				
				//setMain('管理员管理','admin/adminList.jsp');
			});
			
			function setMain(title,href){  
            	var centerURL = href;  
            	var centerTitle = title;  
            	$('body').layout('panel','center').panel({  
                	title:"所在位置："+centerTitle,  
                	href:centerURL  
            	});  
            	$('body').layout('panel','center').panel('refresh');  
            	return false;         
        	}  
			
			//弹出窗口
			function showWindow(options){
				$("#myUIWindow").window(options);
			}
			//关闭弹出窗口
			function closeWindow(){
				$("#myUIWindow").window('close');
			}
			 
			
		</script>
		
	</head>

	<body class="easyui-layout">
		
		<div region="north" split="true" style="height:100px;padding:10px">
			欢迎你，<sec:authentication property="name"/>...
		</div>
		<div region="west" split="true" title="系统菜单" style="width:240px;">
			<div class="easyui-accordion" border="false">
				<div title="管理员管理" >  
                    <ul id="tree">  
                    	<!--         
                        <li style="cursor: pointer;" onclick="setMain('管理员管理','admin/adminList.jsp')" >管理员管理</li>  
                         -->          
                    </ul>   
                </div>  
                <!-- 
                <div title="栏目管理" style="overflow:auto;">  
                    <ul>                    
                        <li style="cursor: pointer;" onclick="setMain('管理员管理','admin/adminList.jsp')" >栏目管理</li>  
                    </ul>   
                </div>
               
                <div title="角色管理" style="overflow:auto;">  
                    <ul>                    
                        <li style="cursor: pointer;" onclick="setMain('权限管理','admin/roleList.jsp')" >角色管理</li>  
                    </ul>   
                </div>
                <div title="权限管理" style="overflow:auto;" >  
                    <ul>                    
                        <li style="cursor: pointer;" onclick="setMain('权限管理','admin/authoritiyList.jsp')" >权限管理</li>  
                    </ul>   
                </div>
                <div title="资源管理" style="overflow:auto;">  
                    <ul>                    
                        <li style="cursor: pointer;" onclick="setMain('权限管理','admin/resourceList.jsp')" >资源管理</li>  
                    </ul>   
                </div>
                 -->
			</div>
		</div>
		<div region="center" title="所在位置">
		</div>
		<div id="myUIWindow" modal="true" shadow="false" minimizable="false"
			cache="false" maximizable="false" collapsible="false"
			resizable="false" style="margin: 0px; padding: 0px; overflow: auto;"></div>
	</body>
</html>
