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
import store from '@/vuex/store'

Vue.use(VueRouter)

const ifAuthenticated = (to, from, next) => {

  if (store.getters.isAuthenticated) {
    localStorage.removeItem('path-to');
    next();
    return;
  } else {
    localStorage.setItem('path-to', to.path);
    next("/planner/auth/login");
  }
  return
};


const routes = [
  {
    path: '/',
    redirect: '/planner/index',
    component: Index,
    beforeEnter: ifAuthenticated

  },
  {
    path: '/planner',
    redirect: '/planner/index',
    component: Index,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/index',
    component: Index,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/overview',
    component: Overview,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/roles',
    component: Roles,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/departments',
    component: Department,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/departments/:id',
    component: Department,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/personal',
    component: Engineers,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/projects',
    component: Project,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/projects/:id',
    component: Project,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/documents',
    component: Document,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/documents/:id',
    component: Document,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/title',
    component: TitleList
  },
  {
    path: '/planner/plans',
    component: Plans,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/plans/:year/:month/:department_id',
    component: Plans,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/plans/:year/:month/:department_id/:id',
    component: PlanPoint,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/permissions',
    component: Permission,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/workshops',
    component: Workshop,
    beforeEnter: ifAuthenticated
  },
  {
    path: '/planner/workshops/:id',
    component: Workshop,
    beforeEnter: ifAuthenticated
  },


  {
    path: '/planner/auth/login',
    component: Login
  },


  {
    path: '/planner/about',
    name: 'About',
    component: () => import('@/views/AboutPage.vue'),
    beforeEnter: ifAuthenticated
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
