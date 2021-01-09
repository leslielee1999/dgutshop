<template>
	<view>

		<view class="header">
			<view class="orderType">
				<view v-if="order.type" class="addressControl" @click="changeAddress">
					<view class="addressText">
						{{shopInfo.dgutshop_shop_name}}
					</view>
					<!-- <uni-icons class="icon" style="border-radius: 100%;" type="arrowright" color="#7c7c7c"></uni-icons> -->
					<!-- 有分店的话可以更改店铺 -->
				</view>

				<view v-if="!order.type" class="addressControl" @click="changeAddress">
					<view class="addressText">
						{{getAddressText}}
					</view>
					<uni-icons class="icon" style="border-radius: 100%;" type="arrowright" color="#7c7c7c"></uni-icons>
				</view>

				<view class="select">
					<view :class="[order.type == 1 ? 'on' : 'off']" @click="changePeisongType">自取</view>
					<view :class="[order.type == 0 ? 'on' : 'off']" @click="changePeisongType">外卖</view>
				</view>

			</view>

			<view class="nameAndPhone">
				{{getNameAndPhone}}
			</view>

		</view>


		<view class="orderDetail">
			<view class="itemList" v-for="(item,index) in order.orderItemList" :key='index'>
				<view class="item">
					<image :src="item.product.picture" mode="aspectFit"></image>
					<view class="message">
						<view class="name">
							{{item.product.name}}
						</view>
						<view class="describe">
							{{item.note}}
						</view>
					</view>

					<view class="priceAndNum">
						<view class="price">
							￥ {{item.productActualPrice}}
						</view>
						<view class="num">
							x {{item.sum}}
						</view>
					</view>

				</view>
			</view>

			<view class="delivery" v-if="order.type=='0'">
				<view class="text">
					配送费
				</view>
				<view class="deliveryPrice">
					￥{{orderConfig.dgutshop_order_delivery}}
				</view>
			</view>

			<view class="mark">
				<view class="text">
					备注
				</view>
				<input class="input" placeholder="可输入口味包装等要求" placeholder-class="input-holder" v-model="order.message" />
			</view>

			<view class="totalText">
				共计{{getAllCount}}件商品，小计
				<view class="total-price">
					￥{{getOrderTotalPrice}}
				</view>
			</view>

		</view>


		<view class="bottom">
			<view class="total-price">
				合计
				<view class="price">￥{{getOrderTotalPrice}}</view>
			</view>
			<view class="BtnRight">
				<!-- TODO:结算订单函数 -->
				<view @click="settle" class="text">
					支付
				</view>
			</view>
		</view>


	</view>
</template>

