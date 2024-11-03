<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
   ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_emp_input.jsp</title>

<style>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_manager_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/images/cat4.png" width="250" height="150" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

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
		<td>管理員編號:<font color=red><b>*</b></font></td>
		<td><%=managerVO.getManagerNo()%></td>
	</tr>
	<tr>
		<td>管理員姓名:</td>
		<td><input type="TEXT" name="managerName" value="<%=managerVO.getManagerName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>管理員帳號:</td>
		<td><input type="TEXT" name="managerAccount"   value="<%=managerVO.getManagerAccount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>管理員密碼:</td>
		<td><input type="TEXT" name="managerPassword"   value="<%=managerVO.getManagerPassword()%>" size="45"/></td>
	</tr>
	<tr>
		<td>管理員狀態</td>
		<td><input type="TEXT" name="managerstatus"  value="<%=managerVO.getManagerstatus()%>" size="45"/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="managerNo" value="<%=managerVO.getManagerNo()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

</html>