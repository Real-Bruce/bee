<script lang="ts" setup>
import {defineComponent, reactive} from 'vue'
import {useAppStore} from "@/store";
import {getThemeConfigCacheByKey} from "@/utils/theme";
import logo from "@/assets/images/logo.png"
import Logo from "./logo.vue"
import {EMitt, ESidebarLayoutEnum, EThemeSetting} from "@/constants/enum";
import emits from "@/utils/emits";
import "@/assets/less/header.less"
import CollapseSidebarBtn from "@/layout/header/collapse-sidebar-btn.vue";
import BaseSidebar from "@/layout/sidebar/base-sidebar.vue";
import HeaderMixNavMenus from "@/layout/header/header-mix-nav-menus.vue";
import Breadcrumb from "@/layout/header/breadcrumb.vue";
import Expand from "@/layout/header/expand.vue";

/**
 * 顶部主区域
 */
defineComponent({
  name: "Header"
})

const store = useAppStore();
const state = reactive({
  sidebarLayout: getThemeConfigCacheByKey(EThemeSetting.NavLayout)
});

emits.on(EMitt.OnSetNavLayout, (vl) => {
  state.sidebarLayout = vl;
})

function onRefresh() {
  emits.emit(EMitt.OnSetReloadTabPage);
}

</script>

<template>
  <div class="bee-header-ctx">
    <div class="bee-header-ctx-logo hidden-xs-only">
      <logo :logo-url="logo">bee security manage</logo>
    </div>
    <div class="bee-header-right">
      <div class="bee-header-right-left">
        <div class="bee-header-right-items bee-header-action" :style="`display:${state.sidebarLayout === ESidebarLayoutEnum.Top ? 'none' : ''}`">
          <collapse-sidebar-btn/>
          <div @click="onRefresh" style="cursor: pointer">
            <div class="el-badge">
              <el-icon><refresh-right/></el-icon>
            </div>
          </div>
        </div>
        <div class="bee-header-right-left-br ele-scrollbar-hide hidden-xs-only">
          <base-sidebar v-if="state.sidebarLayout === ESidebarLayoutEnum.Top" mode="horizontal" :router="true"/>
          <header-mix-nav-menus v-else-if="state.sidebarLayout === ESidebarLayoutEnum.Mix"/>
          <breadcrumb v-else/>
        </div>
        <div style="flex-shrink: 0">
          <expand :user-name="store.state.user.username"/>
        </div>

      </div>
    </div>
  </div>
</template>
