import { createRouter, createWebHistory } from 'vue-router'


const routes = [

  {
    path: '/login',
    name: 'login-view',
    component: () => import( '../views/login.vue')
  },
  {
    path: '/',
    name: "main-page",
    component: () => import( '../views/main.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
