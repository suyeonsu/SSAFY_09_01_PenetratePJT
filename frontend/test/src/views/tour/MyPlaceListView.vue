<template>
  <div class="container">
    <form class="search" @submit.prevent="keywordSearch">
      <label class="searchLabel">
        <input type="text" class="searchInput" v-model="keyword" />
        <font-awesome-icon class="icon" :icon="['fas', 'magnifying-glass']" />
      </label>
    </form>
    <div class="menu">
      <div class="select">
        <select class="select" name="sido" v-model="sido">
          <option value="">시/도</option>
          <option
            v-for="item of tourStore.sido"
            :value="item.code"
            :key="item.rnum">
            {{ item.name }}
          </option>
        </select>
        <select class="select" name="gugun" v-model="gugun">
          <option value="">구/군</option>
          <option
            v-for="item of tourStore.gugun"
            :value="item.code"
            :key="item.rnum">
            {{ item.name }}
          </option>
        </select>
      </div>
      <div class="sort">거리순 | 등록순</div>
    </div>
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
    return {
      sido: "",
      gugun: "",
      keyword: "",
    };
  },
  setup() {
    const tourStore = useTourStore();
    return { tourStore };
  },
  created() {
    this.tourStore.getSido();
  },
  watch: {
    sido(now) {
      this.tourStore.getGugun(now);
    },
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
          imageURL:
            item.firstimage.length > 0
              ? item.firstimage
              : require("@/assets/image/default.jpg"),
        };
      });
    },
  },
  methods: {
    async keywordSearch() {
      // console.log(this.keyword, this.sido, this.gugun);
      const nextPlaces = await this.tourStore.getKeywordResult(
        this.keyword,
        this.sido,
        this.gugun
      );
      if (nextPlaces != null) {
        console.log(nextPlaces);
        this.tourStore.makeMarkers(nextPlaces);
      }
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
  width: 100%;
  height: 100%;
  background-color: white; // 임시
  display: flex;
  flex-direction: column;
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
  .menu {
    display: flex;
    width: 100%;
    padding: 0 1rem;
    box-sizing: border-box;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 1rem;
    .select {
      flex: 1;
      select {
        width: 40%;
        height: 2rem;
        margin-right: 0.5rem;
        border-radius: 0.3rem;
        cursor: pointer;
        padding: 0 0.5rem;
        background: white;
      }
    }
    .sort {
      display: flex;
      justify-content: flex-end;
      align-items: center;
      padding-top: 0;
    }
  }
  .list {
    flex: 1;
    height: 0;
    overflow: auto;
    padding: 0 1rem;
    box-sizing: border-box;
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
