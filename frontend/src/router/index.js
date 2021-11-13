import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/views/IndexPage.vue'
import Roles from '@/views/RolesPage.vue'
import Engineers from '@/views/EngineersPage.vue'
import TitleList from '@/views/TitleListPage.vue'
import Project from '@/views/ProjectPage.vue'
import Document from '@/views/Documentpage.vue'
import Plans from '@/views/PlanPage.vue'

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
    path: '/planner/personal',
    component: Engineers
  },
  {
    path: '/planner/projects',
    component: Project
  },
  {
    path: '/planner/documents',
    component: Document
  },
  {
    path: '/planner/title',
    component: TitleList
  },
  {
    path: '/planner/plans',
    component: Plans
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
