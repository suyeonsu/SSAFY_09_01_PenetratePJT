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

        <!-- Header Start -->
        <div class="container-fluid header bg-white p-0 pt-5">
            <div class="row g-0 pt-5 align-items-center flex-column-reverse flex-md-row">
                <div class="col-md-6 p-5 mt-lg-5">
                    <h1 class="display-5 animated fadeIn mb-4">핫플레이스</h1> 
                        <nav aria-label="breadcrumb animated fadeIn">
                        <ol class="breadcrumb text-uppercase">
                            <li class="breadcrumb-item"><a href="#">게시판 홈</a></li>
                            <li class="breadcrumb-item"><a href="#">자유 게시판</a></li>
                            <li class="breadcrumb-item text-body active" aria-current="page">Testimonial</li>
                        </ol>
                    </nav>
                </div>
                <div class="col-md-6 animated fadeIn">
                    <!-- <img class="img-fluid" src="assets/img/header.jpg" alt=""> -->
                </div>
            </div>
        </div>
        <!-- Header End -->


        <!-- Search Start -->
        <div class="container-fluid bg-primary wow fadeIn" data-wow-delay="0.1s" style="padding: 35px;">
            <div class="container">
                <div class="row g-2">
                    <div class="col-md-10">
                        <div class="row g-2">
                            <div class="col-md-8">
                                <input type="text" class="form-control border-0 py-3" placeholder="검색어를 입력하세요">
                            </div>
                            <div class="col-md-2">
                                <select class="form-select border-0 py-3">
                                    <option selected>전체</option>
                                    <option value="1">제목</option>
                                    <option value="2">내용</option>
                                    <option value="3">작성자</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <select class="form-select border-0 py-3">
                                    <option selected>정렬</option>
                                    <option value="1">오름차순</option>
                                    <option value="2">내림차순</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-dark border-0 w-100 py-3">검색하기</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Search End -->

        <!-- 게시판 시작 -->
        <div class="container-fluid mb-5 wow fadeIn" data-wow-delay="0.1s" style="padding: 35px;">
            <table class="table table-hover">
                <thead>
                  <tr class="container-fluid">
                    <th class="col-md-1 d-inline-block text-truncate">#</th>
                    <th class="col-md-7 d-inline-block text-truncate">제목</th>
                    <th class="col-md-2 d-inline-block text-truncate">작성자</th>
                    <th class="col-md-2 d-inline-block text-truncate">작성 시간</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <th class="col-md-1 d-inline-block text-truncate"  scope="row">1</th>
                    <td class="col-md-7 d-inline-block text-truncate" >
                        <a class="text-reset" href="#">       
                            게시판 글 1
                        </a>
                    </td>
                    <td class="col-md-2 d-inline-block text-truncate" >노호종</td>
                    <td class="col-md-2 d-inline-block text-truncate" >14:11:36</td>
                  </tr>
                  <tr>
                    <th class="col-md-1 d-inline-block text-truncate"  scope="row">2</th>
                    <td class="col-md-7 d-inline-block text-truncate" >
                        <a class="text-reset" href="#">       
                            게시판 글 2
                        </a>
                    </td>
                    <td class="col-md-2 d-inline-block text-truncate" >노호종</td>
                    <td class="col-md-2 d-inline-block text-truncate" >2023.03.23</td>
                  </tr>
                </tbody>
              </table>
              <div class="container-fluid">
                <div class="row">
                    <div class="col-1"></div>
                    <!-- <button class="btn btn-primary border-0 py-3 px-5">개념글</button> -->
                    <nav class="col-10 d-flex justify-content-center" aria-label="Page navigation example">
                        <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="#">처음</a></li>
                        <li class="page-item"><a class="page-link" href="#">이전</a></li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">다음</a></li>
                        <li class="page-item"><a class="page-link" href="#">끝</a></li>
                        </ul>
                    </nav>
                    <button class="col-1 btn btn-dark border-0">글쓰기</button>
                    </div>
              </div>
        </div>
        <!-- 게시판 끝 -->

        <!-- Testimonial Start -->
        <div class="container-xxl py-5">
            <div class="container">
                <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                    <h1 class="mb-3">요즘 핫한 게시글</h1>
                    <p>주기적으로 업데이트 예정입니다</p>
                </div>
                <div class="owl-carousel testimonial-carousel wow fadeInUp" data-wow-delay="0.1s">
                    <div class="testimonial-item bg-light rounded p-3">
                        <div class="bg-white border rounded p-4">
                            <p class="fs-3 d-inline-block text-truncate">요즘 유행하는 봄맞이 여행지 top 10</p>
                            <div class="d-flex align-items-center">
                                <img class="img-fluid flex-shrink-0 rounded" src="assets/img/testimonial-1.jpg" style="width: 45px; height: 45px;">
                                <div class="ps-3">
                                    <h6 class="fw-bold mb-1">이경원</h6>
                                    <small>leekyeong@ssafy.com</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="testimonial-item bg-light rounded p-3">
                        <div class="bg-white border rounded p-4">
                            <p class="fs-3 d-inline-block text-truncate">요즘 유행하는 봄맞이 여행지 top 10</p>
                            <div class="d-flex align-items-center">
                                <img class="img-fluid flex-shrink-0 rounded" src="assets/img/testimonial-1.jpg" style="width: 45px; height: 45px;">
                                <div class="ps-3">
                                    <h6 class="fw-bold mb-1">이경원</h6>
                                    <small>leekyeong@ssafy.com</small>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="testimonial-item bg-light rounded p-3">
                        <div class="bg-white border rounded p-4">
                            <p class="fs-3 d-inline-block text-truncate">요즘 유행하는 봄맞이 여행지 top 10</p>
                            <div class="d-flex align-items-center">
                                <img class="img-fluid flex-shrink-0 rounded" src="assets/img/testimonial-1.jpg" style="width: 45px; height: 45px;">
                                <div class="ps-3">
                                    <h6 class="fw-bold mb-1">이경원</h6>
                                    <small>leekyeong@ssafy.com</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Testimonial End -->
    </div>
</body>

</html>