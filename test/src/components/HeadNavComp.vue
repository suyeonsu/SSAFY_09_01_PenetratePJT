<template>
  <header class="nav">
    <ProjectIconComp />
    <nav>
      <router-link :to="{ name: 'home' }">홈</router-link>
      <router-link :to="{ name: 'tour' }">여행지</router-link>
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
import ProjectIconComp from "./ProjectIconComp.vue";
export default {
  name: "HelloWorld",
  components: {
    ProjectIconComp,
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
  height: 10vh;
  box-sizing: border-box;
  nav {
    a {
      color: $primary;
      text-decoration: none;
      margin: 1rem;
    }
  }
}
</style>
