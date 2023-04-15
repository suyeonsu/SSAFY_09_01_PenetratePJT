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

    <title>SignUp </title>
    <script>
    	function equal(){
    		var pwd = document.getElementById('pwd').value;
    		var pwdCheck = document.getElementById('pwdCheck').value;
    		var inner = document.getElementById('check');
    		if(pwd == pwdCheck && pwd != "" && pwdCheck != ""){
    			inner.innerHTML="Correct Password!";
    		}else if(pwd != pwdCheck && pwd != "" && pwdCheck != ""){
    			inner.innerHTML="Incorrect Password!";
    		}else{
    			inner.innerHTML="";
    		}
    	}
    </script>
        	<%@ include file="/include/header.jsp" %>
  </head>
  <body>
  

  
  <div class="content">
    <div class="container">
      <div class="row">
        <div class="col-md-6 order-md-2">
          <img src="../assets/img/illustrations/signupImage.PNG" alt="Image" class="img-fluid">
        </div>
        <div class="col-md-6 contents">
          <div class="row justify-content-center">
            <div class="col-md-8">
              <div class="mb-4">
              <h3>Welcome to<strong>&nbsp;Enjoy Trip!</strong></h3>
            </div>
            <form action="${root }/auth" method="post">
            	<input type="hidden" name="action" id="action" value="signup"> 
              <div class="form-group first">
                <label for="ID">ID</label>
                <input type="text" class="form-control" id="id" name="id">

              </div>
              <div class="form-group first mb-4">
                <label for="pwd">PWD</label>
                <input type="password" class="form-control" id="pwd" name="pwd">

              </div>
              <div class="form-group first mb-8">
                <label for="pwdCheck">PWD Check</label>
                <input type="password" class="form-control" id="pwdCheck" onchange="equal()">

              </div>
              
              <div class="form-group last mb-4">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name">
                
              </div>
              

              <input type="submit" value="Submit" class="btn text-white btn-block btn-primary">

              <span class="d-block text-left my-4 text-muted"> <div id="check"></div></span>
              
              
            </form>
            </div>
          </div>
          
        </div>
        
      </div>
    </div>
  </div>

  
    <script src="../assets/js/jquery-3.3.1.min.js"></script>
    <script src="../assets/js/popper.min.js"></script>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script src="../assets/js/main.js"></script>
  </body>
</html>