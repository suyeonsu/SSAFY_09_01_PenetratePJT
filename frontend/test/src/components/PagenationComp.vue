<template>
  <ul class="pagenation">
    <div
      @click="pagenation(Math.max(1, this.startNo - 10))"
      class="item"
      v-if="isNotFirstPage">
      <font-awesome-icon :icon="['fas', 'chevron-left']" />
    </div>
    <li v-for="n of pageNo" :key="n">
      <div
        @click="goToPage(n)"
        :class="{ item: true, active: isActivePage(n) }">
        {{ n }}
      </div>
    </li>
    <div @click="pagenation(this.endNo + 1)" class="item" v-if="isNotLastPage">
      <font-awesome-icon :icon="['fas', 'chevron-right']" />
    </div>
  </ul>
</template>

<script>
export default {
  data() {
    return {
      startNo: 1,
      endNo: 10,
      totalPageNo: 1,
      pageNo: [],
    };
  },
  computed: {
    isNotFirstPage() {
      return this.startNo != 1;
    },
    isNotLastPage() {
      return this.endNo != this.totalPageNo;
    },
  },
  created() {
    // 전체 게시글 개수로부터 페이지 개수 구하기
    this.totalPageNo = Math.ceil(this.pagesInfo.totalListLength / 10);
    // 현재 페이지 번호로부터 시작 페이지 번호 구하기.
    const startNo =
      this.pagesInfo.currentPageNo - (this.pagesInfo.currentPageNo % 10) + 1;
    // 페이지 번호 생성하기
    this.pagenation(startNo);
  },

  props: {
    pagesInfo: {
      currentPageNo: Number,
      parentRoot: String,
      totalListLength: Number,
    },
  },
  methods: {
    goToPage(n) {
      this.$router.push({
        name: this.pagesInfo.parentRoot,
        params: { pageNo: n },
      });
    },
    isActivePage(n) {
      return this.$route.params.pageNo == n;
    },
    /** 페이지 정보 불러오기 */
    pagenation(startNo) {
      this.pageNo.length = 0;
      this.startNo = startNo;
      // 시작 페이지 번호로부터 끝 페이지 번호 구하기
      this.endNo = Math.min(this.startNo + 9, this.totalPageNo);
      for (let i = this.startNo; i <= this.endNo; i++) {
        this.pageNo.push(i);
      }
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
      color: white;
      cursor: pointer;
    }
  }
}
</style>
