<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Admin Page</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	

		<link rel="icon" type="image/png" href="img/logo2 (2).png">
		<link rel="stylesheet" href="css/owl.carousel.min.css">
		<link rel="stylesheet" href="css/owl.theme.default.min.css">
		
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/style1.css">
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
		
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>


		

</head>
<body>

<div class="container-fluid full">
	
	<div class="">	
		<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
					  <a class="navbar-brand" href="#" target="blank"><img src="img/logo3 (2).png" width="85px" height="50px"><span class="logoo"></span></a>
					  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					    <span class="navbar-toggler-icon"></span>
					  </button>

					  <div class="collapse navbar-collapse" id="navbarSupportedContent">
					    <ul class="navbar-nav ml-auto id="float">
					      <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><svg class="svg-inline--fa fa-user fa-fw" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="user" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" data-fa-i2svg=""><path fill="currentColor" d="M224 256c70.7 0 128-57.31 128-128s-57.3-128-128-128C153.3 0 96 57.31 96 128S153.3 256 224 256zM274.7 304H173.3C77.61 304 0 381.6 0 477.3c0 19.14 15.52 34.67 34.66 34.67h378.7C432.5 512 448 496.5 448 477.3C448 381.6 370.4 304 274.7 304z"></path></svg><!-- <i class="fas fa-user fa-fw"></i> Font Awesome fontawesome.com --></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#!">Logout</a></li>
                    </ul>
                </li>
            </ul>
		      
		    </ul>

		   
		    
	</div>
</nav>






	</div>



	<!-- body-->

					<div class="container-fluid backgroud">
						<div class="row">
							<div class="col-md-12 col-sm-12">
								<h1 class="text-white text-center">User Log</h1>
								
							</div>
							
						</div>
						
					</div>

					<div class="container">
						<div class="row pl-2">
							<div class="col-md-12 col-sm-12 text-left">
								<h4 class="mt-5 mb-3">Create User Log</h4>
								<form>
									<table class="table table-striped">
									  <thead>
									    <tr>
									      <th scope="col">#</th>
									      <th scope="col">Name</th>
									      <th scope="col">Email</th>
									      <th scope="col">Tel</th>
									    </tr>
									  </thead>
									  <tbody>
										  <tr>
											<th scope="row">1</th>
											<td>김수연</td>
											<td>zxy@gmail.com</td>
											<td>010-7777-7777</td>
										  </tr>
									    <tr>
									      <th scope="row">2</th>
									      <td>윤민재</td>
									      <td>abc@naver.com</td>
									      <td>010-9999-9999</td>
									    </tr>
									  </tbody>
									</table>
								</form>
								<nav aria-label="...">
								  <ul class="pagination pagination-lg">
								    <li class="page-item active" aria-current="page">
								      <span class="page-link">1</span>
								    </li>
								    <li class="page-item"><a class="page-link" href="#">2</a></li>
								    <li class="page-item"><a class="page-link" href="#">3</a></li>
								  </ul>
								</nav>
								<hr class="mt-3 mb-3">
							</div>
						</div>
						<div class="row pl-2">
							<div class="col-md-12 col-sm-12 text-left">
								<h4 class="mt-5 mb-3">Update User Log</h4>
								<form>
									<table class="table table-striped">
									  <thead>
									    <tr>
									      <th scope="col">#</th>
									      <th scope="col">Name</th>
									      <th scope="col">Email</th>
									      <th scope="col">Tel</th>
									    </tr>
									  </thead>
									  <tbody>
									    <tr>
											<th scope="row">1</th>
											<td>김수연</td>
											<td>zxy@gmail.com</td>
											<td>010-7777-7777</td>
										</tr>
									    <tr>
											<th scope="row">2</th>
											<td>윤민재</td>
											<td>abc@naver.com</td>
											<td>010-9999-9999</td>
									    </tr>
									  </tbody>
									</table>
								</form>
								<nav aria-label="...">
								  <ul class="pagination pagination-lg">
								    <li class="page-item active" aria-current="page">
								      <span class="page-link">1</span>
								    </li>
								    <li class="page-item"><a class="page-link" href="#">2</a></li>
								    <li class="page-item"><a class="page-link" href="#">3</a></li>
								  </ul>
								</nav>
								<hr class="mt-3 mb-3">
							</div>
						</div>
						<div class="row pl-2">
							<div class="col-md-12 col-sm-12 text-left">
								<h4 class="mt-5 mb-3">Delete User Log</h4>
								<form>
									<table class="table table-striped">
									  <thead>
									    <tr>
									      <th scope="col">#</th>
									      <th scope="col">Name</th>
									      <th scope="col">Email</th>
									      <th scope="col">Tel</th>
									    </tr>
									  </thead>
									  <tbody>
									    <tr>
											<th scope="row">1</th>
											<td>김수연</td>
											<td>zxy@gmail.com</td>
											<td>010-7777-7777</td>
									    </tr>
									    <tr>
											<th scope="row">2</th>
											<td>윤민재</td>
											<td>abc@naver.com</td>
											<td>010-9999-9999</td>
									    </tr>
									  </tbody>
									</table>
								</form>
								<nav aria-label="...">
								  <ul class="pagination pagination-lg">
								    <li class="page-item active" aria-current="page">
								      <span class="page-link">1</span>
								    </li>
								    <li class="page-item"><a class="page-link" href="#">2</a></li>
								    <li class="page-item"><a class="page-link" href="#">3</a></li>
								  </ul>
								</nav>
								<hr class="mt-3 mb-3">
							</div>
						</div>
					</div>


					


					
											

					<div class="container-fluid backgroud3">
						<div class="container">
							<div class="row">
								<div class="col-md-4 col-sm-12">
									<div class="text-left pt-3">
									<img src="img/ssafy.png" width="275px" height="200px">
									</div>
									
                					
									
								</div>
								<div class="col-md-4 col-sm-12">
									<h2 class="text-left text-white pt-3">Team 8</h2>
									<br />
                					<h5 class="text-left text-white">팀장 : 김수연</h6>
                					<h5 class="text-left text-white">팀원 : 윤민재</h6>
								</div>
								<div class="col-md-4 col-sm-12">
									<div class="text-left pt-3">
									<img src="img/logo3 (2).png" width="275px" height="200px">
									</div>
								</div>
								
							</div>
							
						</div>
						
					</div>

						<!--footer-->
					<div class="container-fluid pt-5 pb-5 backgroud">
						<div class="container">
							<div class="row">
								<div class="col-md-12 col-sm-12">
									<h4 class="text-center text-white">
										Made by 2023-03-18, SSAFY Class 8th Team 9th : Let's go Travel!
									</h4>
								</div>
								
								
							</div>
							
						</div>
						
					</div>
				
</div>




				<!--  javascript   -->

				<script src="jquery.min.js"></script>
		<script src="js/owl.carousel.min.js"></script>

<script>
			$('.owl-carousel').owlCarousel({
    loop:true,
    margin:10,
    nav:true,
    responsive:{
        0:{
            items:1
        },
        600:{
            items:3
        },
        1000:{
            items:5
        }
    }
})

		</script>
				  
	<script src="js/smoothscroll.js" type="text/javascript"></script>
	<script>$('a').smoothScroll({speed: 1500});</script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="js/parallax.min.js"></script>

	<script>$('.parallax-window').parallax();</script>
</body>
</html>