<template>
  <div class="container">
    <div class="picture">
      <img :src="placeInfo.firstimage" alt="이미지" />
    </div>
    <header>
      <div class="title">{{ placeInfo.title }}</div>
      <div class="sub-title">{{ tourStore.activeTheme.text }}</div>
      <address>
        <div class="addr">{{ placeInfo.addr1 }}</div>
      </address>
    </header>
    <div class="contents">
      <p v-html="placeInfo.overview"></p>
    </div>
  </div>
</template>

<script>
import { useTourStore } from "@/store/tourStore";
export default {
  setup() {
    const tourStore = useTourStore();
    return {
      tourStore,
    };
  },
  computed: {
    placeInfo() {
      return this.tourStore.detailPlace;
    },
  },
  unmounted() {
    this.tourStore.deactivatePlace();
  },
};
</script>

<style lang="scss" scoped>
.container {
  width: 100%;
  height: 90vh;
  background-color: beige;
  .picture {
    width: 100%;
    img {
      width: 100%;
    }
  }
}
</style>
