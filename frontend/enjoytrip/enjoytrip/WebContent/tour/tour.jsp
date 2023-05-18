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
	
		<!-- 지도 시작 -->
        <div id="mapwrap" class="container-xxl py-5">
            <div id="map" class="" style="width:100%;height:75vh;">
                <div class="category">
                    <ul>
                        <li id="category__item01">
                            <i class="bi bi-binoculars-fill ico_comm"></i>
                            <!-- <span class="ico_comm ico_coffee"></span> -->
                            관광지
                        </li>
                        <li id="category__item02">
                            <i class="bi bi-house-fill ico_comm"></i>
                            <!-- <span class="ico_comm ico_coffee"></span> -->
                            숙박
                        </li>
                        <li id="category__item03">
                            <i class="bi bi-egg-fried ico_comm"></i>
                            <!-- <span class="ico_comm ico_coffee"></span> -->
                            음식점
                        </li>
                        <li id="category__item04">
                            <i class="bi bi-book-half ico_comm"></i>
                            <!-- <span class="ico_comm ico_coffee"></span> -->
                            문화시설
                        </li>
                        <li id="category__item05">
                            <i class="bi bi-music-note-beamed ico_comm"></i>
                            <!-- <span class="ico_comm ico_coffee"></span> -->
                            공연
                        </li>
                        <li id="category__item07" >
                            <i class="bi bi-cart-fill ico_comm"></i>
                            <!-- <span class="ico_comm ico_coffee"></span> -->
                            쇼핑
                        </li>
                        <li id="category__item06" >
                            <i class="bi bi-bezier2 ico_comm"></i>
                            <!-- <span class="ico_comm ico_coffee"></span> -->
                            여행코스
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- 지도 끝-->
    </div>
    
    <!-- 카카오 맵 JavaScript -->
    <script  type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c27c029a47d8b36a3390ddda157d4950"></script>
    <script  src="${root}/assets/js/tour.js"></script>
</body>

</html>