<script>
	import common from "../../common/util.js"
	export default {
		data() {
			return {
				order: {},
				shopInfo: {},
				orderConfig: {},
				isSelectAddress: false,
				address: {}
			}
		},
		methods: {
			changePeisongType() {
				//修改订单派送类型
				this.order.type = this.order.type == 0 ? 1 : 0;
				if (this.order.type) {
					this.order.consignee = ''
					this.order.consigneePhone = ''
					this.order.consigneeAddress = ''
					this.order.consigneeRoom = ''
					this.order.deliveryPrice = 0
					this.order.orderPrice -= Number(this.orderConfig.dgutshop_order_delivery)
				} else {
					this.addAddressInOrder(this.address)
					this.order.deliveryPrice = this.orderConfig.dgutshop_order_delivery
					console.log('changePeisongType', this.order.deliveryPrice)
					this.order.orderPrice += Number(this.orderConfig.dgutshop_order_delivery)

				}
			},
			computedOrderTotalPrice() {
				this.order.productPrice = 0
				this.order.orderPrice = 0
				let list = this.orderItemList
				for (let i = 0; i < this.order.orderItemList.length; i++) {
					this.order.productPrice += this.order.orderItemList[i].productActualPrice
				}

				this.order.deliveryPrice = Number(this.order.deliveryPrice)
				this.order.orderPrice = this.order.productPrice + this.order.deliveryPrice
				console.log('计算订单总价')
				console.log(this.order)
			},
			changeAddress() {
				//跳转到选择地址
				uni.setStorageSync('settle_order', this.order)
				uni.navigateTo({
					url: '../selectAddress/selectAddress'
				})
			},
			addAddressInOrder(address) {
				if (address == null) {
					address = uni.getStorageSync('defaultAddress')
					if (address == null) return
				} else {
					this.address = address
					this.order.consignee = address.userName
					this.order.consigneePhone = address.userPhone
					this.order.consigneeAddress = address.userAddress
					this.order.consigneeRoom = address.userRoom
				}

			},
			settle() {
				if (this.order.type == '0' && !this.order.consigneeAddress) {
					uni.showToast({
						icon: "none",
						title: "请选择地址"
					})
					return
				}
				let that = this
				let token = uni.getStorageSync("token")
				if (token == null) {
					console.log("token null")
					if (!common.doLogin()) {
						return
					}
				}
				console.log("token:", token)
				console.log(this.order)
				uni.request({
					url: this.$apiUrl + '/wechat/order/submit',
					method: "POST",
					data: this.order,
					header: {
						"Authorization": token
					},
					success: function(submutRes) {
						if (submutRes.statusCode != 200) {
							console.log(submutRes)
							uni.showToast({
								title: '下单失败，请重试',
								icon: 'none'
							});
							return
						}

						uni.showModal({
							title: '模拟支付',
							content: '点击确认模拟支付',
							success: function(res) {
								if (res.confirm) {
									uni.request({
										url: that.$apiUrl + '/wechat/order/h5pay',
										method: 'POST',
										header: {
											"Authorization": token
										},
										data: {
											orderId: submutRes.data.data.orderId
										},
										success: (payRes) => {
											console.log(payRes, 'payRes')
										},
									});

								} else if (res.cancel) {

								}

								setTimeout(function() {
										uni.switchTab({
											url: '/pages/order/order'
										})
									},
									1000
								)
							}
						});


					},
					fail(e) {
						console.log(e)
						uni.showToast({
							title: '下单失败，请重试',
						});
					}
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
			computedDistance(address) {
				let shopInfo = uni.getStorageSync("shopInfo")
				return this.getDistance(address.latitude, address.longitude, shopInfo.dgutshop_shop_latitude,
					shopInfo.dgutshop_shop_longitude)
			},
			getDefaultAddress() {
				let defaultAddress = uni.getStorageSync('defaultAddress')
				if (defaultAddress) {
					return defaultAddress
				} else {
					uni.request({
						url: this.$apiUrl + '/wechat/address/list',
						method: 'GET',
						header: {
							"Authorization": token
						},
						success: (res) => {
							for (let i = 0; i < res.data.data.list.length; i++) {
								if (res.data.data.list[i].isDefault == '1' && computedDistance(res.data.data.list[i]) < 15) this.addAddressInOrder(
									res.data.data.list[i])
							}
						}
					})
				}

			}
		},
		onShow() {
			console.log('onshow')
			this.order = uni.getStorageSync('settle_order')
			let selectedAddress = uni.getStorageSync('selectedAddress')
			console.log(selectedAddress, 'selectedAddress')
			if (selectedAddress != null) {
				this.address = selectedAddress
				this.addAddressInOrder(selectedAddress)
			} else {
				this.addAddressInOrder(uni.getStorageSync('defaultAddress'))
				uni.request({
					url: this.$apiUrl + '/wechat/address/list',
					method: 'GET',
					header: {
						"Authorization": token
					},
					success: (res) => {
						for (let i = 0; i < res.data.data.list.length; i++) {
							if (res.data.data.list[i].isDefault == '1') {
								this.address=res.data.data.list[i]
								this.addAddressInOrder(res.data.data.list[i])
							}
						}
					}
				})
			}


			this.shopInfo = uni.getStorageSync('shopInfo')
			this.orderConfig = uni.getStorageSync("orderConfig")
			console.log("orderPreview_orderConfig", this.orderConfig)
		},
		computed: {
			// 获得购物车所有商品数量
			getAllCount() {
				let res = 0
				for (let i = 0; i < this.order.orderItemList.length; i++) {
					res += this.order.orderItemList[i].sum
				}
				return res
			},
			getOrderTotalPrice() {
				return this.order.orderPrice
			},
			getAddressText() {
				if (this.order.consigneeAddress) {
					return this.order.consigneeAddress + this.order.consigneeRoom
				} else {
					return '点击选择收货地址'
				}
			},
			getNameAndPhone() {
				if (this.order.type) {
					return ''
				} else if (this.order.consignee) {
					return this.order.consignee + '' + this.order.consigneePhone
				}

				return ""

			}

		},
	}
</script>

<style lang="scss">
	page {
		background-color: #F7F7F7;
	}

	.header {
		padding-left: 20px;
		padding-right: 20px;
		padding-top: 25px;
		padding-bottom: 25px;
		display: flex;
		flex-direction: column;
		background-color: #FFFFFF;
		margin-bottom: 10px;


		.orderType {
			display: flex;
			flex-direction: row;
			justify-content: space-between;
			width: 100%;

			.addressControl {
				width: 70%;
				display: flex;
				flex-direction: row;
				justify-content: flex-start;
				align-items: center;

				.addressText {
					font-size: 20px;
					font-weight: 500;
					color: #000000;
					overflow: hidden;
					word-break: break-all;
					/* break-all(允许在单词内换行。) */
					text-overflow: ellipsis;
					/* 超出部分省略号 */
					display: -webkit-box;
					/** 对象作为伸缩盒子模型显示 **/
					-webkit-box-orient: vertical;
					/** 设置或检索伸缩盒对象的子元素的排列方式 **/
					-webkit-line-clamp: 1;
					/** 显示的行数 **/
				}

				.icon {
					width: 5%;
					display: flex;
					flex-direction: row;
					justify-content: center;
					align-items: center;
				}
			}

			.select {
				width: 30%;
				display: flex;
				align-items: center;
				font-size: 25rpx;
				width: 180rpx;
				height: 60rpx;
				border-radius: 32rpx;
				background-color: #f6f6f6;
			}

			.select .on {
				width: 90rpx;
				height: 60rpx;
				background-color: #343434;
				color: #ffffff;
				border-radius: 32rpx;
				text-align: center;
				line-height: 60rpx;
			}

			.select .off {
				width: 90rpx;
				height: 60rpx;
				color: #d2d2d2;
				border-radius: 25rpx;
				text-align: center;
				line-height: 60rpx;
			}


		}


		.nameAndPhone {
			color: #999999;
			font-size: 13px;
		}
	}


	.orderDetail {
		background-color: #FFFFFF;
		padding-left: 20px;
		padding-right: 20px;
		display: flex;
		flex-direction: column;


		.itemList {
			border-bottom: #f0f0f0 solid 1rpx;
			padding-bottom: 15px;

			.item {
				padding-top: 10px;
				padding-bottom: 10px;
				display: flex;
				flex-direction: row;
				flex-wrap: nowrap;
				justify-content: flex-start;
				align-items: flex-start;
				align-content: center;

				&>image {
					width: 20%;
					width: 140rpx;
					height: 140rpx;
					margin-right: 16rpx;
					margin-left: 2px;
				}

				.message {
					width: 70%;

					.name {
						color: #000000;
						font-size: 15px;
						font-weight: 500;
						margin-top: 10rpx;
						margin-bottom: 10rpx;
					}

					.describe {
						font-size: 24rpx;
						color: #999;
						// overflow: hidden;
						// word-break: break-all;
						// /* break-all(允许在单词内换行。) */
						// text-overflow: ellipsis;
						// /* 超出部分省略号 */
						// display: -webkit-box;
						// /** 对象作为伸缩盒子模型显示 **/
						// -webkit-box-orient: vertical;
						// /** 设置或检索伸缩盒对象的子元素的排列方式 **/
						// -webkit-line-clamp: 2;
						// /** 显示的行数 **/
					}
				}

				.priceAndNum {
					display: flex;
					flex-direction: column;
					justify-content: flex-start;
					align-items: flex-end;
					width: 20%;

					.price {
						font-size: 16px;
						font-weight: 400;
						color: #343434;
					}

					.num {
						margin-top: 7px;
						font-size: 13px;
						color: #999999;
					}
				}

			}
		}

		.delivery {
			display: flex;
			flex-direction: row;
			justify-content: space-between;
			align-items: center;
			padding-top: 10px;
			padding-bottom: 10px;
			border-bottom: #f0f0f0 solid 1rpx;

			.text {}

			.deliveryPrice {}
		}

		.mark {
			margin-top: 25px;
			margin-bottom: 25px;


			.text {
				color: #343434;
				margin-bottom: 10px;
			}

			.input {}

			.input-holder {
				font-weight: 500;
			}
		}

		.totalText {
			display: flex;
			flex-direction: row;
			justify-content: flex-end;
			align-items: center;
			font-size: 15px;
			color: #343434;
			margin-top: 25px;
			margin-bottom: 25px;

			.total-price {
				color: #363636;
				font-size: 20px;
				font-weight: 500;
				margin-left: 5px;
			}

		}


	}


	.bottom {
		margin-bottom: var(--window-bottom);
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		height: 54px;
		z-index: 99;
		display: flex;
		background-color: #F0F0F1;

		.total-price {
			display: flex;
			flex-direction: row;
			justify-content: flex-end;
			align-items: center;
			flex: 4;
			font-size: 15px;
			font-weight: 500;
			color: #363636;


			.price {
				color: #363636;
				font-size: 20px;
				font-weight: 500;
				margin-right: 26px;
				margin-left: 5px;
			}
		}

		.BtnRight {
			flex: 2;
			background-color: #DBA871;
			display: flex;
			align-items: center;
			justify-content: center;
			font-size: 17px;
			font-weight: 500;
			color: #FFFFFF;


			.text {}

		}
	}
</style>
