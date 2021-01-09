<template>
	<view class="container">
		<view class="banner">
			<image :src="shopInfo.dgutshop_shop_poster" mode="widthFix" class="bg"></image>
		</view>
		<view class="content">
			<view class="entrance">
				<view class="item" @tap="takein">
					<image src="/static/index/tangshi.png" class="icon"></image>
					<view class="title">门店自取</view>
					<view class="text">
						下单免排队
					</view>
				</view>
				<view class="item" @tap="takeout">
					<image src="/static/index/waimai.png" class="icon"></image>
					<view class="title">外卖</view>
					<view class="text">
						无接触配送，送喜到家
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				shopInfo:{},
			}
		},
		methods: {
			takein() {
				uni.switchTab({
					url: '/pages/store/store'
				})
			},
			takeout() {
				let token = uni.getStorageSync("token")
				let isLogin = uni.getStorageSync("isLogin")
				if (isLogin && token) {
					uni.setStorageSync("isTakeOut",true)
					uni.switchTab({
						url: "/pages/store/store",
						
					})
					return
				}
				uni.navigateTo({
					url: '/pages/login/login'
				})
				return

			},
		},
		onShow() {
			uni.request({
				url: this.$apiUrl + '/wechat/config/mall',
				method: 'GET',
				success: (res) => {
					uni.setStorageSync('shopInfo', res.data.data)
					this.shopInfo=res.data.data
				}
			})
			
			uni.request({
				url: this.$apiUrl + '/wechat/config/order',
				method: 'GET',
				success: (res) => {
					uni.setStorageSync('orderConfig', res.data.data)		
					console.log('orderConfig',res)
				}
			})
		}
	}
</script>

<style lang="scss" scoped>
	/* #ifdef H5 */
	page {
		height: auto;
		min-height: 100%;
	}

	/* #endif */

	.banner {
		position: relative;
		width: 100%;
		// height: 1000rpx;

		.bg {
			width: 100%;
			// max-height: 1000rpx;
		}

		.intro {
			position: absolute;
			top: calc(50rpx + var(--status-bar-height));
			left: 40rpx;
			color: #FFFFFF;
			display: flex;
			flex-direction: column;

			.greet {
				font-size: 32rpx;
				margin-bottom: 10rpx;
			}

			.note {
				font-size: 24rpx;
			}
		}
	}

	.content {
		padding: 0 30rpx;
	}

	.entrance {
		position: relative;
		margin-top: -80rpx;
		margin-bottom: 30rpx;
		border-radius: 10rpx;
		background-color: #ffffff;
		box-shadow: 0 20rpx 20rpx -20rpx rgba($color: #333, $alpha: 0.1);
		;
		padding: 30rpx 0;
		display: flex;
		align-items: center;
		justify-content: center;

		.item {
			flex: 1;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			position: relative;

			&:nth-child(1):after {
				content: '';
				position: absolute;
				width: 1rpx;
				background-color: #ddd;
				right: 0;
				height: 100%;
				transform: scaleX(0.5) scaleY(0.8);
			}

			.icon {
				width: 140rpx;
				height: 140rpx;
				margin: 20rpx;
			}

			.title {
				font-size: 45rpx;
				color: #000000;
				font-weight: 500;
			}
			.text{
				font-size: 30rpx;
				color: #4b4b4b;
				
			}
		}
	}
</style>
