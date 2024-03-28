import "@/assets/icons/iconfont/iconfont.js";
import ElementPlus from "element-plus";
import "element-plus/theme-chalk/display.css";
import "element-plus/theme-chalk/index.css";
import locale from "element-plus/es/locale/lang/zh-cn";
import { createApp } from "vue";
import { createPinia } from "pinia";
import App from "./App.vue";
import router from "./router";
import * as ElementPlusIcons from "@element-plus/icons-vue";
import axios from "axios";
import "virtual:svg-icons-register";

const app = createApp(App);

Object.keys(ElementPlusIcons).forEach((iconName) => {
  app.component(iconName, ElementPlusIcons[iconName as keyof typeof ElementPlusIcons]);
});

app
  .use(createPinia())
  .use(ElementPlus, {size: "default", locale: locale})
  .use(router)
  .mount("#app");

window.axios = axios;