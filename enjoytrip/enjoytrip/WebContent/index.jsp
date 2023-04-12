<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 사용 --%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로 --%>
    
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/head.jsp" %>
</head>
<body>
	<div class="container-xxl bg-white p-0">
		<%@ include file="/include/spinner.jsp" %>
		<%@ include file="/include/nav.jsp" %>
	
		<!-- Header Start -->
	    <div class="container-fluid header bg-white p-0">
	        <div class="row g-0 align-items-center flex-column-reverse flex-md-row">
	            <div class="col-md-6 p-5 mt-lg-5">
	                <h1 class="display-5 animated fadeIn mb-4"><span class="text-primary">가족, 친구, 연인</span>과 <br> 함께하는 <span class="text-secondary">인생 여행</span></h1>
	                <p class="animated fadeIn mb-4 pb-2">여행이란, <br> 우리가 사는 장소를 바꿔주는 것이 아니라 <br> 우리의 생각과 편견을 바꿔주는 것이다.</p>
	                <a href="" class="btn btn-primary py-3 px-5 me-3 fs-3 animated fadeIn">여행 시작하기</a>
	            </div>
	            <div class="col-md-6 animated fadeIn">
	                <div class="owl-carousel header-carousel">
	                    <div class="owl-carousel-item">
	                        <img class="img-fluid" src="assets/img/carousel-1.jpg" alt="">
	                    </div>
	                    <div class="owl-carousel-item">
	                        <img class="img-fluid" src="assets/img/carousel-2.jpg" alt="">
	                    </div>
	                    <div class="owl-carousel-item">
	                        <img class="img-fluid" src="assets/img/carousel-3.jpg" alt="">
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Header End -->
	
	
	    <!-- Search Start -->
	    <div class="container-fluid bg-primary mb-5 wow fadeIn" data-wow-delay="0.1s" style="padding: 35px;">
	        <div class="container">
	            <div class="row g-2">
	                <div class="col-md-10">
	                    <div class="row g-2">
	                        <div class="col-md-4">
	                            <input type="text" class="form-control border-0 py-3" placeholder="검색어 입력">
	                        </div>
	                        <div class="col-md-4">
	                            <select class="form-select border-0 py-3">
	                                <option selected>분류</option>
	                                <option value="1">관광지</option>
	                                <option value="2">음식점</option>
	                                <option value="3">숙박업소</option>
	                            </select>
	                        </div>
	                        <div class="col-md-4">
	                            <select class="form-select border-0 py-3">
	                                <option selected>지역</option>
	                                <option value="1">서울</option>
	                                <option value="2">대전</option>
	                                <option value="3">전주</option>
	                            </select>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-md-2">
	                    <button class="btn btn-dark border-0 w-100 py-3">검색</button>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- Search End -->
	 
		<%@ include file="/include/footer.jsp" %>
		<!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
	</div>
</body>

</html>