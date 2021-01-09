import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false
Vue.prototype.$apiUrl = 'http://59.110.50.181:8080'

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
