<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSTL 사용 --%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="container-fluid nav-bar bg-transparent">
    <nav class="navbar navbar-expand-lg bg-white navbar-light py-0 px-4">
        <a href="${root}/" class="navbar-brand d-flex align-items-center text-center">
            <div class="icon p-2 me-2"> 
                <img class="img-fluid" src="${root}/assets/img/icon-deal.png" alt="Icon" style="width: 30px; height: 30px;">
            </div>
            <h1 class="m-0 text-primary">Day Off</h1>
        </a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto">
                <a href="${root}/" class="nav-item nav-link active">홈</a>
                <a href="${root}/tour?param=map" class="nav-item nav-link">여행지</a>

                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">게시판</a>
                    <div class="dropdown-menu rounded-0 m-0">
                        <a href="${root}/tour?param=hotplace" class="dropdown-item">핫플레이스</a>
                        <a href="${root}/tour?param=tourplan" class="dropdown-item">여행계획</a>
                        <!-- <a href="404.html" class="dropdown-item">404 Error</a> -->
                    </div>
                </div>
                <a href="${root}/auth?param=mypage" class="nav-item nav-link d-flex align-items-center text-center">
                    <img class="img-fluid me-3 rounded-circle" src="${root}/assets/img/team-1.jpg" alt="Icon" style="width: 30px; height: 30px;">
                    닉네임(마이페이지)
                </a>
            </div>
            <a href="${root}/auth?param=login" class="btn btn-primary px-3 d-none d-lg-flex">로그인</a>
        </div>
    </nav>
</div>