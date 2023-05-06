<template>
  <div class="tour">
    <div id="map">
      <div :class="{ 'side-bar': true, hidden }">
        <ul class="category">
          <template v-for="category in this.categories" :key="category.id">
            <li :id="category.id">
              <i :class="category.image"></i>
              {{ category.text }}
            </li>
          </template>
        </ul>
        <router-link
          class="opener"
          @click="toggleSideBar"
          :to="{ name: 'searchList' }">
        </router-link>
        <router-view></router-view>
      </div>
    </div>
  </div>
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
      hidden: true,
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
    toggleSideBar() {
      this.hidden = !this.hidden;
    },
  },
};
</script>
<style lang="scss" scoped>
.tour {
  position: relative;
  height: 90vh;
  background: tomato;
  #map {
    position: absolute;
    overflow: hidden;
    width: 100%;
    height: 100%;
  }
  .side-bar {
    // 사이드바
    width: 25vw;
    height: 100%;
    background: blue;
    position: absolute;
    transition: 0.3s;
    z-index: 9;
    top: 0;
    &.hidden {
      transform: translateX(-100%); // 집어넣기
    }
    .category {
      // 상단부 메뉴
      position: absolute;
      top: 10px;
      right: 0;
      transform: translate(105%, 0);
      background: white;
      display: flex;
      li {
        // 상단 메뉴 아이콘들
        width: 70px;
        height: 70px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        box-sizing: border-box;
        border: 1px solid $primary;
        cursor: pointer;
        &:hover {
          background-color: $primary;
        }
        i {
          width: 50%;
          height: 50%;
          background-color: tomato;
          margin-bottom: 5px;
        }
      }
    }
    .opener {
      // 사이드바 토글용 버튼
      width: 20px;
      height: 40px;
      background-color: pink;
      position: absolute;
      top: 50%;
      right: 0;
      transform: translate(100%, -50%);
    }
  }
}
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
