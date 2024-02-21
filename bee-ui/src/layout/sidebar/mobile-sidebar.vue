<script lang="ts">
import {defineComponent, reactive} from 'vue'
import BaseSidebar from "@/layout/sidebar/base-sidebar.vue";
import Logo from "@/layout/header/logo.vue";
import {EMitt} from "@/constants/enum";
import emits from "@/utils/emits";
import logoUrl from "@/assets/images/logo.png"

export default defineComponent({
  name: "MobileSidebar",
  components: { BaseSidebar,Logo },
  setup() {
    const state = reactive({
      show: true
    });
    emits.on(EMitt.OnMobileOpenSidebar, () => {
      state.show = true;
    });
    const onSelect = () => {
      state.show = false;
    };
    return { state, onSelect, logoUrl }
  }
})
</script>

<template>
  <el-drawer v-model="state.show" :append-to-body="false" size="230" :with-header="false" direction="ltr" class="bee-setting-wrap">
    <div class="bee-header-ctx-logo bee-header-cts-logo-mobile">
      <logo :logoUrl="logoUrl" logoName="bee"/>
    </div>
    <div class="bee-sidebar-mobile-inner" style="overflow: auto; height: calc(100vh - 50px); width: initial !important;">
      <base-sidebar :router="true" mode="vertical" :isMobile="true" :onSelect="onSelect"/>
    </div>
  </el-drawer>
</template>
