import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/home',
    name: 'homeWithPath',
    component: HomeView
  },
  {
    path: '/Manage',
    name: 'Manage',
    component: () => import(/* webpackChunkName: "about" */ '../views/Manage.vue')
  },
  {
    path: '/Message',
    name: 'Message',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Message.vue')
  },
  {
    path: '/Announce',
    name: 'Announce',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Announce.vue')
  },
  {
    path: '/LoginorRegister',
    name: 'Handler',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Handler.vue')
  },
  // {
  //   path: '/Register',
  //   name: 'Register',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/Register.vue')
  // },
  {
    path: '/Update',
    name: 'Update',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Update.vue')
  },
  // {
  //   path: '/MadrenWork',
  //   name: 'MadrenWork',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/MaidenWork.vue')
  // },
  {
    path: '/Markdown',
    name: 'Markdown',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/Markdown.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to,from,next) => {
  // if(to.path === '/Login' || to.path === '/Register'){
  //   console.log(1)
  //   next()
  //   return
  // }
  // let token = localStorage.getItem('token')
  // console.log(token)
  // if(token === null || token === ''){
  //   console.log(2)
  //   next('/Login')
  //   return
  // }
  // else{
  //   console.log(3)
  //   next()
  //   return
  // }
  let token = localStorage.getItem('token')

  if(to.path === '/Update' && (token === null || token === '')){
    alert('????????????')
    next('/LoginorRegister')
  }
  else{
    next()
  }
})

export default router
