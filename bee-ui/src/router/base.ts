import {RouteRecordRaw} from "vue-router";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    component: () => import("@/layout/layout.vue"),
    redirect: "home",
    meta: { title: "工作台", icon: "icon-desktop"},
    children: [
      {
        path: "/home",
        component: () => import("@/views/home.vue"),
        meta: { title: "主页", icon: "icon-home" }
      }
    ]
  },
  {
    path: "/login",
    component: () => import("@/views/login.vue"),
    meta: {title: "登录", isNavigationMenu: false}
  },
  {
    path: "/user/password",
    component: () => import("@/views/sys/user-update-password.vue"),
    meta: { title: "修改密码", requireAuth: true, isNavigationMenu: false }
  },
  {
    path: "/iframe/:id?",
    component: () => import("@/views/iframe.vue"),
    meta: {title: "iframe", isNavigationMenu: false}
  },
  {
    path: "/error",
    name: "error",
    component: () => import("@/views/error.vue"),
    meta: {title: "错误界面", isNavigationMenu: false}
  }
];

export const errorRoute: Array<RouteRecordRaw> = [
  {
    path: "/:path(.*)*",
    redirect: { path: "/error", query: {to: 404}, replace: true},
    meta: { isNavigationMenu: false }
  }
]

export default routes