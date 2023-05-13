<template>
  <div class="tour">
    <div id="map">
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
                    active: theme.id === activeTheme.id ? true : false,
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
import axios from "axios";
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
      activeTheme: "",
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
    // 테마 선택 이벤트 핸들러 (지도 화면에 마커 뿌리기)
    async sidebarClickHandler(theme) {
      this.activeTheme = theme;

      await this.getAreaInfo();
    },

    /** kakao map script file loading 함수
     */
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

    /** 지도 초기 로딩 함수
     */
    loadMap() {
      window.kakao.maps.load(() => {
        // =============
        // 시작 - 지도 초기 로딩
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
        // 끝 - 지도 초기 로딩
        // =============

        // =============
        // 시작 - 지도 컨트롤러 추가
        // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
        const mapTypeControl = new kakao.maps.MapTypeControl();

        // 지도 최소 최대 확대비율 정하기
        this.map.setMinLevel(2);
        this.map.setMaxLevel(10);

        // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
        // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
        this.map.addControl(
          mapTypeControl,
          kakao.maps.ControlPosition.TOPRIGHT
        );

        // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
        const zoomControl = new kakao.maps.ZoomControl();
        this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
        // 끝 - 지도 컨트롤러 추가
        // =============
      });
    },

    // =============
    // 시작 - 일반 지도 함수 영역
    /** 지도 중심좌표를 부드럽게 이동시키는 메소드
     * @param lat (Number) 위도 좌표
     * @param lon (Number) 경도 좌표
     */
    movePosition(lat, lon) {
      // 해당 위치로 맵을 부드럽게 이동한다.
      const moveLatLon = new kakao.maps.LatLng(lat, lon);
      this.map.panTo(moveLatLon);
    },
    /** 지도의 중심좌표를 현재 위치로 이동시키는 메소드
     */
    moveToCurrent() {
      // 현재 위치로 이동하기
      navigator.geolocation.getCurrentPosition(
        this.mapInitSuccess, // 성공시 메소드
        this.mapInitError // 실패시 메소드
      );
    },
    // 위치 얻기 성공시
    mapInitSuccess(pos) {
      const crd = pos.coords;

      // console.log(crd);
      this.movePosition(crd.latitude, crd.longitude);
    },
    // 에러 발생시
    mapInitError(err) {
      console.log(err);
    },
    /** 위, 경도를 이용해 두 지점 사이 거리를 구하는 함수
     * @param lat1 지점1 위도
     * @param lon1 지점1 경도
     * @param lat2 지점2 위도
     * @param lon2 지점2 경도
     */
    getDistanceFromLatLonInM(lat1, lon1, lat2, lon2) {
      function deg2rad(deg) {
        return deg * (Math.PI / 180);
      }

      const R = 6371; // Radius of the earth in km
      const dLat = deg2rad(lat2 - lat1); // deg2rad below
      const dLon = deg2rad(lon2 - lon1);
      const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(lat1)) *
          Math.cos(deg2rad(lat2)) *
          Math.sin(dLon / 2) *
          Math.sin(dLon / 2);
      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      const d = R * c * 1000; // Distance in M
      return d;
    },
    // 끝 - 일반 지도 함수 영역
    // =============

    // =============
    // 시작 - 마커 관련 함수
    /** 마커 생성 서순
     * 1. 위도, 경도를 fetch로 받아와서 locationArray 를 생성한다.
     * 		[데이터 타입: kakao.maps.LatLng(위도 latitude, 경도 longitude)]
     * 2. locationArray의 요소들로 createMarker(position, image)를 실행해 마커를 생성하고 마커 객체 배열을 생성한다.
     * 		[데이터 타입: kakao.maps.Marker({position: kakao.maps.LatLng(), image: kakao.maps.MarkerImage(src, size, options) })]
     * 3. MarkerImage는 createMarkerImage(이미지 url, new kakao.maps.Size(width, height), 옵션) 로 생성한다.
     * 		=> 내부에 kakao.maps.MarkerImage(src, size, options)를 실행함.
     * 4. create테마Markers()를 선언한다.
     * 		1) locationArray의 요소만큼 반복문 실행
     * 		2) imageSize: kakao.maps.Size(width, height)로 마커 이미지 사이즈 설정
     * 		3) imageOptions: {
     * 						spriteOrigin: 이미지 시작점. kakao.maps.Point(x좌표(세로), y좌표(가로)),
     * 						spriteSize : 이미지 크기. kakao.maps.Size(width, height)
     * 				}  를 생성
     * 		4) createMarkerImage(이미지 URL, imageSize, imageOptions) 로 markerImage 객체 생성
     * 		5) createMarker(position 객체, image 객체)로 마커를 생성
     * 		6) 생성된 마커를 마커 객체 배열에 추가 (push)
     * 5. set테마Markers(map) 을 선언한다.
     * 		1) 마커 객체 배열을 돌면서 Marker.setMap(map) 실행
     */

    // 1. 백엔드에서 지역 정보를 가져와 Pinia의 positions 배열 완성.
    /** 백엔드에 테마별로 장소 정보 요청
     *
     */
    async getAreaInfo() {
      const center = this.map.getCenter(); // 중심 좌표. La : longitude, Ma : latitude
      const bounds = this.map.getBounds(); // 영역 정보
      const swLatLng = bounds.getSouthWest(); // 남서 꼭지점 좌표
      const neLatLng = bounds.getNorthEast(); // 북동 꼭지점 좌표

      const mapAreaInfo = {
        centerLat: center.Ma, // 중심 latitude
        centerLon: center.La, // 중심 longitude
        swLat: swLatLng.Ma,
        swLon: swLatLng.La,
        neLat: neLatLng.Ma,
        neLon: neLatLng.La,
      };

      // 오픈 API 이용
      const distance = this.getDistanceFromLatLonInM(
        mapAreaInfo.swLat,
        mapAreaInfo.swLon,
        mapAreaInfo.neLat,
        mapAreaInfo.neLon
      );

      const parameters = {
        MobileOS: "WIN",
        MobileApp: "EnjoyTrip",
        mapX: mapAreaInfo.centerLon, // 중심 경도
        mapY: mapAreaInfo.centerLat, // 중심 위도
        radius: distance,
        contentTypeId: this.activeTheme.id,
        _type: "json",
        pageNo: 1,
        numOfRows: 10,
        serviceKey:
          "Ee0EApL3oZ3hp4+Oc13AHLXzxi8eCyupR6C1Od3o12ybTMrN/3PuLRSVWpaAoNkZtosEJERnAV+CmhPyo8d5Jw==",
      };
      const params = new URLSearchParams(parameters);
      const queryString = params.toString();
      const url = `https://apis.data.go.kr/B551011/KorService1/locationBasedList1?${queryString}`;
      const res = await axios.get(url);
      console.log(res.data.response.body.items.item);
      this.tourStore.places = res.data.response.body.items.item;
      // this.tourStore.places.concat(res.data.response.body.items.item);
      console.log(this.tourStore.places);
    },

    /** 테마 바꾸기 서순
     * 1. 각 테마 버튼 엘리먼트를 가져온다.
     * 2. 클릭되면 해당 엘리먼트의 클래스에 menu_selected를 추가해 스타일을 입힌다.
     * 3. set테마Marker(map)을 실행한다.
     * 4. 만약 마커를 없애고 싶다면 null을 넣어준다.
     */

    /** 마커 이미지 생성 함수
     * @param src [String] 이미지 주소 URL
     * @param size [kakao.maps.Size(width, height)] 마커 이미지 사이즈
     * @param options [Object] 옵션 객체.
     * @returns [kakao.maps.MarkerImage] 마커 이미지 객체
     */
    createMarkerImage(src, size, options) {
      const markerImage = new kakao.maps.MarkerImage(src, size, options);
      return markerImage;
    },

    /** 마커 객체 생성 팩토리 메소드
     * @param position [kakao.maps.Point] 지도 상의 좌표
     * @param image [kakao.maps.MarkerImage] 마커 이미지 객체
     * @returns [kakao.maps.Marker] 마커 객체
     */
    createMarker(position, image) {
      const marker = new kakao.maps.Marker({
        position,
        image,
      });

      return marker;
    },

    // 끝 - 마커 관련 함수
    // =============

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
