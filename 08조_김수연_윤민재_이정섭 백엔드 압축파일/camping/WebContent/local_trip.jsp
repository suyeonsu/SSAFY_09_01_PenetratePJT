<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Local Trip Information</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    	<link href="https://fonts.googleapis.com/css?family=Cookie" rel="stylesheet">
		<link rel="icon" type="image/png" href="img/logo3 (2).png">
		
		
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<link rel="stylesheet" type="text/css" href="css/view.css">
		<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
		
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=072af5d564b2fe0e2778a38436435f49"></script>
		<script>
			window.onload=()=>{
      document.querySelector('.dropbtn_click').onclick = ()=>{
        dropdown();
      }
      document.getElementsByClassName('Province').onclick = ()=>{
        showMenu(value);
      };
      dropdown = () => {
        var v = document.querySelector('.dropdown-content');
        var dropbtn = document.querySelector('.dropbtn')
        v.classList.toggle('show');
        dropbtn.style.borderColor = 'rgb(94, 94, 94)';
      }

      showMenu=(value)=>{
        var dropbtn_icon = document.querySelector('.dropbtn_icon');
        var dropbtn_content = document.querySelector('.dropbtn_content');
        var dropbtn_click = document.querySelector('.dropbtn_click');
        var dropbtn = document.querySelector('.dropbtn');

        dropbtn_icon.innerText = '';
        dropbtn_content.innerText = value;
        dropbtn_content.style.color = '#252525';
        dropbtn.style.borderColor = '#3992a8';
      }
    }
    window.onclick= (e)=>{
      if(!e.target.matches('.dropbtn_click')){
        var dropdowns = document.getElementsByClassName("dropdown-content");

        var dropbtn_icon = document.querySelector('.dropbtn_icon');
        var dropbtn_content = document.querySelector('.dropbtn_content');
        var dropbtn_click = document.querySelector('.dropbtn_click');
        var dropbtn = document.querySelector('.dropbtn');

        var i;
        for (i = 0; i < dropdowns.length; i++) {
          var openDropdown = dropdowns[i];
          if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
          }
        }
      }
    }
		</script>
		<style>
			@import url('https://fonts.googleapis.com/icon?family=Material+Icons');

.dropdown-label{
  font-size : 12px;
  margin : 10px;
  font-weight : bold;
  color : rgb(50, 50, 50);
}

.dropdown{
  position : relative;
  display : inline-block;
}

.dropbtn_icon{
  font-family : 'Material Icons';
}
.dropbtn{
  display : block;
  border : 2px solid rgb(94, 94, 94);
  border-radius : 4px;
  background-color: #fcfcfc;
  font-weight: 400;
  color : rgb(124, 124, 124);
  padding : 12px;
  width : 240px;
  text-align: left;
  cursor : pointer;
  font-size : 12px;
  z-index :1;
  position : relative;
}
.dropdown-content{
  display : none;
  font-weight: 400;
  background-color: #fcfcfc;
  min-width : 240px;
  border-radius: 8px;
  height : 160px;
  overflow : scroll;
  box-shadow: 0px 0px 10px 3px rgba(190, 190, 190, 0.6);
}
.dropdown-content::-webkit-scrollbar{
  width : 5px;
  height : 10px;
}
.dropdown-content::-webkit-scrollbar-thumb{
  border-radius : 2px;
  background-color :rgb(194, 194, 194)
}

.dropdown-content div{
  display : block;
  text-decoration : none;
  color : rgb(37, 37, 37);
  font-size: 12px;
  padding : 12px 20px;
}
.dropdown-content div:hover{
  background-color: rgb(226, 226, 226);
}

.dropdown-content.show{
  display : block;
}
		</style>

		

