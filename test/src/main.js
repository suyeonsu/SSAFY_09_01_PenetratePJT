import { createApp } from "vue";
import App from "./App.vue";

// pinia
import { createPinia } from "pinia";
const pinia = createPinia();

// vue-router
import router from "./router";

// fontawesome 사용 library
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

// fontawesome config js 파일
import "@/fontAwesome";

createApp(App)
  .component("font-awesome-icon", FontAwesomeIcon)
  .use(pinia)
  .use(router)
  .mount("#app");
