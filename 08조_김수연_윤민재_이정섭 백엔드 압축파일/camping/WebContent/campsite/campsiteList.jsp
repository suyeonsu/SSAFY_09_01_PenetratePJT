<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/header.jsp" %>
</head>
<body>
	<%@ include file="/include/nav.jsp" %>
<table class="table">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">제목</th>
      <th scope="col">주소</th>
    </tr>
  </thead>
  <tbody>
	<c:forEach items="${requestScope.campList}" var="camp" >
		<tr>
			<td>${camp.content_id}</td>	
			<td><a href="${root}/camp?action=detail&content_id=${camp.content_id}">${camp.title}</td>					
			<td>${camp.addr}</td>			
		</tr>
	</c:forEach>
  </tbody>
</table>
</body>
</html>