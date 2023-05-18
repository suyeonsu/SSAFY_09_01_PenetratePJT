<template>
  <router-link class="logo" to="/">
    <font-awesome-icon class="icon" :icon="['fas', 'plane-departure']" />
    <span> MyTrip </span>
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
  }
  span {
    font-size: 2rem;
    font-family: "Gugi", "Nanum Pen Script", oswald;
  }
}
</style>
