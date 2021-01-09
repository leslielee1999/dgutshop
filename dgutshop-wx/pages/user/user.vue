<template>
	<view class="container">
		<view style="position: relative;">
			<image src="../../static/user/bg.jpg" mode="aspectFill" class="bg"></image>
		</view>

		<view style="padding: 0 30rpx;">
			<!-- user box begin -->
			<view class="user-box" style="display: flex;flex-direction: column;background-color: #FFFFFF;">

				<view style="display: flex;align-items: center;">
					<view class="avatar">
						<image :src="user.avatarUrl"></image>
					</view>
					<view style="margin-top: 20rpx;display: flex;flex-direction: column;overflow: hidden;">
						<view style="display: flex;justify-content: flex-start;align-items: center;font-weight: bold;font-size: large;">
							<view style="font-size: large;font-weight: bold;">{{ user.nickName }}</view>
						</view>
					</view>
				</view>
				<!-- user grid begin -->
				<view style="width: 100px;display: flex;align-items: center;justify-content: center;">
					<view class="user-grid">
					</view>
				</view>
				<!-- user grid end -->
			</view>
		</view>
		<!-- service box begin -->
		<view class="service-box">
			<view style="margin-left: 32rpx;margin-bottom: 20rpx;font-size: large;font-weight: bold;">我的服务</view>
			<view class="row">
				<view class="grid" @tap="orders">
					<view class="image">
						<image src="/static/user/order.png"></image>
					</view>
					<view>我的订单</view>
				</view>
				<view class="grid" @tap="addresses">
					<view class="image">
						<image src="/static/user/address.png"></image>
					</view>
					<view>收货地址</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: {}
			}
		},
		onShow() {
			let isLogin = uni.getStorageSync("isLogin")
			if (!isLogin || isLogin == null) {
				uni.showToast({
					title: '请登录',
				});
				setTimeout(function() {
					uni.navigateTo({
						url: '../login/login'
					})
				}, 800)

				return
			}
			this.user = uni.getStorageSync("userInfo")
		},
		methods: {
			addresses() {
				uni.navigateTo({
					url: '/pages/address/address'
				})
			},
			orders() {
				uni.switchTab({
					url: '/pages/order/order'
				})
			},
		}
	}
</script>

<style lang="scss" scoped>
	page {
		height: auto;
		min-height: 100%;
		background-color: #f8eee3;
	}

	.bg {
		width: 100%;
		height: calc(410 / 594 * 750rpx);
	}


	.user-box {
		position: relative;
		border-radius: 8rpx;
		margin-bottom: 30rpx;
		margin-top: -115rpx;
		box-shadow: 0 20rpx 20rpx -20rpx rgba($color: #333, $alpha: 0.1);
	}

	.avatar {
		position: relative;
		margin-top: -35rpx;
		margin-left: 35rpx;
		margin-right: 35rpx;
		width: 160rpx;
		height: 160rpx;
		border-radius: 100%;
		display: flex;
		align-items: center;
		justify-content: center;
		background-color: #FFFFFF;
		box-shadow: 0 0 20rpx rgba($color: #000000, $alpha: 0.2);

		image {
			width: 140rpx;
			height: 140rpx;
			border-radius: 100%;
		}

	}



	.user-grid {
		width: 25%;
		padding: 30rpx;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;

		.value {
			margin-bottom: 20rpx;
		}
	}



	.service-box {
		width: 100%;
		background-color: #FFFFFF;
		padding-top: 30rpx;
		box-shadow: 0 20rpx 20rpx -20rpx rgba($color: #333, $alpha: 0.1);


		.row {
			display: flex;
			flex-direction: row;
			align-items: center;
			justify-content: space-around;
			flex-wrap: wrap;
			color: #919293;
			font-size: 24rpx;
			padding-bottom: -40rpx;
			margin-right: 32rpx;
			margin-left: 32rpx;

			.grid {
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: center;
				margin-bottom: 40rpx;
				width: 25%;
				position: relative;

				.image {
					image {
						width: 80rpx;
						height: 80rpx;
						margin-bottom: 20rpx;
					}
				}

				.new-badage {
					width: 40rpx;
					height: 40rpx;
					position: absolute;
					top: 0;
					right: 30rpx;
				}
			}
		}
	}
</style>
