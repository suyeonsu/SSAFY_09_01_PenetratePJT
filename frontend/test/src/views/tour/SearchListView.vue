<template>
  <div class="container">
    <form class="search">
      <label class="searchLabel">
        <input type="text" class="searchInput" />
        <font-awesome-icon class="icon" :icon="['fas', 'magnifying-glass']" />
      </label>
    </form>
    <div>최신순 | 인기순</div>
    <ul class="list">
      <PlaceCardCompVue v-for="item in items" :key="item.title" :item="item" />
    </ul>
    <div :class="{ 'detail-container': true, hidden: tourStore.hideDetail }">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import PlaceCardCompVue from "@/components/PlaceCardComp.vue";
import { useTourStore } from "@/store/tourStore";

export default {
  data() {
    return {};
  },
  setup() {
    const tourStore = useTourStore();
    return {
      tourStore,
    };
  },
  computed: {
    items() {
      return this.tourStore.places.map((item) => {
        return {
          id: item.contentid,
          title: item.title,
          subTitle: this.tourStore.activeTheme.text,
          address: item.addr1,
          hearts: 18,
          stars: 29,
          imageURL: item.firstimage,
        };
      });
    },
  },
  components: {
    PlaceCardCompVue,
  },
};
</script>

<style scoped lang="scss">
.container {
  // 사이드바 전체
  background-color: white; // 임시
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  position: relative;
  .search {
    // 검색창
    padding: 1rem;
    .searchLabel {
      position: relative;
      .searchInput {
        font-size: 1rem;
        padding: 0.5rem 1rem;
        border-radius: 2rem;
        border: 2px solid $primary;
        width: 100%;
        box-sizing: border-box;
      }
      .icon {
        position: absolute;
        right: 1rem;
        top: 50%;
        transform: translateY(-50%);
        color: $primary;
        cursor: pointer;
      }
    }
  }
  .list {
    height: 100%;
    overflow: scroll;
    padding: 0 1rem;
    &::-webkit-scrollbar {
      // 스크롤바
      width: 4px;
    }
    &::-webkit-scrollbar-thumb {
      background-color: $primary;
      border-radius: 2px;
    }
  }
  .detail-container {
    position: absolute;
    width: 100%;
    height: 90vh;
    top: 0;
    left: 100%;
    transition: 0.2s;
    z-index: -1;
    &.hidden {
      left: 0;
    }
  }
}
</style>
