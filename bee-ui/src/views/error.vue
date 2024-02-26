<script setup lang="ts">
import {defineComponent} from 'vue'
import {useRoute, useRouter} from "vue-router";
import {IObject} from "@/types/interface";

interface ITip {
  title: string,
  message: string
}

defineComponent({
  name: "error"
})

const route = useRoute();
const router = useRouter();
const { to } = route.query;
const tips: IObject = {
  404: {
    title: "404",
    message: "您访问的页面不存在"
  },
  error: {
    title: "错误",
    message: "访问出错了"
  }
};
const tip: ITip = tips[to?.toString() ?? "error"];

function onBack() {
  router.back();
}

function onToHome() {
  router.replace("/");
}

</script>

<template>
  <div class="bee-error">
    <el-result>
      <template #icon>
        <el-icon style="font-size: 64px; color: #f5222d"><warning/></el-icon>
      </template>
      <template #title>
        <span style="font-size: 48px; font-weight: 800">{{ tip.title }}</span>
      </template>
      <template #sub-title>
        <span style="font-size: 36px">{{ tip.message }}</span>
      </template>
      <template #extra>
        <el-space :size="30">
          <el-button type="info" plain @click="onBack"> 返回 </el-button>
          <el-button type="primary" plain @click="onToHome"> 主页 </el-button>
        </el-space>
      </template>
    </el-result>
  </div>
</template>