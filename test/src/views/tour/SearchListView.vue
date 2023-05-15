<template>
  <div class="sidebar">
    <form class="search">
      <label class="searchLabel">
        <input type="text" class="searchInput" />
        <font-awesome-icon class="icon" :icon="['fas', 'magnifying-glass']" />
      </label>
    </form>
    <ul class="list">
      <PlaceCardCompVue v-for="item in items" :key="item.title" :item="item" />
    </ul>
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
.sidebar {
  // 사이드바 전체
  background-color: white; // 임시
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  border-top: 1px solid lightgrey;
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
}
</style>
