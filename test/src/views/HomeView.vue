<template>
  <div class="home">
    <main class="container">
      <h1>
        지금 바로 <br />
        떠나볼까요?
      </h1>
      한번의 클릭으로 여정을 시작해봅시다
      <router-link :to="{ name: 'tour' }">
        <ButtonComp>여정 시작하기</ButtonComp>
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
  background-color: pink;
  height: 100vh;
}
</style>
