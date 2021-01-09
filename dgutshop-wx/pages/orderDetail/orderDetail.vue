<template>
	<view class="content">

		<view class="header" v-if="order.orderStatus=='101'">
			<view class="title">订单未支付</view>
			<uni-countdown :show-day="false" :minute="minues" :second="second" @timeup="timeup"></uni-countdown>
			<view class="text">
				订单三十分钟未支付将自动取消
			</view>
		</view>

		<view class="header" v-else-if="order.orderStatus=='201'">
			<view class="title">订单制作中</view>
			<view class="title" v-if="order.type=='1'">{{getTakeCode}}</view>
			<view class="text">
				饮品制作中，马上就来~
			</view>
		</view>

		<view class="header" v-else-if="order.orderStatus=='301'&&order.type=='1'">
			<view class="title">取餐码</view>
			<view class="title">{{getTakeCode}}</view>
			<view class="text">
				尽快取餐味道更佳
			</view>
		</view>


		<view class="header" v-else-if="order.orderStatus=='401'">
			<view class="title">订单派送中</view>
			<view class="text">
				派送员正在快马加鞭，请稍作等待
			</view>
		</view>


		<view class="header" v-else-if="order.orderStatus>'401'">
			<view class="title">订单已完成</view>
			<view class="text">
				感谢您的支持，欢迎再次光临
			</view>
		</view>

		<view class="header" v-else>
			<view class="title">订单已取消</view>
			<view class="text">
				感谢您的支持，欢迎再次光临
			</view>
		</view>



		<view class="card">
			<view class="title">
				商品信息
			</view>
			<view class="itemList">
				<view class="item" v-for="(item,index) in order.orderItemList" :key="index">
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
							￥{{item.productActualPrice}}
						</view>
						<view class="num">
							x{{item.sum}}
						</view>
					</view>

				</view>
			</view>

			<view class="totalPriceRow">
				<view class="text">
					商品总价
				</view>
				<view class="totalPrice">
					￥{{order.orderPrice}}
				</view>
			</view>

			<view class="bottom">
				<view class="text">
					共{{getAllCount}}件商品，合计
				</view>
				<view class="price">
					￥{{order.orderPrice}}
				</view>
			</view>
		</view>

		<view class="card">
			<view class="title">
				订单信息
			</view>
			<view class="row">
				<view class="text">下单时间：</view>
				<view class="value">{{order.createTime}}</view>
			</view>
			<view class="row" v-if="order.type=='1'&&order.orderStatus=='301'">
				<view class="text">取餐码：</view>
				<view class="value">{{order.takeCode}}</view>
			</view>
			<view class="row">
				<view class="text">订单编号：</view>
				<view class="value">{{order.code}}</view>
			</view>
			<view class="row">
				<view class="text">备注信息：</view>
				<view class="value">{{order.message}}</view>
			</view>

		</view>


		<view class="card" v-if="order.type=='0'&&order.orderStatus=='401'">
			<view class="title">
				配送信息
			</view>

			<view class="row">
				<view class="text">收货地址：</view>
				<view class="value">{{order.consigneeAddress+order.consigneeRoom}}</view>
			</view>

			<view class="row">
				<view class="text">配送时间：</view>
				<view class="value">{{order.deliveryTime}}</view>
			</view>

			<view class="row">
				<view class="text">配送公司：</view>
				<view class="value">{{order.deliveryCompany}}</view>
			</view>

			<view class="row">
				<view class="text">配送员电话：</view>
				<view class="value">{{order.deliverymanPhone}}</view>
			</view>



		</view>
		<button class="comfire-btn" type="default" @click="pay()" v-if="order.orderStatus=='101'">支付订单</button>
		<button class="cancel-btn" type="default" @click="cancel()" v-if="order.orderStatus=='101'">取消订单</button>
		<button class="comfire-btn" type="default" @click="confirm()" v-if="(order.orderStatus=='301'&&order.type=='1')||order.orderStatus=='402'">确认订单</button>


	</view>
</template>

