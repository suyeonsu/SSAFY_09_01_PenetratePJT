import { defineStore } from "pinia";
import { createApp, h, inject, ref } from "vue";
import axios from "axios";
import router from "@/router";
import OverlayComp from "@/components/OverlayComp.vue";
import { useUserStore } from "./userStore";

/* global kakao */
export const useTourStore = defineStore(
  "tour",
  () => {
    // ====================================================
    // 전역변수영역
    const map = ref(null); // 지도 객체
    const places = ref([]); // 현재 위치 기반 장소 정보
    const markers = ref([]); // 장소에 따른 마커
    const overlays = ref([]); // 장소에 따른 오버레이
    const activeTheme = ref({}); // 활성화 된 테마
    const activePlace = ref(""); // 활성화 된 장소
    const hideList = ref(true); // 장소 리스트 숨기기
    const hideDetail = ref(true); // 상세 페이지 숨기기
    const detailPlace = ref({}); // 상세 페이지 요소
    const centerLat = ref(0); // 표시 기준 위도
    const centerLon = ref(0); // 표시 기준 경도
    const searchPageNo = ref(1); // 검색 페이지

    const DOMAIN_URL = "http://localhost:9000/group5";
    const sido = ref([]); // 시도 배열
    const gugun = ref([]); // 구군 배열
    const selectedSido = ref(0); // 선택된 시도
    const selectedGugun = ref(0); // 선택된 구군
    const selectedSort = ref("readcount"); // 정렬 기준
    const keywords = ref(""); // 검색어
    const userStore = useUserStore();

    function $reset() {
      map.value = null; // 지도 객체
      places.value = []; // 현재 위치 기반 장소 정보
      markers.value = []; // 장소에 따른 마커
      overlays.value = []; // 장소에 따른 오버레이
      activeTheme.value = {}; // 활성화 된 테마
      activePlace.value = ""; // 활성화 된 장소
      hideList.value = true; // 장소 리스트 숨기기
      hideDetail.value = true; // 상세 페이지 숨기기
      // detailPlace.value = {}; // 상세 페이지 요소
      searchPageNo.value = 1;
      centerLat.value = 37.498004414546934;
      centerLon.value = 127.02770621963765;
      selectedSido.value = 0;
      selectedGugun.value = 0;
      selectedSort.value = "readcount";
      keywords.value = "";
    }

    const router = inject("router"); // router 사용.
    // ====================================================

    // ====================================================
    // 시작 - 초기 세팅
    /** 지도를 불러오는 함수
     * @param {지도요소} container
     */
    async function initMap(container) {
      // 지도 옵션
      const options = {
        center: new kakao.maps.LatLng(centerLat.value, centerLon.value), //지도의 중심좌표.
        level: 4, //지도의 레벨(확대, 축소 정도)
      };
      // 지도 생성
      map.value = new kakao.maps.Map(container, options);
      moveToCurrent(); // 현재 접속 위치로 이동.

      // 지도 컨트롤러 - 지도 타입 (일반 지도, 스카이뷰)
      const mapTypeControl = new kakao.maps.MapTypeControl();
      map.value.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT); // 우상단에 컨트롤러 추가.

      // 지도 컨트롤러 - 줌
      map.value.setMinLevel(2); // 최대 확대 한계
      map.value.setMaxLevel(14); // 최소 축소 한계
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
      activeTheme.value = nextTheme;
      console.log("액티브 테마: ", activeTheme.value);
      resetMarker();
      return await infinityScroll();
    }
    /** 인피니티 스크롤
     * 한번 호출될 때 마다 다음 페이지의 내용을 가져온다.
     */
    async function infinityScroll() {
      if (activeTheme.value.id > 10) {
        // 일반 테마일 경우
        return await getPlaceInfo(map.value.getCenter());
      } else {
        // 다른 서브페이지로 이동하는 경우(추천명소)
        return await getBookmarkList();
      }
    }
    /** 투어 페이지 리셋하기
     * 1. 기존의 마커 및 오버레이 제거
     * 2. 현재 active 된 장소 해제.
     */
    function resetMarker() {
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
      searchPageNo.value = 1;
    }
    // 끝 - 테마 변경
    // ====================================================

    // ====================================================
    // 시작 - 현재 위치 기반 장소 정보 받아오기
    /** 현재 위치 기반 장소 정보 요청하기
     * @param {kakao.map.center} center 카카오 맵 중심 객체
     * @returns {Promise<Object[]>} 장소 객체 배열
     */
    async function getPlaceInfo(center) {
      try {
        const url = `${DOMAIN_URL}/trip`;
        const params = {
          curlatitude: center.Ma, // 중심 lat
          curlongitude: center.La, // 중심 lon
          gugun: selectedGugun.value,
          sido: selectedSido.value,
          keywords: keywords.value.replaceAll(" ", ","),
          pageNum: searchPageNo.value++,
          pageSize: 10,
          sort: selectedSort.value,
          type: activeTheme.value.id,
        };

        const options = {
          method: "get",
          url,
          params,
        };
        console.log("파라미터:", params);
        const res = await axios(options);
        // console.log("결과?", res);
        // console.log(...res.data.list);
        if (res.data.list != null) {
          places.value.push(...res.data.list);
          return res.data.list;
        }
        return null;
      } catch (error) {
        console.log("장소 불러오기 에러:", error);
        throw error;
      }
    }
    // 끝 - 현재 위치 기반 장소 정보 받아오기
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
        console.log("현재장소: ", now);
        const position = new kakao.maps.LatLng(now.latitude, now.longitude);

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
        // 커스텀 오버레이 SFC
        const app = createApp({
          render: () =>
            h(OverlayComp, {
              // props
              title: now.title,
              imageName: activeTheme.value.image,
              contentId: now.contentId,
            }),
        });
        app.mount(overlayContent);
        overlays.value.push(customOverlay);
        customOverlay.setMap(map.value);

        // 마커 클릭이벤트 등록
        overlayContent.addEventListener("click", () => {
          // console.log("클릭");
          focusHandler(marker, customOverlay);

          // movePosition(now.mapy, now.mapx);
          goToDetail(now.contentId);
        });
        overlayContent.addEventListener("mouseover", () => {
          // console.log("마우스오버");
          focusHandler(marker, customOverlay);
        });
        overlayContent.addEventListener("mouseout", () => {
          // console.log("마우스아웃");
          blurHandler(marker, customOverlay, now.contentId);
        });
      }
    }
    /** 마커, 오버레이 포커스 이벤트 핸들러 */
    function focusHandler(marker, customOverlay) {
      marker.setZIndex(999);
      customOverlay.setZIndex(1000);
    }
    /** 마커, 오버레이 포커스 해제 이벤트 핸들러 */
    function blurHandler(marker, customOverlay, targetId) {
      if (targetId !== activePlace.value) {
        marker.setZIndex(0);
        customOverlay.setZIndex(0);
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
      const url = `${DOMAIN_URL}/trip/detail`;
      const params = {
        attractionId: id,
        userId: userStore.userInfo.id,
      };
      const options = {
        method: "get",
        url,
        params,
      };
      console.log("장소디테일 옵션:", options);
      const res = await axios(options);
      detailPlace.value = res.data;
      console.log("장소디테일:", res.data);
      movePosition(
        detailPlace.value.attraction.latitude,
        detailPlace.value.attraction.longitude
      );
    }
    /** 상세 정보 페이지로 이동하기
     * 1. pinia의 activePlace를 현재 장소의 id로 바꾼다. (activatePlace)
     * 2. pinia의 detailPlace에 id값을 이용해 정보를 저장한다. (getDetail(id))
     * 3. activePlace의 id를 기준으로 추천검색, 추천명소, 여행경로 페이지로 라우팅한다.
     */
    async function goToDetail(id) {
      activatePlace(id);
      await getDetail(id);
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
    // ====================================================
    // 시작 - 디테일 페이지 함수

    /** 별점 주기
     *
     * @param {*} attractionId
     * @param {*} star
     */
    async function rating(attractionId, star) {
      try {
        const url = `${DOMAIN_URL}/rate`;
        const params = {
          attractionId,
          star,
          userId: userStore.userInfo.id,
        };
        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "post",
          url,
          params,
        };
        const res = await axios(options);

        alert("별점주기 성공");
      } catch (error) {
        console.log("별점주기 에러");
        throw error;
      }
    }

    // 끝 - 디테일 페이지 함수
    // ====================================================
    // ====================================================
    // 시작 - 북마크
    const ACCESS_TOKEN = `Bearer ${userStore.accessToken}`;
    async function getBookmarkList() {
      try {
        const url = `${DOMAIN_URL}/myplace`;
        const params = {
          pageNum: searchPageNo.value++,
          pageSize: 10,
          userid: userStore.userInfo.id,
        };
        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "get",
          url,
          params,
        };

        console.log("북마크 목록 옵션: ", options);
        const res = await axios(options);
        if (res.data.list != null) {
          places.value.push(...res.data.list);
          return res.data.list;
        }
        console.log("북마크 목록 성공", res);
        return null;
      } catch (error) {
        console.log("북마크 목록 에러");
        throw error;
      }
    }
    async function putBookmark(attractionId) {
      try {
        const url = `${DOMAIN_URL}/myplace`;
        const params = {
          attractionId,
          userId: userStore.userInfo.id,
        };
        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "post",
          url,
          params,
        };
        console.log("북마크 등록 옵션: ", options);
        const res = await axios(options);
        console.log("북마크 등록 성공", res);
      } catch (error) {
        console.log("북마크 등록 에러");
        throw error;
      }
    }

    async function deleteBookmark(attractionId) {
      try {
        const url = `${DOMAIN_URL}/myplace`;
        const params = {
          attractionId,
          userId: userStore.userInfo.id,
        };
        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "delete",
          url,
          params,
        };

        console.log("북마크 취소 옵션: ", options);
        const res = await axios(options);
        console.log("북마크 취소 성공", res);
      } catch (error) {
        console.log("북마크 취소 에러");
        throw error;
      }
    }
    // 끝 - 북마크
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
    /** 상세 정보 페이지 열기 */
    function activatePlace(id) {
      activePlace.value = id;
      hideDetail.value = false;
    }
    /** 상세 정보 페이지 닫기 */
    function deactivatePlace() {
      activePlace.value = "";
      hideDetail.value = true;
    }
    // 끝 - 사이드 바 토글
    // ====================================================

    // ====================================================
    // 시작 - 일반 지도 함수
    /** 지도의 기준 좌표를 계산해 지정하는 함수
     * hideList, hideDetail의 값에 따라 중심 좌표가 달라진다.
     */
    function getMapCenter(lat, lon) {
      // 화면정보
      const bounds = map.value.getBounds();
      console.log(bounds);
      // 화면너비
      const width = bounds.oa - bounds.ha;
      // hide 정도에 따른 위치 조정

      let constant = 0.15;
      if (hideList.value && hideDetail.value) constant = 0;
      else if (!hideList.value && !hideDetail.value) constant = 0.3;
      const rightOffset = width * constant;
      centerLat.value = lat;
      centerLon.value = lon - rightOffset;
    }

    /** 지도를 부드럽게 이동시키는 메소드
     * @param {Number} lat 위도 좌표
     * @param {Number} lon 경도 좌표
     */
    function movePosition(lat, lon) {
      // 해당 위치로 맵을 부드럽게 이동한다.
      getMapCenter(lat, lon);
      const moveLatLon = new kakao.maps.LatLng(
        centerLat.value,
        centerLon.value
      );
      map.value.panTo(moveLatLon);
    }

    /** 지도의 중심좌표를 현재 위치로 이동시키는 메소드 */
    function moveToCurrent() {
      // 현재 위치로 이동하기
      navigator.geolocation.getCurrentPosition(
        (res) => {
          // 성공시 메소드
          const crd = res.coords;
          movePosition(crd.latitude, crd.longitude);
        },
        (err) => {
          // 실패시 메소드
          console.log(err);
        }
      );
    }
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
        serviceKey: process.env.VUE_APP_SERVICE_KEY,
      };
      const options = {
        method: "get",
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
      console.log(sido);
      gugun.value.length = 0;
      if (sido === "") return;
      const parameters = {
        MobileOS: "WIN",
        MobileApp: "EnjoyTrip",
        _type: "json",
        pageNo: 1,
        numOfRows: 100,
        areaCode: sido,
        serviceKey: process.env.VUE_APP_SERVICE_KEY,
      };
      const options = {
        method: "get",
        url: "https://apis.data.go.kr/B551011/KorService1/areaCode1",
        params: parameters,
      };

      const res = await axios(options);
      // console.log(res);
      gugun.value.push(...res.data.response.body.items.item);
    }
    // 끝 - 일반 지도 함수
    // ====================================================

    return {
      $reset,
      initMap,
      moveToCurrent,
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
      infinityScroll,
      selectedSido,
      selectedGugun,
      selectedSort,
      keywords,
      rating,
      getBookmarkList,
      putBookmark,
      deleteBookmark,
      resetMarker,
    };
  },
  {
    persist: true,
  }
);
