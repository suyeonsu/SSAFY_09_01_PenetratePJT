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

export default {
  name: "HelloWorld",
  components: {
    ProjectIconComp,
    ButtonComp,
  },
  mounted() {
    // 로그인 유지 기능
    if (!this.userStore.isRemember) {
      this.userStore.$reset();
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
      console.log("로그아웃");
      this.userStore.$reset();
    },
  },
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.nav {
  display: flex;
  font-family: "S-CoreDream-3Light";
  font-weight: 500;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 5%;
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
