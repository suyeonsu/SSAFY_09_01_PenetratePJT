<template>
  <li class="item">
    <div class="image">
      <img :src="item.imageURL" alt="이미지" />
    </div>
    <div class="contents" @click="goToDetail">
      <div class="title">
        <h3>
          {{ item.title }}
        </h3>
      </div>
      <h4>{{ item.subTitle }}</h4>
      <address>
        <div>{{ item.address }}</div>
      </address>
    </div>
  </li>
</template>

<script>
import { useTourStore } from "@/store/tourStore";
export default {
  props: {
    item: {
      id: String,
      title: String,
      subTitle: String,
      address: String,
      stars: Number,
      imageURL: URL,
    },
  },
  setup() {
    const tourStore = useTourStore();
    return {
      tourStore,
    };
  },
  methods: {
    async goToDetail() {
      this.tourStore.goToDetail(this.item.id);
    },
  },
};
</script>

<style lang="scss" scoped>
* {
  box-sizing: border-box;
}
.item {
  margin-bottom: 1rem;
  position: relative;
  border-radius: 10px;
  overflow: hidden;
  &:hover,
  &.active {
    .contents {
      height: 100%;
      h3,
      address {
        overflow: visible;
        white-space: normal;
        text-overflow: unset;
      }
    }
  }
  .image {
    width: 100%;
    height: 30vh;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .contents {
    // 리스트 아이템
    position: absolute;
    bottom: 0;
    color: $background;
    background-color: rgba(0, 0, 0, 0.5);
    width: 100%;
    cursor: pointer;
    transition: 0.3s;
    padding: 10px;
    height: 50%;
    .title {
      display: flex;
      justify-content: space-between;
      .my-place {
        font-size: 1rem;
      }
      h3 {
        // 아이템 제목
        font-size: 1.5rem;
        font-weight: bold;
        margin-bottom: 10px;
      }
    }
    h4 {
      // 아이템 부제목
      font-size: 1.3rem;
      margin-bottom: 1rem;
    }
    h3,
    address {
      // 텍스트 자르기
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
  }
}
</style>
