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
function roleUpdate(){
	$.post("admin/roleUpdate.jspx",$("#roleForm").serializeArray(),
		function(data){
			$('#myUIWindow').window('close');
			$('#roleTable').datagrid('reload');  
			$.messager.alert('提示信息',data.message,'info');
	});
}
</script>
<form id="roleForm" method="post">
	<div >
		<input type="hidden" name="roleId" />
		<input type="hidden" name="roleCreateTime" />
		角色名称：<input type="text" name="roleName" /><br/>
		角色描述：<input type="text" name="roleDesc" /><br/>
		<a class="easyui-linkbutton" id="btn-update" iconCls="icon-save" onclick="roleUpdate();"  >修改</a>
		<a class="easyui-linkbutton" id="btn-back" iconCls="icon-back" onclick="closeWindow();" >返回</a>
	</div>
</form>