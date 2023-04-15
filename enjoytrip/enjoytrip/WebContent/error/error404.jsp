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

		<!-- 404 Start -->
		<div class="container-xxl py-5 wow fadeInUp" data-wow-delay="0.1s">
				<div class="container text-center">
						<div class="row justify-content-center">
								<div class="col-lg-6">
										<i class="bi bi-exclamation-triangle display-1 text-primary"></i>
										<h1 class="display-1">404</h1>
										<h1 class="mb-4">Page Not Found</h1>
										<p class="mb-4">We’re sorry, the page you have looked for does not exist in our website! Maybe go to our home page or try to use a search?</p>
										<a class="btn btn-primary py-3 px-5" href="">Go Back To Home</a>
								</div>
						</div>
				</div>
		</div>
		<!-- 404 End -->

	</div>
</body>

</html>