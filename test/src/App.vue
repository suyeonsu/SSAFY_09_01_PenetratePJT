<template>
  <HeadNav />
  <router-view />
</template>

<script>
import HeadNav from "@/components/HeadNavComp.vue";
export default {
  components: {
    HeadNav,
  },
};
</script>

<style lang="scss">
@import url("https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css");
@import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Gugi&family=Nanum+Pen+Script&family=Oswald&display=swap");
#app,
* {
  font-family: "Gowun Dodum", "Gugi", "Nanum Pen Script", "Oswald", Avenir,
    Helvetica, Arial, sans-serif;
}
</style>
