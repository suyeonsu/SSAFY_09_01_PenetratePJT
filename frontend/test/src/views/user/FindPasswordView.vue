<template>
  <div class="join">
    <div class="container">
      <ProjectIconCompVue class="logo" />
      <form class="join-form" @submit.prevent="join">
        <InputCompVue
          class="verify-input"
          type="email"
          required
          :disabled="emailConfirmed"
          @changeValue="setEmail"
          >이메일</InputCompVue
        >
        <button :disabled="isError" class="button">
          {{ isError ? "이메일을 입력해 활성화하기" : "비밀번호 찾기" }}
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
      user: {
        email: "",
      },
      isError: true, // 에러 발생 시 회원가입 버튼 비활성화
    };
  },
  setup() {
    const userStore = useUserStore();
    return {
      userStore,
    };
  },
  watch: {
    "user.email"(now) {
      if (now != "") this.isError = false;
      else this.isError = true;
    },
  },
  methods: {
    setEmail(data) {
      this.user.email = data;
    },

    /**이메일 인증요청 */
    async requestVerifyEmail() {
      if (!this.user.email.includes("@")) {
        alert("🙏이메일을 확인해주세요🙏");
        return;
      }
      try {
        const res = await this.userStore.requestVerifyEmail(
          this.user.email,
          "findpw"
        );
        if (res) {
          alert("임시 비밀번호를 이메일로 발송했습니다.");
        }
      } catch (error) {
        alert("임시 비밀번호 발급 실패: ", error);
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
