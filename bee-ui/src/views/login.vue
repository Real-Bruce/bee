<script lang="ts" setup>

import {defineComponent, onMounted, reactive, ref} from "vue";
import {useAppStore} from "@/store";
import {useRouter} from "vue-router";
import {getUuid} from "@/utils/common";
import app from "@/constants/app";
import baseService from "@/service/baseService";
import {setCache} from "@/utils/cache";
import {CacheToken} from "@/constants/cacheKey";
import {ElMessage} from "element-plus";

defineComponent({
  name:"login"
})

const store = useAppStore();
const router = useRouter();
const formRef = ref();

const state = reactive({
  captchaUrl: "",
  loading: false,
  year: new Date().getFullYear()
})

const rules = ref({
  username: [{ require: true, message: "用户名不能为空！", trigger: "blur"}],
  password: [{ require: true, message: "密码不能为空！", trigger: "blur"}],
  captcha: [{ require: true, message: "验证码不能为空！", trigger: "blur"}],
})

const login = reactive({
  username: "",
  password: "",
  captcha: "",
  uuid: ""
})

onMounted(() => {
  store.logout();
  getCaptchaUrl();
})

function getCaptchaUrl() {
  login.uuid = getUuid();
  state.captchaUrl = `${app.api}/captcha?uuid=${login.uuid}`;
}

function onRefreshCode() {
  getCaptchaUrl();
}

function onLogin() {
  formRef.value.validate((valid: boolean) => {
    if (valid) {
      state.loading = true;
      baseService.post("/login", login)
          .then((res) => {
            state.loading = false;
            if (res.code === 0) {
              setCache(CacheToken, res.data, true);
              ElMessage.success("登录成功！");
              router.push("/");
            } else {
              ElMessage.error(res.msg);
            }
          })
          .catch(() => {
            state.loading = false;
            onRefreshCode();
          });
    }
  });
}

</script>

<template>
  <div class="bee-login">
    <div class="bee-login-wrap">
      <div class="bee-login-left hidden-sm-and-down">
        <p class="bee-login-left-title">BEE-SECURITY</p>
      </div>

      <div class="bee-login-right">
        <div class="bee-login-right-main">
          <h4 class="bee-login-right-main-title">登录</h4>
          <el-form ref="formRef" label-width="80px" :status-icon="true" :model="login" :rules="rules" @keyup.enter="login">
            <el-form-item label-width="0" prop="username">
              <el-input v-model="login.username" placeholder="用户名" prefix-icon="user" autocomplete="off"/>
            </el-form-item>
            <el-form-item label-width="0" prop="password">
              <el-input placeholder="密码" v-model="login.password" prefix-icon="lock" autocomplete="off" show-password/>
            </el-form-item>
            <el-form-item label-width="0" prop="captcha">
              <el-space class="bee-login-right-main-code">
                <el-input v-model="login.captcha" placeholder="验证码" prefix-icon="first-aid-kit"></el-input>
                <img style="vertical-align: middle; height: 40px; cursor: pointer" :src="state.captchaUrl" @click="onRefreshCode" alt="" />
              </el-space>
            </el-form-item>
            <el-form-item label-width="0">
              <el-button class="bee-login-right-main-btn" type="primary" size="small" :disabled="state.loading" @click="onLogin">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
    <div class="login-footer">
      <p>Copyright © {{ state.year }} Bruce All rights reserved.</p>
    </div>
  </div>
</template>


<style lang="less" scoped>
@import url("@/assets/theme/base.less");
.bee-login {
  width: 100vw;
  height: 100vh;
  background: #019ec4;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  @media only screen and (max-width: 992px) {
    .bee-login-wrap {
      width: 96% !important;
    }
    .bee-login-right {
      width: 100% !important;
    }
  }

  &-wrap {
    margin: 0 auto;
    width: 1000px;
    box-shadow: -4px 5px 10px rgba(0, 0, 0, 0.4);
    animation-duration: 1s;
    animation-fill-mode: both;
    border-radius: 5px;
    overflow: hidden;
  }

  &-left {
    justify-content: center;
    flex-direction: column;
    background-color: @--color-primary;
    color: #fff;
    float: left;
    width: 50%;

    &-title {
      text-align: center;
      color: #fff;
      font-weight: 300;
      letter-spacing: 2px;
      font-size: 32px;
    }
  }

  &-right {
    border-left: none;
    color: #fff;
    background-color: #fff;
    width: 50%;
    float: left;

    &-main {
      margin: 0 auto;
      width: 65%;
      &-title {
        color: #333;
        margin-bottom: 40px;
        font-weight: 500;
        font-size: 24px;
        text-align: center;
        letter-spacing: 4px;
      }

      &-lang .iconfont {
        font-size: 20px;
        color: #606266;
        font-weight: 800;
        width: 20px;
        height: 20px;
      }

      .el-input__inner {
        border-width: 0;
        border-radius: 0;
        border-bottom: 1px solid #dcdfe6;
      }

      &-code {
        width: 100%;
        .el-space__item:first-child {
          flex: 1;
        }
      }
      &-btn {
        width: 100%;
        height: 45px;
        font-size: 18px !important;
        letter-spacing: 2px;
        font-weight: 300 !important;
        cursor: pointer;
        margin-top: 30px;
        font-family: neo, sans-serif;
        transition: 0.25s;
      }
    }
  }

  .login-footer {
    text-align: center;
    position: absolute;
    bottom: 0;
    padding: 20px;
    color: rgba(255, 255, 255, 0.6);
    p {
      margin: 10px 0;
    }
    a {
      padding: 0 5px;
      color: rgba(255, 255, 255, 0.6);
      &:focus,
      &:hover {
        color: #fff;
      }
    }
  }

  &-left,
  &-right {
    position: relative;
    min-height: 500px;
    align-items: center;
    display: flex;
  }

  @keyframes animate-down {
    0%,
    60%,
    75%,
    90%,
    to {
      animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
    }
    0% {
      opacity: 0;
      transform: translate3d(0, -3000px, 0);
    }
    60% {
      opacity: 1;
      transform: translate3d(0, 25px, 0);
    }
    75% {
      transform: translate3d(0, -10px, 0);
    }
    90% {
      transform: translate3d(0, 5px, 0);
    }
    to {
      transform: none;
    }
  }

  .animate-down {
    animation-name: animate-down;
  }
}
</style>