import {createApp} from "vue";
import App from "@/App.vue";
import router from "./router";
import ElementPlus from "element-plus";
import {createPinia} from "pinia";

import * as ElementPlusIcons from "@element-plus/icons-vue";
import axios from "axios";
import "virtual:svg-icons-register"

const app = createApp(App);

Object.keys(ElementPlusIcons).forEach((iconName) => {
  app.component(iconName, ElementPlusIcons[iconName as keyof typeof ElementPlusIcons]);
})

app
  .use(createPinia())
  .use(ElementPlus, {size: "default"})
  .use(router)
  .mount("#app");

window.axios = axios