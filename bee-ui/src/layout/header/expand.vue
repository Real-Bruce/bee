<script lang="ts" setup>
import "@/assets/less/header.less"
import {defineComponent} from 'vue'
import {useRouter} from "vue-router";
import {useAppStore} from "@/store";
import {useFullscreen} from "@vueuse/core";
import {ElMessageBox} from "element-plus";
import baseService from "@/service/baseService";
import SvgIcon from "@/components/svg-icon/index.vue";
import userLogo from "@/assets/images/logo.png"

interface IExpand {
  userName?:string;
}

/**
 * 顶部右侧扩展区域
 */
defineComponent({
  name: "Expand"
})

const props = defineProps({
  userName: String
})

const router = useRouter();
const store = useAppStore();
const { isFullscreen, toggle } = useFullscreen();

function onClickMenus (path: string) {
  if (path === '/login') {
    ElMessageBox.confirm("确定进行[退出操作]？", "提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning"
    }).then(() => {
      baseService.post("/logout").finally(() => {
        router.push(path)
      })
    }).catch(() =>{})
  } else {
    router.push(path)
  }
}

</script>

<template>
  <div class="bee-header-right-items">
    <div @click="toggle" class="hidden-xs-only">
      <span>
        <svg-icon :name="isFullscreen ? 'tuichuquanping' : 'fullscreen2'"/>
      </span>
    </div>
    <div style="display: flex; justify-content: center; align-items: center">
      <image :src="userLogo" :alt="props.userName" style="width: 30px; height: 30px; border-radius: 50%; margin-top: 3px; margin-right: 5px"/>
      <el-dropdown @command="onClickMenus">
        <template #dropdown>
          <el-dropdown-item icon="lock" command="/user/password">修改密码</el-dropdown-item>
          <el-dropdown-item icon="switch-button" divided command="/login">退出登录</el-dropdown-item>
        </template>
        <span class="el-dropdown-link" style="display: flex">
          {{ props.userName }}
          <el-icon class="el-icon--right" style="font-size: 14px"><arrow-down/></el-icon>
        </span>
      </el-dropdown>
    </div>
  </div>
</template>

<style scoped>

</style>