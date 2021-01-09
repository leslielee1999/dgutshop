<script>
	export default {
		onLaunch: function() {
			const that = this;
			/**
			 * 初次加载判断网络情况
			 * 无网络状态下根据实际情况进行调整
			 */
			wx.getNetworkType({
				success(res) {
					const networkType = res.networkType
					if (networkType === 'none') {
						that.globalData.isConnected = false
						wx.showToast({
							title: '当前无网络',
							icon: 'loading',
							duration: 2000
						})
					}
				}
			});
			/**
			 * 监听网络状态变化
			 * 可根据业务需求进行调整
			 */
			wx.onNetworkStatusChange(function(res) {
				if (!res.isConnected) {
					that.globalData.isConnected = false
					wx.showToast({
						title: '网络已断开',
						icon: 'loading',
						duration: 2000
					})
				} else {
					that.globalData.isConnected = true
					wx.hideToast()
				}
			})
			
			
			//TODO:添加获取菜单列表及其商品细则的数据
			//TODO:
			
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},
		data() {
			return {
				isConnected: true
			}
		},
	}
</script>

<style>
	/*每个页面公共css */
</style>
