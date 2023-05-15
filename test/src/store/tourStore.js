import { defineStore } from "pinia";
import { ref } from "vue";
import axios from "axios";

/* global kakao */
export const useTourStore = defineStore("tour", () => {
  // ====================================================
  // 전역변수영역
  const map = ref(null); // 지도 객체
  const places = ref([]); // 장소 정보
  const markers = ref([]); // 장소에 따른 마커
  const activeTheme = ref({}); // 활성화 된 테마
  // ====================================================

  // ====================================================
  // 시작 - 초기 세팅
  /** 지도를 불러오는 함수
   * @param {지도요소} container
   */
  function initMap(container) {
    // 지도 옵션
    const options = {
      center: new kakao.maps.LatLng(37.498004414546934, 127.02770621963765), //지도의 중심좌표.
      level: 4, //지도의 레벨(확대, 축소 정도)
    };
    // 지도 생성
    map.value = new kakao.maps.Map(container, options);

    // 지도 컨트롤러 - 지도 타입 (일반 지도, 스카이뷰)
    const mapTypeControl = new kakao.maps.MapTypeControl();
    map.value.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT); // 우상단에 컨트롤러 추가.

    // 지도 컨트롤러 - 줌 컨트롤
    map.value.setMinLevel(2); // 최대 확대 한계
    map.value.setMaxLevel(11); // 최소 축소 한계
    const zoomControl = new kakao.maps.ZoomControl();
    map.value.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
  }
  // 끝 - 초기 세팅
  // ====================================================

  // ====================================================
  // 시작 - 테마 변경
  /** 테마 변경 함수
   * @param {object} nextTheme
   */
  async function changeTheme(nextTheme) {
    if (activeTheme.value.id !== nextTheme.id) {
      activeTheme.value = nextTheme;

      // 기존에 존재하던 마커 제거
      for (const now of markers.value) {
        now.setMap(null);
      }
      places.value = [];
      markers.value = [];

      return await getPlaceInfo(map.value.getCenter(), map.value.getBounds());
    }
    return null;
  }
  // 끝 - 테마 변경
  // ====================================================

  // ====================================================
  // 시작 - 일반 지도 함수
  /** 지도 중심좌표를 부드럽게 이동시키는 메소드
   * @param {Number} lat 위도 좌표
   * @param {Number} lon 경도 좌표
   */
  function movePosition(lat, lon) {
    // 해당 위치로 맵을 부드럽게 이동한다.
    const moveLatLon = new kakao.maps.LatLng(lat, lon);
    map.value.panTo(moveLatLon);
  }
  /** 지도의 중심좌표를 현재 위치로 이동시키는 메소드 */
  function moveToCurrent() {
    // 현재 위치로 이동하기
    navigator.geolocation.getCurrentPosition(
      (res) => {
        // 성공시 메소드
        const crd = res.coords;
        this.movePosition(crd.latitude, crd.longitude);
      },
      (err) => {
        // 실패시 메소드
        console.log(err);
      }
    );
  }
  // 끝 - 일반 지도 함수
  // ====================================================

  // ====================================================
  // 시작 - 장소 정보 받아오기
  /** 마커 생성 서순
   * 1.
   */

  /** 위, 경도를 이용해 두 지점 사이 거리를 구하는 함수
   * @param {Number} lat1 지점1 위도
   * @param {Number} lon1 지점1 경도
   * @param {Number} lat2 지점2 위도
   * @param {Number} lon2 지점2 경도
   */
  function getDistanceFromLatLonInM(lat1, lon1, lat2, lon2) {
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
  }

  /** 장소 정보 요청하기 by Open API
   * @param {kakao.maps.Point} center 화면 중심 좌표
   * @param {kakao.maps.Point} bounds 화면 영역 정보
   */
  async function getPlaceInfo(center, bounds) {
    const neLatLng = bounds.getNorthEast(); // 북동 꼭지점 좌표

    const mapAreaInfo = {
      centerLat: center.Ma, // 중심 latitude
      centerLon: center.La, // 중심 longitude
      neLat: neLatLng.Ma,
      neLon: neLatLng.La,
    };

    // 중심부터 꼭지점 사이의 거리 (단위: m)
    const distance = getDistanceFromLatLonInM(
      mapAreaInfo.centerLat,
      mapAreaInfo.centerLon,
      mapAreaInfo.neLat,
      mapAreaInfo.neLon
    );

    const parameters = {
      MobileOS: "WIN",
      MobileApp: "EnjoyTrip",
      mapX: mapAreaInfo.centerLon, // 중심 경도
      mapY: mapAreaInfo.centerLat, // 중심 위도
      radius: distance, // 반경
      contentTypeId: activeTheme.value.id,
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
    console.log("결과:", res.data.response.body.items.item);
    places.value.push(...res.data.response.body.items.item);
    console.log(places.value);

    return res.data.response.body.items.item;
  }
  // 끝 - 장소 정보 받아오기
  // ====================================================

  // ====================================================
  // 시작 - 마커 생성하기

  /** 마커 생성 메소드
   * @param {Array} nextPlaces
   */
  function makeMarkers(nextPlaces) {
    console.log(nextPlaces);
    // 커스텀 마커 이미지 생성
    const imageSize = new kakao.maps.Size(22, 36);
    const imageSrc = require(`@/assets/image/tour/${activeTheme.value.image}.png`);
    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    console.log("마커이미지", markerImage);
    // 마커 객체 생성
    for (let now of nextPlaces) {
      console.log(now);
      const position = new kakao.maps.LatLng(now.mapy, now.mapx);
      const marker = new kakao.maps.Marker({ position, image: markerImage });
      markers.value.push(marker);
      marker.setMap(map.value);
      console.log(marker);
    }
  }

  // 끝 - 마커 생성하기
  // ====================================================
  return {
    initMap,
    changeTheme,
    makeMarkers,
    activeTheme,
    map,
    places,
    markers,
    getPlaceInfo,
  };
});
