<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/header.jsp" %>
	
	<style>
		body {
			margin-top: 100px;
		}
	</style>
</head>

<body>
	<%@ include file="/include/nav.jsp" %>

	<%-- 페이지만의 내용 --%>

<div class="container">

    <!-- Portfolio Item Heading -->
    <h1 class="my-4">${campsite.title}
      <small>${campsite.addr}</small>
    </h1>
  
    <!-- Portfolio Item Row -->
    <div class="row">
  
      <div class="col-md-8">
        <img class="img-fluid" src="${campsite.img}" alt="${campsite.title} 이미지"  >
      </div>
  
      <div class="col-md-4">
        <p>${campsite.overview}</p>
      </div>
  
    </div>
  
  </div>
  <!-- /.container -->
  
     <script src="vendors/@popperjs/popper.min.js"></script>
    <script src="vendors/bootstrap/bootstrap.min.js"></script>
    <script src="vendors/is/is.min.js"></script>
    <script src="vendors/plyr/plyr.polyfilled.min.js"></script>
  

	<%@ include file="/include/footer.jsp" %>
</body>
</html>