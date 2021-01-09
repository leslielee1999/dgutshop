// 原始数据
import Vue from 'vue'
import Vuex from 'vuex'
import storage from '@/storage'

Vue.use(Vuex)


export default new Vuex.Store({
  state: {

    user: {},
    token:'',
    type:'',
    userInform:[],
    announcement:[]
  },
  mutations: {
    setToken(state,token){
state.token = token
localStorage.setItem('token',token)
    },
    setUser(state,user){
      state.user=user
      var obj = JSON.stringify(user);
      localStorage.setItem('user',obj)
    },
    // setUserType(state,type){
    //   state.user.type=type
    //   localStorage.setItem('user',user)
    // },
		initUser(state){
      let user = window.sessionStorage.getItem('user')
			if(user){
        state.user= JSON.parse(user)
        state.token = storage.get('token')
				// state.token = state.user.token
			}
  },
  
  login(state,user){
    // 保存登录状态
    state.user = user
    state.token = storage.get('token')
    // 存储到本地存储
    window.sessionStorage.setItem('user',JSON.stringify(state.user))
    window.sessionStorage.setItem('token',state.token)
  },


  },
  actions: {
  },
  modules: {
  }
})

// import Vue from "vue";
// let store = {
//   state: Vue.observable({
//     user: {
//       id:'',
//       identity:'graduate'
//     },
//     isLogin:false

//   }),
//   setUser(newValue) {
//     this.state.user = newValue
//     console.log(this.state.user)
//   },
//   loginSuccess(){
//     this.state.isLogin=true;
//   }
// }
// export default store

// // 要用于全局的数据和管理数据的方法 在这里定义