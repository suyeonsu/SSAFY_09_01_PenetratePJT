<template>
  <div class="home">
    <main class="container">
      <router-link :to="{ name: 'searchList' }">
        <ButtonComp class="huge">여정 시작하기</ButtonComp>
      </router-link>
    </main>
  </div>
</template>

<script>
import ButtonComp from "@/components/ButtonComp.vue";

export default {
  name: "HomeView",
  components: {
    ButtonComp,
  },
};
</script>

<style lang="scss" scoped>
.home {
  height: 90vh;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  box-sizing: border-box;
  background-image: url("@/assets/image/home.jpg");
  background-size: 100% 100%;
  .huge {
    font-size: 2rem;
    border: 2px solid $primary;
  }
}
</style>
