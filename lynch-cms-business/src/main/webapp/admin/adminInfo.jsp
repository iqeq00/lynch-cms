<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style type="text/css">
	.form_div{
	width:490px;
	height:340px;
	line-height:340px;
	border:1px solid #777;
	text-align:center;
	display:table-cell;
	vertical-align:middle;
	}
</style>
<script type="text/javascript">
function adminUpdate(){
	$.post("admin/adminUpdate.jspx",$("#adminForm").serializeArray(),
		function(data){
			$('#myUIWindow').window('close');
			$('#adminTable').datagrid('reload');  
			$.messager.alert('提示信息',data.message,'info');
	});
}
</script>
<form id="adminForm" method="post">
	<div >
		<input type="hidden" name="adminId" />
		<input type="hidden" name="adminCreateTime" />
		用户姓名：<input type="text" name="adminName" /><br/>
		用户密码：<input type="password" name="adminPassword" /><br/>
		<a class="easyui-linkbutton" id="btn-update" iconCls="icon-save" onclick="adminUpdate();"  >修改</a>
		<a class="easyui-linkbutton" id="btn-back" iconCls="icon-back" onclick="closeWindow();" >返回</a>
	</div>
</form>