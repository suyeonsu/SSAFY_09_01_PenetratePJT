import { createApp } from "vue";
import App from "./App.vue";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

// vue-router
import router from "./router";

// pinia
import { createPinia } from "pinia";
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

// fontawesome 사용 libravry
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

// fontawesome config js 파일
import "@/fontAwesome";

createApp(App)
  .component("font-awesome-icon", FontAwesomeIcon)
  .provide("router", router) // pinia에서 router 사용하기 위함
  .use(pinia)
  .use(router)
  .mount("#app");
