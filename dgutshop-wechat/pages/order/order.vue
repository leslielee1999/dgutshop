<template>
	<view style="height: 100%;">
		<view class="tabs-box">
			<view class="hx-tabs">
				<view class="hx-tabs-item" v-for="(item,i) in tabs" :key="i" :class="{'hx-tabs-active': swiperCurrent == i}" @click="swiperChange(i)"
				 :style="{transition: transtionTime + 'ms'}">
					<text>{{item.name}}</text>
				</view>
				<view class="hx-tabs-slider-box" :style="{width: (100/tabs.length) + '%',transition: transtionTime + 'ms',left:swiperCurrentSliderLeft + '%'}">
					<view class="hx-tabs-slider"></view>
				</view>
			</view>
		</view>

		<view class="main" :style="{height: mainHeight}">
			<swiper class="swiper" id="mainSwiper" style="height: 100%;" :current="swiperCurrent" :duration="transtionTime" @animationfinish="animationfinish">


				<!-- 当前订单 -->
				<swiper-item class="swiper-item">
					<view class="scroll-items">
						<scroll-view style="height: 100%;width: 100%;" scroll-with-animation :scroll-y="true" refresher-background="#eee"
						 :refresher-enabled="reloadDown" :refresher-triggered="triggered" :refresher-threshold="100" @refresherrefresh="onRefresh"
						 @refresherrestore="onRestore">

							<view class="nullOrder" v-if="tabs[0].sum==0">
								<image src="/static/shopcart/shopcart0.png" mode=""></image>
								<text class="tit">暂无订单</text>

							</view>
							<block v-for="(order,j) in tabs[0].data" :key="j" v-else>

								<view class="order-item" v-if="order" @click="showDetail(order)">
									<view class="item-top">
										<view class="row1">
											<view class="tit">
												<text v-if="order.type=='0'">{{order.consigneeAddress+order.consigneeRoom}}</text>
												<text v-else="order.type=='1'">{{shopInfo.dgutshop_shop_name}}</text>
											</view>
											<view class="right" v-if="order.orderStatus == '101'"><text>待支付</text>
												<uni-icons class="icon" style="border-radius: 100%;" type="arrowright" color="#7c7c7c"></uni-icons>
											</view>
											<view class="right" v-else-if="order.orderStatus == '201'"><text>制作中</text>
												<uni-icons class="icon" style="border-radius: 100%;" type="arrowright" color="#7c7c7c"></uni-icons>
											</view>
											<view class="right" v-else-if="order.orderStatus == '301'"><text>待取货</text>
												<uni-icons class="icon" style="border-radius: 100%;" type="arrowright" color="#7c7c7c"></uni-icons>
											</view>
											<view class="right" v-else-if="order.orderStatus == '401'"><text>派送中</text>
												<uni-icons class="icon" style="border-radius: 100%;" type="arrowright" color="#7c7c7c"></uni-icons>
											</view>
										</view>

										<view class="row2">
											<text>{{order.createTime}}</text>
										</view>
									</view>
									<view class="item-center">
										<view class="left">
											<image v-for="(item,index) in order.orderItemList" :key="index" :src="item.product.picture" mode=""></image>
										</view>
										<view class="right">
											<text class="total-price">￥{{order.orderPrice}}</text>
											<text class="count">共2件</text>
										</view>
									</view>
									<view class="item-bottom">
										<view class="left"></view>
										<view class="btn" v-if="order.orderStatus == '101'" @click.stop="cancel(order.id)">取消订单</view>
										<view class="btn bg-yellow" v-if="order.orderStatus == '101'" @click.stop="goPay(order.id)">去支付</view>
										<view class="btn bg-yellow" v-else-if="order.orderStatus == '301'||order.orderStatus == '402'" @click.stop="confirmOrder(order.id)">确认订单</view>

									</view>
								</view>
							</block>
						</scroll-view>
					</view>
				</swiper-item>

				<!-- 历史订单 -->
				<swiper-item class="swiper-item">
					<view class="scroll-items">
						<scroll-view style="height: 100%;width: 100%;" scroll-with-animation :scroll-y="true" refresher-background="#eee"
						 :refresher-enabled="reloadDown" :refresher-triggered="triggered" :refresher-threshold="100" @refresherrefresh="onRefresh"
						 @refresherrestore="onRestore">

							<view class="nullOrder" v-if="tabs[1].sum==0">
								<image src="/static/shopcart/shopcart0.png" mode="aspectFill"></image>
								<text class="tit">暂无订单</text>
							</view>
							<block v-for="(order,j) in tabs[1].data" :key="j" v-else>

								<view class="order-item" v-if="order" @click="showDetail(order)">
									<view class="item-top">
										<view class="row1" >
											<view class="tit">
												<text v-if="order.type=='0'">{{order.consigneeAddress+order.consigneeRoom}}</text>
												<text v-else="order.type=='1'">{{shopInfo.dgutshop_shop_name}}</text>
												
											</view>
											<view class="right" v-if="order.orderStatus >='501'"><text>已完成</text></view>
											<view class="right" v-else><text>已取消</text></view>

										</view>

										<view class="row2">
											<text>{{order.createTime}}</text>
										</view>
									</view>
									<view class="item-center">
										<view class="left">
											<image v-for="(item,index) in order.orderItemList" :key="index" :src="item.product.picture" mode=""></image>
										</view>
										<view class="right">
											<text class="total-price">￥{{order.orderPrice}}</text>
											<text class="count">共2件</text>
										</view>
									</view>
									<view class="item-bottom">
										<view class="left"></view>
									</view>
								</view>
							</block>
						</scroll-view>
					</view>
				</swiper-item>


			</swiper>
		</view>

	</view>
