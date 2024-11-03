<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Manager: Home</title>

<style>
body{
	background: url("https://s3-alpha-sig.figma.com/img/dfdb/7342/e97f5a6af1cf2872bb12d94cb62f2995?Expires=1730678400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=nJ8qFx0Y5zLo22-dO3WfTfB4t6JAdWsw20uauzDjlQhbN2tcJ09YMW0psMZxXb4XNEBfOSdWW~5v9oMrxdEmHFobX3iFgTWgzglH7R~-03Euac32oT-SSOAEC5AuaZnVk9YGRg5OwZZLQO4SuSdhr6IiCtrErqbWexeId792SyK-SUtY7JoEgIs7KxoBO3rBLqZR5JJtxGsrNhI7jAWX9fzvZ6y9c4cpK1EQ-xW24lur7dQo4WkjTFjkoAD9oexQwmYe8B7hZ8bOYJnTFHA6Tsy5LrU3RVwbPCheKtWm5DZA9n2LLTn0Dx01FDodOiJXktEpobiWyrcHxxxWdPUM5Q__") lightgray 50% / cover no-repeat;
	
	display:flex;
 	flex-direction:column; 
	align-items:center;
	
}
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
 h3 {
 display:inline;
 
 }
</style>

</head>
<body >

<table id="table-1">
   <tr><td><h3>IBM Manager: Home</h3><h4>( MVC )</h4><img src="<%= request.getContextPath()%>/back-end/images/cat2.gif" width="150" height="80" border="0"></td></tr>
   
</table>

<p>This is the Home page for IBM Manager: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllManager.jsp'>List</a> all Managers.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="manager.do" >
        <b>輸入員工編號 (如7001):</b>
        <input type="text" name="managerNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="managerSvc" scope="page" class="com.manager.model.ManagerService" />
   
  <li>
     <FORM METHOD="post" ACTION="manager.do" >
       <b>選擇員工編號:</b>
       <select size="1" name="managerNo">
         <c:forEach var="managerVO" items="${managerSvc.all}" > 
          <option value="${managerVO.managerNo}">${managerVO.managerNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="manager.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="managerNo">
         <c:forEach var="managerVO" items="${managerSvc.all}" > 
          <option value="${managerVO.managerNo}">${managerVO.managerName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addManager.jsp'>Add</a> a new Manager.</li>
</ul>

</body>
</html>