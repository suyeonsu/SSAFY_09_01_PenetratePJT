<template>
  <div class="login">
    <div class="container">
      <ProjectIconCompVue class="logo" />
      <form class="login-form" @submit.prevent="submitHandler">
        <InputCompVue required @changeValue="mm">아이디</InputCompVue>
        <InputCompVue required type="password" @changeValue="mm"
          >비밀번호</InputCompVue
        >
        <label class="checkbox">
          <input type="checkbox" />
          로그인 상태 유지
        </label>
        <button class="main-button">로그인</button>
        <div class="sub-menu">
          <div class="item">아이디 찾기</div>
          <div class="item">비밀번호 찾기</div>
          <router-link class="item" :to="{ name: 'join' }"
            >회원 가입</router-link
          >
        </div>
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
  methods: {
    mm(value) {
      console.log("신기", value);
    },
    submitHandler() {
      console.log("서브밋");
    },
  },
};
</script>

<style lang="scss" scoped>
.login {
  height: 90vh;
  display: flex;
  justify-content: center;
  background-image: url("@/assets/image/login.jpg");
  background-size: cover;
  .container {
    width: 40%;
    background-color: white;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .logo {
      margin-bottom: 3rem;
    }
    .login-form {
      width: 80%;
      box-sizing: border-box;
      padding: 1rem;
      .checkbox {
        display: block;
        margin: 0.5rem 0;
      }
      .main-button {
        display: flex;
        width: 100%;
        border: none;
        background-color: $primary;
        font-size: 1.5rem;
        padding: 0.5rem;
        justify-content: center;
        color: white;
        font-weight: bold;
        cursor: pointer;
      }
      .sub-menu {
        display: flex;
        .item {
          width: 50%;
          display: flex;
          justify-content: center;
          margin: 1rem 0;
          border-right: 1px solid lightgray;
          cursor: pointer;
          color: black;
          text-decoration: none;
          &:last-child {
            border-right: none;
          }
        }
      }
    }
  }
}
</style>
