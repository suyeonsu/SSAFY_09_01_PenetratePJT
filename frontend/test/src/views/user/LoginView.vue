<template>
  <div class="login">
    <div class="container">
      <ProjectIconCompVue class="logo" />
      <form class="login-form" @submit.prevent="login">
        <InputCompVue required @changeValue="setId">아이디</InputCompVue>
        <InputCompVue required type="password" @changeValue="setPassword"
          >비밀번호</InputCompVue
        >
        <label class="checkbox">
          <input type="checkbox" v-model="isRemember" />
          로그인 유지
        </label>
        <button :disabled="isError" class="main-button">
          {{ isError ? "모두 채우고 로그인하기" : "로그인" }}
        </button>
        <div class="sub-menu">
          <router-link class="item" :to="{ name: 'findPassword' }"
            >비밀번호 찾기</router-link
          >
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
import { useUserStore } from "@/store/userStore";
import jwt_decode from "jwt-decode";

export default {
  data() {
    return {
      user: {
        id: "",
        password: "",
      },
      isRemember: false,
      isError: true,
    };
  },
  setup() {
    const userStore = useUserStore();
    return {
      userStore,
    };
  },
  components: {
    InputCompVue,
    ProjectIconCompVue,
  },
  watch: {
    "user.password"(now) {
      if (now != "" && this.user.id != "") {
        this.isError = false;
      } else {
        this.isError = true;
      }
    },
  },
  methods: {
    async login() {
      try {
        const res = await this.userStore.login(this.user);
        const userInfo = jwt_decode(res);
        console.log("디코딩된 유저정보:", userInfo);
        this.userStore.userInfo.id = userInfo.id;
        this.userStore.userInfo.name = userInfo.name;
        this.userStore.userInfo.email = userInfo.email;
        this.userStore.accessToken = res;
        this.userStore.isRemember = this.isRemember;

        console.log("로그인 성공:", res.data);
        this.$router.push({ name: "home" });
      } catch (error) {
        console.log("로그인 실패:", error);
      }
    },
    setId(value) {
      this.user.id = value;
    },
    setPassword(value) {
      this.user.password = value;
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
        &:disabled {
          background: gray;
          cursor: auto;
        }
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
