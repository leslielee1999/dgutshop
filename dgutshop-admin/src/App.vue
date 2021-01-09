<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
import storage from '@/storage'
export default {
  
  name: 'app',
  components: {
  },
  created() {
		// 初始化用户信息
		this.$store.commit('setToken',storage.get('token'))
	},
}
</script>

<style>
    @import "./assets/css/main.css";
    /* @import "./assets/css/color-dark.css";     深色主题 */
</style>
