<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <nav class="navbar navbar-expand-lg fixed-top py-3 backdrop" data-navbar-on-scroll="data-navbar-on-scroll">
        <div class="container"><a class="navbar-brand d-flex align-items-center fw-bold fs-2" href="${root}/index.jsp"> <img class="d-inline-block align-top img-fluid" src="assets/img/gallery/logo-icon.png" alt="" width="50" /><span class="fs-4 ps-2" style="color:#093064">Camping</span></a>
          <button class="navbar-toggler collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
          <div class="collapse navbar-collapse border-top border-lg-0 mt-4 mt-lg-0" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto pt-2 pt-lg-0">
              <li class="nav-item"><a class="nav-link active" aria-current="page" href="${root}/index.jsp">Home</a></li>
              <li class="nav-item"><a class="nav-link text-600" href="${root}/board?action=list">Board</a></li>
            </ul>
            <form class="ps-lg-5">
            	<c:choose>
					<c:when test="${empty sessionScope.userInfo}">
						<button type="button" class="btn btn-lg btn-outline-primary order-0" aria-haspopup="true" aria-expanded="false" onclick="location.href='${root}/auth?action=loginPage'">Sign In</button>
						&nbsp;
						<a class="btn btn-lg btn-primary hover-top" href="${root}/auth?action=signupPage" role="button">Sign Up</a>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-lg btn-outline-primary order-0" aria-haspopup="true" aria-expanded="false" onclick="location.href='${root}/auth?action=logout'">Sign Out</button>
					</c:otherwise>
				</c:choose>
            </form>
          </div>
        </div>
      </nav>