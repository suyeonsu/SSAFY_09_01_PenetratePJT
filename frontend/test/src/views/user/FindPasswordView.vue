<template>
  <div class="join">
    <div class="container">
      <ProjectIconCompVue class="logo" />
      <form class="join-form" @submit.prevent="join">
        <div class="verify">
          <InputCompVue
            class="verify-input"
            required
            :disabled="idConfirmed"
            @changeValue="setUserId"
            >아이디</InputCompVue
          >
          <button
            @click.prevent="isValidID"
            :disabled="idConfirmed"
            class="verify-button">
            {{ idConfirmed ? "확정" : "중복체크" }}
          </button>
        </div>
        <div class="verify">
          <InputCompVue
            class="verify-input"
            type="email"
            required
            :disabled="emailConfirmed"
            @changeValue="setEmail"
            >이메일</InputCompVue
          >
          <button
            @click.prevent="requestVerifyEmail"
            :disabled="emailConfirmed"
            class="verify-button">
            {{ emailConfirmed ? "인증됨" : "인증하기" }}
          </button>
        </div>
        <div v-if="emailVerifyTime != 0 && !emailConfirmed" class="verify">
          <InputCompVue
            :disabled="emailVerifyTime == 0 || emailConfirmed"
            class="verify-input"
            type="email"
            required
            @keypress.enter="sendVerifyCode"
            @changeValue="setEmailVerifyCode"
            >인증코드</InputCompVue
          >
          <button
            @click.prevent="sendVerifyCode"
            :disabled="emailVerifyTime == 0 || emailConfirmed"
            class="verify-button">
            입력
          </button>
        </div>
        <div class="timer" v-if="!emailConfirmed">
          {{ emailVerifyTimeMessage }}
        </div>

        <button :disabled="isError" class="button">
          {{ isError ? "전부 입력해 활성화하기" : "회원 가입하기" }}
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import InputCompVue from "@/components/InputComp.vue";
import ProjectIconCompVue from "@/components/ProjectIconComp.vue";
import { useUserStore } from "@/store/userStore";
export default {
  components: {
    InputCompVue,
    ProjectIconCompVue,
  },
  data() {
    return {
      samePasswordError: "",
      user: {
        id: "",
        email: "",
        name: "",
        password: "",
      },
      idConfirmed: false, // 아이디 사용 확정
      emailConfirmed: false, // 이메일 사용 확정
      confirmPassword: "", // 비밀번호 확인
      emailVerifyCode: "", // 이메일 인증 코드
      emailVerifyTime: 0, // 이메일 인증 제한시간 (초)
      timer: null, // 타이머
      emailVerifyTimeMessage: "", // 타이머 남은 시간 문구
      isError: true, // 에러 발생 시 회원가입 버튼 비활성화
      isValidEmail: false, // 이메일 인증 여부
    };
  },
  watch: {
    confirmPassword(now) {
      if (
        now == "" ||
        !this.idConfirmed ||
        !this.emailConfirmed ||
        this.user.name == ""
      )
        this.isError = true;
      else {
        this.samePasswordError =
          now !== this.user.password ? "비밀번호와 일치하지 않습니다." : "";
        this.isError = !!this.samePasswordError;
      }
    },
  },
  setup() {
    const userStore = useUserStore();
    return {
      userStore,
    };
  },
  created() {
    if (this.userStore.userInfo.id != "") {
      this.user.id = this.userStore.userInfo.id;
      this.user.email = this.userStore.userInfo.email;
      this.user.name = this.userStore.userInfo.name;
      this.isEditUser = true;
      this.isValidEmail = true;
    }
  },
  methods: {
    setUserId(data) {
      this.user.id = data;
    },
    setUserName(data) {
      this.user.name = data;
    },
    setEmail(data) {
      this.user.email = data;
    },
    setEmailVerifyCode(data) {
      this.emailVerifyCode = data;
    },
    setUserPassword(data) {
      this.user.password = data;
    },
    setConfirmPassword(data) {
      this.confirmPassword = data;
    },
    /** 아이디 중복체크 */
    async isValidID() {
      if (this.user.id.length == 0) {
        alert("아이디를 입력해주세요");
        return;
      }
      try {
        const res = await this.userStore.checkValidKey("userid", this.user.id);
        console.log(res);
        if (res == "duplicated") {
          alert("아이디가 중복되었습니다. 다른 아이디를 사용해주세요. 🙏");
        } else {
          const ok = confirm("아이디 사용 가능. 아이디를 사용하시겠습니까?");
          if (!ok) {
            return;
          }
          this.idConfirmed = true;
        }
      } catch (error) {
        console.log("아이디 중복체크 오류: ", error);
      }
    },
    /**이메일 인증요청 */
    async requestVerifyEmail() {
      if (!this.user.email.includes("@")) {
        alert("🙏이메일을 확인해주세요🙏");
        return;
      }
      try {
        const dupRes = await this.userStore.checkValidKey(
          "email",
          this.user.email
        );
        if (dupRes == "duplicated") {
          alert("이메일이 중복되었습니다. 다른 이메일을 사용해주세요.");
          return;
        } else {
          const res = await this.userStore.requestVerifyEmail(this.user.email);
          // const res = true;
          if (res) {
            const time = 300;
            this.emailVerifyTime = time; // 이메일 인증 시간 (초)
            this.emailVerifyTimeMessage = `${Math.floor(
              this.emailVerifyTime / 60
            )}분 ${this.emailVerifyTime % 60}초 남았습니다`;
            this.timer = setInterval(() => {
              this.emailVerifyTimeMessage = `${Math.floor(
                (this.emailVerifyTime - 1) / 60
              )}분 ${(this.emailVerifyTime - 1) % 60}초 남았습니다`;
              if (--this.emailVerifyTime <= 0) {
                this.emailVerifyTimeMessage = "";
                clearInterval(this.timer);
              }
            }, 1000);
          }
        }
      } catch (error) {
        this.emailVerifyTimeMessage = "";
        clearInterval(this.timer);
        alert("이메일 인증요청 에러: ", error);
      }
    },
    /** 이메일 인증 확인코드 입력 */
    sendVerifyCode() {
      console.log(this.emailVerifyCode);
      const res = this.userStore.sendVerifyCode(this.emailVerifyCode);
      if (res) {
        alert("인증 성공");
        this.emailVerifyTime = 0;
        this.emailVerifyTimeMessage = "";
        clearInterval(this.timer);
        this.emailConfirmed = true;
      } else {
        alert("인증 실패");
      }
    },
    async join() {
      try {
        console.log("회원가입 데이터:", this.user);
        const res = await this.userStore.join(this.user);
        if (res) {
          alert("회원가입 성공!");
          this.$router.push({ name: "login" });
        } else {
          alert("회원가입 실패");
        }
      } catch (error) {
        alert("회원가입 실패:", error);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.join {
  width: 100%;
  height: 90vh;
  display: flex;
  justify-content: center;
  background-color: #f5f6f7;
  font-family: serif;
  .container {
    width: 40%;
    // background-color: $background;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .logo {
      margin-bottom: 1rem;
    }
    .join-form {
      width: 80%;
      & > * {
        margin-top: 1.1rem;
      }
      .verify {
        display: flex;
        .verify-input {
          width: 80%;
        }
        .verify-button {
          display: flex;
          width: 20%;
          color: $background;
          font-weight: bold;
          box-sizing: border-box;
          background: $primary;
          justify-content: center;
          align-items: center;
          border: 1px solid gray;
          cursor: pointer;
          &:disabled {
            background: gray;
            cursor: auto;
          }
        }
      }
      .timer {
        margin-top: 0;
      }
      .button {
        width: 100%;
        padding: 0.5rem 0;
        font-size: 2rem;
        background-color: $primary;
        border: none;
        color: $background;
        font-weight: bold;
        cursor: pointer;
        transition: 2s;
        &:disabled {
          color: grey;
          background-color: darkgray;
          cursor: auto;
        }
      }
    }
  }
}
</style>
