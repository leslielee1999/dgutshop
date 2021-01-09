<template>
	<view>
		<view class="selectText">
			收货地址
		</view>
		<view class="addressItem" v-for="(item,index) in addressList">
			<view class="left" @click="editAddress(item)">
				<view class="address">
					{{item.userAddress+' '}}{{item.userRoom}}
				</view>
				<view class="nameAndPhone">
					{{item.userName+' '}}{{item.userPhone}}
				</view>
			</view>
			<view class="right">
				<uni-icons size='20' class="icon" type="trash" color="#bdbdbd" @click.stop="deleteAddress(item)"></uni-icons>
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
				addressList:[],
			}
		},
		methods: {
			getAddressList(){
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
						this.addressList = res.data.data.list
						console.log(this.addressList,'getAddressList')
					}
				})
			},
			editAddress(item){
				//修改地址，点击的地址set进缓存，然后跳转
				uni.setStorageSync('editAddressType','edit')
				uni.setStorageSync('needEditAddress',item)
				uni.navigateTo({
					url:'../editAddress/editAddress'
				})
			},
			createAddress(){
				//新建地址，然后跳转
				uni.setStorageSync('editAddressType','create')
				uni.navigateTo({
					url:'../editAddress/editAddress'
				})
			},
			deleteAddress(item){
				let that=this
				uni.showModal({
					title: '删除地址',
					content: '点击确认删除地址',
					success: function(res) {
						if (res.confirm) {
							let token = uni.getStorageSync("token")
							if (token == null) {
								console.log("token null")
								if (!common.doLogin()) {
									return
								}
							}
							//修改收货地址
							uni.request({
								url: that.$apiUrl + '/wechat/address/delete',
								method: "POST",
								data: item,
								header: {
									"Authorization": token
								},
								success: function(res) {
									console.log(res, 'delAddressRes')
									that.getAddressList()
							
								}
							})
				
						} else if (res.cancel) {
				
						}
						
						
						
					}
				});
				
				

			}

		},
		
		onShow() {
			this.getAddressList()
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
