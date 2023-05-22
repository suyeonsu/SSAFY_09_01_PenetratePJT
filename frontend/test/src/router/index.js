import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import TourView from "@/views/tour/TourView.vue";
import SearchListView from "@/views/tour/SearchListView.vue";
import SearchDetailView from "@/views/tour/SearchDetailView.vue";
import JoinView from "@/views/user/JoinView.vue";
import LoginView from "@/views/user/LoginView.vue";
import UserPageView from "@/views/user/UserPageView.vue";
import MyPlaceListView from "@/views/tour/MyPlaceListView.vue";
import MyPlaceDetailView from "@/views/tour/MyPlaceDetailView.vue";
import PlanListView from "@/views/tour/PlanListView.vue";
import PlanDetailView from "@/views/tour/PlanDetailView.vue";
import FreeBoardViewVue from "@/views/board/FreeBoardView.vue";
import FreeBoardListViewVue from "@/views/board/FreeBoardListView.vue";
import FreeBoardEditViewVue from "@/views/board/FreeBoardEditView.vue";
import FreeBoardDetailViewVue from "@/views/board/FreeBoardDetailView.vue";

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
        children: [
          {
            path: ":id",
            name: "planDetail",
            component: PlanDetailView,
          },
        ],
      },
    ],
  },
  {
    path: "/board",
    name: "freeBoard",
    component: FreeBoardViewVue,
    children: [
      {
        path: ":pageNo",
        name: "freeBoardList",
        component: FreeBoardListViewVue,
      },
    ],
  },
  {
    path: "/board/edit/:id",
    name: "freeBoardEdit",
    component: FreeBoardEditViewVue,
  },
  {
    path: "/board/detail/:id",
    name: "freeBoardDetail",
    component: FreeBoardDetailViewVue,
  },
  { path: "/user/login", name: "login", component: LoginView },
  { path: "/user/join", name: "join", component: JoinView },
  { path: "/user/:id", name: "userpage", component: UserPageView },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
