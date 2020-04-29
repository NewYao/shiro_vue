import Vue from 'vue'
import Router from 'vue-router'
import index from '@/views/index'
import Unknown from '@/views/404'
import per_base from '@/views/personnel/base'
import welcome from '@/views/welcome'
import timeline from '@/views/sys/timeline'
import uploadFile from  '@/views/sys/uploadFile'
import chat from '@/views/chat/chat'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: resolve => require(['@/views/login'], resolve),
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
        },{
          name:'时间线',
          path:'/sys/timeline',
          component :timeline
        },{
          name:'上传文件',
          path:'/sys/uploadFile',
          component :uploadFile
        },{
          name:'聊天',
          path:'/chat/chat',
          component :chat
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
