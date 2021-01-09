<template>
	<view>
		<view class="canSelectAddress" style="border-bottom: #dadada solid 1rpx;padding-bottom: 10px;">
			<view class="selectText">
				选择收货地址
			</view>

			<view class="addressItem" v-for="(item,index) in addressList" :key="index" v-if="!item.isToFar">
				<view class="left" @click="selectAddress(item)">
					<view class="address">
						{{item.userAddress+' '}}{{item.userRoom}}
					</view>
					<view class="nameAndPhone">
						{{item.userName+' '}}{{item.userPhone}}
					</view>
				</view>
				<view class="right">
					<uni-icons size='20' class="icon" type="compose" color="#bdbdbd" @click="editAddress(item)"></uni-icons>
				</view>
			</view>
		</view>

		<view class="toFarAddress">
			<view class="selectText" style="margin-top: 20px;">
				距离过远
			</view>
			<view class="addressItem" v-for="(item,index) in addressList" :key="index" v-if="item.isToFar">
				<view class="left">
					<view class="address" style="color: #999999;">
						{{item.userAddress+' '}}{{item.userRoom}}
					</view>
					<view class="nameAndPhone">
						{{item.userName+' '}}{{item.userPhone}}
					</view>
				</view>
				<view class="right">
					<uni-icons size='20' class="icon" type="compose" color="#bdbdbd" @click="editAddress(item)"></uni-icons>
				</view>
			</view>
		</view>




		<view class="bottom" @click="createAddress">
			<!-- <view class="BtnRight"> -->
			<button class="btn" type="default">+ 添加地址</button>
			<!-- </view> -->
		</view>

	</view>
</template>

<script>
	import common from "../../common/util.js"
	export default {
		data() {
			return {
				addressList: [],
			}
		},
		methods: {
			selectAddress(item) {
				//选择地址，set进缓存，然后back
				uni.setStorageSync('selectedAddress', item)
				uni.navigateBack({
					animationType: 'slide-out-right',
					animationDuration: 800
				})
			},
			editAddress(item) {
				//修改地址，点击的地址set进缓存，然后跳转
				uni.setStorageSync('editAddressType', 'edit')
				uni.setStorageSync('needEditAddress', item)
				uni.navigateTo({
					url: '../editAddress/editAddress'
				})
			},
			createAddress() {
				//新建地址，然后跳转
				uni.setStorageSync('editAddressType', 'create')
				uni.navigateTo({
					url: '../editAddress/editAddress'
				})
			},
			Rad: function(d) { //根据经纬度判断距离
				return d * Math.PI / 180.0;
			},
			getDistance: function(lat1, lng1, lat2, lng2) {
				// lat1用户的纬度
				// lng1用户的经度
				// lat2商家的纬度
				// lng2商家的经度
				var radLat1 = this.Rad(lat1);
				var radLat2 = this.Rad(lat2);
				var a = radLat1 - radLat2;
				var b = this.Rad(lng1) - this.Rad(lng2);
				var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(
					Math.sin(b / 2), 2)));
				s = s * 6378.137;
				s = Math.round(s * 10000) / 10000;
				s = s.toFixed(2) //保留两位小数
				console.log('经纬度计算的距离:' + s)
				return s

			},

		},
		onShow() {
			//needApi:获取用户地址列表
			let token = uni.getStorageSync("token")
			if (token == null) {
				console.log("token null")
				if (!common.doLogin()) {
					return
				}
			}
			uni.request({
				url: this.$apiUrl + '/wechat/address/list',
				method: 'GET',
				header: {
					"Authorization": token
				},
				success: (res) => {
					let shopInfo = uni.getStorageSync("shopInfo")
					this.addressList = res.data.data.list
					console.log(this.addressList, 'getAddressList')

					for (let i = 0; i < this.addressList.length; i++) {
						let distance = this.getDistance(this.addressList[i].latitude, this.addressList[i].longitude, shopInfo.dgutshop_shop_latitude,
							shopInfo.dgutshop_shop_longitude)
						console.log("address", this.addressList[i])
						console.log("distance", distance)
						if (distance > 15) {
							this.addressList[i].isToFar = true
						} else {
							this.addressList[i].isToFar = false
						}
					}


				}
			})
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #F8F8F8;
	}

	.selectText {
		margin-top: 10px;
		margin-bottom: 10px;
		margin-left: 20px;
		font-size: 13px;
		color: #8a8a8a;
	}

	.addressItem {
		padding: 20px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		background-color: #FFFFFF;
		border-bottom: #f0f0f0 solid 1rpx;


		.left {
			width: 85%;
			display: flex;
			flex-direction: column;

			.address {
				margin-bottom: 8px;
			}

			.nameAndPhone {
				font-size: 14px;
				color: #999999;
				font-size: 600;
			}
		}

		.right {
			display: flex;
			flex-direction: row;
			justify-content: center;
			align-items: center;
			width: 15%;
		}

	}


	.bottom {
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		height: 54px;
		z-index: 99;
		display: flex;
		margin-bottom: 20px;



		.btn {
			display: flex;
			align-items: center;
			justify-content: center;
			width: 40%;
			color: #DBA871;
			font-size: 17px;
			background-color: #FFFFFF;
			border-bottom: #f0f0f0 solid 1rpx;
		}


	}
</style>
