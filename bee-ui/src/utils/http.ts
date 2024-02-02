import axios, {AxiosRequestConfig} from "axios";
import app from "@/constants/app";
import {getToken} from "@/utils/cache";
import router from "@/router"
import qs from "qs";
import {ElMessage} from "element-plus";
import {IHttpResponse, IObject} from "@/types/interface";
import {reject} from "lodash";
import {getValueByKeys} from "@/utils/common";

const http = axios.create({
  baseURL: app.api,
  timeout: app.requestTimeout
})

/**
 * 配置request
 */
http.interceptors.request.use(function (config: any) {
    config.headers["X-Request-With"] = "XMLHTTPRequest";
    config.headers["Request-Start"] = new Date().getTime();
    const token = getToken();
    if (token) {
      config.headers["token"] = token;
    }
    // GTE请求添加时间戳
    if (config.method?.toUpperCase() === "GET") {
      config.param = {...config.param, _t: new Date().getTime()}
    }
    if (Object.values(config.headers).includes("application/x-www-form-urlencoded")) {
      config.data = qs.stringify(config.data);
    }
    return config;
  },
    function (error) {
    return Promise.reject(error);
  }
)

/**
 * 配置response
 */
http.interceptors.response.use(
  (response) => {
    // response success
    if (response.data.code === 0) {
      return response;
    }
    // response error
    ElMessage.error(response.data.msg)
    // 自定义状态码
    if (response.data.code === 401) {
      redirectLogin()
    }
    return Promise.reject(new Error(response.data.msg || "Error"));
  },
  (error) => {
    const status = getValueByKeys(error, "response.status", 500);
    const httpCodeLabel: IObject<string> = {
      400: "请求参数错误",
      401: "未授权，请登录",
      403: "拒绝访问",
      404: `请求地址错误：${getValueByKeys(error, "response.config.url", "")}`,
      408: "请求超时",
      500: "API接口报错",
      501: "服务未实现",
      503: "网关不可用",
      504: "网关超时",
      505: "HTTP版本不支持"
    };
    if (error && error.response) {
      console.log("请求错误", error.response.data)
    }
    if (status === 401) {
      redirectLogin();
    }
    return Promise.reject(new Error(httpCodeLabel[status] || "接口错误"));
  }
)

/**
 * 路由重定向到login
 */
const redirectLogin = ()=> {
  router.replace("/login");
  return;
}

export default (o: AxiosRequestConfig): Promise<IHttpResponse> => {
  return new Promise((resolve, reject) => {
    http(o)
      .then((res) => {return resolve(res.data)})
      .catch(reject);
  });
};


