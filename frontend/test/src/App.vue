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
@font-face {
  font-family: "yg-jalnan";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "S-CoreDream-3Light";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@import url("https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css");
@import url("https://fonts.googleapis.com/css2?family=Gowun+Dodum&family=Gugi&family=Nanum+Pen+Script&family=Oswald&display=swap");
#app,
* {
  font-family: "yg-jalnan", "Gowun Dodum", "Gugi", "Nanum Pen Script", "Oswald",
    Avenir, Helvetica, Arial, sans-serif;
}
</style>
