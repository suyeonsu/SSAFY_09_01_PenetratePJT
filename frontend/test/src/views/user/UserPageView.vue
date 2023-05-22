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
              <tr v-for="n in 10" :key="n">
                <td>{{ n }}</td>
                <td>글제목1</td>
                <td>23</td>
                <td>2023. 5. 20.</td>
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
            <td>SSAFY</td>
          </tr>
          <tr>
            <th>닉네임</th>
            <td>SSAFY</td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>ssafy@naver.com</td>
          </tr>
        </table>
        <div class="menu">
          <div class="item">수정하기</div>
          <div class="item">탈퇴하기</div>
        </div>
      </section>
    </div>
  </div>
</template>
<script>
import ProjectIconCompVue from "@/components/ProjectIconComp.vue";
export default {
  components: {
    ProjectIconCompVue,
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
    background: white;
    border-radius: 1rem;
    overflow: hidden;
    box-shadow: 0 1rem 5rem rgba(0, 0, 0, 0.3);
    .board {
      width: 70%;
      display: flex;
      flex-direction: column;
      .title {
        margin: 1rem 40% 1rem 0;
        padding: 0.5rem 1rem 0.5rem 5%;
        background: $primary;
        font-size: 2rem;
        color: white;
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
              width: 60%;
            }
            &:nth-child(3) {
              width: 10%;
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
                color: white;
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
      color: white;
      font-weight: bold;
      border-left: 3px dashed white;
      .icon {
        width: max-content;
        color: white;
        padding: 3rem 0;
      }
      table {
        width: 100%;
        tr {
          height: 2.5rem;
          display: flex;
          align-items: center;
          box-sizing: border-box;
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
          color: white;
          &:hover {
            transform: scale(1.1);
          }
        }
      }
    }
  }
}
</style>
