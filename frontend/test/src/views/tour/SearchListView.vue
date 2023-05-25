<template>
  <div class="container">
    <template v-if="tourStore.activeTheme.id > 10">
      <form class="search" @submit.prevent="keywordSearch">
        <label class="searchLabel">
          <input type="text" class="searchInput" v-model="tourStore.keywords" />
          <font-awesome-icon class="icon" :icon="['fas', 'magnifying-glass']" />
        </label>
      </form>
      <div class="menu">
        <select class="select" name="sido" v-model="tourStore.selectedSido">
          <option value="0">시/도</option>
          <option
            v-for="item of tourStore.sido"
            :value="item.code"
            :key="item.rnum">
            {{ item.name }}
          </option>
        </select>
        <select class="select" name="gugun" v-model="tourStore.selectedGugun">
          <option value="0">구/군</option>
          <option
            v-for="item of tourStore.gugun"
            :value="item.code"
            :key="item.rnum">
            {{ item.name }}
          </option>
        </select>
        <select class="select" name="sort" v-model="tourStore.selectedSort">
          <option v-for="item of sorts" :value="item.en" :key="item.en">
            {{ item.ko }}
          </option>
        </select>
      </div>
    </template>
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
      sorts: [
        {
          ko: "조회순",
          en: "readcount",
        },
        {
          ko: "거리순",
          en: "distance",
        },
        {
          ko: "별점순",
          en: "star",
        },
        {
          ko: "제목순",
          en: "subject",
        },
      ],
      isFirst: false,
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
    "tourStore.selectedSido"(now) {
      this.tourStore.getGugun(now);
    },
    "tourStore.selectedSort"() {
      this.keywordSearch();
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
      console.log("장소들", this.tourStore.places);
      return this.tourStore.places.map((item) => {
        return {
          id: item.contentId,
          title: item.title,
          subTitle: this.tourStore.activeTheme.text,
          address: item.addr1,
          stars: item.totalScore,
          imageURL:
            item.firstImage.length > 0
              ? item.firstImage
              : require("@/assets/image/default.jpg"),
        };
      });
    },
  },
  methods: {
    async keywordSearch() {
      this.tourStore.resetMarker();
      const nextPlaces = await this.tourStore.infinityScroll();
      if (nextPlaces != null) {
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
  background-color: $background; // 임시
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
      width: 30%;
      height: 2rem;
      margin-right: 0.5rem;
      border-radius: 0.3rem;
      cursor: pointer;
      padding: 0 0.5rem;
      background: $background;
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
    background: $background;
    &.hidden {
      left: 0;
    }
  }
}
</style>
