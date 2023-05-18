import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import TourView from "@/views/tour/TourView.vue";
import SearchListView from "@/views/tour/SearchListView.vue";
import SearchDetailView from "@/views/tour/SearchDetailView.vue";
import JoinView from "@/views/user/JoinView.vue";
import LoginView from "@/views/user/LoginView.vue";
import MyPageView from "@/views/user/MyPageView.vue";
import MyPlaceListView from "@/views/tour/MyPlaceListView.vue";
import MyPlaceDetailView from "@/views/tour/MyPlaceDetailView.vue";
import PlanListView from "@/views/tour/PlanListView.vue";
import PlanDetailView from "@/views/tour/PlanDetailView.vue";

const routes = [
  { path: "/", name: "home", component: HomeView },
  {
    path: "/tour",
    name: "tour",
    component: TourView,
    children: [
      {
        path: "search",
        name: "searchList",
        component: SearchListView,
        children: [
          {
            path: ":id",
            name: "searchDetail",
            component: SearchDetailView,
          },
        ],
      },
      {
        path: "myPlace",
        name: "myPlaceList",
        component: MyPlaceListView,
        children: [
          {
            path: ":id",
            name: "myPlaceDetail",
            component: MyPlaceDetailView,
          },
        ],
      },
      {
        path: "plan",
        name: "planList",
        component: PlanListView,
        childeren: [
          {
            path: ":id",
            name: "planDetail",
            component: PlanDetailView,
          },
        ],
      },
    ],
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
