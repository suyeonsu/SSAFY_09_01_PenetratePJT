<template>
  <section>
    <article class="rate-graphic">
      <div v-for="n in full" :key="n">
        <font-awesome-icon :icon="['fas', 'star']" />
      </div>
      <div v-if="half">
        <font-awesome-icon :icon="['fas', 'star-half-stroke']" />
      </div>
      <div v-for="n in blank" :key="n">
        <font-awesome-icon :icon="['far', 'star']" />
      </div>
      <div>{{ this.rate }}</div>
    </article>
  </section>
</template>

<script>
export default {
  props: {
    rate: Number,
  },
  data() {
    return {
      total: 5,
    };
  },
  computed: {
    full() {
      console.log("풀:", Math.trunc(this.rate));
      return Math.trunc(this.rate);
    },
    half() {
      if (this.rate % 1 > 0) {
        return true;
      } else return false;
    },
    blank() {
      return this.total - this.full;
    },
  },
};
</script>

<style lang="scss" scoped>
section {
  width: 100%;
}
</style>
