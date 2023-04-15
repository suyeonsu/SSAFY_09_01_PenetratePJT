<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US" dir="ltr">

  <head>
	<%@ include file="/include/header.jsp" %>
  </head>

  <body>

    <!-- ===============================================-->
    <!--    Main Content-->
    <!-- ===============================================-->
    <main class="main" id="top">
	<%@ include file="/include/nav.jsp" %>
	
      <section class="py-0">
        <div class="bg-holder d-none d-md-block" style="background-image:url(assets/img/illustrations/hero.png);background-position:right bottom;background-size:contain;">
        </div>
        <!--/.bg-holder-->

        <div class="container position-relative">
          <div class="row align-items-center min-vh-75 my-lg-8">
            <div class="col-md-7 col-lg-6 text-center text-md-start py-8">
              <h1 class="mb-4 display-1 lh-sm">Camping around <br class="d-block d-lg-none d-xl-block" />the Korea</h1>
              <p class="mt-4 mb-5 fs-1 lh-base">지금 당장
               <br class="d-none d-lg-block" />짐을 싸세요! <br class="d-none d-lg-block" />캠핑 한번 떠나볼까요?</p><a class="btn btn-lg btn-primary hover-top" href="${root}/camp?action=campList" role="button">Explore</a>
            </div>
          </div>
        </div>
      </section>


      <!-- ============================================-->
      <!-- <section> begin ============================-->
      <section class="pt-5" id="featuresVideos">

        <div class="container">
          <div class="row flex-center mb-5">
            <div class="col-lg-8 text-center">
              <h1 class="fw-bold fs-md-3 fs-lg-4 fs-xl-5">Camping videos</h1>
              <hr class="mx-auto text-primary my-4" style="height:3px; width:70px;" />
              <p class="mx-auto">여행자들의 캠핑 영상을 감상하세요</p>
            </div>
          </div>
          <div class="row flex-center">
            <div class="col-12">
              <div class="carousel slide" id="carouselExampleIndicators" data-bs-touch="false" data-bs-interval="false">
                <div class="row align-items-center">
                  <div class="col-12 col-xxl-7 px-2">
                    <div class="carousel-inner">
                      <div class="carousel-item active h-100">
                        <div class="player" data-plyr-provider="youtube" data-plyr-embed-id="bTqVqk7FSmY">
                          <iframe src="https://www.youtube.com/embed/jUnW1wrnkeQ" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen=""></iframe>
                        </div>
                      </div>
                      <div class="carousel-item">
                        <div class="player" data-plyr-provider="youtube" data-plyr-embed-id="bTqVqk7FSmY">
                          <iframe src="https://www.youtube.com/embed/VSP_YCjwCUo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen=""></iframe>
                        </div>
                      </div>
                      <div class="carousel-item">
                        <div class="player" data-plyr-provider="youtube" data-plyr-embed-id="bTqVqk7FSmY">
                          <iframe src="https://www.youtube.com/embed/4f9tEeNU64I" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen=""></iframe>
                        </div>
                      </div>
                      <div class="carousel-item">
                        <div class="player" data-plyr-provider="youtube" data-plyr-embed-id="bTqVqk7FSmY">
                          <iframe src="https://www.youtube.com/embed/CymVP6TRq9Y" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen=""></iframe>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-6 col-md-3 col-xxl-2 pt-3 pt-md-0">
                    <div class="carousel-indicators">
                      <div class="row h-100 w-100">
                        <div class="col-12 px-1">
                          <button class="active" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" aria-current="true" aria-label="Slide 1"><img class="d-block" src="assets/img/gallery/campMovie01.PNG" alt="..." style="width:221px; height:100px" /></button>
                        </div>
                        <div class="col-12 px-1">
                          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"><img class="d-block" src="assets/img/gallery/campMovie02.PNG" alt="..." style="width:221px; height:100px" /></button>
                        </div>
                        <div class="col-12 px-1">
                          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"><img class="d-block" src="assets/img/gallery/campMovie03.PNG" alt="..." style="width:221px; height:100px" /></button>
                        </div>
                        <div class="col-12 px-1">
                          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"><img class="d-block" src="assets/img/gallery/campMovie04.PNG" alt="..." style="width:221px; height:100px" /></button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-6 col-lg-3 px-xxl-0">
                    <div class="row">
                      <div class="col-12 col-md-11 col-lg-12 col-xl-10 col-xxl-7 px-xxl-0 ps-lg-0 ps-md-4">
                        <div class="card h-100 py-md-2 active" style="min-height:113px">
                          <div class="card-body d-flex flex-column justify-content-center">
                            <p class="mb-0 fw-semi-bold text-dark">camp</p>
                            <p class="card-text"><small class="text-800">01</small></p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-md-11 col-lg-12 col-xl-10 col-xxl-7 px-xxl-0 ps-lg-0 ps-md-4">
                        <div class="card h-100 py-md-2" style="min-height:113px">
                          <div class="card-body d-flex flex-column justify-content-center">
                            <p class="mb-0 fw-semi-bold text-500">camp</p>
                            <p class="card-text"><small class="text-800">02</small></p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-md-11 col-lg-12 col-xl-10 col-xxl-7 px-xxl-0 ps-lg-0 ps-md-4">
                        <div class="card h-100 py-md-2" style="min-height:113px">
                          <div class="card-body d-flex flex-column justify-content-center">
                            <p class="mb-0 fw-semi-bold text-500">camp</p>
                            <p class="card-text"><small class="text-800">03</small></p>
                          </div>
                        </div>
                      </div>
                      <div class="col-12 col-md-11 col-lg-12 col-xl-10 col-xxl-7 px-xxl-0 ps-lg-0 ps-md-4">
                        <div class="card h-100 py-md-3" style="min-height:113px">
                          <div class="card-body d-flex flex-column justify-content-center">
                            <p class="mb-0 fw-semi-bold text-500">camp</p>
                            <p class="card-text"><small class="text-800">04</small></p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- end of .container-->

      </section>
      <!-- <section> close ============================-->
      <!-- ============================================-->




      <!-- ============================================-->
      <!-- <section> begin ============================-->
      <section class="pt-5">

        <div class="container">
          <div class="row flex-center mb-5">
            <div class="col-lg-8 text-center">
              <h1 class="fw-bold fs-md-3 fs-lg-4 fs-xl-5">Traveller's Experiences</h1>
              <hr class="mx-auto text-primary my-4" style="height:3px; width:70px;" />
            </div>
            <div class="carousel slide" id="carouselExampleDark" data-bs-ride="carousel">
              <div class="carousel-inner">
                <div class="carousel-item active" data-bs-interval="10000">
                  <div class="row h-100 flex-center">
                    <div class="col-12 col-lg-7 mb-3 mb-md-0">
                      <div class="card h-100">
                        <div class="card-body d-flex flex-center flex-column">
                          <p class="text-center card-text mb-5">“Curabitur posuere ullamcorper pulvinar. Donec dignissim bibendum leo, at faucibus enim aliquam eu. Nullam quis pulvinar diam, ac elementum urna. Integer id vehicula tortor, nec pulvinar libero. Ut elit elit, fringilla a nisi ut, dapibus eleifend quam.” </p>
                          <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/gallery/user-2.png" alt="" /></div>
                          </div>
                          <div class="mt-3 text-center">
                            <h6 class="text-1000 fw-bold">Donquixote Law</h6>
                            <p class="fs--1 fw-normal mb-0">Traveller</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="carousel-item" data-bs-interval="2000">
                  <div class="row h-100 flex-center">
                    <div class="col-12 col-lg-7 mb-3 mb-md-0">
                      <div class="card h-100">
                        <div class="card-body d-flex flex-center flex-column">
                          <p class="text-center card-text mb-5">“Curabitur posuere ullamcorper pulvinar. Donec dignissim bibendum leo, at faucibus enim aliquam eu. Nullam quis pulvinar diam, ac elementum urna. Integer id vehicula tortor, nec pulvinar libero. Ut elit elit, fringilla a nisi ut, dapibus eleifend quam.” </p>
                          <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/gallery/user-1.png" alt="" /></div>
                          </div>
                          <div class="mt-3 text-center">
                            <h6 class="text-1000 fw-bold">Viezh Robert</h6>
                            <p class="fs--1 fw-normal mb-0">Traveller</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="carousel-item" data-bs-interval="3000">
                  <div class="row h-100 flex-center">
                    <div class="col-12 col-lg-7 mb-3 mb-md-0">
                      <div class="card h-100">
                        <div class="card-body d-flex flex-center flex-column">
                          <p class="text-center card-text mb-5">“Curabitur posuere ullamcorper pulvinar. Donec dignissim bibendum leo, at faucibus enim aliquam eu. Nullam quis pulvinar diam, ac elementum urna. Integer id vehicula tortor, nec pulvinar libero. Ut elit elit, fringilla a nisi ut, dapibus eleifend quam.” </p>
                          <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/gallery/user-2.png" alt="" /></div>
                          </div>
                          <div class="mt-3 text-center">
                            <h6 class="text-1000 fw-bold">Donquixote Law</h6>
                            <p class="fs--1 fw-normal mb-0">Traveller</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="carousel-item">
                  <div class="row h-100 flex-center">
                    <div class="col-12 col-lg-7 mb-3 mb-md-0">
                      <div class="card h-100">
                        <div class="card-body d-flex flex-center flex-column">
                          <p class="text-center card-text mb-5">“Curabitur posuere ullamcorper pulvinar. Donec dignissim bibendum leo, at faucibus enim aliquam eu. Nullam quis pulvinar diam, ac elementum urna. Integer id vehicula tortor, nec pulvinar libero. Ut elit elit, fringilla a nisi ut, dapibus eleifend quam.” </p>
                          <div class="d-flex justify-content-between align-items-center">
                            <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/gallery/user-1.png" alt="" /></div>
                          </div>
                          <div class="mt-3 text-center">
                            <h6 class="text-1000 fw-bold">Viezh Robert</h6>
                            <p class="fs--1 fw-normal mb-0">Traveller</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row px-3 px-md-0">
                <div class="col-12 position-relative d-flex flex-center">
                  <ol class="carousel-indicators">
                    <li class="active" data-bs-target="#carouselExampleDark" data-bs-slide-to="0"></li>
                    <li data-bs-target="#carouselExampleDark" data-bs-slide-to="1"></li>
                  </ol>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- footer =============================-->

	<%@ include file="/include/footer.jsp" %>


    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->




    <!-- ===============================================-->
    <!--    JavaScripts-->
    <!-- ===============================================-->
    <script src="vendors/@popperjs/popper.min.js"></script>
    <script src="vendors/bootstrap/bootstrap.min.js"></script>
    <script src="vendors/is/is.min.js"></script>
    <script src="vendors/plyr/plyr.polyfilled.min.js"></script>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=window.scroll"></script>
    <script src="assets/js/theme.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@200;300;400;600;700;800;900&amp;display=swap" rel="stylesheet">
  </body>

</html>