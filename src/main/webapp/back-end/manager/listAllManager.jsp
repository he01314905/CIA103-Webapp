<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ManagerService managerSvc = new ManagerService();
    List<ManagerVO> list = managerSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllManager.jsp</title>

<style>
body{
	background: url("https://s3-alpha-sig.figma.com/img/dfdb/7342/e97f5a6af1cf2872bb12d94cb62f2995?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=nJ8qFx0Y5zLo22-dO3WfTfB4t6JAdWsw20uauzDjlQhbN2tcJ09YMW0psMZxXb4XNEBfOSdWW~5v9oMrxdEmHFobX3iFgTWgzglH7R~-03Euac32oT-SSOAEC5AuaZnVk9YGRg5OwZZLQO4SuSdhr6IiCtrErqbWexeId792SyK-SUtY7JoEgIs7KxoBO3rBLqZR5JJtxGsrNhI7jAWX9fzvZ6y9c4cpK1EQ-xW24lur7dQo4WkjTFjkoAD9oexQwmYe8B7hZ8bOYJnTFHA6Tsy5LrU3RVwbPCheKtWm5DZA9n2LLTn0Dx01FDodOiJXktEpobiWyrcHxxxWdPUM5Q__") lightgray 50% / cover no-repeat;
	
	display:flex;
	flex-direction:column;
	align-items:center;
}
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  input{
  border-radius:10px;
  }
  input:hover{
	cursor: pointer;
	box-shadow: 2px 2px 1px black;
}
input:active{
	box-shadow:3px 3px 4px black inset ,inset -3px -3px 3px  black;
}
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllManager.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/images/cat1.gif" width="150" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>管理員編號</th>
		<th>管理員姓名</th>
		<th>管理員帳號</th>
		<th>管理員密碼</th>
		<th>管理員狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="managerVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${managerVO.managerNo}</td>
			<td>${managerVO.managerName}</td>
			<td>${managerVO.managerAccount}</td>
			<td>${managerVO.managerPassword}</td>
			<td>${managerVO.managerstatus}</td>
			<td>
			  <FORM METHOD="post" type="" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="managerNo"  value="${managerVO.managerNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/manager/manager.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="managerNo"  value="${managerVO.managerNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>