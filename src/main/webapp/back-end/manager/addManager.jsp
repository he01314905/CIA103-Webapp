<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>

<%
//��com.emp.controller.EmpServlet.java��238��s�Jreq��empVO���� (������J�榡�����~�ɪ�empVO����)
   ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��Ʒs�W - addManager.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    width:500px;
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
		 <h3>���u��Ʒs�W - addManager.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="<%= request.getContextPath()%>/back-end/images/cat.gif"  width="150" height="200" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="manager.do" name="form1">
<table>
	
	
	
	
	<tr>
		<td>�޲z���m�W:</td>
		<td><input type="TEXT" name="managerName" value="<%= (managerVO==null)? "�j�^��" : managerVO.getManagerName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�޲z���b��:</td>
		<td><input type="TEXT" name="managerAccount"   value="<%= (managerVO==null)? "tibame" : managerVO.getManagerAccount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�޲z���K�X:</td>
		<td><input type="TEXT" name="managerPassword"   value="<%= (managerVO==null)? "CIA103" : managerVO.getManagerPassword()%>" size="45"/></td>
	</tr>
	<tr>
		<td>�޲z�����A:</td>
		<td><input type="TEXT" name="managerstatus"  value="<%= (managerVO==null)? "1" : managerVO.getManagerstatus()%>" size="45"/></td>
	</tr>


</table>
 
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>

</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

</html>