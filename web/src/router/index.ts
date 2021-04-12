import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Doc from '../views/doc.vue'
import AdminDoc from '../views/admin/admin-doc.vue'
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
