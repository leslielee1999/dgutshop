import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './assets/css/base.css'
import instance from "./http"
import echarts from 'echarts'
import storage from './storage'


Vue.config.productionTip = false
Vue.prototype.$http = instance;
Vue.prototype.$axios = instance;
Vue.prototype.$echarts = echarts
Vue.prototype.$storage = storage;


new Vue({
  // echarts,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
