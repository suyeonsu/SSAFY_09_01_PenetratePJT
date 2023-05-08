<template>
  <div class="textInputEl">
    <div :class="{ title: true, filled }">
      <slot />
    </div>
    <input class="value" :type="type" :value="msg" @input="inputHandler" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      filled: false,
      msg: "",
    };
  },
  props: {
    type: {
      type: String,
      default: "text",
    },
  },
  watch: {
    msg(nowMsg) {
      if (nowMsg === "") this.filled = false;
      else this.filled = true;
    },
  },
  methods: {
    inputHandler(event) {
      this.msg = event.target.value;
      this.$emit("change", this.msg);
    },
  },
};
</script>

<style lang="scss" scoped>
.textInputEl {
  position: relative;

  .title {
    position: absolute;
    left: 1rem;
    top: 50%;
    transform: translateY(-50%);
    font-size: inherit;
    transition: 0.1s;
    &.filled {
      font-size: 0.3rem;
      top: 10%;
      transform: none;
    }
  }
  .value {
    // input 요소
    width: 100%;
    padding: 1rem;
    border: 1px solid lightgrey;
    box-sizing: border-box;
    &:hover {
      border: 1px solid $primary;
    }
    &:focus {
      border: 1px solid $primary;
      outline: none;
    }
  }
}
</style>
