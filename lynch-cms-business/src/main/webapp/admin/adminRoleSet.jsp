<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="padding: 10" id="tabdiv">
	<table id="roleTable"></table>
</div>
<form id="roleForm" method="post">
	<div>
		<input type="hidden" id="adminId" name="adminId" value="<%=request.getParameter("adminId") %>"/>
	</div>
</form>

