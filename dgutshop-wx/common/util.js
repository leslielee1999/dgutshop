function doLogin(callback) {
    let that = this;
	let isSuccess=false
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
						uni.setStorageSync("token", res.data.module.token)
						uni.setStorageSync("isLogin",true)
						isSuccess=true
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
	
	return isSuccess
  }
  
module.exports = {
	doLogin
}
