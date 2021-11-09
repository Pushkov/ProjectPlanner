import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/views/IndexPage.vue'
import Roles from '@/views/RolesPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/planner/index',
    component: Index
  },
  {
    path: '/planner',
    redirect: '/planner/index',
    component: Index
  },
  {
    path: '/planner/index',
    component: Index
  },
  {
    path: '/planner/roles',
    component: Roles
  },

  {
    path: '/planner/about',
    name: 'About',
    component: () => import('@/views/AboutPage.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  // base: process.env.BASE_URL,
  routes
})

export default router
