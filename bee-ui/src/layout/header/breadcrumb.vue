<script lang="ts" setup>
import {defineComponent, ref, watch} from 'vue'
import {RouteLocationMatched, useRouter} from "vue-router";
import {IObject} from "@/types/interface";
import {getValueByKeys} from "@/utils/common";

defineComponent({
  name: "Breadcrumb",
})

const router = useRouter();
const breadcrumbs = ref<IObject[]>([]);
const {currentRoute} = router;
const firstRoute = (router.options.routes[0] || {}) as RouteLocationMatched;
const home: RouteLocationMatched = firstRoute.children && firstRoute.children.length > 0 ? (firstRoute.children[0] as RouteLocationMatched) : firstRoute;

watch(
    () => currentRoute.value,
    () => { breadcrumbs.value = currentRoute.value.path !== home.path ? getValueByKeys(currentRoute.value, "meta.matched", []) : []; }
)

</script>

<template>
  <el-breadcrumb separator="/" style="padding-top: 4px">
    <el-breadcrumb-item :to="{ path: home.path }">主页</el-breadcrumb-item>
    <el-breadcrumb-item v-for="item in breadcrumbs" :key="item.path">{{ currentRoute.query._mt || item.title || '' }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>