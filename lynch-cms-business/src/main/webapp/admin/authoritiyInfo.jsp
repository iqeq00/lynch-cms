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
function authoritiyUpdate(){
	$.post("admin/authoritiyUpdate.jspx",$("#authoritiyForm").serializeArray(),
		function(data){
			$('#myUIWindow').window('close');
			$('#authoritiyTable').datagrid('reload');  
			$.messager.alert('提示信息',data.message,'info');
	});
}
</script>
<form id="authoritiyForm" method="post">
	<div >
		<input type="hidden" name="authoritiyId" />
		<input type="hidden" name="authoritiyCreateTime" />
		权限名称：<input type="text" name="authoritiyName" /><br/>
		权限描述：<input type="text" name="authoritiyDesc" /><br/>
		<a class="easyui-linkbutton" id="btn-update" iconCls="icon-save" onclick="authoritiyUpdate();"  >修改</a>
		<a class="easyui-linkbutton" id="btn-back" iconCls="icon-back" onclick="closeWindow();" >返回</a>
	</div>
</form>