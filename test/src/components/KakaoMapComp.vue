<template>
  <div id="map">
    <div class="category">
      <ul>
        <template v-for="category in this.categories" :key="category.id">
          <li :id="category.id">
            <i :class="category.image"></i>
            {{ category.text }}
          </li>
        </template>
      </ul>
    </div>
  </div>
  `
</template>

<script>
export default {
  data() {
    return {
      map: null,
      categories: [
        {
          id: 1,
          image: "binoculars-fill",
          text: "관광지",
        },
        {
          id: 2,
          image: "house-fill",
          text: "숙박",
        },
        {
          id: 3,
          image: "egg-fried",
          text: "음식점",
        },
        {
          id: 4,
          image: "book-half",
          text: "문화시설",
        },
        {
          id: 5,
          image: "cart-fill",
          text: "공연",
        },
        {
          id: 6,
          image: "binoculars-fill",
          text: "쇼핑",
        },
      ],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      // 카카오 객체 및 카카오 맵 정보가 존재하면 맵 실행
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    loadScript() {
      // 카카오 맵 스크립트 추가하기
      const script = document.createElement("script");
      const API_KEY = "c27c029a47d8b36a3390ddda157d4950";
      script.type = "text/javascript";
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${API_KEY}`;
      /* global kakao */ // eslint에게 kakao가 전역변수임을 알려준다.
      script.onload = () => {
        kakao.maps.load(this.loadMap);
      }; // 스크립트 로드 및 maps load가 끝나면, 지도 실행
      document.head.appendChild(script);
      // document의 head에 script 소스 추가.
    },
    loadMap() {
      // 지도 초기 로딩
      window.kakao.maps.load(() => {
        const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

        const options = {
          //지도를 생성할 때 필요한 기본 옵션
          // center: new kakao.maps.LatLng(crd.latitude, crd.longitude), //지도의 중심좌표.
          center: new window.kakao.maps.LatLng(
            37.498004414546934,
            127.02770621963765
          ), //지도의 중심좌표.
          level: 4, //지도의 레벨(확대, 축소 정도)
        };

        this.map = new window.kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
      });
    },
  },
};
</script>

<style lang="scss" scoped>
#map {
  position: absolute;
  overflow: hidden;
  top: 80px; // 화면에 가득 차도록 나중에 props로 받아오기
  bottom: 0;
  left: 0;
  right: 0;
}

.category,
.category * {
  margin: 0;
  padding: 0;
  color: $primary;
}

.category {
  position: absolute;
  overflow: hidden;
  top: 10px;
  left: 10px;
  /* width: 150px; */
  height: 50px;
  z-index: 10;
  border: 1px solid black;
  font-size: 12px;
  text-align: center;
  background-color: #fff;
}

.category .menu_selected {
  background: var(--dark);
  color: var(--light);
  border-left: 1px solid #915b2f;
  border-right: 1px solid #915b2f;
  margin: 0 -1px;
}
.category .menu_selected .ico_comm {
  color: var(--light);
}

.category li {
  list-style: none;
  float: left;
  width: 50px;
  height: 50px;
  /* padding-top: 5px; */
  cursor: pointer;
}

.category .ico_comm {
  display: block;
  margin: 0 auto 5px;
  width: 22px;
  height: 22px;
  font-size: 22px;
  /* background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png') no-repeat; */
}
.category .menu_selected .ico_comm {
  display: block;
  margin: 0 auto 5px;
  width: 22px;
  height: 22px;
  font-size: 22px;
  /* background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/category.png') no-repeat; */
}

/* .category .ico_coffee {
  background-position: -10px 0;
}

.category .ico_store {
  background-position: -10px -36px;
}

.category .ico_carpark {
  background-position: -10px -72px;
} */

/* customOverlay */
.overlaybox {
  position: relative;
  width: 250px;
  height: 250px;
  background-color: #2b2d36;
  /* background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png")
    no-repeat; */
  padding: 15px 10px;
}
.overlaybox div,
ul {
  overflow: hidden;
  margin: 0;
  padding: 0;
}
.overlaybox li {
  list-style: none;
}
.overlaybox .title {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 8px;
}

.overlaybox ul {
  width: inherit;
}
.overlaybox li {
  position: relative;
  margin-bottom: 2px;
  background: #2b2d36;
  padding: 5px 10px;
  color: #aaabaf;
  line-height: 1;
}

.overlaybox img {
  width: 230px;
}

.overlaybox li span {
  display: inline-block;
}
.overlaybox li .content-title {
  font-size: 15px;
  font-weight: bold;
}
.overlaybox li .content-detail {
  font-size: 13px;
}
.overlay-arrow {
  border: 0px solid transparent;
  border-bottom: 10px solid rgba(0, 0, 0, 0);
  top: 6px;
  position: relative;
  right: -27%;
}
.overlay-arrow::before {
  border: 10px solid transparent;
  border-top: 8px solid #2b2d36;
  /* top: 101px; */
  content: "";
  /* position: absolute; */
  /* position: relative; */
  /* right: 50%; */
}
</style>
