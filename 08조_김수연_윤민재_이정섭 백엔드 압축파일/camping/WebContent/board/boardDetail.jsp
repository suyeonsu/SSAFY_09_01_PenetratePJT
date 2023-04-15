<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Board</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
      rel="stylesheet"
    />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../assets/css/board_styles.css" rel="stylesheet" />
    <%@ include file="/include/header.jsp" %>
  </head>
  <body>
    <!-- Navigation-->
    <%@ include file="/include/nav.jsp" %>
    <!-- Header-->
    <header class="bg-white py-5">
      <div class="container px-4 px-lg-5 my-5">
        <div class="text-center text-black">
          <h2 class="display-4 fw-bolder">${board.id} time(s) Memory</h2>
          <p class="lead fw-normal text-white-50 mb-0">
            With this shop hompeage template
          </p>
        </div>
      </div>
    </header>
    <!-- Section-->
    <section class="py-5">
      <div
          class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-12 row-cols-xl-4 justify-content-center"
        >
          <div class="mb-3">
            <img class="card-img-top" src="assets/img/gallery/logo-icon.png" alt="..." />
       
			</div>
        </div>
      <div class="container px-4 px-lg-5 mt-5">
        <div
          class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-12 row-cols-xl-4 justify-content-center"
        >
          <div class="mb-3">
			  <label for="exampleFormControlInput1" class="form-label">Title</label>
			  <h2>${board.title}</h2>
			</div>
        </div>
        <div
          class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-12 row-cols-xl-4 justify-content-center"
        >
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">Author</label>
        <span style="font-weight:bold;">${board.author}</span>
			</div>
        </div>
        <div
          class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-12 row-cols-xl-4 justify-content-center"
        >
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label">Text</label>
			  <p>
          ${board.text}
        </p>
			</div>
        </div>
        
    </div>
    </section>
    
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->
    <script src="../assets/js/board_scripts.js"></script>
  </body>
</html>