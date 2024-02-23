<script setup lang="ts">
import {defineComponent, reactive, ref} from "vue";
import {useAppStore} from "@/store";
import {useRoute} from "vue-router";
import {getThemeConfigCacheByKey} from "@/utils/theme";
import {EMitt, EThemeSetting} from "@/constants/enum";
import emits from "@/utils/emits";
import Tabs from "./tabs.vue"
import app from "@/constants/app";

/**
 * 视图框架
 */
defineComponent({
  name: "View"
})

const store = useAppStore();
const route = useRoute();
const state = reactive({
  openTabPage: getThemeConfigCacheByKey(EThemeSetting.OpenTabPage)
});
const routerKeys = ref({} as any);
const enabledKeepAlive = app.enableKeepAlive;

emits.on(EMitt.OnSetThemeTabsPage, (vl) => {
  state.openTabPage = vl;
});
emits.on(EMitt.OnReloadTabPage, () => {
  routerKeys.value[route.fullPath] = new Date().getTime();
});

</script>

<template>
  <tabs v-if="state.openTabPage" :tabs="store.state.tabs" :active-tab-name="store.state.activeTabName"/>
  <div class="bee-view-ctx">
    <el-card shadow="never" class="bee-view-ctx-cards">
      <router-view v-slot="{ Component }">
        <keep-alive v-if="enabledKeepAlive">
          <component :is="Component" :key="routerKeys[$route.fullPath] || $route.fullPath"/>
        </keep-alive>
        <component :is="Component" v-if="enabledKeepAlive"/>
      </router-view>
    </el-card>
  </div>
</template>