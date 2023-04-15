<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="../assets/fonts/icomoon/style.css">

    <link rel="stylesheet" href="../assets/css/owl.carousel.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    
    <!-- Style -->
    <link rel="stylesheet" href="../assets/css/style.css">

    <title>Login</title>
    	<%@ include file="/include/header.jsp" %>
  </head>
  <body>

  
  <div class="content">
    <div class="container">
      <div class="row">
        <div class="col-md-6 order-md-2">
          <img src="../assets/img/illustrations/loginImage.jpg" alt="Image" class="img-fluid">
        </div>
        <div class="col-md-6 contents">
          <div class="row justify-content-center">
            <div class="col-md-8">
              <div class="mb-4">
              <h3>Login to<strong>&nbsp;Enjoy Trip!</strong></h3>
            </div>
            <form action="${root }/auth" method="post">
            	<input type="hidden" id="action" name="action" value="login">
              <div class="form-group first">
                <label for="id">Id</label>
                <input type="text" class="form-control" id="id" name="id" value="${cookie.rememberId.value}">

              </div>
              <div class="form-group last mb-4">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="pwd" name="pwd">
                
              </div>
              
              <div class="d-flex mb-5 align-items-center">
                <label class="control control--checkbox mb-0"><span class="caption">Remember me</span>
		  <input type="checkbox" name="isRemember" ${(empty cookie.rememberId)?"" : "checked"} />
                  <div class="control__indicator"></div>
                </label>
                <span class="ml-auto"><a href="/camping/auth?action=signupPage" class="forgot-pass">Signup Enjoy Trip!</a></span> 
              </div>

              <input type="submit" value="Log In" class="btn text-white btn-block btn-primary">

              <span class="d-block text-left my-4 text-muted"> or sign in with</span>
              
            </form>
            </div>
          </div>
          
        </div>
        
      </div>
    </div>
  </div>

  	<%@ include file="/include/footer.jsp" %>
    <script src="../assets/js/jquery-3.3.1.min.js"></script>
    <script src="../assets/js/popper.min.js"></script>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script src="../assets/js/main.js"></script>
  </body>
</html>