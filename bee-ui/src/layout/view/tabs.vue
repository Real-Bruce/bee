<script setup lang="ts">
import {defineComponent, reactive, watch} from "vue";
import {RouteLocationMatched, useRouter} from "vue-router";
import {useAppStore} from "@/store";
import {IObject} from "@/types/interface";
import emits from "@/utils/emits";
import {EMitt} from "@/constants/enum";
import {arrayToObject} from "@/utils/common";
import {findIndex} from "lodash";
import {ElMessage} from "element-plus";
import SvgIcon from "@/components/svg-icon/index.vue";
import App from "@/App.vue";

/**
 * Tab标签页
 */
defineComponent({
  name: "Tabs"
});

const props = defineProps({
  tabs: Array,
  activeTabName: String
});

const ops = [
  {label: "关闭当前标签页", value: 5, icon: "close"},
  {label: "关闭当前标签页", value: 1, icon: "close"},
  {label: "关闭当前标签页", value: 4, icon: "circle-close"},
];
const router = useRouter();
const store = useAppStore();
const firstRoute = (router.options.routes[0] || {}) as RouteLocationMatched;
const home: RouteLocationMatched = firstRoute.children && firstRoute.children.length > 0 ? (firstRoute.children[0] as RouteLocationMatched) : firstRoute;
const defaultTab = {label: "", value: home.path};
const state = reactive({
  activeTabName: props.activeTabName || defaultTab.value,
  tabs: (props.tabs && props.tabs.length ? props.tabs : [defaultTab]) as IObject[]
});

watch(
    () => state.tabs,
    (res) => {
      store.updateState({tabs: res})
    },
    {deep: true}
)

emits.on(EMitt.OnPushMenuToTabs, (route) => {
  const path: string = route.value;
  if (path.includes("/error")) {
    return;
  }

  const tabKeys: IObject<number> = arrayToObject(state.tabs, "value", () => 1);
  if (!tabKeys[path]) {
    state.tabs.push(route);
  }
  if (state.activeTabName !== path) {
    state.activeTabName = path;
  }
});

emits.on(EMitt.OnCloseCurrTab, () => {
  onClose(5);
})

function onTabClick(tab: any) {
  tab.props.name && router.push(tab.props.name);
}

function onTabRemove(targetName: string) {
  const index = findIndex(state.tabs, (item) => item.value === targetName);
  if (state.tabs.length > 1) {
    updateClosedTabs([...store.state.closedTabs, targetName], false);
    if (state.activeTabName === targetName) {
      const toIndex = index === 0 ? index + 1 : index - 1;
      state.activeTabName = state.tabs[toIndex].value;
      router.push(state.activeTabName);
    }
    state.tabs.splice(index, 1);
  } else {
    ElMessage({
      type: "error",
      message: "仅剩1个标签不支持关闭",
      offset: 0
    })
  }
}

function updateClosedTabs(closedTabs: any[], isTransform = true) {
  if (isTransform) {
    closedTabs = closedTabs.map((item) => item.value);
  }
  store.updateState({closedTabs});
}

function onClose(value: number) {
  let index = null;
  const rawTabs = state.tabs;
  switch (value) {
      // 其他
    case 1:
      state.tabs = state.tabs.filter((item) => [home.path, state.activeTabName].includes(item.value));
      updateClosedTabs(rawTabs.filter((item) => ![home.path, state.activeTabName].includes(item.value)));
      break;
      // 右侧
    case 2:
      index = findIndex(state.tabs, (item) => item.value === state.activeTabName);
      state.tabs.splice(index + 1, state.tabs.length - (index + 1));
      updateClosedTabs(rawTabs.splice(index + 1));
      break;
      // 左侧
    case 3:
      index = findIndex(state.tabs, (item) => item.value === state.activeTabName);
      state.tabs.splice(1, index - 1);
      updateClosedTabs(rawTabs.slice(1, index - 1));
      break;
      // 全部
    case 4:
      state.tabs = [defaultTab];
      state.activeTabName = defaultTab.value;
      updateClosedTabs(rawTabs);
      router.push(state.activeTabName);
      break;
      // 当前
    case 5:
      if (state.activeTabName !== defaultTab.value) {
        updateClosedTabs([...store.state.closedTabs, state.activeTabName], false);
        index = findIndex(state.tabs, (item) => item.value === state.activeTabName);
        state.tabs.splice(index, 1);
        state.activeTabName = state.tabs[state.tabs.length - 1].value;
        router.push(state.activeTabName);
      }
      break;
    default:
      break;
  }
}

</script>

<template>
  <div class="bee-view-tab-wrap">
    <el-tabs class="bee-view-tab" v-model="state.activeTabName" @tab-click="onTabClick" @tab-remove="onTabRemove">
      <el-tab-pane :name="home.path" :closable="false">
        <template #label>
          <svg-icon name="home"/>
        </template>
      </el-tab-pane>
      <el-tab-pane v-for="item in state.tabs.slice(1)" :key="item.value" :label="item.label" :name="item.value"
                   :closable="true"/>
    </el-tabs>
    <el-dropdown trigger="click" placement="bottom-end" class="bee-view-tab-ops" @command="onClose">
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item v-for="item in ops" :key="item.value" :icon="item.icon" :command="item.value">
            {{ item.label }}
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
      <span class="el-dropdown-link">
        <el-icon class="el-icon--right"><arrow-down/></el-icon>
      </span>
    </el-dropdown>
  </div>
</template>