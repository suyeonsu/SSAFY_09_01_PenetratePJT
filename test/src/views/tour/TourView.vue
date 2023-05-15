<template>
  <div class="tour">
    <div id="map" ref="map">
      <div class="side-bar">
        <ul class="title">
          <div>
            <template v-for="theme in this.themes" :key="theme.id">
              <router-link
                @click="sidebarClickHandler(theme)"
                :to="{ name: 'searchList' }">
                <li
                  :class="{
                    item: true,
                    active:
                      theme.id === tourStore.activeTheme.id ? true : false,
                  }"
                  :id="theme.id">
                  <img
                    :src="require(`@/assets/image/tour/${theme.image}.png`)"
                    alt="" />
                  {{ theme.text }}
                </li>
              </router-link>
            </template>
          </div>
          <div>
            <router-link :to="{ name: 'hotplaceList' }">
              <li class="item">
                <img
                  :src="require(`@/assets/image/tour/hotplace.png`)"
                  alt="" />
                추천명소
              </li>
            </router-link>
            <router-link :to="{ name: 'planList' }">
              <li class="item">
                <img
                  :src="require(`@/assets/image/tour/destination.png`)"
                  alt="" />
                여행계획
              </li>
            </router-link>
          </div>
        </ul>

        <div :class="{ list: true, hidden }">
          <div class="opener" @click="toggleSideBar"></div>
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useTourStore } from "@/store/tourStore";

export default {
  data() {
    return {
      map: null,
      themes: [
        {
          id: 12,
          image: "tourism",
          text: "관광지",
        },
        {
          id: 32,
          image: "hotel",
          text: "숙박",
        },
        {
          id: 39,
          image: "restaurant",
          text: "음식점",
        },
        {
          id: 14,
          image: "culture",
          text: "문화시설",
        },
        {
          id: 15,
          image: "concert",
          text: "공연",
        },
        {
          id: 38,
          image: "shopping",
          text: "쇼핑",
        },
      ],
      hidden: true, // 나중에 true로 바꿔주기
    };
  },
  setup() {
    const tourStore = useTourStore();
    return {
      tourStore,
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
    /** 카카오 맵 스크립트 파일 로딩 메소드 */
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

    /** 지도 초기 로딩 함수 */
    loadMap() {
      window.kakao.maps.load(() => {
        const container = this.$refs.map; //지도를 담을 영역의 DOM 레퍼런스
        this.tourStore.initMap(container);
      });
    },

    // 테마 선택 이벤트 핸들러 (지도 화면에 마커 뿌리기)
    async sidebarClickHandler(theme) {
      const nextPlaces = await this.tourStore.changeTheme(theme);
      console.log("다음장소:", nextPlaces);
      if (nextPlaces != null) {
        this.tourStore.makeMarkers(nextPlaces);
      }
    },

    toggleSideBar() {
      // 왼쪽 사이드 바 토글기능
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
    width: 100%;
    height: 100%;
  }
  .side-bar {
    // 사이드바 전체
    width: 70px;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 3;

    .title {
      // 좌측 메뉴
      position: absolute;
      top: 0;
      left: 0;
      height: 100%;
      background: white;
      display: flex;
      justify-content: space-between;
      flex-direction: column;
      z-index: 3;
      border-top: 1px solid lightgrey;
      border-right: 1px solid lightgray;
      a {
        text-decoration: none;
        color: black;
      }
      .item {
        // 좌측 메뉴 항목들
        width: 70px;
        height: 70px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        box-sizing: border-box;
        cursor: pointer;
        &.active {
          background-color: $primary;
          color: white;
        }
        &:hover {
          background-color: $primary;
        }
        img {
          width: 50%;
          height: 50%;
          margin-bottom: 5px;
        }
      }
    }
    .list {
      position: absolute;
      left: 70px;
      width: 25vw;
      height: 100%;
      transition: 0.5s;
      z-index: 2;
      &.hidden {
        transform: translateX(-100%);
        z-index: -1;
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
        cursor: pointer;
      }
    }
  }
}
</style>
