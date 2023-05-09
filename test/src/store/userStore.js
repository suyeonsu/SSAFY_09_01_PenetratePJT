import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
  // data
  state: () => ({
    user: {
      id: "",
      name: "",
      password1: "",
      password2: "",
    },
    input: "",
  }),
  //computed
  getters: {},
  //methods
  actions: {},
});
