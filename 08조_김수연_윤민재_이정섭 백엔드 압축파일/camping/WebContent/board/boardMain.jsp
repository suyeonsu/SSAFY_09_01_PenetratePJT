<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Board</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
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
                    <h1 class="display-4 fw-bolder">Our Trip</h1>
                    <br>
                    <button type="button" class="btn btn-lg btn-outline-primary order-0" aria-haspopup="true" aria-expanded="false" onclick="location.href='${root}/board?action=write'">Write</button>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop hompeage template</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	<%-- 게시판 1개 --%>
                	<c:forEach items="${list}" var="board">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="assets/img/gallery/nosee.png" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Board Title -->
                                    <h5 class="fw-bolder">${board.title}</h5>
                                    <!-- Board Tag -->
                                    #Trip
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="${root}/board?action=detail&id=${board.id}">OPEN</a></div>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                    <%-- 게시판 1개 끝--%>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="../assets/js/board_scripts.js"></script>
    </body>
</html>
