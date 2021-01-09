<template>
	<view class="content">
		<view class="bb15">

		</view>
		<view class="list-box">
			<view class="row">
				<text class="tit">店名</text>
				<text>{{shopInfo.dgutshop_shop_name}}</text>
			</view>
			<view class="row">
				<text class="tit">地址</text>
				<text>{{shopInfo.dgutshop_shop_address}}</text>
			</view>

			<view class="row">
				<text class="tit">联系方式</text>
				<text @click="chooseLocation">{{shopInfo.dgutshop_shop_phone}}</text>
			</view>
			<view class="row">
				<text class="tit">营业时间</text>
				<text>{{shopInfo.dgutshop_shop_businesshours}}</text>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				shopInfo: {}
			}
		},
		methods: {
			//地图选择地址
			chooseLocation() {
				uni.chooseLocation({
					success: (data) => {
						console.log(data, '地图选择地点的结果')
						this.addressData.userAddress = data.address + data.name;
						this.addressData.latitude = data.latitude
						this.addressData.longitude = data.longitude
					}
				})
			},



			//提交

			onShow() {
				this.shopInfo = uni.getStorageSync('shopInfo')
			},
		},
	}
</script>

<style lang="scss">
	page {
		background-color: #F7F7F7;
	}

	.bb15 {
		padding-top: 10px;
	}

	.list-box {
		display: flex;
		flex-direction: column;
		background-color: #fff;
		border-radius: 10px;
		overflow: hidden;
		margin: 0 10px;
		padding-left: 15px;
		padding-right: 15px;
	}

	.row {
		display: flex;
		flex-direction: row;
		align-items: center;
		height: 110upx;
		background: #fff;
		border-bottom: #dadada solid 1rpx;

		.tit {
			color: #2f2f2f;
			width: 100px;
			font-size: 30upx;
		}

		.input {
			flex: 1;
			font-size: 30upx;

		}

		.icon-locationfill {
			font-size: 36upx;
		}
	}

	.add-btn {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 20px;
		font-size: 20px;
		font-weight: 500;
		background-color: #343434;
		color: #FFFFFF;
		width: 90%;
		height: 55px;
	}
</style>
