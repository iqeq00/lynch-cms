<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$('#resourceTable').datagrid({
		title:'资源列表', 						//标题
		method:'post',							//提交方式
		iconCls:'icon-edit', 					//图标
		singleSelect:false, 					//可以多选
		height:480, 							//高度
		fitColumns: true, 						//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, 							//奇偶行颜色不同
		collapsible:true,						//可折叠
		url:"admin/resourceList.jspx",		//ajax请求地址
		sortName: 'code', 						//排序的列
		sortOrder: 'desc', 						//倒序
		loadMsg:'正在加载，请稍后...',
		remoteSort: true, 						//服务器端排序
		idField:'resourceId', 						//主键字段
		queryParams:{}, 						//查询条件  
		pagination:true, 						//显示分页
		rownumbers:true, 						//显示行号
		columns:[[
			{field:'ck',checkbox:true,width:10}, //显示复选框
			{field:'resourceId',title:'资源Id',width:10,sortable:true},
			{field:'resourceName',title:'资源名称',width:10,sortable:true},
			{field:'resourceType',title:'资源类型',width:10,sortable:true},
			{field:'resourceString',title:'资源连接',width:10,sortable:true},
			{field:'resourcePriority',title:'资源优先级',width:10,sortable:true},
			{field:'resourceDesc',title:'资源描述',width:10,sortable:true},
			{field:'resourceCreateTime',title:'资源创建时间',width:15,sortable:true,},
			{field:'resourceUpadteTime',title:'资源修改时间',width:15,sortable:true,}
		]],
		toolbar:[{
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				resourceAdd();
			}
		},'-',{
			text:'更新',
			iconCls:'icon-edit',
			handler:function(){
				resourceUpdate();
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				resourceDelete();
			}
		},'-'],
		onLoadSuccess:function(){
			$('#resourceTable').datagrid('clearSelections'); 
		}
	});
	
	//新增  
	function resourceAdd(){  
		showWindow({  
	    	title:'新增资源',  
	    	href:'admin/resourceAdd.jsp',  
	        width:500,  
	        height:350,  
	        onLoad: function(){  
	            $('#resourceForm').form('clear');  
	        }  
	    });  
	}  
	
	//更新  
    function resourceUpdate(){  
		
        var rows = $('#resourceTable').datagrid('getSelections');  
        if(rows.length==0){  
            $.messager.alert('提示',"请选择你要更新的资源",'info');  
            return;  
        }  
        if(rows.length > 1){  
            $.messager.alert('提示',"只能选择一个资源信息进行更新",'info');  
            return;  
        }  
        showWindow({  
            title:'更新资源信息',  
            href:'admin/resourceInfo.jsp',  
            width:500,  
            height:350,  
            onLoad: function(){  
                $("#resourceForm").form('load', rows[0]);  
            }  
        });  
    }  
	
	//删除  
    function resourceDelete(){  
		
		var rows = $('#resourceTable').datagrid('getSelections');
		 if(rows.length==0){  
            $.messager.alert('提示',"请选择你要删除的资源",'info');  
            return;  
        }  
        $.messager.confirm('提示','确定要删除吗?',function(result){  
            if(result){  
                var params = "";  
                $.each(rows,function(i,cmsResource){  
                    if(i==0){ 
                        params += "?resourceIds="+cmsResource.resourceId;  
                    }else{  
                        params += "&resourceIds="+cmsResource.resourceId;  
                    }    
                });  
                $.post('admin/resourceDelete.jspx'+params,function(data){  
                    $('#resourceTable').datagrid('reload');   
                    $.messager.alert('提示',data.message,'info');  
                });  
            }  
        });  
    }  
	
    //清空查询条件 
	$("#clearForm").click(function (){
		$('#queryForm').form('clear');  
		var params = $('#resourceTable').datagrid('options').queryParams; 	//先取得 datagrid 的查询参数  
        var fields = $('#resourceForm').serializeArray(); 					//自动序列化表单元素为JSON对象  
        $.each( fields, function(i, field){  
            params[field.name] = field.value; 							//设置查询参数  
        });   
        $('#resourceTable').datagrid('reload');
	});
	
	//表格查询  
	$("#searchAdmin").click(function (){
        var params = $('#resourceTable').datagrid('options').queryParams; 	//先取得 datagrid 的查询参数  
        var fields = $('#queryForm').serializeArray(); 					//自动序列化表单元素为JSON对象  
        $.each( fields, function(i, field){  
            params[field.name] = $.trim(field.value); 					//设置查询参数  
        });   
        $('#resourceTable').datagrid('reload');   
	});
	
});
</script>

<!-- 
<form id="queryForm" style="margin:10;text-align: center;">  
	<table width="100%">  
	    <tr>  
		    <td>名字：<input name="adminName" type="text" style="width:200px;"/></td>  
		    <td>年龄：<input name="adminAge" min="1" max="120" increment="1" class="easyui-numberspinner" style="width:200px;"/></td>  
		    <td align="center"><a id="clearForm" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>  
	    </tr>  
	    <tr>  
		    <td>日期：<input name="createTime" type="text" style="width:200px;" class="Wdate" onClick="WdatePicker();"/></td>  
		    <td>部门：<input id="deptCombo" name="deptId" style="width:200px;"/></td>  
		    <td align="center"><a id="searchAdmin" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>  
	    </tr>  
	</table>  
</form>  
 -->
<div style="padding: 10" id="tabdiv">
	<table id="resourceTable"></table>
</div>

