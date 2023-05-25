<template>
  <div class="background">
    <div class="container">
      <main>
        <header>
          <h1 class="title">
            <InputCompVue
              :defaultMessage="boardStore.nowPost.subject"
              @changeValue="setTitle"
              >제목</InputCompVue
            >
          </h1>
          <div class="info">
            <div>조회수: {{ boardStore.nowPost.hit }}</div>
            <div v-if="localeTime">
              작성시간:
              {{ localeTime }}
            </div>
            <div>작성자: {{ boardStore.nowPost.userid }}</div>
          </div>
          <div class="controllers">
            <template v-if="isauthor">
              <ButtonCompVue v-if="isNew" @click="posting" class="btn"
                >글쓰기</ButtonCompVue
              >

              <ButtonCompVue v-else @click="editArticle" class="btn"
                >수정완료</ButtonCompVue
              >

              <ButtonCompVue @click="removePosting" class="btn"
                >삭제하기</ButtonCompVue
              >
            </template>
            <ButtonCompVue @click="goToBack" class="btn"
              >돌아가기</ButtonCompVue
            >
          </div>
        </header>
        <div class="contents">
          <textarea v-model="boardStore.nowPost.content" cols="30" rows="10">
          </textarea>
        </div>
        <img
          class="decoration"
          src="@/assets/image/tour/stamp.png"
          alt="stamp" />
      </main>
    </div>
  </div>
</template>

<script>
import { useBoardStore } from "@/store/boardStore";
import ButtonCompVue from "@/components/ButtonComp.vue";
import { useUserStore } from "@/store/userStore";
import InputCompVue from "@/components/InputComp.vue";

export default {
  setup() {
    const boardStore = useBoardStore();
    const userStore = useUserStore();
    return {
      boardStore,
      userStore,
    };
  },
  components: {
    ButtonCompVue,
    InputCompVue,
  },
  computed: {
    localeTime() {
      if (this.boardStore.nowPost.regtime == "") return "";
      else {
        const options = {
          year: "numeric",
          month: "numeric",
          day: "numeric",
          hour: "numeric",
          minute: "numeric",
          second: "numeric",
          hour12: false,
          timeZone: "UTC",
        };
        return new Intl.DateTimeFormat("Ko-KR", options).format(
          new Date(this.boardStore.nowPost.regtime)
        );
      }
    },
    isauthor() {
      if (this.userStore.userInfo.id != "") {
        if (this.boardStore.nowPost.userid === this.userStore.userInfo.id) {
          return true;
        }
      }
      return false;
    },
    isNew() {
      if (this.boardStore.nowPost.articleno == 0) return true;
      else return false;
    },
  },
  methods: {
    setTitle(data) {
      this.boardStore.nowPost.subject = data;
    },
    async posting() {
      const flag = confirm("등록하시겠습니까?");
      if (flag) {
        try {
          await this.boardStore.postArticle();
          console.log("등록성공");
          this.$router.push({
            name: "freeBoardList",
            params: { pageNo: 1 },
          });
        } catch (error) {
          alert("등록실패: ", error);
        }
      } else {
        console.log("취소됨");
      }
    },
    async editArticle() {
      const flag = confirm("저장하시겠습니까?");
      if (flag) {
        try {
          await this.boardStore.editArticle();
          console.log("수정성공");
          this.$router.push({
            name: "freeBoardDetail",
            params: { id: this.boardStore.nowPost.articleno },
          });
        } catch (error) {
          alert("수정실패: ", error);
        }
      } else {
        console.log("취소됨");
      }
    },
    async removePosting() {
      try {
        const flag = confirm("정말 삭제하시겠습니까?");
        if (flag) {
          await this.boardStore.deleteArticle(
            this.boardStore.nowPost.articleno
          );
          console.log("삭제됨");
          this.$router.push({ name: "freeBoard" });
        } else {
          console.log("취소됨");
        }
      } catch (error) {
        alert("삭제실패: ", error);
      }
    },
    goToBack() {
      this.$router.back(1);
    },
  },
};
</script>

<style lang="scss" scoped>
* {
  box-sizing: border-box;
}
.background {
  height: 90vh;
  background: navajowhite;
  display: flex;
  justify-content: center;
  padding: 2rem 5rem;
  .container {
    width: 80%;
    padding: 1rem;
    position: relative;
    background: repeating-linear-gradient(
      135deg,
      $primary 0 2%,
      white 2% 4%,
      $secondary 4% 6%,
      white 6% 8%
    );
    box-shadow: 1rem 1rem burlywood;
    border-radius: 1rem;
    overflow: hidden;
    main {
      background: white;
      width: 100%;
      height: 100%;
      padding: 3rem;
      display: flex;
      position: relative;
      header {
        width: 40%;
        height: 100%;
        border-right: 1px solid $primary;
        padding-right: 1rem;
        .title {
          margin-bottom: 2rem;
        }
        .info {
          font-size: 1.2rem;
          line-height: 2rem;
          text-decoration: underline 1px $primary;
        }
        .controllers {
          position: absolute;
          bottom: 10%;
          left: 22.5%;
          transform: translateX(-50%);
          display: flex;
          .btn {
            margin-right: 1rem;
            &:last-child {
              margin-right: none;
            }
          }
        }
      }
      .contents {
        width: 60%;
        height: 100%;
        padding-left: 1rem;
        font-size: 1rem;
        line-height: 1.4rem;
        textarea {
          width: 100%;
          height: 100%;
          resize: none;
          background: white;
        }
      }
      .decoration {
        width: 12.5%;
        height: var(width);
        position: absolute;
        right: 2%;
        bottom: 2%;
        opacity: 10%;
      }
    }
  }
}
</style>
