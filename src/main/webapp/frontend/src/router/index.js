import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/About',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
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
  }
]

const router = createRouter({
  history: createWebHashHistory(),
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

  if(to.path !== '/Login' && to.path !== '/Register' && (token === null || token === '')){
    next('/Login')
  }
  else{
    next()
  }
})

export default router