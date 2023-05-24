<template>
  <div @click="clickHandler">
    <font-awesome-icon :icon="['far', 'circle-dot']" />
  </div>
</template>

<script>
import { useTourStore } from "@/store/tourStore";
export default {
  setup() {
    const tourStore = useTourStore();
    return {
      tourStore,
    };
  },
  methods: {
    clickHandler() {
      this.tourStore.moveToCurrent();
    },
  },
};
</script>

<style lang="scss" scoped>
div {
  width: 100px;
  height: 100px;
  background: lightgray;
  border: 1px solid black;
  display: flex;
  justify-content: center;
  align-content: center;
  font-size: 1.5rem;
}
</style>
