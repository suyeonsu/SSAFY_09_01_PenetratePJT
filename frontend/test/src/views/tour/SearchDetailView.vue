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
        <div class="bookMark">
          <button @click="bookmarking">
            <font-awesome-icon v-if="bookmarked" :icon="['fas', 'bookmark']" />
            <font-awesome-icon v-else :icon="['far', 'bookmark']" />
          </button>
        </div>
      </header>
      <address>
        <div class="address-item">
          <font-awesome-icon :icon="['fas', 'star']" />
          {{ placeInfo.totalScore }}
          <button @click="rating">5점주기</button>
          <!-- <StarRateCompVue :rate="placeInfo.totalScore"> -->
        </div>
        <div class="address-item">
          <font-awesome-icon :icon="['fas', 'location-dot']" />
          {{ placeInfo.addr1 }}
        </div>
        <div v-if="placeInfo.tel" class="address-item">
          <font-awesome-icon :icon="['fas', 'phone']" />
          {{ placeInfo.tel }}
        </div>
      </address>
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
// import StarRateCompVue from "@/components/StarRateComp.vue";
export default {
  setup() {
    const tourStore = useTourStore();
    return {
      tourStore,
    };
  },
  // components: {
  //   StarRateCompVue,
  // },
  created() {
    this.tourStore.getDetail();
  },
  computed: {
    placeInfo() {
      return this.tourStore.detailPlace.attraction;
    },
    bookmarked() {
      return this.tourStore.detailPlace.myplace;
    },
    imageURL() {
      if (this.placeInfo == null) return;
      if (this.placeInfo.firstImage != null) {
        return this.placeInfo.firstImage.length > 0
          ? this.placeInfo.firstImage
          : require("@/assets/image/default.jpg");
      }
      return null;
    },
  },
  unmounted() {
    this.tourStore.deactivatePlace();

    // console.log("unmounted()");
    // this.$router.replace("/tour");
  },
  methods: {
    async rating() {
      await this.tourStore.rating(this.placeInfo.contentId, 5);
      alert("별점 주기 성공");
    },
    async bookmarking() {
      if (this.bookmarked) {
        this.tourStore.deleteBookmark(this.placeInfo.contentId);
        this.tourStore.detailPlace.myplace = false;
      } else {
        this.tourStore.putBookmark(this.placeInfo.contentId);
        this.tourStore.detailPlace.myplace = true;
      }
    },
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
      display: flex;
      .title {
        width: 90%;
        font-size: 2rem;
        margin-right: 1rem;
        word-break: keep-all;
        margin-bottom: 1rem;
        .sub-title {
          font-size: 1.3rem;
        }
      }
      .bookMark {
        width: 10%;
        button {
          color: $primary;
          font-size: 2rem;
          padding: 0;
          height: fit-content;
          background: transparent;
          border: none;
          outline: none;
          cursor: pointer;
        }
      }
    }
    address {
      width: 100%;
      font-family: "S-CoreDream-3Light";
    }
    section {
      flex: 1;
      overflow: auto;
      font-family: "S-CoreDream-3Light";
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
    background: $background;
    z-index: 10;
    padding: 0.5rem;
    font-size: 1.3rem;
    border-radius: 0 0.2rem 0.2rem 0;
    color: rgb(117, 117, 117);
    cursor: pointer;
  }
}
</style>
