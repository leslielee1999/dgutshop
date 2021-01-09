<template>
	<view class="shopcart">
		<!-- @click="toggleList" -->
		<view class="cartBottom" v-model="order">
			<view class="carIcon" @click.stop="toggleList">
				<view class="iconBox" :class="getAllCount ? 'active' : '' ">
					<text class="allcount" v-if="getAllCount">{{getAllCount}}</text>
					<image :src="getAllCount?'/static/shopcart/shopcart.png':'/static/shopcart/shopcart0.png'" mode="aspectFit" class="img"></image>
				</view>
			</view>
			<view class="middle" @click.stop="toggleList">
				<view class="price" :class="getAllCount ?　'active': ''">￥ {{getOrderTotalPrice}}</view>
				<view class="delivery" v-if="order.type=='0'">
					<view class="deliveryText">
						配送费
					</view>
					<view class="deliveryPrice">
						￥{{orderConfig.dgutshop_order_delivery}}
					</view>
				</view>
			</view>
			<view @click.stop="settle" class="BtnRight">
				<!-- TODO:结算订单函数 -->
				<view class="text">
					结算
				</view>
			</view>
		</view>
		<!-- 选择的商品 -->
		<view class="cartList" v-show="isShowList && order.orderItemList.length">
			<view class="title">
				<view class="clear" @click="clearShopCart">
					<!-- TODO:清空购物袋函数 -->
					<uni-icons type="trash" color="#d6d6d6"></uni-icons>
					<view class="clear-text" style="color: #919191;font-size: 24rpx;">
						清空购物袋
					</view>
				</view>
			</view>
			<scroll-view class="scroll" scroll-y style="height: 400px;display: flex;flex-direction: column;overflow: hidden;">

				<view class="list">
					<view class="list-item" v-for="(item,index) in order.orderItemList" :key="index">
						<view class="image-box">
							<image :src="item.product.picture" mode="aspectFit"></image>
						</view>



						<view class="main">
							<view class="name">{{item.product.name}}

							</view>
							<view class="note">{{item.note}}

							</view>

							<view class="bottom">
								<view class="price">￥ {{item.productActualPrice}}

								</view>
								<view class="control">
									<view class="des">
										<image style="width: 100%;height: 100%;" src="@/static/shopcart/des.png" mode="aspectFill" @click="desOrderItemCount(index)"></image>
									</view>
										
									<text >{{item.sum}}</text>
									<view  class="add" >
										<uni-icons @click="addOrderItemCount(index)" style="background-color: #DBA871;border-radius: 100%;height: 100%;width: 100%;display: flex;align-items: center;justify-content: center;" type="plusempty" color="#FFFFFF"></uni-icons>
									</view>
								</view>
								
									
								
							</view>
						</view>

						<!-- <cartcontrol :food="item" @add="addCart" @dec="decreaseCart"></cartcontrol> -->
						<!-- 换成一个删除按钮就好了 -->
					</view>
				</view>

				<view class="fillHeight">

				</view>
			</scroll-view>
		</view>
		<view class="listMask" v-show="isShowList && order.orderItemList.length" @click="toggleList"></view>
	</view>
</template>

<script>
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	export default {
		props: {
			order: {
				type: Object
			},
			shopInfo:{
				type:Object
			},
			orderConfig:{
				type:Object
			}
		},
		data() {
			return {
				isShowList: false,
			};
		},
		components: {
			uniIcons
		},
		computed: {
			// 获得购物车所有商品数量
			getAllCount() {
				let res=0
				for(let i=0;i<this.order.orderItemList.length;i++){
					res+=this.order.orderItemList[i].sum
				}
				return res
			},
			getOrderTotalPrice(){
				return this.order.orderPrice
			}

		},
		methods: {
			// 解决浮点数 运算出现多位小数
			// accMul(arg1, arg2) {
			// 	let m = 0,
			// 		s1 = '',
			// 		s2 = '';
			// 	if (arg1 && arg1 != null)
			// 		s1 = arg1.toString();
			// 	if (arg2 && arg2 != null)
			// 		s2 = arg2.toString();
			// 	// console.log('m1',s2.replace('.',''))
			// 	try {
			// 		m += s1.split('.')[1].length
			// 	} catch (e) {}
			// 	try {
			// 		m += s2.split('.')[1].length
			// 	} catch (e) {}

			// 	return Number(s1.replace('.', '')) * Number(s2.replace('.', '')) / Math.pow(10, m);
			// },
			settle(){
				//结算函数
				this.$emit('settle');
				
			},


			toggleList() {
				//展开列表
				console.log('tog')
				if (this.order.orderItemList.length) {
					this.isShowList = !this.isShowList;
				}
			},
			clearShopCart() {
				this.$emit('delAll');
			},
			desOrderItemCount(index) {
				this.$emit('dec', index)
			},
			addOrderItemCount(index){
				this.$emit('add',index)
			}

		},
	}
