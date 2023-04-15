<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용 --%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
	
	<!-- 이미지 마커 메뉴(왼쪽 상단) CSS -->
    <link rel="stylesheet" href="${root}/assets/css/components/map.css">
</head>
<body>
	<div class="container-xxl bg-white p-0">
		<%@ include file="/include/spinner.jsp" %>
		<%@ include file="/include/nav.jsp" %>


	  <h2>로그인</h2>
	  <form id="loginForm" action="${root}/auth" method="post">
	  	<input type="hidden" name="param" value="login">
	    <div class="form-group">
	      <label for="id">아이디</label>
	      <input type="text" class="form-control" id="id" name="id" placeholder="아이디" value="${cookie.rememberId.value}">
		  <input type="checkbox" name="isRemember" ${(empty cookie.rememberId)?"" : "checked"} /> 아이디 저장
	    </div>
	    <div class="form-group">
	      <label for="password">비밀번호</label>
	      <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호">
	    </div>

	    <button type="submit" class="btn btn-primary" >로그인</button>
	    <a class="btn btn-secondary" href="${root}" >취소</a>
	  </form>
	
	
	<%-- --%>
    </div>
</body>

</html>
	