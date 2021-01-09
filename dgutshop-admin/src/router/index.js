import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login.vue'
import Home from '../components/Home/Home.vue'
import Main from '../views/common/Main.vue'
import Password from '../views/common/Password.vue'
import Personal from '../views/common/Personal.vue'
import ManagerManage from '../views/super_manager/ManagerManage.vue'
import MallManage from '../views/super_manager/mall/MallManage.vue'
import ActiveManage from '../views/manager/Active/ActiveManage.vue'
import CheckActive from '../views/manager/Active/CheckActive.vue'
import DrinkManage from '../views/manager/Drink/DrinkManage.vue'
import SolveOrder from '../views/manager/Order/SolveOrder.vue'
import OrderConfiguration from '../views/manager/Order/OrderConfiguration.vue'
import DrinkSortManage from '../views/manager/DrinkSort/DrinkSortManage.vue'
import UserManage from '../views/manager/User/UserManage.vue'
import AddressManage from '../views/manager/Address/AddressManage.vue'
import ToppingsManage from '../views/manager/Toppings/ToppingsManage.vue'
import SlideShowManage from '../views/manager/SlideShow/SlideShowManage.vue'
import Test from '../views/manager/Echarts/test.vue'
import storage from '@/storage'


Vue.use(VueRouter)

const routes = [
  {
    //
    path: '/',
    component: Home,
    redirect: "/login",
    children: [
      {
        path: '/Main',
        component: Main,
        meta: { title: '主页',requireAuth: true },
      },
      {
        path: '/ManagerManage',
        component: ManagerManage,
        meta: { title: '管理管理员',requireAuth: true },
      },
      {
        path: '/ActiveManage',
        component: ActiveManage,
        meta: { title: '活动管理',requireAuth: true },
      },
      {
        path: '/CheckActive',
        component: CheckActive,
        meta: { title: '查看活动',requireAuth: true },
      },
      {
        path: '/DrinkManage',
        component: DrinkManage,
        meta: { title: '饮品管理',requireAuth: true },
      },
      {
        path: '/SolveOrder',
        component: SolveOrder,
        meta: { title: '处理订单',requireAuth: true },
      },
      {
        path: '/OrderConfiguration',
        component: OrderConfiguration,
        meta: { title: '订单配置',requireAuth: true },
      },
      {
        path: '/DrinkSortManage',
        component: DrinkSortManage,
        meta: { title: '奶茶分类',requireAuth: true },
      },
      {
        path: '/UserManage',
        component: UserManage,
        meta: { title: '用户管理',requireAuth: true },
      },
      {
        path: '/AddressManage',
        component: AddressManage,
        meta: { title: '地址管理',requireAuth: true },
      },
      {
        path: '/ToppingsManage',
        component: ToppingsManage,
        meta: { title: '小料',requireAuth: true },
      },
      {
        path: '/MallManage',
        component: MallManage,
        meta: { title: '商家信息管理',requireAuth: true },
      },
      {
        path: '/SlideShowManage',
        component: SlideShowManage,
        meta: { title: '轮播图管理',requireAuth: true },
      },
      {
        path: '/Password',
        component: Password,
        meta: { title: '修改密码',requireAuth: true },
      },
      {
        path: '/Personal',
        component: Personal,
        meta: { title: '个人信息页',requireAuth: true },
      },
      {
        path: '/Test',
        component: Test,
        meta: { title: '测试',requireAuth: true },
      },
    ]
  },
  {
    path: '/login',
    component: Login
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})
// router.beforeEach((to, from, next) => {
//   // 默认requiresAuth为false才不需要登录，其他都要
//   // to.matched.some(r => r.meta.requireAuth) or to.meta.requiresAuth
//   if (to.matched.some(r => r.meta.requireAuth) !== false) {
//     let tokenInfo = storage.get('token')
//     if (tokenInfo) {
//       console.log("有token")
//       next();

//     } else {
//       storage.remove('token')
//       next({
//         path: "/login",
//         query: {
//           redirect: to.fullPath
//         } // 将刚刚要去的路由path（却无权限）作为参数，方便登录成功后直接跳转到该路由
//       });
//     }

//   } else {
//     next(); //如果无需token,那么随它去吧
//   }
// });
export default router