</head>
<body>
	

	<%@ include file="/include/nav.jsp" %>



								<!--body-->



	<div class="container">
		<div class="row pl-2">
			<div class="col-md-9 col-sm-12 border-left border-info border-right mt-3">
				<h2 class="text-center border-bottom" style="margin-bottom: 20px">지역별 관광 정보</h2>
				
				<div class="row col-md-12 justify-content-center mb-2">
					<div class="form-group col-md-2">
					  <select class="form-select bg-secondary text-light" id="sido"><option value="">시도선택</option>
						  <option value="1100000000">서울특별시</option>
						  
						  <option value="2600000000">부산광역시</option>
						  
						  <option value="2700000000">대구광역시</option>
						  
						  <option value="2800000000">인천광역시</option>
						  
						  <option value="2900000000">광주광역시</option>
						  
						  <option value="3000000000">대전광역시</option>
						  
						  <option value="3100000000">울산광역시</option>
						  
						  <option value="4100000000">경기도</option>
						  
						  <option value="4200000000">강원도</option>
						  
						  <option value="4300000000">충청북도</option>
						  
						  <option value="4400000000">충청남도</option>
						  
						  <option value="4500000000">전라북도</option>
						  
						  <option value="4600000000">전라남도</option>
						  
						  <option value="4700000000">경상북도</option>
						  
						  <option value="4800000000">경상남도</option>
						  
						  <option value="5000000000">제주특별자치도</option>
						  </select>
					</div>
					<div class="form-group col-md-2">
					  <select class="form-select bg-secondary text-light" id="gugun"><option value="">구군선택</option>
						  <option value="1111000000">종로구</option>
						  
						  <option value="1114000000">중구</option>
						  
						  <option value="1117000000">용산구</option>
						  
						  <option value="1120000000">성동구</option>
						  
						  <option value="1121500000">광진구</option>
						  
						  <option value="1123000000">동대문구</option>
						  
						  <option value="1126000000">중랑구</option>
						  
						  <option value="1129000000">성북구</option>
						  
						  <option value="1130500000">강북구</option>
						  
						  <option value="1132000000">도봉구</option>
						  
						  <option value="1135000000">노원구</option>
						  
						  <option value="1138000000">은평구</option>
						  
						  <option value="1141000000">서대문구</option>
						  
						  <option value="1144000000">마포구</option>
						  
						  <option value="1147000000">양천구</option>
						  
						  <option value="1150000000">강서구</option>
						  
						  <option value="1153000000">구로구</option>
						  
						  <option value="1154500000">금천구</option>
						  
						  <option value="1156000000">영등포구</option>
						  
						  <option value="1159000000">동작구</option>
						  
						  <option value="1162000000">관악구</option>
						  
						  <option value="1165000000">서초구</option>
						  
						  <option value="1168000000">강남구</option>
						  
						  <option value="1171000000">송파구</option>
						  
						  <option value="1174000000">강동구</option>
						  </select>
					</div>
					<div class="form-group col-md-2">
					  <select class="form-select bg-secondary text-light" id="dong"><option value="">동선택</option>
						  <option value="1114010100">무교동</option>
						  
						  <option value="1114010200">다동</option>
						  
						  <option value="1114010300">태평로1가</option>
						  
						  <option value="1114010400">을지로1가</option>
						  
						  <option value="1114010500">을지로2가</option>
						  
						  <option value="1114010600">남대문로1가</option>
						  
						  <option value="1114010700">삼각동</option>
						  
						  <option value="1114010800">수하동</option>
						  
						  <option value="1114010900">장교동</option>
						  
						  <option value="1114011000">수표동</option>
						  
						  <option value="1114011100">소공동</option>
						  
						  <option value="1114011200">남창동</option>
						  
						  <option value="1114011300">북창동</option>
						  
						  <option value="1114011400">태평로2가</option>
						  
						  <option value="1114011500">남대문로2가</option>
						  
						  <option value="1114011600">남대문로3가</option>
						  
						  <option value="1114011700">남대문로4가</option>
						  
						  <option value="1114011800">남대문로5가</option>
						  
						  <option value="1114011900">봉래동1가</option>
						  
						  <option value="1114012000">봉래동2가</option>
						  
						  <option value="1114012100">회현동1가</option>
						  
						  <option value="1114012200">회현동2가</option>
						  
						  <option value="1114012300">회현동3가</option>
						  
						  <option value="1114012400">충무로1가</option>
						  
						  <option value="1114012500">충무로2가</option>
						  
						  <option value="1114012600">명동1가</option>
						  
						  <option value="1114012700">명동2가</option>
						  
						  <option value="1114012800">남산동1가</option>
						  
						  <option value="1114012900">남산동2가</option>
						  
						  <option value="1114013000">남산동3가</option>
						  
						  <option value="1114013100">저동1가</option>
						  
						  <option value="1114013200">충무로4가</option>
						  
						  <option value="1114013300">충무로5가</option>
						  
						  <option value="1114013400">인현동2가</option>
						  
						  <option value="1114013500">예관동</option>
						  
						  <option value="1114013600">묵정동</option>
						  
						  <option value="1114013700">필동1가</option>
						  
						  <option value="1114013800">필동2가</option>
						  
						  <option value="1114013900">필동3가</option>
						  
						  <option value="1114014000">남학동</option>
						  
						  <option value="1114014100">주자동</option>
						  
						  <option value="1114014200">예장동</option>
						  
						  <option value="1114014300">장충동1가</option>
						  
						  <option value="1114014400">장충동2가</option>
						  
						  <option value="1114014500">광희동1가</option>
						  
						  <option value="1114014600">광희동2가</option>
						  
						  <option value="1114014700">쌍림동</option>
						  
						  <option value="1114014800">을지로6가</option>
						  
						  <option value="1114014900">을지로7가</option>
						  
						  <option value="1114015000">을지로4가</option>
						  
						  <option value="1114015100">을지로5가</option>
						  
						  <option value="1114015200">주교동</option>
						  
						  <option value="1114015300">방산동</option>
						  
						  <option value="1114015400">오장동</option>
						  
						  <option value="1114015500">을지로3가</option>
						  
						  <option value="1114015600">입정동</option>
						  
						  <option value="1114015700">산림동</option>
						  
						  <option value="1114015800">충무로3가</option>
						  
						  <option value="1114015900">초동</option>
						  
						  <option value="1114016000">인현동1가</option>
						  
						  <option value="1114016100">저동2가</option>
						  
						  <option value="1114016200">신당동</option>
						  
						  <option value="1114016300">흥인동</option>
						  
						  <option value="1114016400">무학동</option>
						  
						  <option value="1114016500">황학동</option>
						  
						  <option value="1114016600">서소문동</option>
						  
						  <option value="1114016700">정동</option>
						  
						  <option value="1114016800">순화동</option>
						  
						  <option value="1114016900">의주로1가</option>
						  
						  <option value="1114017000">충정로1가</option>
						  
						  <option value="1114017100">중림동</option>
						  
						  <option value="1114017200">의주로2가</option>
						  
						  <option value="1114017300">만리동1가</option>
						  
						  <option value="1114017400">만리동2가</option>
						  </select>
					</div>
					<div class="form-group col-md-2">
					  <button type="button" id="list-btn" class="btn btn-outline-primary">
						Search
					  </button>
					</div>
				  </div>
				  
				  <br/>
				
				  <div id="map" style="width:100%;height:500px;"></div>
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9d29d54a5264a72919fa63d8292496d2"></script>
						<script>
							var container = document.getElementById('map');
							var options = {
								center: new kakao.maps.LatLng(33.450701, 126.570667),
								level: 3
							};

							var map = new kakao.maps.Map(container, options);
						</script>
				
				<p class="text-left">아랫 문단</p>
				
			</div>

			<div class="col-md-3 col-sm-12 border-right border-info">
				<form class="mt-4">
					<input type="Search" class="form-control" name="" placeholder="Search">
				</form>

				<h4 class="mt-4">RECENT POSTS</h4>
				<p class="text-left">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>

				<h4 class="mt-4">RECENT COMMENTS</h4>

				<h4 class="mt-4">ARCHIVES</h4>
				<P>JUNE 2018</P>

				<h4 class="mt-4">CATAGORIES</h4>
				<P>catagoris 1<br>catagoris 2<br>catagoris 3<br>catagoris 4<br></P><br>
				
			
			</div>
			
		</div>
		
	</div>

						<!--footer-->
<%@ include file="/include/footer.jsp" %>
				
</div>
				




				<!--  javascript   -->

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