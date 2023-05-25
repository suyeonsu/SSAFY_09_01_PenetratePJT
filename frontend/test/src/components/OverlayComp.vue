<template>
  <div :class="{ container: true, active }">
    <div class="image">
      <img
        :src="require(`@/assets/image/tour/${imageName}.png`)"
        alt="이미지" />
    </div>
    <span>
      {{ title }}
    </span>
  </div>
</template>

<script>
import { useTourStore } from "@/store/tourStore";
export default {
  props: {
    title: String,
    imageName: String,
    contentId: Number,
  },
  setup() {
    const tourStore = useTourStore();
    return { tourStore };
  },
  computed: {
    active() {
      return this.tourStore.activePlace === this.contentId;
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  background: $background;
  transform: translateX(-50%);
  height: 2.4rem;
  display: flex;
  flex-direction: row;
  align-items: center;
  border-radius: 2rem;
  border: 2px solid $primary;
  max-width: 20rem;
  padding-right: 1rem;
  cursor: pointer;
  transition: 0.1s;
  position: relative;
  &:hover,
  &.active {
    background: $primary;
    color: $background;
    z-index: 9999999999 !important;
    position: relative;
  }
  .image {
    width: 2rem;
    height: 2rem;
    border-radius: 50%;
    margin: 0 0.2rem 0 0.2rem;
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    align-items: center;
    background: $background;
    flex-shrink: 0;
    img {
      width: 65%;
      height: 65%;
    }
  }
  span {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }
}
</style>
