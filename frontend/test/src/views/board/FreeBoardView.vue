<template>
  <div class="container">
    <header>
      <h1>자유게시판</h1>
      <h3>자유롭게 작성하고 의견을 공유해요!</h3>
    </header>
    <main>
      <section>
        <article class="search">
          <form @submit.prevent="boardStore.loadFreeBoardList(1)">
            <select class="range" v-model="boardStore.filter">
              <optgroup label="검색기준">
                <option v-for="item of filter" :key="item.en" :value="item.en">
                  {{ item.ko }}
                </option>
              </optgroup>
            </select>
            <input type="text" v-model="boardStore.keywords" />
            <button>
              <font-awesome-icon
                class="icon"
                :icon="['fas', 'magnifying-glass']" />
            </button>
          </form>
        </article>
        <article class="sort">
          <span>정렬기준</span>
          <select v-model="boardStore.sort">
            <optgroup label="정렬기준">
              <option v-for="item of sort" :key="item.en" :value="item.en">
                {{ item.ko }}
              </option>
            </optgroup>
          </select>
        </article>
      </section>
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
import { useBoardStore } from "@/store/boardStore";
export default {
  data() {
    return {
      sort: [
        { en: "regtime", ko: "최신순" },
        { en: "hit", ko: "조회수순" },
      ],
      filter: [
        {
          en: "subject",
          ko: "제목",
        },
        {
          en: "content",
          ko: "제목+내용",
        },
        {
          en: "userid",
          ko: "작성자",
        },
      ],
    };
  },
  setup() {
    const boardStore = useBoardStore();
    return { boardStore };
  },
  methods: {
    goToDetail(articleno) {
      this.$router.push({ name: "freeBoardDetail", params: { id: articleno } });
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  height: 90vh;
  width: 50%;
  margin: 0 auto;
  padding-top: 2rem;
  box-sizing: border-box;
  header {
    margin-bottom: 2rem;
    display: flex;
    flex-direction: column;
    align-items: center;
    h1 {
      font-size: 2rem;
      width: fit-content;
      margin-bottom: 1rem;
    }
  }
  main {
    box-sizing: border-box;
    .search {
      display: flex;
      justify-content: center;
      align-items: center;
      margin-bottom: 2rem;
      form {
        width: 70%;
        display: flex;
        border-radius: 2rem;
        overflow: hidden;
        border: 2px solid $primary;
        & > * {
          border: none;
          &:focus {
            outline: none;
          }
        }
        .range {
          width: 5rem;
          text-align: center;
          border-right: 1px solid $primary;
          cursor: pointer;
        }
        input {
          width: 100%;
          height: 100%;
          box-sizing: border-box;
          padding: 0.5rem;
        }
        button {
          color: $primary;
          background: none;
          padding-right: 1rem;
          cursor: pointer;
        }
      }
    }
    .sort {
      display: flex;
      justify-content: flex-end;
      align-items: center;
      margin-bottom: 2rem;
      span {
        margin-right: 10px;
      }
      select {
        padding: 2px 5px;
        font-size: 1rem;
        border: 1px solid $primary;
      }
    }
  }
}
</style>