</template>

<script>
	var sysInfo = uni.getSystemInfoSync();
	var statusBarHeight = uni.getSystemInfoSync().statusBarHeight;
	//引入测试数据
	import common from "../../common/util.js"
	export default {
		data() {
			return {
				mainHeight: 0,
				statusBarHeight,
				tabs: [{
						name: '当前订单',
						data: [],
						sum: 0,
					},
					{
						name: '历史订单',
						data: [],
						sum: 0,
					},
				],

				swiperCurrent: 0, //表示当前页
				transtionTime: 100,
				swiperCurrentSliderLeft: 0,
				orderData: [

				],
				triggered: false,
				//下拉刷新
				reloadDown: true,
				shopInfo:{}

			}
		},
		onShow(option) {
			let token=uni.getStorageSync("token")
			console.log("token",token)
			if (token) {
				this.getOrderData()
				
			}else{
				uni.showToast({
					title: '请登录',
				});
				uni.navigateTo({
					url: '../login/login'
				})
				return
			}


			this.mainHeight = sysInfo.screenHeight - statusBarHeight - 50 - 60 - 50 + 'px';
			this._freshing = false;




		},
		mounted() {
			this.shopInfo=uni.getStorageSync("shopInfo")
		},
		methods: {
			animationfinish({
				detail: {
					current,
				}
			}) {
				/* this.$refs.tabs.setFinishCurrent(current); */
				this.swiperCurrent = current;
				this.current = current;
				this.swiperChange(current);
			},
			//点击上方tab更换订单类型
			swiperChange(index) {
				//swiperCurrent表示当前页
				this.swiperCurrent = index;
				this.swiperCurrentSliderLeft = (100 / this.tabs.length) * index;
			},
			//获取数据函数
			async getOrderData() {
				uni.showLoading({
				    title: '加载中',
					mask:true
				});
				await this.request(this)
				uni.hideLoading()
				console.log('request done')
				this.tabs[0].data = new Array
				this.tabs[0].sum = 0
				this.tabs[1].data = new Array
				this.tabs[1].sum = 0
				let orderData = this.orderData
				for (let i in orderData) {
					if (orderData[i].orderStatus == '102' || orderData[i].orderStatus == '103' || orderData[i].orderStatus > '500') {
						this.tabs[1].data.push(orderData[i])
						this.tabs[1].sum++
					} else {
						this.tabs[0].data.push(orderData[i])
						this.tabs[0].sum++
					}
				}

				console.log(this.tabs, 'tabs')
			},

			request: (that) => {
				//------------------------------------------
				//--------------------------------------------	
				console.log("get")
				return new Promise((resolve, reject) => {
					let token = uni.getStorageSync("token")
					if (token == null) {
						console.log("token null")
						if (!common.doLogin()) {
							return
						}
					}
					console.log("getOrder")
					
					uni.request({
						url: that.$apiUrl + '/wechat/order/list',
						method: 'GET',
						header: {
							"Authorization": token
						},
						success: (res) => {
							if(res.data.errno=="501"){
								uni.showToast({
									title: '请登录',
								});
								uni.navigateTo({
									url: '../login/login'
								})
								return
							}
							

							console.log(res)
							that.orderData = res.data
							console.log(that.orderData, 'orderData')
							resolve('suc');
						},
					});


				})
				//--------------------------------------------
				//--------------------------------------------
			},
			// 下拉刷新
			async onRefresh() {
				if (this._freshing) return;
				this._freshing = true;
				this.triggered = true

				await this.getOrderData()

				this.triggered = false;
				this._freshing = false;

			},
			onRestore() {
				console.log("重置刷新");
			},
			//去支付
			goPay(orderId) {
				let that = this
				let token = uni.getStorageSync("token")
				if (token == null) {
					console.log("token null")
					if (!common.doLogin()) {
						return
					}
				}
				
				let userStatus=uni.getStorageSync("userStatus")
				
				if(userStatus=='1'||userStatus=='2'){
					uni.showToast({
						title: '无权限',
						icon:'none'
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
									orderId: orderId
								},
								success: (res) => {
									console.log(res, 'payRes')
									uni.showToast({
										title:"支付成功"
									})
									
									that.getOrderData()
								},
							});
						} else if (res.cancel) {

						}
					}
				});
			},

			async cancel(orderId) {
				let that = this
				uni.showModal({
					title: '提示',
					content: '是否确认取消订单',
					success: function(res) {
						if (res.confirm) {
							let token = uni.getStorageSync("token")
							if (token == null) {
								if (!common.doLogin()) {
									return
								}
							}
							uni.request({
								url: that.$apiUrl + '/wechat/order/cancel',
								method: 'POST',
								data: {
									orderId: orderId
								},
								header: {
									"Authorization": token
								},
								success: (res) => {
									console.log(res, 'cancelOrderRes')
									uni.showToast({
										title:"订单取消成功"
									})
								},
							});
							that.getOrderData()

						} else if (res.cancel) {

						}
					}
				});


			},
			async confirmOrder(orderId) {
				let that = this
				uni.showModal({
					title: '提示',
					content: '是否确认订单',
					success: function(res) {
						if (res.confirm) {
							let token = uni.getStorageSync("token")
							if (token == null) {
								if (!common.doLogin()) {
									return
								}
							}
							uni.request({
								url: that.$apiUrl + '/wechat/order/confirm',
								method: 'POST',
								data: {
									orderId: orderId
								},
								header: {
									"Authorization": token
								},
								success: (res) => {
									console.log(res, 'comfirmOrderRes')
								},
							});

							that.getOrderData()

						} else if (res.cancel) {

						}
					}
				});
			},
			showDetail(order) {
				uni.setStorageSync('orderDetail', order)
				uni.navigateTo({
					url: '/pages/orderDetail/orderDetail'
				});

			}
		}
	}
