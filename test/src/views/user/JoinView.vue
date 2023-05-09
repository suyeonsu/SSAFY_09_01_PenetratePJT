<template>
  <div class="join">
    <div class="container">
      <ProjectIconCompVue class="logo" />
      <form class="join-form" @submit.prevent="submitHandler(event)">
        <InputCompVue
          required
          @changeValue="setUserId"
          :errorMsg="duplicateError"
          >아이디</InputCompVue
        >
        <!-- <div class="inspection">
          아이디 중복 여부:
          <span :class="{ red: duplicate, green: !duplicate }">
            {{ duplicateMessage }}
          </span>
        </div> -->
        <InputCompVue required @changeValue="setUserName">이름</InputCompVue>
        <InputCompVue type="password" required @changeValue="setUserPassword"
          >비밀번호</InputCompVue
        >
        <InputCompVue
          type="password"
          required
          @changeValue="setConfirmPassword"
          :errorMsg="samePasswordError"
          >비밀번호 확인</InputCompVue
        >
        <!-- <div class="inspection">
          비밀번호 중복 여부:
          <span :class="{ red: !samePassword, green: samePassword }">
            {{ samePasswordMessage }}
          </span>
        </div> -->

        <button class="button">회원 가입</button>
      </form>
    </div>
  </div>
</template>

<script>
import InputCompVue from "@/components/InputComp.vue";
import ProjectIconCompVue from "@/components/ProjectIconComp.vue";
export default {
  components: {
    InputCompVue,
    ProjectIconCompVue,
  },
  data() {
    return {
      duplicateError: "",
      samePasswordError: "",
      user: {
        id: "",
        name: "",
        password: "",
      },
      confirmPassword: "",
    };
  },
  watch: {
    "user.id"(now) {
      // 여기에 아이디 중복 여부 api 넣기
      console.log(now);
      this.duplicateError = "아이디가 중복되었습니다.";
    },
    confirmPassword(now) {
      this.samePasswordError =
        now != this.user.password ? "비밀번호와 일치하지 않습니다." : "";
    },
  },
  methods: {
    setUserId(data) {
      this.user.id = data;
    },
    setUserName(data) {
      this.user.name = data;
    },
    setUserPassword(data) {
      this.user.password = data;
    },
    setConfirmPassword(data) {
      this.confirmPassword = data;
    },
    submitHandler() {
      console.log(this.user);
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
  .container {
    width: 40%;
    // background-color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .logo {
      margin-bottom: 1rem;
    }
    .join-form {
      width: 80%;
      * {
        margin-top: 1.1rem;
      }
      .inspection {
        display: block;
        .green {
          color: green;
        }
        .red {
          color: $warning;
        }
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
      }
    }
  }
}
</style>
