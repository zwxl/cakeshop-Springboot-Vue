import Vue from 'vue'
import VueRouter from 'vue-router'
import Manage from '../views/Manage.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/manage',
    name: 'Manage',
    component: Manage,
    redirect:"/login",
    children:[
      {
        path: '/home',
        component: () => import('../views/Home.vue')
      },
      {
        path: '/user',
        name: '用户管理',
        component: () => import('../views/User.vue')
      },
      {
        path: '/goods',
        name: '商品管理',
        component: () => import('../views/Goods.vue')
      },
      {
        path: '/cateGoryAdmin',
        name: '商品分类',
        component: () => import('../views/CateGoryAdmin.vue'),
      },
      {
        path: '/orderListAdmin',
        name: '订单管理',
        component: () => import('../views/OrderListAdmin.vue'),
      },

    ]
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {
    path: '/emailLogin',
    name: 'emailLogin',
    component: () => import('../views/emailLogin.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/layout',
    name: 'Layout',
    component: () => import('../userview/Layout'),
    children: [ {
      path: '/userhome',
      name: 'UserHome',
      component: () => import('../userview/UserHome.vue'),
    },
      {
        path: '/cart',
        name: 'Cart',
        component: () => import('../userview/cart.vue'),
      },
      {
        path: '/order',
        name: 'Order',
        component: () => import('../userview/Order.vue'),
      },
      {
        path: '/category',
        name: 'Category',
        component: () => import('../userview/Category.vue'),
      },
      {
        path: '/goodsDetailsView',
        name: 'GoodsDetailsView',
        component: () => import('../views/GoodsDetailsView.vue'),
      },
    ]
  },
  {
    path: '/userCenter',
    name: 'UserCenter',
    component: () => import('../userview/UserCenter.vue'),
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
export default router
