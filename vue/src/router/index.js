import { createRouter, createWebHistory } from 'vue-router'
import layout from '../layout/Layout.vue'
const routes = [
  {
    path: '/',
    name: 'layout',
    component: layout,
    redirect:'/book',
    children:[
        {
          path:'user',
          name:'User',
          component: () => import('@/views/user')
        },
      {
        path:'book',
        name:'Book',
        component: () => import('@/views/book')
      },
      {
        path:'fx',
        name:'fx',
        component: () => import('@/views/fx')
      },

    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login')
  },{

    path:'/register',
    name:'Register',
    component: () => import('@/views/Register')
  },
  {

    path:'/tr',
    name:'tr',
    component: () => import('@/views/tr')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
