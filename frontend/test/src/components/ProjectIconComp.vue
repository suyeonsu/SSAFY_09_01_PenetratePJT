<template>
  <router-link class="logo" to="/">
    <img class="icon" :src="require('@/assets/logo.png')" alt="로고" />
  </router-link>
</template>

<script>
export default {};
</script>

<style scoped lang="scss">
.logo {
  color: $primary;
  padding: 5px;
  text-decoration: none;
  .icon {
    font-size: 2rem;
    width: 4rem;
    height: 4rem;
  }
  span {
    font-size: 2rem;
    font-family: "Gugi", "Nanum Pen Script", oswald;
  }
}
</style>
