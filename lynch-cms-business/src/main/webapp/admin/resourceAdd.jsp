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
function resourceAdd(){
	$.post("admin/resourceAdd.jspx",$("#resourceForm").serializeArray(),
		function(data){
			$('#myUIWindow').window('close');
			$('#resourceTable').datagrid('reload');  
			$.messager.alert('提示信息',data.message,'info');
	});
}
</script>
<form id="resourceForm" method="post">
	<div >
		资源名称：<input type="text" name="resourceName" /><br/>
		资源类型：<input type="text" name="resourceType" /><br/>
		资源连接：<input type="text" name="resourceString" /><br/>
		资源优先级：<input type="text" name="resourcePriority" /><br/>
		资源描述：<input type="text" name="resourceDesc" /><br/>
		<a class="easyui-linkbutton" id="btn-add" iconCls="icon-save" onclick="resourceAdd();"  >保存</a>
		<a class="easyui-linkbutton" id="btn-back" iconCls="icon-back" onclick="closeWindow();" >返回</a>
	</div>
</form>

