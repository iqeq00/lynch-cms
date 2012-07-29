<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$('#roleTable').datagrid({
		title:'角色列表', 						//标题
		method:'post',							//提交方式
		iconCls:'icon-edit', 					//图标
		singleSelect:false, 					//可以多选
		height:480, 							//高度
		fitColumns: true, 						//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, 							//奇偶行颜色不同
		collapsible:true,						//可折叠
		url:"admin/roleList.jspx",		//ajax请求地址
		sortName: 'code', 						//排序的列
		sortOrder: 'desc', 						//倒序
		loadMsg:'正在加载，请稍后...',
		remoteSort: true, 						//服务器端排序
		idField:'roleId', 						//主键字段
		queryParams:{}, 						//查询条件  
		pagination:true, 						//显示分页
		rownumbers:true, 						//显示行号
		columns:[[
			{field:'ck',checkbox:true,width:10}, //显示复选框
			{field:'roleId',title:'角色Id',width:10,sortable:true},
			{field:'roleName',title:'角色名称',width:20,sortable:true},
			{field:'roleDesc',title:'角色描述',width:20,sortable:true},
			{field:'roleCreateTime',title:'角色时间',width:20,sortable:true,},
			{field:'roleUpadteTime',title:'角色时间',width:20,sortable:true,}
		]],
		toolbar:[{
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				roleAdd();
			}
		},'-',{
			text:'更新',
			iconCls:'icon-edit',
			handler:function(){
				roleUpdate();
			}
		},'-',{
			text:'可拥有权限',
			iconCls:'icon-edit',
			handler:function(){
				roleAuthoritiy();
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
				roleDelete();
			}
		},'-'],
		onLoadSuccess:function(){
			$('#roleTable').datagrid('clearSelections'); 
		}
	});
	
	//新增  
	function roleAdd(){  
		showWindow({  
	    	title:'新增角色',  
	    	href:'admin/roleAdd.jsp',  
	        width:500,  
	        height:350,  
	        onLoad: function(){  
	            $('#roleForm').form('clear');  
	        }  
	    });  
	}  
	
	//更新  
    function roleUpdate(){  
		
        var rows = $('#roleTable').datagrid('getSelections');  
        if(rows.length==0){  
            $.messager.alert('提示',"请选择你要更新的角色",'info');  
            return;  
        }  
        if(rows.length > 1){  
            $.messager.alert('提示',"只能选择一个角色信息进行更新",'info');  
            return;  
        }  
        showWindow({  
            title:'更新角色信息',  
            href:'admin/roleInfo.jsp',  
            width:500,  
            height:350,  
            onLoad: function(){  
                $("#roleForm").form('load', rows[0]);  
            }  
        });  
    }  
	
	//可拥有权限
	function roleAuthoritiy(){
		
		var rows = $('#roleTable').datagrid('getSelections');  
        if(rows.length==0){  
            $.messager.alert('提示',"请选择你要更新的角色",'info');  
            return;  
        }  
        if(rows.length > 1){  
            $.messager.alert('提示',"只能选择一个角色信息进行更新",'info');  
            return;  
        }  
        showWindow({  
        	title:'更新拥有权限信息',  
            href:'admin/roleAuthoritiySet.jsp',  
            width:800,  
            height:600,  
            onLoad: function(){  
        	
                $("#authoritiyForm").form('load', rows[0]);
                //var selectNum = new Array();
                $('#authoritiyTable').datagrid({
					title:'拥有权限列表', 						//标题
					method:'post',							//提交方式
					iconCls:'icon-edit', 					//图标
					singleSelect:false, 					//可以多选
					height:555, 							//高度
					fitColumns: true, 						//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
					striped: true, 							//奇偶行颜色不同
					collapsible:true,						//可折叠
					url:"admin/roleAuthoritiyList.jspx",		//ajax请求地址
					sortName: 'code', 						//排序的列
					sortOrder: 'desc', 						//倒序
					remoteSort: true, 						//服务器端排序
					idField:'authoritiyId', 						//主键字段
					loadMsg:'正在加载，请稍后...',
					queryParams:{roleId:$("#roleId").val()}, 						//查询条件  
					columns:[[
						{field:'ck',checkbox:true,width:10}, //显示复选框
						{field:'authoritiyId',title:'权限Id',width:10,sortable:true},
						{field:'authoritiyName',title:'权限名称',width:20,sortable:true},
						{field:'authoritiyDesc',title:'权限描述',width:20,sortable:true},
						{field:'authoritiyCreateTime',title:'创建时间',width:20,sortable:true,},
						{field:'authoritiyUpadteTime',title:'修改时间',width:20,sortable:true,},
						{field:'enabled',title:'状态',width:10,sortable:true,
							formatter:function(value,row,index){
								if(value){
									//selectNum.push(index);
									return "可用";
								}else {
									return "不可用";
								}
							}}
					]],
					toolbar:[{
						text:'修改',
						iconCls:'icon-edit',
						handler:function(){
							roleAuthoritiySet();
						}
					},'-',{
						text:'返回',
						iconCls:'icon-undo',
						handler:function(){
							closeWindow();
						}
					},],
					onLoadSuccess:function(){
	
						$('#authoritiyTable').datagrid('clearSelections'); 
						//for (var i=0;i<selectNum.length;i++){
							//$("#authoritiyTable").datagrid("selectRow",selectNum[i]);
						//}
						//selectNum.splice(0,ary.length); 
					}
				});
			}
		});  
	}
	
	//更新拥有权限信息
	function roleAuthoritiySet(){
		
		var rows = $('#authoritiyTable').datagrid('getSelections');
		if(rows.length==0){  
			$.messager.alert('提示',"您确定不给此角色任何权限?",'info');  
	        return;  
		}else {
	    	var params = "?roleId=" + $("#roleId").val();  
	        $.each(rows,function(i,cmsAuthoritiy){  
	        	params += "&authoritiyIds=" + cmsAuthoritiy.authoritiyId;  
	        });  
	        //alert(params);
	        $.post('admin/roleAuthoritiySet.jspx'+params,function(data){  
				$('#authoritiyTable').datagrid('reload');   
	            $.messager.alert('提示',data.message,'info');  
	        });  
		}
	}
	
	//删除  
    function roleDelete(){  
		
		var rows = $('#roleTable').datagrid('getSelections');
		 if(rows.length==0){  
            $.messager.alert('提示',"请选择你要删除的角色",'info');  
            return;  
        }  
        $.messager.confirm('提示','确定要删除吗?',function(result){  
            if(result){  
                var params = "";  
                $.each(rows,function(i,cmsRole){  
                    if(i==0){ 
                        params += "?roleIds="+cmsRole.roleId;  
                    }else{  
                        params += "&roleIds="+cmsRole.roleId;  
                    }    
                });  
                $.post('admin/roleDelete.jspx'+params,function(data){  
                    $('#roleTable').datagrid('reload');   
                    $.messager.alert('提示',data.message,'info');  
                });  
            }  
        });  
    }  
	
    //清空查询条件 
	$("#clearForm").click(function (){
		$('#queryForm').form('clear');  
		var params = $('#roleTable').datagrid('options').queryParams; 	//先取得 datagrid 的查询参数  
        var fields = $('#queryForm').serializeArray(); 					//自动序列化表单元素为JSON对象  
        $.each( fields, function(i, field){  
            params[field.name] = field.value; 							//设置查询参数  
        });   
        $('#roleTable').datagrid('reload');
	});
	
	//表格查询  
	$("#searchAdmin").click(function (){
        var params = $('#authoritiyTable').datagrid('options').queryParams; 	//先取得 datagrid 的查询参数  
        var fields = $('#queryForm').serializeArray(); 					//自动序列化表单元素为JSON对象  
        $.each( fields, function(i, field){  
            params[field.name] = $.trim(field.value); 					//设置查询参数  
        });   
        $('#roleTable').datagrid('reload');   
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
	<table id="roleTable"></table>
</div>

