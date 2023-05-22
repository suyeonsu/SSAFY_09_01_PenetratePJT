<template>
  <div class="background">
    <div class="container">
      <main>
        <header>
          <h1 class="title">
            <InputCompVue :defaultMessage="boardStore.nowPost.subject"
              >제목</InputCompVue
            >
          </h1>
          <div class="info">
            <div>조회수: {{ boardStore.nowPost.hit }}</div>
            <div>
              작성시간:
              {{ localeTime }}
            </div>
            <div>작성자: {{ boardStore.nowPost.userid }}</div>
          </div>
          <div class="controllers">
            <template v-if="isauthor">
              <ButtonCompVue @click="savePosting" class="btn"
                >저장하기</ButtonCompVue
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
        this.boardStore.nowPost.regtime
      );
    },
    isauthor() {
      if (this.userStore.userInfo.id != "") {
        if (this.boardStore.nowPost.userid === this.userStore.userInfo.id) {
          return true;
        }
      }
      return false;
    },
  },
  created() {
    this.boardStore.getPostingData(this.$route.params.id);
  },
  methods: {
    savePosting() {
      const flag = confirm("저장하시겠습니까?");
      if (flag) {
        console.log("저장함");
        this.$router.push({
          name: "freeBoardDetail",
          params: { id: this.$route.params.id },
        });
      } else {
        console.log("취소됨");
      }
    },
    removePosting() {
      const flag = confirm("정말 삭제하시겠습니까?");
      if (flag) {
        console.log("삭제됨");
        this.$router.push({ name: "freeBoard" });
      } else {
        console.log("취소됨");
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