</script>

<style lang="scss">
	.tabs-box {
		display: flex;
		justify-content: center;
		align-items: center;
		width: 100%;
		position: sticky;
		z-index: 10;
		background-color: white;
		height: 45px;
		// padding-bottom: 2px;

		.hx-tabs {
			position: relative;
			display: flex;
			height: 100%;
			width: 100%;
			justify-content: space-evenly;

			&-item {
				display: flex;
				flex: 1;
				flex-direction: row;
				justify-content: center;
				align-items: center;
				width: 70px;
				color: #666666;

				text {
					font-size: 17px;
				}

			}

			&-active {
				color: #333333;
				font-weight: bold;
			}

			&-slider-box {
				position: absolute;
				display: flex;
				justify-content: center;
				bottom: 0;
				width: 20%;
			}

			&-slider {
				display: flex;
				background: #000000;
				width: 68px;
				height: 3px;
			}
		}
	}

	.main {
		position: relative;
		background-color: #ffffff;
		height: 100%;
		width: 100%;

		#mainSwiper {
			background-color: #eeeeee;
			height: 100%;
			width: 100%;

			.nullOrder {
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: center;
				margin: 40px auto;

				image {
					width: 130px;
					height: 130px;
				}

				.tit {
					display: flex;
					flex-direction: column;
					font-size: 16px;
					font-weight: bold;
					color: #ecb57a;
					margin-top: 6px;

				}
			}

			.scroll-items {
				height: 100%;
				// padding-top: 10px;

				.order-item {
					padding: 5px 20px;
					display: flex;
					flex-direction: column;
					background-color: #fff;
					margin: 8px 0px;

					// border-radius: 10px;
					// padding: 0 15px;
					.item-top {
						display: flex;
						flex-direction: column;
						align-items: flex-start;
						padding: 12px 0;

						.row1 {
							display: flex;
							flex-direction: row;
							justify-content: space-between;
							width: 100%;
							margin-bottom: 4px;

							.tit {
								flex: 1;
								font-size: 16px;
								font-weight: 700px;
								color: #000000;
							}

							.right {
								font-size: 14px;
								color: #505050;
							}
						}

						.row2 {
							font-size: 10px;
							color: #787878;
						}



					}

					.item-center {
						display: flex;
						flex-direction: row;
						justify-content: space-between;
						margin-top: 12px;
						color: #666;
						font-size: 14px;
						width: 100%;

						.left {
							display: flex;
							flex-direction: row;
							justify-content: flex-start;


							image {
								border-radius: 6px;
								height: 60px;
								width: 60px;
								margin-right: 10px;
							}
						}



						.right {
							display: flex;
							flex-direction: column;
							justify-content: center;
							align-items: center;

							.total-price {
								font-size: 16px;
								color: #000000;
								font-weight: bold;
							}

							.count {
								font-size: 13px;
								color: #8f8f8f;
							}
						}
					}

					.item-bottom {
						display: flex;
						flex-direction: row;
						margin-top: 12px;
						margin-bottom: 15px;

						.left {
							flex: 1;
						}

						.btn {
							padding: 7px 14px;
							border: 1px solid #cdcdcd;
							font-size: 12px;
							color: #6d6d6d;
							border-radius: 3px;
							transition: all 0.2s;
							background: #fff;
							margin-left: 10px;
						}

						.btn:active {
							color: #888;
							background: #c5c5c5;
						}

						.bg-yellow {
							background: #FFFFFF;
							border-color: #DBA871;
							color: #DBA871;
						}

						.bg-yellow:active {
							background: #DBA871;
						}
					}
				}
			}

			// position: sticky;
			//top: calc(50px + 44px + var(--status-bar-height));

		}

	}
</style>
