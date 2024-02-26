<script setup lang="ts">
import {defineComponent, onMounted, ref, watch} from 'vue'
import {RouteLocationNormalizedLoaded, useRoute} from "vue-router";

defineComponent({
  name: "iframe"
})

const route = useRoute();
const url = ref("about:_blank");
const loading = ref(false);

watch(
    () => route,
    (vl) => {
      if (vl.path === "/iframe") {
        setUrl(vl);
      }
    }
)

onMounted(() => {
  setUrl(route);
})

function setUrl(route: RouteLocationNormalizedLoaded) {
  const { meta, query } = route;
  loading.value = true;
  if (query.url) {
    url.value = query.url as string;
  } else {
    if (meta && meta.isIframe) {
      url.value = meta.url as string;
    }
  }
}

function load() {
  loading.value = false;
}

</script>

<template>
  <div v-loading="loading">
    <iframe class="iframe" :src="url" @load="load"/>
  </div>
</template>

<style lang="less" scoped>
.iframe {
  min-width: calc(100vh - 130px);
  width: 100%;
  border: 0;
}
</style>