<script>
	import uniCountdown from '@/components/uni-countdown/uni-countdown.vue'
	export default {
		components: {
			uniCountdown
		},
		data() {
			return {
				order: {},
				minues: '20',
				second: '0',
				orderConfig:{}
			}
		},
		methods: {
			getCurrentTime() {
				var date = new Date(); //当前时间
				var month = zeroFill(date.getMonth() + 1); //月
				var day = zeroFill(date.getDate()); //日
				var hour = zeroFill(date.getHours()); //时
				var minute = zeroFill(date.getMinutes()); //分
				var second = zeroFill(date.getSeconds()); //秒

				//当前时间
				var curTime = date.getFullYear() + "-" + month + "-" + day +
					" " + hour + ":" + minute + ":" + second;

				return curTime;
			},
			pay() {
				let that = this
				let token = uni.getStorageSync("token")
				if (token == null) {
					console.log("token null")
					if (!common.doLogin()) {
						return
					}
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
									orderId: that.order.id
								},
								success: (res) => {
									if (res.statusCode != 200) {
										console.log(submutRes)
										uni.showToast({
											title: '支付失败，请重试',
											icon: 'none'
										});
										return
									}
									console.log(res, 'payRes')
									uni.navigateBack({
										delta: 1
									})
								},
							});


						} else if (res.cancel) {

						}
					}
				});
			},
			cancel() {
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
									orderId: that.order.id
								},
								header: {
									"Authorization": token
								},
								success: (res) => {
									console.log(res, 'cancelOrderRes')
									uni.navigateBack({
										delta: 1
									})
								},
							});
						} else if (res.cancel) {

						}
					}
				});
			},
			confirm() {
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
									orderId: that.order.id
								},
								header: {
									"Authorization": token
								},
								success: (res) => {
									console.log(res, 'comfirmOrderRes')
									uni.navigateBack({
										delta: 1
									})
								},
							});
						} else if (res.cancel) {

						}
					}
				});
			},
			timeup() {
				this.order.orderStatus = "103"
			}
		},
		onShow() {
			this.orderConfig=uni.getStorageSync("orderConfig")
			this.order = uni.getStorageSync('orderDetail')

			if (this.order.orderStatus == '101') {
				var dateStr = this.order.createTime.replace(/\-/g, "/");
				var orderDate = new Date(dateStr); //date1就是一个日期类型了
				// var currentDateStr=this.getCurrentTime().replace(/\-/g, "/")
				var currentDate = new Date()

				console.log("当前时间：", currentDate)
				console.log("订单时间：", orderDate)

				var date3 = orderDate.getTime() - currentDate.getTime() + Number(this.orderConfig.dgutshop_order_unpaid) * 60 * 1000 - 2000; //时间差秒
				console.log(date3)
				//计算出相差天数
				var days = Math.floor(date3 / (24 * 3600 * 1000))

				//计算出小时数
				var leave1 = date3 % (24 * 3600 * 1000) //计算天数后剩余的毫秒数
				var hours = Math.floor(leave1 / (3600 * 1000))

				//计算相差分钟数
				var leave2 = leave1 % (3600 * 1000) //计算小时数后剩余的毫秒数
				var minutes = Math.floor(leave2 / (60 * 1000))

				//计算相差秒数
				var leave3 = leave2 % (60 * 1000) //计算分钟数后剩余的毫秒数
				var seconds = Math.round(leave3 / 1000)
				console.log("时间差" + days + "天" + hours + "时" + minutes + "分" + seconds + "秒");

				this.minues = minutes
				this.second = seconds
			}



		},
		computed: {
			getAllCount() {
				let res = 0
				for (let i = 0; i < this.order.orderItemList.length; i++) {
					res += this.order.orderItemList[i].sum
				}
				return res
			},
			getTakeCode(){
				return this.order.takeCode.substring(8)
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f5f5f5f5;
	}

	.content {
		display: flex;
		flex-direction: column;
		align-items: center;

		.header {
			width: 80%;

			padding-top: 20px;
			padding-bottom: 20px;
			padding-left: 20px;
			padding-right: 20px;
			display: flex;
			flex-direction: column;
			align-items: center;
			background-color: #FFFFFF;
			margin-top: 15px;
			margin-bottom: 10px;
			border-radius: 10px;

			.title {
				font-size: 19px;
				font-weight: 600;
			}

			.text {
				margin-top: 10px;
				font-size: 13px;
				color: #878787;
			}

		}

		.card {
			width: 80%;
			display: flex;
			flex-direction: column;
			background-color: #FFFFFF;
			border-radius: 10px;
			padding-top: 17px;
			padding-left: 20px;
			padding-right: 20px;
			margin-bottom: 10px;

			.title {
				font-size: 28rpx;
				font-weight: 600;
				border-bottom: #f0f0f0 solid 1rpx;
				padding-bottom: 17px;
			}

			.itemList {
				border-bottom: #f0f0f0 solid 1rpx;

				.item {
					padding-top: 15px;
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
							font-size: 28rpx;
							font-weight: 500;
							margin-top: 10rpx;
							margin-bottom: 10rpx;
						}

						.describe {
							font-size: 22rpx;
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
							font-size: 26rpx;
							font-weight: 400;
							color: #343434;
						}

						.num {
							margin-top: 7px;
							font-size: 20rpx;
							color: #999999;
						}
					}

				}
			}

			.totalPriceRow {
				display: flex;
				flex-direction: row;
				justify-content: space-between;
				padding-top: 20px;
				padding-bottom: 20px;
				border-bottom: #f0f0f0 solid 1rpx;
				font-size: 27rpx;

				.text {}

				.totalPrice {}
			}

			.bottom {
				display: flex;
				flex-direction: row;
				justify-content: flex-end;
				align-items: center;
				padding-top: 20px;
				padding-bottom: 20px;
				font-size: 27rpx;

				.text {}

				.price {
					font-size: 35rpx;
					font-weight: 700;
					margin-left: 3px;
				}
			}


			.row {
				display: flex;
				flex-direction: row;
				justify-content: flex-start;
				margin-top: 10px;
				margin-bottom: 10px;
				font-size: 26rpx;

				.text {
					color: #999;
					width: 30%;
				}

				.value {
					color: #555555;
					width: 70%;
				}
			}

		}

		.cancel-btn {
			display: flex;
			justify-content: center;
			align-items: center;
			margin-top: 10px;
			font-size: 17px;
			font-weight: 500;
			background-color: #343434;
			color: #FFFFFF;
			width: 80%;
			height: 40px;
		}

		.comfire-btn {
			display: flex;
			justify-content: center;
			align-items: center;
			margin-top: 10px;
			font-size: 17px;
			font-weight: 500;
			background: #FFFFFF;
			border-color: #DBA871;
			color: #DBA871;
			width: 80%;
			height: 40px;
		}


		.comfire-btn:active {
			background: #ffebd7;
		}

	}
</style>
