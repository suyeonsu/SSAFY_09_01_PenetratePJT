<template>
  <ul class="pagenation">
    <div
      v-if="pagenationInfo.navigateFirstPage != 1"
      @click="goToPrivious"
      class="item">
      <font-awesome-icon :icon="['fas', 'chevron-left']" />
    </div>
    <li v-for="n of pagenationInfo.navigatepageNums" :key="n">
      <div
        @click="goToPage(n)"
        :class="{ item: true, active: n == pagenationInfo.pageNum }">
        {{ n }}
      </div>
    </li>
    <div
      v-if="pagenationInfo.navigateLastPage != pagenationInfo.pages"
      @click="goToNext"
      class="item">
      <font-awesome-icon :icon="['fas', 'chevron-right']" />
    </div>
  </ul>
</template>

<script>
export default {
  props: {
    pagenationInfo: {
      type: Object,
    },
  },
  methods: {
    goToPage(pageNo) {
      this.$router.push({ name: "freeBoardList", params: { pageNo } });
    },
    goToPrivious() {
      let prev = this.pagenationInfo.navigateFirstPage;
      if (prev != 1) prev--;
      this.goToPage(prev);
    },
    goToNext() {
      let next = this.pagenationInfo.navigateLastPage;
      if (next != this.pagenationInfo.pages) next++;
      this.goToPage(next);
    },
  },
};
</script>

<style lang="scss" scoped>
.pagenation {
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.3rem;
  box-sizing: border-box;
  .item {
    display: flex;
    align-items: center;
    justify-content: center;
    min-width: 2rem;
    padding: 0 0.2rem;
    height: 2rem;
    border-radius: 0.3rem;
    margin-right: 0.3rem;
    &:hover,
    &.active {
      background: $primary;
      color: $background;
      cursor: pointer;
    }
  }
}
</style>
