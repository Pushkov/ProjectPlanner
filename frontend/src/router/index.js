import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '@/views/IndexPage.vue'
import Roles from '@/views/RolesPage.vue'
import Engineers from '@/views/EngineersPage.vue'
import TitleList from '@/views/TitleListPage.vue'
import Project from '@/views/ProjectPage.vue'
import Document from '@/views/Documentpage.vue'
import Plans from '@/views/PlanPage.vue'
import Permission from '@/views/PermissionPage.vue'
import Workshop from '@/views/WorkshopPage.vue'
import Login from '@/views/LoginPage.vue'
import Overview from '@/views/OverviewPage.vue'
import Department from '@/views/DepartmentPage.vue'
import PlanPoint from "@/components/plan/PlanPoint";

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
    path: '/planner/overview',
    component: Overview
  },
  {
    path: '/planner/roles',
    component: Roles
  },
  {
    path: '/planner/departments',
    component: Department
  },
  {
    path: '/planner/departments/:id',
    component: Department
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
    path: '/planner/projects/:id',
    component: Project
  },
  {
    path: '/planner/documents',
    component: Document
  },
  {
    path: '/planner/documents/:id',
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
    path: '/planner/plans/:year/:month/:department_id',
    component: Plans
  },
  {
    path: '/planner/plans/:year/:month/:department_id/:id',
    component: PlanPoint
  },
  {
    path: '/planner/permissions',
    component: Permission
  },
  {
    path: '/planner/workshops',
    component: Workshop
  },
  {
    path: '/planner/workshops/:id',
    component: Workshop
  },


  {
    path: '/planner/logout',
    component: Login
  },


  {
    path: '/planner/about',
    name: 'About',
    component: () => import('@/views/AboutPage.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
