<script lang="ts" setup>
import {defineComponent, reactive} from 'vue'
import SvgIcon from "@/components/svg-icon";
import {getThemeConfigCacheByKey, setThemeConfigToCache} from "@/utils/theme";
import {EMitt, EThemeSetting} from "@/constants/enum";
import emits from "@/utils/emits";

/**
 * 侧边栏展开收起按钮
 */
defineComponent({
  name: "CollapseSidebarBtn"
})

const state = reactive({
  collapseSidebar: getThemeConfigCacheByKey(EThemeSetting.SidebarCollapse)
});

function onClickSidebar() {
  const key = EThemeSetting.SidebarCollapse;
  state.collapseSidebar = !state.collapseSidebar;
  emits.emit(EMitt.OnSwitchLeftSidebar);
  emits.emit(EMitt.OnSetTheme, [key, key + "-" + state.collapseSidebar]);
  setThemeConfigToCache(key, state.collapseSidebar);
}

function onclickSidebarByMobile() {
  emits.emit(EMitt.OnMobileOpenSidebar);
}

</script>

<template>
  <div class="hidden-xs-only" @click="onClickSidebar">
    <svg-icon :name="state.collapseSidebar ? 'indent' : 'outdent'"/>
  </div>
  <div class="hidden-sm-and-up show-xs-only" @click="onclickSidebarByMobile">
    <svg-icon name="icon-indent"/>
  </div>
</template>