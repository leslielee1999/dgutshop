<template>
	<view class="content">
		<button class="btn" open-type="getUserInfo" @getuserinfo="getUserInfo"> 点击登录 </button>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {
			getUserInfo: function(e) {
				console.log(e, "getInfoCallBack")
				uni.setStorageSync("userInfo", e.detail.userInfo)
				let that = this
				wx.login({
					success: function(loginRes) {
						console.log("loginRes", loginRes);
						if (loginRes.code) {
							// 请求服务端的登录接口
							uni.request({
								url: that.$apiUrl + "/login/wechat",
								method: "POST",
								data: {
									code: loginRes.code, // 临时登录凭证
									encryptedData: e.detail.encryptedData, // 用户敏感信息
									iv: e.detail.iv, // 解密算法的向量
								},

								success: function(res) {
									console.log("login success:", res);
									uni.setStorageSync("userStatus", res.data.module.staus)
									if (res.data.module.staus == '1') {
										uni.showToast({
											title: '禁止登陆！',
											icon:'none'
										});
										setTimeout(function() {
												uni.switchTab({
													url:'/pages/index/index'
												})
											},
											2000
										)
										return
									}


									uni.setStorageSync("token", res.data.module.token)
									console.log("token", res.data.module.token)
									uni.setStorageSync("isLogin", true)

									uni.showToast({
										title: '登陆成功',
									});

									setTimeout(function() {
											uni.navigateBack({
												delta: 1
											});
										},
										1000
									)
								},

								fail: function(error) {
									// 调用服务端登录接口失败
									console.log("调用接口失败");
									console.log(error);
								},
							});
						} else {
							// 获取 code 失败
							console.log("登录失败");
							console.log("调用wx.login获取code失败");
							uni.showToast({
								title: '登陆失败，请重试',
							});
						}
					},

					fail: function(error) {
						// 调用 wx.login 接口失败
						console.log("接口调用失败");
						console.log(error);
						uni.showToast({
							title: '登陆失败，请重试',
						});
					},
				});

			}
		},
	}
</script>

<style lang="scss">
	page {
		width: 100%;
		height: 100%;
	}

	.content {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;

		.btn {
			width: 60%;
		}
	}
</style>
