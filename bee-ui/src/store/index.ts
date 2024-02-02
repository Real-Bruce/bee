import {defineStore} from "pinia";
import {IObject} from "@/types/interface";
import baseService from "@/service/baseService";
import {mergeServerRoute} from "@/utils/router";
import {getSysRouteMap} from "@/router";
import {removeCache} from "@/utils/cache";
import {CacheToken} from "@/constants/cacheKey";

export const useAppStore = defineStore("useAppStore", {
  state: () => ({
    state: {
      // 是否登录
      appIsLogin: false,
      // app数据是否就绪
      appIsReady: false,
      // app是否开始渲染
      appIsRender: false,
      // 权限集合
      permission: [],
      // 用户信息
      user: {
        createDate: "",
        deptId: "",
        deptName: "",
        email: "",
        gender: 0,
        headUrl: "",
        id: "",
        mobile: "",
        postIdList: "",
        realName: "",
        roleIdList: "",
        status: 0,
        superAdmin: 0,
        username: ""
      },
      // 字典
      dicts: [],
      // 最终路由集合
      routes: [],
      // 菜单集合
      menus: [],
      // url对应的标题信息
      routeToMeta: {},
      // tab标签集合
      tabs: [],
      // tab当前聚焦页
      activeTabName: "",
      // 已经关闭的tab页集合
      closeTabs: []

    } as IObject
  }),
  actions: {
    updateState(data: IObject) {
      Object.keys(data).forEach((x: string) => {
        this.state[x] = data[x];
      });
    },
    initApp() {
      return Promise.all([
        // 加载菜单
        baseService.get("/sys/menu/nav"),
        // 加载权限
        baseService.get("/sys/menu/permission"),
        // 加载用户信息
        baseService.get("/sys/user/info"),
        // 加载字典
        baseService.get("/sys/dict/type/all"),
      ]).then(([menus, permissions, user, dicts]) => {
        if (user.code !== 0) {
          console.error("初始化用户数据失败", user.msg)
        }
        const [routes, routeToMeta] = mergeServerRoute(menus.data || [], getSysRouteMap());
        this.updateState({
          permission: permissions.data || [],
          user: user.data || {},
          dicts: dicts.data || [],
          routeToMeta: routeToMeta || {},
          menus: []
        });
        return routes;
      });
    },

    // 退出
    logout() {
      removeCache(CacheToken, true);
      this.updateState({
        appIsLogin: false,
        permissions: [],
        user: {},
        dicts: [],
        menus: [],
        routes: [],
        tabs: [],
        activeTabName: ""
      })
    }
  }
})