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
      <div class="sort">
        <label class="sort-item">
          <input
            class="sort-input"
            :class="{ active: sortParam == '거리순' }"
            type="radio"
            name="sortParam"
            value="거리순"
            v-model="sortParam" />
          거리순
        </label>
        <label class="sort-item">
          <input
            class="sort-input"
            :class="{ active: sortParam == '인기순' }"
            type="radio"
            name="sortParam"
            value="인기순"
            v-model="sortParam" />
          인기순
        </label>
        <label class="sort-item">
          <input
            class="sort-input"
            :class="{ active: sortParam == '별점순' }"
            type="radio"
            name="sortParam"
            value="별점순"
            v-model="sortParam" />
          별점순
        </label>
      </div>
    </div>
    <template v-if="items.length != 0">
      <ul @scroll="scrollHandler" class="list">
        <PlaceCardCompVue
          v-for="item in items"
          :key="item.title"
          :item="item" />
      </ul>
    </template>
    <template v-else>
      <div class="list">
        <img class="default-image" :src="noImage" :alt="noResult" />
        <div class="default-text">{{ noResult }}</div>
      </div>
    </template>
    <div :class="{ 'detail-container': true, hidden: tourStore.hideDetail }">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import PlaceCardCompVue from "@/components/PlaceCardComp.vue";
import { useTourStore } from "@/store/tourStore";
import finding from "@/assets/image/finding.jpg";
import missing from "@/assets/image/missing.jpg";

export default {
  data() {
    return {
      sido: "",
      gugun: "",
      keyword: "",
      isFirst: false, // 처음 진입한 경우
      sortParam: "",
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
    sortParam(now) {
      console.log(now);
    },
  },
  computed: {
    noImage() {
      return this.isFirst ? finding : missing;
    },
    noResult() {
      return this.isFirst ? "테마를 선택하세요" : "결과가 없습니다";
    },
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
          bookMark: false,
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
    async scrollHandler(e) {
      const { scrollHeight, scrollTop, clientHeight } = e.target;
      // console.log(scrollHeight, scrollTop, clientHeight);
      if (scrollHeight === scrollTop + clientHeight) {
        const nextPlaces = await this.tourStore.infinityScroll();
        console.log("인피니티:", nextPlaces);
        if (nextPlaces != null) {
          this.tourStore.makeMarkers(nextPlaces);
        }
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
      .sort-input {
        appearance: none;
      }
    }
  }
  .list {
    flex: 1;
    height: 0;
    overflow: scroll;
    padding: 0 1rem;
    box-sizing: border-box;
    .default-image {
      width: 100%;
    }
    .default-text {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 3rem;
      font-size: 1.5rem;
    }
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
    background: white;
    &.hidden {
      left: 0;
    }
  }
}
</style>
