import Vue from 'vue'
import Router from 'vue-router'
import login from '@/views/login'
import index from '@/views/index'
import Unknown from '@/views/404'
import per_base from '@/views/personnel/base'
import welcome from '@/views/welcome'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/index',
      name: 'index',
      component: index,
      children: [
        {
          name:'默认欢迎页面',
          path:'/index',
          component: welcome
        },
        {
          name:'人员列表',
          path:'/per/base',
          component: per_base
        },
        {
          name:'404',
          path:'/*',
          component: Unknown
        }
      ]
    },
    {
      path:'/404',
      name:'404',
      component: Unknown
    }
    
  ]
})
