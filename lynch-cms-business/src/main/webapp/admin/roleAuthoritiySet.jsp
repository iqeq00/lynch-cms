<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="padding: 10" id="tabdiv">
	<table id="authoritiyTable"></table>
</div>
<form id="authoritiyForm" method="post">
	<div>
		<input type="hidden" id="roleId" name="roleId" value="<%=request.getParameter("roleId") %>"/>
	</div>
</form>

