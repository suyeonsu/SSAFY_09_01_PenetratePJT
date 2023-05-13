import { defineStore } from "pinia";
import { ref } from "vue";

export const useTourStore = defineStore("tour", () => {
  const places = ref([]);

  return { places };
});
