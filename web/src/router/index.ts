import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Report from '../views/report.vue'
import Doc from '../views/doc.vue'
import Login from '../views/login.vue'
import Register from '../views/register.vue'
import FindPassword from '../views/find-password.vue'
import AdminDoc from '../views/admin/admin-doc.vue'
import AdminUser from '../views/admin/admin-user.vue'
import AdminDocEdit from '../views/admin/admin-doc-edit.vue'


const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/report',
    name: 'Report',
    component: Report
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/find-password',
    name: 'FindPassword',
    component: FindPassword
  },
  {
    path: '/doc',
    name: 'Doc',
    component: Doc
  },
  {
    path: '/admin/doc',
    name: 'AdminDoc',
    component: AdminDoc
  },
  {
    path: '/admin/user',
    name: 'AdminUser',
    component: AdminUser
  },
  {
    path: '/admin/docEdit',
    name: 'AdminDocEdit',
    component: AdminDocEdit
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
