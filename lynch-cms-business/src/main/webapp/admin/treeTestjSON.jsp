<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%

 String treeData="[{\"id\":1,\"text\":\"管理员管理\",\"iconCls\":\"icon-ok\",\"children\":[{\"id\":2,\"text\":\"管理员管理\",\"checked\":true},{\"id\":3,\"text\":\"角色管理\",\"state\":\"open\",\"children\":[{\"id\":4,\"text\":\"角色管理\",\"checked\":true,\"iconCls\":\"icon-reload\"},{\"id\": 8,\"text\":\"Async Folder\",\"state\":\"closed\"}]}]},{\"text\":\"Languages\",\"state\":\"closed\",\"children\":[{\"text\":\"Java\"},{\"text\":\"C#\"}]}]";


	response.getWriter().write(treeData);
%>
