import appPack from "../../package.json"
import {getValueByKeys} from "@/utils/common";
export default {
  /**
   * 系统版本号，自动读取package.json内的version字段
   */
  version: appPack.version,
  /**
   * 系统默认语言
   */
  defaultLang: "zh-CN",
  /**
   * api请求地址，此处读取env环境变量中的VITE_APP_API，优先使用全局变量windows.SITE_CONFIG.apiURL钩子，可以在index.html内配置
   */
  api: getValueByKeys(window, "SITE_CONFIG.apiURL") || import.meta.env.VITE_APP_API,
  /**
   * 是否展示logo，尺寸32*32，路径@/assert/images/logo.png
   */
  enableLogo: false,
  /**
   * 是否开启页面缓存
   */
  enableKeepAlive: true,
  /**
   * 网络请求超时时间，单位ms
   */
  requestTimeout: 30000,
  /**
   * 全屏渲染页面
   */
  fullscreenPages: ["/login"]
};