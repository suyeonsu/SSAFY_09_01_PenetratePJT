<template>
  <div class="join">
    <form>
      <input type="text" />
      <span>아이디 중복여부: {{ duplicateMessage }}</span>
      <input type="password" />
      <input type="password" />
      <button>회원 가입하기</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      duplicateMessage: "사용 가능",
    };
  },
};
</script>

<style lang="scss" scoped></style>
