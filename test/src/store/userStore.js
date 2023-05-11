import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
  // data
  state: () => ({
    userInfo: {
      // 유저 정보
      id: "",
      name: "노호종",
    },
    accessToken: "",
  }),
  //computed
  getters: {},
  //methods
  actions: {
    getCookies(cName) {
      // 쿠키 가져오기.
      const cookies = document.cookie.split("; ").reduce((acc, cookie) => {
        const [key, value] = cookie.split("=");
        acc[key] = decodeURIComponent(value);
        return acc;
      }, {});
      return cookies[cName];
    },
    removeCookie(cName) {
      //쿠키 삭제하기
      const nowDate = new Date();
      document.cookie = `${cName}=; ${nowDate}; path=/;`;
    },
  },
});
