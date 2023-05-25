<template>
  <div class="tour">
    <div id="map" ref="map">
      <div @click="tourStore.moveToCurrent" id="go-to-current">
        <font-awesome-icon :icon="['far', 'circle-dot']" />
      </div>
      <div class="side-bar">
        <ul class="title">
          <div>
            <template v-for="theme in this.themes" :key="theme.id">
              <!-- 테마 -->
              <router-link
                @click="changeTheme(theme)"
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
        </ul>

        <div :class="{ list: true, 'hide-list': tourStore.hideList }">
          <div
            :class="{ opener: true, 'hide-detail': !tourStore.hideDetail }"
            @click="toggleSideBar">
            <font-awesome-icon
              v-show="tourStore.hideList && tourStore.hideDetail"
              :icon="['fas', 'chevron-right']" />
            <font-awesome-icon
              v-show="!tourStore.hideList || !tourStore.hideDetail"
              :icon="['fas', 'chevron-left']" />
          </div>
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
        {
          id: 1,
          image: "myplace",
          text: "북마크",
        },
      ],
    };
  },
  setup() {
    const tourStore = useTourStore();
    return {
      tourStore,
    };
  },
  created() {
    this.tourStore.$reset();
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
    /** 카카오 맵 스크립트 파일 로딩 메소드
     * 1. 카카오 맵 스크립트를 로딩
     * 2. 로딩 성공시 loadMap() 메소드 실행
     */
    loadScript() {
      // 카카오 맵 스크립트 추가하기
      const script = document.createElement("script");
      script.type = "text/javascript";
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_API_KEY}`;
      /* global kakao */ // eslint에게 kakao가 전역변수임을 알려준다.
      script.onload = () => {
        // kakao.maps.load(this.loadMap);
        this.loadMap();
      }; // 스크립트 로드 및 maps load가 끝나면, 지도 실행
      document.head.appendChild(script);
      // document의 head에 script 소스 추가.
    },

    /** 지도 초기 로딩 함수
     * map 레퍼런스에 지도 객체 할당하는 initMap() 함수 실행
     * this.$refs.map: 지도를 담을 영역의 DOM 레퍼런스
     */
    loadMap() {
      window.kakao.maps.load(() => {
        this.tourStore.initMap(this.$refs.map);
      });
    },

    /** 테마 선택 이벤트 핸들러
     * 1. 테마 버튼을 선택 시 초기 페이지를 요청한다.
     * 2. 결과를 pinia의 markers 배열에 추가한다.
     * 3. 1회당 결과를 받아와서 마커를 만들고 지도에 추가한다.
     */
    async changeTheme(theme) {
      const nextPlaces = await this.tourStore.changeTheme(theme);
      console.log("다음장소:", nextPlaces);
      if (nextPlaces != null) {
        this.tourStore.makeMarkers(nextPlaces);
      }
    },

    toggleSideBar() {
      // 왼쪽 사이드 바 토글기능
      this.tourStore.toggleHideList();
      this.tourStore.toggleHideDetail();
    },
  },
};
</script>
<style lang="scss" scoped>
.tour {
  position: relative;
  height: 90vh;
  background: beige;
  #map {
    position: absolute;
    width: 100%;
    height: 100%;
    #go-to-current {
      position: absolute;
      width: 2rem;
      height: 2rem;
      right: 1px;
      top: 200px;
      border: 1px solid lightgray;
      background: $background;
      border-radius: 0.5rem;
      font-size: 1.2rem;
      z-index: 99999;
      display: flex;
      justify-content: center;
      align-items: center;
      cursor: pointer;
      &:hover {
        background: lightgray;
      }
    }
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
      font-family: "S-CoreDream-3Light";
      background: $background;
      display: flex;
      justify-content: space-between;
      flex-direction: column;
      z-index: 11;
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
          color: $background;
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
      width: $side-bar-width;
      height: 100%;
      transition: 0.5s;
      box-shadow: 1px 0 5px lightgray;
      &.hide-list {
        transform: translateX(-100%);
        z-index: -1;
      }
      .opener {
        // 사이드바 토글용 버튼
        width: 20px;
        height: 40px;
        background-color: $background;
        position: absolute;
        top: 50%;
        left: $side-bar-width;
        transform: translateY(-50%);
        transition: 0.2s;
        cursor: pointer;
        border-radius: 0 5px 5px 0;
        border: 1px solid lightgray;
        border-left: none;
        display: flex;
        justify-content: center;
        align-items: center;
        color: gray;
        &.hide-detail {
          left: $side-bar-width * 2;
        }
      }
    }
  }
}
</style>
