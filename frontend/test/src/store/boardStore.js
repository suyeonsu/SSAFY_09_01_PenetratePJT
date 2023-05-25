import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
import { useUserStore } from "./userStore";

export const useBoardStore = defineStore(
  "board",
  () => {
    // 시작 - 게시글 리스트 및 pagenation 관련
    const userStore = useUserStore();
    const DOMAIN_URL = "http://localhost:9000/group5";

    const postList = ref([]); // 게시글 리스트
    const pageSize = ref(10); // 한 페이지당 아이템 개수
    const sort = ref("regtime"); // 정렬기준
    const filter = ref("subject"); // 검색기준
    const keywords = ref(""); // 키워드

    const pagenationInfo = ref({
      pageNum: 1, // 현재 페이지 번호
      pages: 9, // 전체 페이지의 개수
      navigatepageNums: [1, 2, 3, 4, 5, 6, 7, 8],
      navigateFirstPage: 1,
      navigateLastPage: 8,
    });

    function resetDetail() {
      nowPost.value = {
        articleno: 0,
        userid: userStore.userInfo.id,
        subject: "",
        content: "",
        hit: 0,
        regtime: "",
      };
    }

    async function myRecentList(userid) {
      try {
        const ACCESS_TOKEN = `Bearer ${userStore.accessToken}`;

        const params = {
          pageNum: 1,
          keywords: userid,
          filter: "userid",
          sort: "regtime",
          pageSize: 10,
        };

        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "get",
          url: `${DOMAIN_URL}/board`,
          params,
        };

        const res = await axios(options);
        console.log("페이지 로딩 성공:", res);
        return res.data.list;
      } catch (error) {
        alert("페이지 로딩 실패:", error);
      }
    }

    /** 게시글 리스트 데이터 받아오기 by API
     * pinia의 postList 배열에 데이터를 받아온다.
     */
    async function loadFreeBoardList(pageNum) {
      try {
        console.log("현재 페이지 :", pageNum);
        const ACCESS_TOKEN = `Bearer ${userStore.accessToken}`;
        console.log(ACCESS_TOKEN);

        const params = {
          pageNum,
          keywords: keywords.value.replaceAll(" ", ","),
          filter: filter.value,
          sort: sort.value,
          pageSize: pageSize.value,
        };

        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "get",
          url: `${DOMAIN_URL}/board`,
          params,
        };

        const res = await axios(options);
        postList.value = res.data.list;
        pagenationInfo.value.pageNum = res.data.pageNum;
        pagenationInfo.value.pages = res.data.pages;
        pagenationInfo.value.navigatepageNums = res.data.navigatepageNums;
        pagenationInfo.value.navigateFirstPage = res.data.navigateFirstPage;
        pagenationInfo.value.navigateLastPage = res.data.navigateLastPage;
        console.log("페이지 로딩 성공:", res);
      } catch (error) {
        alert("페이지 로딩 실패:", error);
      }
    }

    // 끝 - 게시글 리스트 및 pagenation 관련

    // 시작 - 게시글 관련
    const nowPost = ref({});
    /** 게시글 상세 정보 함수 */
    async function loadFreeBoardDetail(articleno) {
      try {
        const ACCESS_TOKEN = `Bearer ${userStore.accessToken}`;
        // console.log(ACCESS_TOKEN);

        // const parameters = {
        //   articleno
        // };

        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "get",
          url: `${DOMAIN_URL}/board/${articleno}`,
        };

        const res = await axios(options);
        console.log("페이지 로딩 성공:", res);
        nowPost.value = res.data;
        return nowPost.value;
      } catch (error) {
        alert("페이지 로딩 실패:", error);
      }
    }
    // 끝 - 게시글 관련

    /** 게시글 삭제 요청
     *
     * @param {*} articleno
     */
    async function deleteArticle(articleno) {
      try {
        const ACCESS_TOKEN = `Bearer ${userStore.accessToken}`;
        // console.log(ACCESS_TOKEN);

        const params = {
          articleno,
        };

        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "delete",
          url: `${DOMAIN_URL}/board`,
          params,
        };

        const res = await axios(options);
        console.log("페이지 삭제 성공:", res);
        resetDetail();
      } catch (error) {
        alert("페이지 삭제 실패:", error);
      }
    }

    /** 게시글 수정 요청
     */
    async function editArticle() {
      try {
        const ACCESS_TOKEN = `Bearer ${userStore.accessToken}`;
        console.log(nowPost.value);

        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "put",
          url: `${DOMAIN_URL}/board`,
          data: nowPost.value,
        };

        const res = await axios(options);
        console.log("페이지 수정 성공:", res);
      } catch (error) {
        alert("페이지 수정 실패:", error);
      }
    }

    /** 게시글 생성 요청
     */
    async function postArticle() {
      try {
        const ACCESS_TOKEN = `Bearer ${userStore.accessToken}`;
        console.log(nowPost.value);

        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "post",
          url: `${DOMAIN_URL}/board`,
          data: nowPost.value,
        };

        const res = await axios(options);
        console.log("페이지 등록 성공:", res);
      } catch (error) {
        alert("페이지 등록 실패:", error);
      }
    }

    // 시작 - 공통 함수
    const today = ref("");
    /** 현재 날짜를 String으로 지정하는 함수.
     * 예시) "2023. 5. 20."
     */
    function setToday() {
      today.value = new Intl.DateTimeFormat("ko-KR").format(new Date());
    }
    /** Date 객체에서 날짜 String을 반환하는 함수
     * 현재 날짜와 불일치 -> "YYYY. D. MM."
     * 현재 날짜와 일치 -> "H:MM:SS PM"
     * @param {String} time
     * @returns {String} 날짜정보
     */
    function getTimeFormat(time) {
      // console.log("들어오는 날짜:", time);
      const dateObj = new Date(time);
      const dateString = new Intl.DateTimeFormat("ko-KR").format(dateObj);
      // console.log("변환:", dateString);
      if (dateString === today.value) {
        return dateObj.toLocaleTimeString("en-US");
      }
      return dateString;
    }
    // 끝 - 공통 함수

    return {
      postList,
      pageSize,
      sort,
      filter,
      keywords,
      loadFreeBoardList,
      loadFreeBoardDetail,
      nowPost,
      setToday,
      getTimeFormat,
      pagenationInfo,
      deleteArticle,
      resetDetail,
      editArticle,
      postArticle,
      myRecentList,
    };
  },
  {
    persist: true,
  }
);
