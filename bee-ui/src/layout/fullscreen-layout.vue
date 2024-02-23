<script setup lang="ts">
import {defineComponent} from "vue";
import {useRoute} from "vue-router";

/**
 * 全屏布局
 */
defineComponent({
  name:"FullScreenLayout"
})

const route = useRoute();
</script>

<template>
  <div :class="`bee-fullscreen ${route.query.pop ? 'new-pop-window' : ''}`">
    <router-view/>
  </div>
</template>