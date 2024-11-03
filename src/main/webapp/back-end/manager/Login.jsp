<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>

    <%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
   ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO");
%>
<style>
body{
	background: url("https://s3-alpha-sig.figma.com/img/dfdb/7342/e97f5a6af1cf2872bb12d94cb62f2995?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=nJ8qFx0Y5zLo22-dO3WfTfB4t6JAdWsw20uauzDjlQhbN2tcJ09YMW0psMZxXb4XNEBfOSdWW~5v9oMrxdEmHFobX3iFgTWgzglH7R~-03Euac32oT-SSOAEC5AuaZnVk9YGRg5OwZZLQO4SuSdhr6IiCtrErqbWexeId792SyK-SUtY7JoEgIs7KxoBO3rBLqZR5JJtxGsrNhI7jAWX9fzvZ6y9c4cpK1EQ-xW24lur7dQo4WkjTFjkoAD9oexQwmYe8B7hZ8bOYJnTFHA6Tsy5LrU3RVwbPCheKtWm5DZA9n2LLTn0Dx01FDodOiJXktEpobiWyrcHxxxWdPUM5Q__") lightgray 50% / cover no-repeat;
	
	display:flex;
	flex-direction:column;
	align-items:center;
	margin-top:150px;
}
input{
padding:10px;
margin:10px;
border-radius:10px;
}

input#button{
margin-left:230px;
}
input#button:hover{
cursor: pointer;
	box-shadow: 2px 2px 1px black;
}
input#button:active{
	box-shadow:3px 3px 4px black inset ,inset -3px -3px 3px  black;
}
}
</style>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="manager.do" name="form1">
<table>
	<tr>
		<td>管理員帳號:</td>
		<td><input type="TEXT" placeholder="請輸入帳號..." name="managerAccount" value="<%= (managerVO==null)? "" : managerVO.getManagerAccount()%>"    size="45"/></td>
	</tr>
	<tr>
		<td>管理員密碼:</td>
		<td><input type="TEXT" placeholder="請輸入密碼..." name="managerPassword"  value="<%= (managerVO==null)? "" : managerVO.getManagerPassword()%>"   size="45"/></td>
	</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="login">
<input type="submit" id="button" value="送出修改"></FORM>
</body>
</html>