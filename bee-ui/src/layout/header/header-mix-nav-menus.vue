<script setup lang="ts">
import {computed, defineComponent, reactive, watch} from "vue";
import {useAppStore} from "@/store";
import {RouteRecordRaw, useRoute, useRouter} from "vue-router";
import {getValueByKeys} from "@/utils/common";
import {getThemeConfigCacheByKey} from "@/utils/theme";
import {EMitt, ESidebarLayoutEnum, EThemeSetting} from "@/constants/enum";
import emits from "@/utils/emits";
import BaseSidebar from "@/layout/sidebar/base-sidebar.vue";

/**
 * 顶部菜单导航栏，混合布局模式时使用
 */
defineComponent({
  name: "HeaderMixNavMenus"
})

const store = useAppStore;
const router = useRouter();
const route = useRoute();
const routers = router.options.routes;
const state = reactive({
  currRoute: getValueByKeys(getValueByKeys(router.currentRoute.value.meta, "matched", [])[0], "path", "")
});

watch(
    () => route.path,
    () => {
      if (getThemeConfigCacheByKey(EThemeSetting.NavLayout) === ESidebarLayoutEnum.Mix) {
        const matchedRoute = getValueByKeys(getValueByKeys(router.currentRoute.value.meta, "matched",[])[0], "path", "");
        if (matchedRoute) {
          state.currRoute = matchedRoute;
          emits.emit(EMitt.OnSelectHeaderNavMenuByMixNav, matchedRoute);
        }
      }
    }
);

const topHeaderMenus = computed(() => {
  const rs: any[] = [];
  store.state.routes.forEach((item: RouteRecordRaw) => {
    rs.push({
      path: item.path,
      children: [],
      meta: item.meta ? item.meta : {}
    });
  });
  return rs
})

function onSelect(path: string) {
  const curr = routers.find((item: RouteRecordRaw) => item.path === path);

  if (!curr?.children?.length) {
    router.push(path);
  } else {
    state.currRoute = path;
    emits.emit(EMitt.OnSelectHeaderNavMenuByMixNav, path);
  }
}


</script>

<template>
  <base-sidebar mode="horizontal" :menus="topHeaderMenus" :router="false" :curr-rout="state.currRoute" is-mobile="false" :on-select="onSelect"/>
</template>

<style scoped>

</style>