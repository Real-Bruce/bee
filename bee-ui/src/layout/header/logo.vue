<script lang="ts" setup>
import app from '@/constants/app';
import {defineComponent} from 'vue'

interface ILogo {
  logoUrl?: string;
  logoName?: string;
}

/**
 * 顶部logo
 */
defineComponent({
  name: "Logo"
})

const props = defineProps({
  logoUrl: String,
  logoName: {
    type: String,
    default: "logo"
  }
})

</script>

<template>
  <span>
    <span :class="`bee-header-ctx-logo-img-wrap ${'enabled-logo-' + app.enableLogo}`">bee</span>
    <span class="bee-header-ctx-logo"/>
  </span>
  <span class="bee-header-ctx-logo-text">{{ props.logoName }}</span>
</template>