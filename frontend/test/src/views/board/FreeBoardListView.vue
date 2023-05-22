<template>
  <main>
    <table>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>조회수</th>
          <th>작성일시</th>
        </tr>
      </thead>
      <tbody>
        <template v-for="post of boardStore.postList" :key="post.articleno">
          <tr>
            <td>{{ post.articleno }}</td>
            <td @click="goToDetail(post.articleno)">{{ post.subject }}</td>
            <td>{{ post.userid }}</td>
            <td>{{ post.hit }}</td>
            <td>{{ boardStore.getTimeFormat(post.regtime) }}</td>
          </tr>
        </template>
      </tbody>
    </table>
    <PagenationCompVue :pagesInfo="pagesInfo" />
  </main>
</template>

<script>
import { useBoardStore } from "@/store/boardStore";
import PagenationCompVue from "@/components/PagenationComp.vue";
export default {
  data() {
    return {
      pagesInfo: {
        currentPageNo: 1,
        parentRoot: "freeBoardList",
        totalListLength: 1,
      },
    };
  },
  components: {
    PagenationCompVue,
  },
  setup() {
    const boardStore = useBoardStore();
    return { boardStore };
  },
  created() {
    this.getListInfo(this.$route.params.pageNo);
  },
  beforeRouteUpdate(to) {
    this.getListInfo(to.params.pageNo);
  },
  methods: {
    goToDetail(articleno) {
      this.$router.push({ name: "freeBoardDetail", params: { id: articleno } });
    },
    getListInfo(currentPageNo) {
      this.boardStore.setToday();
      this.boardStore.getPostListData(currentPageNo);
      this.pagesInfo.currentPageNo = currentPageNo;
      this.pagesInfo.totalListLength = this.boardStore.totalListLength;
    },
  },
};
</script>

<style lang="scss" scoped>
main {
  width: 100%;
  box-sizing: border-box;
  th,
  td {
    padding: 10px;
  }
  table {
    width: 100%;
    margin-bottom: 1rem;
    th:nth-child(1) {
      width: 10%;
    }
    th:nth-child(2) {
      width: 50%;
    }
    th:nth-child(3) {
      width: 15%;
    }
    th:nth-child(4) {
      width: 10%;
    }
    th:nth-child(5) {
      width: 15%;
    }
    tr {
      border-bottom: 1px solid $primary;
    }
    tbody {
      tr {
        &:hover {
          background: $primary;
          cursor: pointer;
        }
        &:last-child {
          border-bottom: none;
        }
      }
      td:not(:nth-child(2)) {
        text-align: center;
      }
    }
  }
}
</style>
