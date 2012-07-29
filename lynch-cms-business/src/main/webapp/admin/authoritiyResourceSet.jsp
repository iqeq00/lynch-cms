<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="padding: 10" id="tabdiv">
	<table id="resourceTable"></table>
</div>
<form id="resourceForm" method="post">
	<div>
		<input type="hidden" id="authoritiyId" name="authoritiyId" value="<%=request.getParameter("authoritiyId") %>"/>
	</div>
</form>

