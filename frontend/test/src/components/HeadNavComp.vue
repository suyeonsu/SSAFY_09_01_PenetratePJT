<template>
  <header class="nav">
    <ProjectIconComp />
    <nav>
      <router-link :to="{ name: 'home' }">홈</router-link>
      <router-link :to="{ name: 'searchList' }">여행지</router-link>
      <router-link :to="{ name: 'freeBoardList', params: { pageNo: 1 } }"
        >자유게시판</router-link
      >
      <template v-if="userStore.userInfo.id">
        <router-link
          :to="{ name: 'userpage', params: { id: userStore.userInfo.id } }">
          {{ userStore.userInfo.name
          }}{{ `(${userStore.userInfo.id})님 환영합니다` }}
        </router-link>
        <ButtonComp @click="logout">로그아웃</ButtonComp>
      </template>
      <template v-else>
        <router-link :to="{ name: 'login' }">
          <ButtonComp :fill="true">로그인</ButtonComp>
        </router-link>
      </template>
    </nav>
  </header>
</template>

<script>
import { useUserStore } from "@/store/userStore";
import ButtonComp from "./ButtonComp.vue";
import ProjectIconComp from "./ProjectIconComp.vue";
import { mapStores } from "pinia";

export default {
  name: "HelloWorld",
  components: {
    ProjectIconComp,
    ButtonComp,
  },
  created() {
    // 로그인 유지 기능
    const loginedUser = window.localStorage.getItem("userInfo");
    // console.log(loginedUser);
    if (loginedUser != null) {
      this.userStore.userInfo = JSON.parse(loginedUser);
      // console.log(this.userStore.userInfo);
    }
  },
  setup() {
    const userStore = useUserStore();
    return {
      userStore,
    };
  },
  methods: {
    logout() {
      // 로그아웃
      this.userStore.userInfo.id = "";
      this.userStore.userInfo.name = "";
      window.localStorage.removeItem("userInfo");
      // 쿠키도 삭제해야됨.
    },
  },
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 10%;
  height: 10vh;
  box-sizing: border-box;
  nav {
    a {
      color: $primary;
      text-decoration: none;
      margin: 1rem;
      &:hover {
        color: $secondary;
      }
    }
  }
}
</style>
