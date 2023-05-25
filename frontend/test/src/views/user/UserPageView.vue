<template>
  <div class="background">
    <div class="container">
      <section class="board">
        <div class="title">
          <font-awesome-icon :icon="['fas', 'font-awesome']" />
          최근 작성한 글
        </div>
        <div class="table-container">
          <table>
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>조회수</th>
                <th>작성일시</th>
              </tr>
            </thead>
            <tbody>
              <tr
                @click="goToDetail(item.articleno)"
                v-for="item of recentArticle"
                :key="item.regtime">
                <td>{{ item.articleno }}</td>
                <td>{{ item.subject }}</td>
                <td>{{ item.hit }}</td>
                <td>{{ boardStore.getTimeFormat(item.regtime) }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </section>
      <section class="user-info">
        <ProjectIconCompVue class="icon" />
        <table>
          <tr>
            <th>아이디</th>
            <td>{{ userStore.userInfo.id }}</td>
          </tr>
          <tr>
            <th>이름</th>
            <td class="edit" v-if="isEditName">
              <input type="text" v-model="nextName" />
            </td>
            <td v-else>{{ userStore.userInfo.name }}</td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>{{ userStore.userInfo.email }}</td>
          </tr>
          <template v-if="isEditPassword">
            <tr class="change-password">
              <th>
                현재 <br />
                비밀번호
              </th>
              <td class="edit">
                <input type="text" v-model="currentPassword" />
              </td>
            </tr>
            <tr class="change-password">
              <th>
                새 <br />
                비밀번호
              </th>
              <td class="edit">
                <input type="text" v-model="nextPassword1" />
              </td>
            </tr>
            <tr class="change-password">
              <th>
                비밀번호 <br />
                확인
              </th>
              <td class="edit">
                <input type="text" v-model="nextPassword2" />
              </td>
            </tr>
          </template>
        </table>
        <div class="menu">
          <div @click="edit" v-if="isEditName || isEditPassword" class="item">
            수정 완료하기
          </div>
          <template v-else>
            <div @click="goToEditName" class="item">이름 수정하기</div>
            <div @click="goToEditPassword" class="item">비밀번호 수정하기</div>
            <div class="item">탈퇴하기</div>
          </template>
        </div>
      </section>
    </div>
  </div>
</template>
<script>
import ProjectIconCompVue from "@/components/ProjectIconComp.vue";
import { useUserStore } from "@/store/userStore";
import { useBoardStore } from "@/store/boardStore";
export default {
  components: {
    ProjectIconCompVue,
  },
  setup() {
    const userStore = useUserStore();
    const boardStore = useBoardStore();
    return {
      userStore,
      boardStore,
    };
  },
  data() {
    return {
      recentArticle: [],
      isEditName: false,
      isEditPassword: false,
      nextName: "",
      currentPassword: "",
      nextPassword1: "",
      nextPassword2: "",
    };
  },
  async created() {
    this.recentArticle = await this.boardStore.myRecentList(
      this.$route.params.id
    );
  },
  methods: {
    goToDetail(articleno) {
      this.$router.push({ name: "freeBoardDetail", params: { id: articleno } });
    },
    goToEditName() {
      this.isEditName = true;
      this.isEditPassword = false;
    },
    goToEditPassword() {
      this.isEditName = false;
      this.isEditPassword = true;
    },
    async edit() {
      try {
        if (this.isEditName) {
          if (this.nextName == "") {
            alert("값을 입력해주세요");
          } else {
            await this.userStore.editUserName(this.nextName);
            alert("이름 업데이트 성공!");
            this.isEditName = false;
          }
        } else if (this.isEditPassword) {
          if (this.currentPassword == "") {
            alert("값을 입력해주세요");
          } else if (this.nextPassword1 != this.nextPassword2) {
            alert("입력하신 비밀번호를 다시 확인해주세요.");
          } else {
            await this.userStore.editUserPassword(this.nextPassword1);
            this.isEditPassword = false;
            this.userStore.$reset();
          }
        } else {
          return;
        }
      } catch (error) {
        console.log("로그인 에러");
      }
    },
  },
};
</script>
<style lang="scss" scoped>
.background {
  width: 100%;
  height: 90vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: lightgray;
  .container {
    width: 80%;
    height: 80%;
    margin: 0 auto;
    box-sizing: border-box;
    display: flex;
    background: $background;
    border-radius: 1rem;
    overflow: hidden;
    box-shadow: 0 1rem 5rem rgba(0, 0, 0, 0.3);
    .board {
      width: 70%;
      display: flex;
      flex-direction: column;
      .title {
        margin: 1rem 40% 0 0;
        padding: 0.5rem 1rem 0.5rem 5%;
        background: $primary;
        font-size: 2rem;
        color: $background;
        font-weight: bold;
      }
      .table-container {
        padding: 1rem;
        display: flex;
        flex: 1;
        overflow: hidden;
        table {
          width: 100%;
          height: 100%;
          th {
            font-size: 1.3rem;
            &:nth-child(1) {
              width: 10%;
            }
            &:nth-child(2) {
              width: 55%;
            }
            &:nth-child(3) {
              width: 15%;
            }
            &:nth-child(4) {
              width: 20%;
            }
          }
          th,
          td {
            padding: 10px;
          }
          thead {
            border-bottom: 5px double $primary;
          }
          tbody {
            overflow: scroll;
            tr {
              transition: 0.1s;
              cursor: pointer;
              &:hover {
                background: $primary;
                color: $background;
              }
            }
            td:not(:nth-child(2)) {
              text-align: center;
            }
          }
        }
      }
    }
    .user-info {
      background-color: $primary;
      width: 30%;
      height: 100%;
      padding: 1rem;
      display: flex;
      flex-direction: column;
      align-items: center;
      box-sizing: border-box;
      position: relative;
      color: $background;
      .edit {
        input {
          width: 100%;
          background: $primary;
          border: none;
          color: $background;
          font-size: 1.1rem;
          font-weight: bold;
          border-bottom: 2px solid $background;
          outline: none;
        }
      }
      font-weight: bold;
      border-left: 3px dashed $background;
      .icon {
        width: 2rem;
        color: $background;

        padding: 1rem 0;
      }
      table {
        width: 100%;
        tr {
          height: 2.5rem;
          display: flex;
          align-items: center;
          box-sizing: border-box;
          &.change-password {
            th {
              font-size: 1rem;
            }
          }
          th,
          td {
            display: flex;
            align-items: center;
            height: 100%;
            box-sizing: border-box;
          }
          th {
            font-size: 1.5rem;
            justify-content: flex-end;
            width: 30%;
            border-right: 1px solid $primary;
            padding-right: 1rem;
          }
          td {
            padding-left: 1rem;
          }
        }
      }
      .menu {
        width: 80%;
        position: absolute;
        bottom: 2rem;
        .item {
          width: 100%;
          display: flex;
          height: 3rem;
          justify-content: center;
          align-items: center;
          border-radius: 0.5rem;
          margin-top: 1rem;
          cursor: pointer;
          transition: 0.2s;
          background: $secondary;
          color: $background;
          &:hover {
            transform: scale(1.1);
          }
        }
      }
    }
  }
}
</style>
