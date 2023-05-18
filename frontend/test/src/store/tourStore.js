import { defineStore } from "pinia";
import { createApp, h, inject, ref } from "vue";
import axios from "axios";
import router from "@/router";
import OverlayComp from "@/components/OverlayComp.vue";

/* global kakao */
export const useTourStore = defineStore("tour", () => {
  // ====================================================
  // 전역변수영역
  const SERVICE_KEY =
    "Ee0EApL3oZ3hp4+Oc13AHLXzxi8eCyupR6C1Od3o12ybTMrN/3PuLRSVWpaAoNkZtosEJERnAV+CmhPyo8d5Jw==";
  const map = ref(null); // 지도 객체
  const places = ref([]); // 현재 위치 기반 장소 정보
  const markers = ref([]); // 장소에 따른 마커
  const overlays = ref([]); // 장소에 따른 오버레이
  const activeTheme = ref({}); // 활성화 된 테마
  const activePlace = ref(""); // 활성화 된 장소
  const hideList = ref(true); // 장소 리스트 숨기기
  const hideDetail = ref(true); // 상세 페이지 숨기기
  const detailPlace = ref({}); // 상세 페이지 요소

  const router = inject("router"); // router 사용.
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

    hideDetail.value = true;
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
      console.log("액티브", activeTheme);
      reset();

      if (activeTheme.value.id > 10) {
        // 일반 테마일 경우
        return await getPlaceInfo(map.value.getCenter(), map.value.getBounds());
      }
      // 다른 서브페이지로 이동하는 경우(추천명소, 여행계획)
    }
    return null;
  }
  /** 투어 페이지 리셋하기 */
  function reset() {
    // 기존에 존재하던 마커 제거
    for (const now of markers.value) {
      now.setMap(null);
    }
    for (const now of overlays.value) {
      now.setMap(null);
    }
    places.value = [];
    markers.value = [];
    deactivatePlace();
    hideDetail.value = true;
  }
  // 끝 - 테마 변경
  // ====================================================

  // ====================================================
  // 시작 - 장소 자세히보기
  /** 상세 정보 페이지 정보 확인 */
  function activatePlace(id) {
    activePlace.value = id;
    hideDetail.value = false;
  }
  /** 상세 정보 페이지 닫기 */
  function deactivatePlace() {
    activePlace.value = "";
    hideDetail.value = true;
  }
  // 끝 - 장소 자세히보기
  // ====================================================

  // ====================================================
  // 시작 - 사이드 바 토글
  /** 사이드 바 리스트 항목 숨기기 토글 */
  function toggleHideList() {
    hideList.value = !hideList.value;
  }
  /** 사이드 바 상세 항목 숨기기 토글 */
  function toggleHideDetail() {
    if (activePlace.value) {
      hideDetail.value = !hideDetail.value;
    } else {
      hideDetail.value = true;
    }
  }
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
  /** 위, 경도를 이용해 두 지점 사이 거리를 구하는 함수
   * @param {Number} lat1 지점1 위도
   * @param {Number} lon1 지점1 경도
   * @param {Number} lat2 지점2 위도
   * @param {Number} lon2 지점2 경도
   * @returns {Number} 두 지점 사이의 거리
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
  // 끝 - 일반 지도 함수
  // ====================================================

  // ====================================================
  // 시작 - 현재 위치 기반 장소 정보 받아오기

  /** 현재 위치 기반 장소 정보 요청하기 by Open API
   * @param {kakao.maps.Point} center 화면 중심 좌표
   * @param {kakao.maps.Point} bounds 화면 영역 정보
   * @returns {Array} 장소 객체 배열
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
      serviceKey: SERVICE_KEY,
    };
    const options = {
      methods: "get",
      url: "https://apis.data.go.kr/B551011/KorService1/locationBasedList1",
      params: parameters,
    };
    const res = await axios(options);
    places.value.push(...res.data.response.body.items.item);

    return res.data.response.body.items.item;
  }
  // 끝 - 현재 위치 기반 장소 정보 받아오기
  // ====================================================
  // ====================================================
  // 시작 - 키워드 검색
  const sido = ref([]);
  const gugun = ref([]);

  /** 시도 코드를 가져오는 함수
   * @returns {Array} 장소 객체 배열
   */
  async function getSido() {
    sido.value.length = 0;
    const parameters = {
      MobileOS: "WIN",
      MobileApp: "EnjoyTrip",
      _type: "json",
      pageNo: 1,
      numOfRows: 20,
      serviceKey: SERVICE_KEY,
    };
    const options = {
      methods: "get",
      url: "https://apis.data.go.kr/B551011/KorService1/areaCode1",
      params: parameters,
    };
    const res = await axios(options);
    sido.value.push(...res.data.response.body.items.item);
  }
  /** 시도 코드를 통해 구군 코드를 얻어서 저장하는 함수
   * @param {Number} sido
   */
  async function getGugun(sido) {
    gugun.value.length = 0;
    if (sido === "") return;
    const parameters = {
      MobileOS: "WIN",
      MobileApp: "EnjoyTrip",
      _type: "json",
      pageNo: 1,
      numOfRows: 100,
      areaCode: sido,
      serviceKey: SERVICE_KEY,
    };
    const options = {
      methods: "get",
      url: "https://apis.data.go.kr/B551011/KorService1/areaCode1",
      params: parameters,
    };

    const res = await axios(options);
    console.log(res);
    gugun.value.push(...res.data.response.body.items.item);
  }
  /** 키워드 검색 기능.
   * @param {String} keyword 키워드
   * @param {Number} areaCode 시도코드
   * @param {Number} sigunguCode 구군코드
   */
  async function getKeywordResult(keyword, areaCode, sigunguCode) {
    console.log("키워드", keyword);
    const parameters = {
      MobileOS: "WIN",
      MobileApp: "EnjoyTrip",
      keyword,
      areaCode,
      sigunguCode,
      contentTypeId: activeTheme.value.id,
      _type: "json",
      pageNo: 1,
      numOfRows: 10,
      serviceKey: SERVICE_KEY,
    };
    const options = {
      methods: "get",
      url: "https://apis.data.go.kr/B551011/KorService1/searchKeyword1",
      params: parameters,
    };
    const res = await axios(options);
    console.log(res);
    reset();
    places.value.push(...res.data.response.body.items.item);

    return res.data.response.body.items.item;
  }
  // 끝 - 키워드 검색
  // ====================================================

  // ====================================================
  // 시작 - 내장소 영역
  /** 내장소 리스트 가져오기 함수
   */
  function getmyPlaceList() {
    // axios로 내가 저장한 장소 리스트 받아올 예정
    const items = [{}];
    return items;
  }
  // 끝 - 내장소 영역
  // ====================================================

  // ====================================================
  // 시작 - 마커 생성하기

  /** 마커 생성 메소드
   * 1. 커스텀 마커 이미지를 가져와 커스텀 마커 객체를 만든뒤 화면에 표시한다.
   * 2. 커스텀 오버레이를 생성해(view파일) 마커 객체와 겹치도록 화면에 표시한다.
   * 3. 마커 클릭 이벤트를 등록한다.
   * @param {Array} nextPlaces
   */
  function makeMarkers(nextPlaces) {
    console.log(nextPlaces);
    // 커스텀 마커 이미지 생성
    const imageSize = new kakao.maps.Size(22, 36);
    const imageSrc = require(`@/assets/image/tour/pointer.svg`);
    const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커 객체 생성
    for (let now of nextPlaces) {
      console.log(now);
      const position = new kakao.maps.LatLng(now.mapy, now.mapx);

      // 마커 생성
      const marker = new kakao.maps.Marker({
        position,
        image: markerImage,
        clickable: true,
      }); // 커스텀 마커
      // clickable: 마커 클릭이벤트용 옵션
      markers.value.push(marker);
      marker.setMap(map.value);

      // 커스텀 오버레이
      const overlayContent = document.createElement("div");
      const customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: overlayContent,
        xAnchor: 0,
        yAnchor: 1.3,
      });
      const app = createApp({
        render: () =>
          h(OverlayComp, {
            // props
            title: now.title,
            imageName: activeTheme.value.image,
            contentid: now.contentid,
          }),
      });
      app.mount(overlayContent);
      overlays.value.push(customOverlay);
      customOverlay.setMap(map.value);

      // 마커 클릭이벤트 등록
      kakao.maps.event.addListener(marker, "click", function () {
        goToDetail(now.contentid);
      });
    }
  }
  // 끝 - 마커 생성하기
  // ====================================================

  // ====================================================
  // 시작 - 디테일 정보 가져오기
  /** id에 따라 상세 정보 가져오기 함수
   * pinia의 detailPlace에 가져온 장소 정보를 저장한다.
   * @param {String} id attraction 아이디
   */
  async function getDetail(id) {
    const parameters = {
      MobileOS: "WIN",
      MobileApp: "EnjoyTrip",
      contentId: id,
      _type: "json",
      serviceKey: SERVICE_KEY,
      defaultYN: "Y",
      firstImageYN: "Y",
      areacodeYN: "Y",
      addrinfoYN: "Y",
      mapinfoYN: "Y",
      overviewYN: "Y",
    };
    const options = {
      methods: "get",
      url: "https://apis.data.go.kr/B551011/KorService1/detailCommon1",
      params: parameters,
    };
    const res = await axios(options);
    // console.log("결과물:", res.data.response.body.items.item[0]);
    detailPlace.value = res.data.response.body.items.item[0];
    console.log("디테일", detailPlace.value);
  }
  /** 상세 정보 페이지로 이동하기
   * 1. pinia의 activePlace를 현재 장소의 id로 바꾼다. (activatePlace)
   * 2. pinia의 detailPlace에 id값을 이용해 정보를 저장한다. (getDetail(id))
   * 3. activePlace의 id를 기준으로 추천검색, 추천명소, 여행경로 페이지로 라우팅한다.
   */
  async function goToDetail(id) {
    activatePlace(id);
    await getDetail(id);
    console.log(activePlace);
    if (activePlace.value > 10) {
      // 일반 검색
      router.push({ name: "searchDetail", params: { id } });
    } else if (activePlace.value == 1) {
      // 추천명소
      router.push({ name: "myPlaceDetail", params: { id } });
    } else if (activePlace.value == 2) {
      // 여행 경로
      router.push({ name: "planDetail", params: { id } });
    }
  }
  // 끝 - 디테일 정보 가져오기
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
    activePlace,
    activatePlace,
    deactivatePlace,
    hideDetail,
    toggleHideDetail,
    getDetail,
    detailPlace,
    goToDetail,
    hideList,
    toggleHideList,
    getSido,
    getGugun,
    sido,
    gugun,
    getKeywordResult,
  };
});
