import { defineStore } from "pinia";

export const useMainStore = defineStore("main", {
  state: () => ({
    primary: "#00d2d3",
    secondary: "#ff9f43",
  }),
  getters: {},
  actions: {},
});
