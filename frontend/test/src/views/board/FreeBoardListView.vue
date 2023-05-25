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
    <footer>
      <PagenationCompVue class="center" :pagenationInfo="pagenationInfo" />
      <ButtonCompVue
        v-if="userStore.accessToken"
        class="right"
        @click="goToWrite"
        >글쓰기</ButtonCompVue
      >
    </footer>
  </main>
</template>

<script>
import { useBoardStore } from "@/store/boardStore";
import PagenationCompVue from "@/components/PagenationComp.vue";
import ButtonCompVue from "@/components/ButtonComp.vue";
import { useUserStore } from "@/store/userStore";
export default {
  components: {
    PagenationCompVue,
    ButtonCompVue,
  },
  setup() {
    const boardStore = useBoardStore();
    const userStore = useUserStore();
    return { boardStore, userStore };
  },
  async created() {
    await this.getListInfo(this.$route.params.pageNo);
  },
  async beforeRouteUpdate(to) {
    await this.getListInfo(to.params.pageNo);
  },
  computed: {
    pagenationInfo() {
      return {
        currentPageNo: this.$route.params.pageNo,
        parentRoot: "freeBoardList",
        pageNum: this.boardStore.pagenationInfo.pageNum,
        pages: this.boardStore.pagenationInfo.pages,
        navigatepageNums: this.boardStore.pagenationInfo.navigatepageNums,
        navigateFirstPage: this.boardStore.pagenationInfo.navigateFirstPage,
        navigateLastPage: this.boardStore.pagenationInfo.navigateLastPage,
      };
    },
  },
  watch: {
    "boardStore.sort"() {
      this.boardStore.loadFreeBoardList(1);
    },
  },
  methods: {
    goToDetail(articleno) {
      this.$router.push({ name: "freeBoardDetail", params: { id: articleno } });
    },

    async getListInfo(currentPageNo) {
      this.boardStore.setToday();
      await this.boardStore.loadFreeBoardList(currentPageNo);
      console.log("페이지네이션:", this.pagenationInfo);
    },
    goToWrite() {
      this.boardStore.resetDetail();
      this.$router.push({ name: "freeBoardEdit" });
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
  footer {
    width: 100%;
    position: relative;
    padding-top: 1rem;
    padding-bottom: 5rem;
    display: flex;
    align-items: center;
    .center {
      position: absolute;
      left: 50%;
      transform: translateX(-50%);
    }
    .right {
      position: absolute;
      right: 0;
    }
  }
}
</style>
