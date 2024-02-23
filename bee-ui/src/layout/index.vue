<script setup lang="ts">
import {computed, defineComponent, reactive} from 'vue'
import {useMediaQuery} from "@vueuse/core";
import {getThemeConfigCache, getThemeConfigCacheByKey, getThemeConfigToClass} from "@/utils/theme";
import {EMitt, ESidebarLayoutEnum, EThemeSetting} from "@/constants/enum";
import {RouteRecordRaw, useRouter} from "vue-router";
import {useAppStore} from "@/store";
import emits from "@/utils/emits";
import {getValueByKeys} from "@/utils/common";
import BaseHeader from "@/layout/header/base-header.vue";
import BaseSidebar from "@/layout/sidebar/base-sidebar.vue";
import MobileSidebar from "@/layout/sidebar/mobile-sidebar.vue";
import BaseView from "@/layout/view/base-view.vue";

/**
 * 多标签页布局
 */
defineComponent({
  name: "Layout"
})

const isMobile = useMediaQuery("max-width: 768px");
const themeCache = getThemeConfigCache();
const sidebarLayoutCache = getThemeConfigCacheByKey(EThemeSetting.NavLayout, themeCache);
const router = useRouter();
const store = useAppStore();
const  state = reactive({
  isShowNav: sidebarLayoutCache != ESidebarLayoutEnum.Top,
  sidebarLayout: sidebarLayoutCache,
  themeClass: getThemeConfigToClass(themeCache),
  loading: false,
  mixLayoutRoutes: router.options.routes.find((item: RouteRecordRaw) => item.path === "/") ?.children ?? ([] as RouteRecordRaw[])
})

const containerClassNames = computed(() => {
  Object.values(state.themeClass).concat(isMobile.value ? ["ui-mobile"] : []).join(" ");
})

emits.on(EMitt.OnSelectHeaderNavMenuByMixNav, (path) => {
  state.mixLayoutRoutes = store.state.routes.find((item: RouteRecordRaw) => item.path === path)?.children ?? [];
});

emits.on(EMitt.OnSetTheme, ([type, value]) => {
  state.themeClass[type] = "ui-" + value;
})

emits.on(EMitt.OnSetNavLayout, (vl) => {
  state.sidebarLayout = vl;
  state.isShowNav = vl !== ESidebarLayoutEnum.Top;
  if (vl === ESidebarLayoutEnum.Mix) {
    const currRoute = getValueByKeys(getValueByKeys(router.currentRoute.value.meta, "matched", [])[0], "path", "");
    state.mixLayoutRoutes = store.state.routes.finds((item: RouteRecordRaw) => item.path === currRoute)?.children ?? [];
  }
})

emits.on(EMitt.OnLoading, (vl) => {
  state.loading = vl;
})

</script>

<template>
  <el-container :class="`bee ${containerClassNames}`" v-loading="state.loading" element-loading-background="#0000" element-loading-lock="true" element-loading-custom="bee-loading">
    <el-header class="bee-header" height="550px">
      <base-header/>
    </el-header>
    <el-container class="bee-body">
      <el-aside v-if="state.isShowNav" class="bee-sidebar hidden-xs-only" width="auto">
        <base-sidebar v-if="state.sidebarLayout === ESidebarLayoutEnum.Left" :router="true" mode="vertical" :is-mobile="false"/>
        <base-sidebar v-else :menus="state.mixLayoutRoutes" :router="true" mode="vertical" is-mobile="false"/>
      </el-aside>
      <div class="bee-sidebar bee-sidebar-mobile hidden-sm-and-up show-xs-only">
        <mobile-sidebar/>
      </div>
      <el-container class="bee-view-container">
        <el-main class="bee-view">
          <base-view/>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>