import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore(
  "user",
  () => {
    const DOMAIN_URL = "http://localhost:9000/group5";
    const userInfo = ref({
      id: "",
      name: "",
      email: "",
    });
    const accessToken = ref("");
    const isRemember = ref(false);

    function $reset() {
      userInfo.value = {
        id: "",
        name: "",
        email: "",
      };
      accessToken.value = "";
    }

    /** 아이디 및 이메일 중복 체크
     * @param {String} key 아이디: userid, 이메일: email
     * @param {String} val 사용자 입력값
     * @returns message: "duplicated"/"valid"
     */
    async function checkValidKey(key, val) {
      try {
        const url = `${DOMAIN_URL}/auth/check`;
        const params = {
          key,
          val,
        };
        const options = {
          method: "get",
          url,
          params,
        };
        const res = await axios(options);
        console.log("중복체크 결과: ", res);
        return res.data.message;
      } catch (error) {
        console.log("아이디 중복체크 오류:", error);
        throw error;
      }
    }

    /** 이메일 주소로 인증요청 보내기
     * @param {String} email 이메일 주소
     * @param {String} type 이메일 인증[register] / 임시 비밀번호 [findpw]
     * @returns {Boolean} 요청 성공 여부
     */

    const verifyCode = ref("");
    async function requestVerifyEmail(email, type) {
      try {
        const url = `${DOMAIN_URL}/auth/sendmail`;
        const data = {
          type,
          email,
        };
        const options = {
          headers: {
            "Content-Type": "application/json",
          },
          method: "post",
          url,
          data,
        };
        const res = await axios(options);
        console.log("이메일 인증요청 결과: ", res);
        verifyCode.value = res.data.code;
        return true;
      } catch (error) {
        console.log("이메일 인증요청 오류:", error);
        throw error;
      }
    }

    /** 이메일 주소 인증코드 보내기 */
    async function sendVerifyCode(code) {
      if (code == verifyCode.value) {
        return true;
      }
      return false;
    }

    /**회원가입
     */
    async function join(user) {
      try {
        const url = `${DOMAIN_URL}/auth/signup`;
        const data = {
          email: user.email,
          userid: user.id,
          username: user.name,
          userpwd: user.password,
          joindate: null,
        };
        const options = {
          headers: {
            "Content-Type": "application/json",
          },
          method: "post",
          url,
          data,
        };
        const res = await axios(options);
        console.log("회원가입 결과:", res);
        return true;
      } catch (error) {
        console.log("회원가입 오류:", error);
        throw error;
      }
    }

    /** 로그인
     * @param {Object} user
     * @returns {String} JWT 토큰
     */
    async function login(user) {
      try {
        const url = `${DOMAIN_URL}/auth/login`;
        const data = {
          email: null,
          userid: user.id,
          username: null,
          userpwd: user.password,
          joindate: null,
        };

        const options = {
          headers: {
            "Content-Type": "application/json",
          },
          method: "post",
          url,
          data,
        };
        const res = await axios(options);
        console.log("로그인 결과:", res);
        return res.data.token;
      } catch (error) {
        console.log("로그인 오류:", error);
        throw error;
      }
    }

    /** 유저 이름 변경하기
     * @param {String} nextName 유저이름
     */
    async function editUserName(nextName) {
      try {
        const url = `${DOMAIN_URL}/auth`;
        const ACCESS_TOKEN = `Bearer ${accessToken.value}`;

        const data = {
          userid: userInfo.value.id,
          username: nextName,
          email: userInfo.value.email,
          userpwd: "",
          joindate: "",
        };

        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "put",
          url,
          data,
        };
        console.log("유저 정보 업데이트 옵션:", options);
        const res = await axios(options);
        console.log("유저 정보 업데이트 성공:", res);
        if (res.data.token != null) {
          accessToken.value = res.data.token;
          return true;
        }
        return false;
      } catch (error) {
        console.log("유저 정보 업데이트 실패:", error);
        throw error;
      }
    }

    /** 비밀번호 변경 함수
     *
     * @param {String} nextPassword 새 비밀번호
     */
    async function editUserPassword(curpwd, newpwd) {
      try {
        const url = `${DOMAIN_URL}/auth/updatepw`;
        const ACCESS_TOKEN = `Bearer ${accessToken.value}`;

        const data = {
          userid: userInfo.value.id,
          username: userInfo.value.name,
          email: userInfo.value.email,
          newpwd,
          curpwd,
          joindate: "",
        };

        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "put",
          url,
          data,
        };

        console.log("비밀번호 업데이트 옵션:", options);
        const res = await axios(options);
        console.log("비밀번호 업데이트 성공:", res);
        if (res.data != null) {
          return true;
        }
        return false;
      } catch (error) {
        console.log("비밀번호 업데이트 실패:", error);
        throw error;
      }
    }

    async function deleteUser(userpwd) {
      try {
        const url = `${DOMAIN_URL}/auth`;
        const ACCESS_TOKEN = `Bearer ${accessToken.value}`;

        const data = {
          userid: userInfo.value.id,
          username: userInfo.value.name,
          email: userInfo.value.email,
          userpwd,
          joindate: "",
        };

        const options = {
          headers: {
            Authorization: ACCESS_TOKEN,
          },
          method: "delete",
          url,
          data,
        };

        console.log("회원탈퇴 옵션:", options);
        const res = await axios(options);
        console.log(res);
        if (res.data == 200) {
          console.log("회원탈퇴 성공:", res);
          return true;
        }
        return false;
      } catch (error) {
        console.log("회원탈퇴 실패:", error);
        throw error;
      }
    }

    return {
      userInfo,
      accessToken,
      checkValidKey,
      requestVerifyEmail,
      sendVerifyCode,
      join,
      login,
      isRemember,
      $reset,
      editUserName,
      editUserPassword,
      deleteUser,
    };
  },
  {
    persist: true,
  }
);
