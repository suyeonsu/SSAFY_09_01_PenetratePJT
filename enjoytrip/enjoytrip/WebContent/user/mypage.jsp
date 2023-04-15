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

        마이페이지
    </div>
</body>

</html>