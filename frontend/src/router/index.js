import Vue from "vue";
import VueRouter from "vue-router";
// import MainView from "@/views/MainView.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    redirect: "/info",
    component: () => import("@/views/MainView.vue"),
    children: [
      {
        path: "info",
        name: "infoView",
        component: () => import("@/components/sidebar/InfoView.vue"),
      },
      {
        path: "search",
        name: "aptView",
        component: () => import("@/components/sidebar/AptView.vue"),
      },
      // 상세정보
      {
        path: "search/:aptCode",
        name: "aptDetail",
        component: () => import("@/components/sidebar/AptDetailView.vue"),
      },
    ],
  },
  {
    path: "/admin",
    name: "admin",
    component: () => import("@/views/AdminView.vue"),
  },
  {
    path: "/user",
    name: "user",
    redirect: "/user/login",
    component: () => import("@/views/UserView.vue"),
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/LoginView.vue"),
      },
      {
        path: "join",
        name: "join",
        component: () => import("@/components/user/JoinView.vue"),
      },
      {
        path: "detail/:id",
        name: "detail",
        component: () => import("@/components/user/UserDetailView.vue"),
      },
      {
        path: "/admin/detail",
        name: "adminDetail",
        props: true,
        component: () =>
          import("@/components/user/admin/adminUserDetailView.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "register",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:no",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:no",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
