import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useBoardStore = defineStore("board", () => {
  // 시작 - 게시글 리스트 및 pagenation 관련
  const postList = ref([]); // 게시글 리스트
  /** 게시글 리스트 데이터 받아오기 by API
   * pinia의 postList 배열에 데이터를 받아온다.
   */
  function getPostListData(currentPageNo) {
    console.log("현재 페이지 :", currentPageNo);
    postList.value = [
      {
        articleno: 123 + currentPageNo,
        userid: "유저아이디1",
        subject: "제목1",
        hit: 283,
        regtime: new Date("2023-05-19T10:30:00Z"),
        content: "컨텐츠입니다. 123",
      },
      {
        articleno: 125 + currentPageNo,
        userid: "유저아이디2",
        subject: "제목2",
        hit: "282",
        regtime: new Date("2023-05-20T10:00:00Z"),
        content: "컨텐츠입니다. 125",
      },
      {
        articleno: 124 + currentPageNo,
        userid: "유저아이디3",
        subject: "제목3",
        hit: "281",
        regtime: new Date("2023-05-19T10:10:00Z"),
        content: "컨텐츠입니다. 124",
      },
      {
        articleno: 129 + currentPageNo,
        userid: "ssafy",
        subject: "제목4",
        hit: "280",
        regtime: new Date("2023-05-19T10:00:00Z"),
        content: "컨텐츠입니다. 129",
      },
    ];
    totalListLength.value = 1101; // 전체 게시글 개수
  }

  const totalListLength = ref(0); // 전체 리스트 개수
  // 끝 - 게시글 리스트 및 pagenation 관련

  // 시작 - 게시글 관련
  const nowPost = ref({});
  /** 게시글 상세 정보를 가져오는 함수 */
  function getPostingData(articleno) {
    nowPost.value = {
      articleno,
      userid: "ssafy",
      subject: `제목${articleno}`,
      hit: "280",
      regtime: new Date("2023-05-19T10:00:00Z"),
      content: "콘텐츠입니다.",
    };
    return nowPost.value;
  }
  // 끝 - 게시글 관련

  // 시작 - 공통 함수
  const today = ref("");
  /** 현재 날짜를 String으로 지정하는 함수.
   * 예시) "2023. 5. 20."
   */
  function setToday() {
    const now = new Date();
    today.value = new Intl.DateTimeFormat("ko-KR").format(now);
  }
  /** Date 객체에서 날짜 String을 반환하는 함수
   * 현재 날짜와 불일치 -> "YYYY. D. MM."
   * 현재 날짜와 일치 -> "H:MM:SS PM"
   * @param {Date} time
   * @returns {String} 날짜정보
   */
  function getTimeFormat(time) {
    const dateString = new Intl.DateTimeFormat("ko-KR").format(time);
    if (dateString === today.value) {
      return time.toLocaleTimeString("en-US");
    }
    return dateString;
  }
  // 끝 - 공통 함수

  return {
    postList,
    getPostListData,
    getPostingData,
    nowPost,
    setToday,
    getTimeFormat,
    totalListLength,
  };
});
