<template>
  <main class="home">
    <div class="title">
      <h3>당신의 목적지로</h3>
      <h1>
        <span class="upper">여긴</span>
        <span class="lower">어떤데 -</span>
      </h1>
      <router-link class="button" :to="{ name: 'searchList' }">
        둘러보기
        <font-awesome-icon :icon="['fas', 'chevron-right']" />
      </router-link>
    </div>
    <div class="aux">
      <span>어디가 가고싶은건데</span>
      <span>어긴 어떤데</span>
      <span>그럼 여긴 어떤데</span>
      <span>그럼 여긴..</span>
      <span>ㄱ..</span>
    </div>
  </main>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.home {
  height: 90vh;
  position: relative;
  box-sizing: border-box;
  background: $background;
  .title {
    position: absolute;
    left: 6rem;
    top: 40%;
    transform: translateY(-50%);
    h3 {
      font-size: 1.2rem;
      margin-bottom: 1.5rem;
      font-family: "S-CoreDream-3Light";
    }
    h1 {
      font-size: 5rem;
      display: flex;
      flex-direction: column;
      color: $primary;
      .upper {
        font-size: 6rem;
        color: gray;
      }
      margin-bottom: 7rem;
    }
    .button {
      color: $primary;
      text-decoration: none;
      font-size: 1.5rem;
      transition: 0.3s;
      &:hover {
        color: $secondary;
      }
    }
  }
  .aux {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    position: absolute;
    right: 6rem;
    top: 50%;
    line-height: 1.5rem;
    font-weight: bold;
    color: rgba(0, 0, 0, 0.8);
    font-family: "S-CoreDream-3Light";
  }
}
</style>
