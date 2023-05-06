import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import TourView from "@/views/tour/TourView.vue";
import HotPlaceListView from "@/views/tour/HotPlaceListView.vue";
import HotPlaceDetailView from "@/views/tour/HotPlaceDetailView.vue";
import JoinView from "@/views/user/JoinView.vue";
import LoginView from "@/views/user/LoginView.vue";
import MyPageView from "@/views/user/MyPageView.vue";

const routes = [
  { path: "/", name: "home", component: HomeView },
  { path: "/tour", name: "tour", component: TourView },
  {
    path: "/hotPlace/page/:pageNo",
    name: "hotPlaceList",
    component: HotPlaceListView,
  },
  {
    path: "/hotPlace/detail/:id",
    name: "hotPlaceDetail",
    component: HotPlaceDetailView,
  },
  { path: "/user/login", name: "login", component: LoginView },
  { path: "/user/join", name: "join", component: JoinView },
  { path: "/user/:id", name: "mypage", component: MyPageView },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
