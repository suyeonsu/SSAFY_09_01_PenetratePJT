import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore(
  "user",
  () => {
    const userInfo = ref({
      id: "",
      name: "",
    });
    const accessToken = ref("");

    function getCookies(cName) {
      // 쿠키 가져오기.
      const cookies = document.cookie.split("; ").reduce((acc, cookie) => {
        const [key, value] = cookie.split("=");
        acc[key] = decodeURIComponent(value);
        return acc;
      }, {});
      return cookies[cName];
    }
    function removeCookie(cName) {
      //쿠키 삭제하기
      const nowDate = new Date();
      document.cookie = `${cName}=; ${nowDate}; path=/;`;
    }

    return {
      userInfo,
      accessToken,
      getCookies,
      removeCookie,
    };
  },
  {
    persist: true,
  }
);
