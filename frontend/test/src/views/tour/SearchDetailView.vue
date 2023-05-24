<template>
  <div class="container">
    <div class="pictures">
      <img :src="imageURL" alt="이미지" />
    </div>
    <div class="contents">
      <header>
        <div class="title">
          {{ placeInfo.title }}
          <span class="sub-title">
            {{ tourStore.activeTheme.text }}
          </span>
        </div>
        <address>
          <div class="addr">
            <font-awesome-icon :icon="['fas', 'location-dot']" />
            {{ placeInfo.addr1 }}
          </div>
          <div class="tel"></div>
        </address>
      </header>
      <section>
        <article v-html="placeInfo.overview"></article>
      </section>
    </div>
    <div
      v-show="!tourStore.hideDetail"
      @click="tourStore.deactivatePlace()"
      class="close">
      <font-awesome-icon :icon="['fas', 'x']" />
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
    imageURL() {
      if (this.placeInfo.firstimage != null) {
        return this.placeInfo.firstimage.length > 0
          ? this.placeInfo.firstimage
          : require("@/assets/image/default.jpg");
      }
      return null;
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
  display: flex;
  flex-direction: column;
  box-shadow: 1px 0 5px lightgray;
  box-sizing: border-box;
  position: relative;
  .pictures {
    width: 100%;
    height: 30vh;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .contents {
    padding: 1rem;
    flex: 1;
    display: flex;
    flex-direction: column;
    header {
      margin-bottom: 1rem;
      .title {
        font-size: 2rem;
        margin-right: 1rem;
        word-break: keep-all;
        margin-bottom: 1rem;
        .sub-title {
          font-size: 1.3rem;
        }
      }
      address {
        width: 100%;
      }
    }
    section {
      flex: 1;
      overflow: auto;
      article {
        height: 0;
        line-height: 1.6rem;
      }
    }
  }
  .close {
    position: absolute;
    top: 1rem;
    left: 100%;
    background: white;
    z-index: 10;
    padding: 0.5rem;
    font-size: 1.3rem;
    border-radius: 0 0.2rem 0.2rem 0;
    color: rgb(117, 117, 117);
    cursor: pointer;
  }
}
</style>
