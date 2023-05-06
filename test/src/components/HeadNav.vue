<template>
  <header class="nav">
    <project-icon />
    <nav>
      <router-link :to="{ name: 'home' }">홈</router-link>
      <router-link :to="{ name: 'tour' }">여행지</router-link>
      <router-link :to="{ name: 'hotPlaceList', params: { pageNo: 1 } }">
        핫플레이스
      </router-link>
      <router-link :to="{ name: 'login' }">
        <ButtonComp>로그인</ButtonComp>
      </router-link>
      <router-link :to="{ name: 'join' }">
        <ButtonComp :fill="true">회원가입</ButtonComp>
      </router-link>
    </nav>
  </header>
</template>

<script>
import ButtonComp from "./ButtonComp.vue";
import ProjectIcon from "./ProjectIcon.vue";
export default {
  name: "HelloWorld",
  components: {
    ProjectIcon,
    ButtonComp,
  },
};
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
.nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px solid red;
  padding: 1rem 10%;
  nav {
    a {
      color: $primary;
      text-decoration: none;
      margin: 1rem;
    }
  }
}
</style>
