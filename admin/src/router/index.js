import { createRouter, createWebHistory } from 'vue-router'
import store from "@/store";
import {notification} from "ant-design-vue";


const routes = [

  {
    path: '/',
    name: "main-page",
    component: () => import( '../views/main.vue'),

    children:[{
      path:'welcome',
      component:() => import('../views/main/welcome.vue'),
    },{
      path:'about',
      component:() => import('../views/main/about.vue'),
    },{
      path: '',
      redirect: '/welcome'
    }]


  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})




export default router
