<template>
  <button :class="{ btn: true, fill }">
    <slot />
  </button>
</template>

<script>
export default {
  props: {
    color: {
      type: String,
    },
    fill: Boolean,
  },
};
</script>

<style scoped lang="scss">
$now: $primary;
.btn {
  box-sizing: border-box;
  padding: 0.5rem 1rem;
  border: 1px solid $now;
  background-color: transparent;
  color: $now;
  font-size: 1rem;
  font-weight: bold;
  border-radius: 10px;
  transition: 0.5s;
  cursor: pointer;
  &:hover,
  &.fill {
    background-color: $now;
    color: $background;
  }
  &.fill:hover {
    background-color: $background;
    color: $now;
  }
}
</style>