</script>

<style lang="scss">
	.scroll {
		background-color: #FFFFFF;

		.list {
			background-color: #FFFFFF;
			display: flex;
			flex-direction: column;

			&>view {
				display: flex;
			}

			.list-item {
				background-color: #FFFFFF;
				padding-top: 5px;
				display: inline-flex;
				flex-direction: row;
				height: 110px;
				width: 100%;
				border-bottom: #E9E9E9 solid 1px;


				.image-box {
					display: flex;
					align-items: center;
					justify-content: center;
					margin-right: 16rpx;
					margin-left: 2px;
					width: 25%;

					&>image {
						width: 60px;
						height: 60px;
					}
				}

				.main {
					display: flex;
					flex-direction: column;
					width: 75%;

					.name {
						color: #000000;
						font-size: 30rpx;
						font-weight: 700;
						margin-top: 10rpx;
						margin-bottom: 10rpx;
					}

					.note {
						font-size: 24rpx;
						width: 90%;
						color: #999;
						overflow: hidden;
						word-break: break-all;
						/* break-all(允许在单词内换行。) */
						text-overflow: ellipsis;
						/* 超出部分省略号 */
						display: -webkit-box;
						/** 对象作为伸缩盒子模型显示 **/
						-webkit-box-orient: vertical;
						/** 设置或检索伸缩盒对象的子元素的排列方式 **/
						-webkit-line-clamp: 2;
						/** 显示的行数 **/
					}

					.bottom {
						display: flex;
						flex-direction: row;
						justify-content:space-between;
						align-items: center;
						width: 100%;
						margin-top: 8px;

						.price {
							width: 100px;
							font-size: 35rpx;
							font-weight: 700;
							color: #000000;
						}


						.control {
							display: flex;
							flex-direction: row;
							justify-content: space-around;
							align-items: center;
							width: 30%;
							height: 100%;
							margin-right: 10px;
						
						
							.des {
								display: flex;
								align-items: center;
								justify-content: center;
								width: 32px;
								height: 32px;
								box-sizing: border-box;
								border-radius: 100%;
							}
							
							.add{
								display: flex;
								align-items: center;
								justify-content: center;
								width: 25px;
								height: 25px;
								box-sizing: border-box;
								border-radius: 100%;
							}
						}

					}
				}


			}
		}

		.fillHeight {
			height: 80px;
		}
	}



	.shopcart .cartBottom {
		margin-bottom: var(--window-bottom);
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		height: 54px;
		z-index: 99;
		display: flex;
		background-color: #F0F0F1;
	}

	.carIcon {
		flex: 2;
	}

	.iconBox {
		position: absolute;
		bottom: 15px;
		left: 18px;
		z-index: 101;

		background-color: #FFFFFF;
		border-radius: 50%;
		height: 55px;
		width: 55px;
		line-height: 55px;
		/* border: 6px solid #141d27; */

		display: flex;
		align-items: center;
		justify-content: center;

		-webkit-box-shadow: 0px 3px 3px #c5c5c5;
		-moz-box-shadow: 0px 3px 3px #c5c5c5;
		box-shadow: 0px 3px 3px #c5c5c5;
	}

	.iconBox .allcount {
		position: absolute;
		right: -6px;
		top: 0;
		display: inline-block;
		padding: 0 6px;
		font-size: 9px;
		line-height: 16px;
		font-weight: 400;
		border-radius: 10px;
		background-color: #DBA871;
		color: #ffffff;
	}

	.img {
		font-size: 24px;
		line-height: 24px;
		width: 30px;
		height: 30px;
		padding: 6px;
		color: #cccccc;
		border-radius: 50%;
	}

	.carIcon .active {
		background-color: #FFFFFF;


	}

	.middle {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		flex: 4;
		font-size: 20px;
		font-weight: 600;


		.price {
			color: #000000;
		}
		
		.delivery{
			display: flex;
			flex-direction: row;
			font-size: 14px;
			margin-right: 7px;
			font-weight: 400;
			.deliveryText{
				
			}
			.deliveryPrice{}
		}
	}

	.BtnRight {
		flex: 2;
		background-color: #DBA871;
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: 17px;
		color: #FFFFFF;


		.text {}

	}

	.cartList {
		position: fixed;
		bottom: 54px;
		left: 0;
		right: 0;
		z-index: 90;
		display: flex;
		flex-direction: column;
	}


	.cartList .title .clear{
		background: #FFFFFF;
		justify-content: flex-end;
		padding: 10px 10px;
		display: flex;
		flex-direction: row;
		border-top-left-radius: 5px;
		border-top-right-radius: 5px;
		border-bottom: #E9E9E9 solid 1px;
	}




	/* 遮布 */
	.listMask {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		z-index: 89;
		opacity: 0.5;
		background: #6a7076;
	}
</style>
