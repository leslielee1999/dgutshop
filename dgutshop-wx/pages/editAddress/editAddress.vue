<template>
	<view class="content">
		<view class="bb15">

		</view>
		<view class="list-box">
			<view class="row">
				<text class="tit">联系人</text>
				<input class="input" type="text" v-model="addressData.userName" placeholder="请填写收货人姓名" placeholder-class="placeholder" />
			</view>
			<view class="row">
				<text class="tit">手机号</text>
				<input class="input" type="number" v-model="addressData.userPhone" placeholder="请填写收货手机号码" placeholder-class="placeholder" />
			</view>

			<view class="row">
				<text class="tit">收货地址</text>
				<text @click="chooseLocation" class="input">
					{{addressData.userAddress}}
				</text>
				<uni-icons class="icon" style="border-radius: 100%;" type="arrowright" color="#7c7c7c"></uni-icons>
			</view>
			<view class="row">
				<text class="tit">门牌号</text>
				<input class="input" type="text" v-model="addressData.userRoom" placeholder="例: B座6楼602室" placeholder-class="placeholder" />
			</view>

			<view class="row">
				<text class="tit">默认</text>
				<checkbox-group @change="checkboxChange">
					<checkbox  :value="addressData.isDefault" :checked="addressData.isDefault=='1'?true:false" />
				</checkbox-group>
			</view>

		</view>
		<button class="add-btn" type="default" @click="confirm">保存</button>
	</view>
</template>

<script>
	import common from "../../common/util.js"
	export default {
		data() {
			return {
				addressData: {
					userName: '',
					userPhone: '',
					userAddress: '在地图选择',
					userRoom: '',
					isDefault: 1
				},
				editType: ''
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

			checkboxChange: function(e) {
				this.addressData.isDefault = this.addressData.isDefault == '1' ? '0' : '1'
			},

			//提交
			confirm() {
				let data = this.addressData;
				// if (!data.userName) {
				// 	this.$api.msg('请填写收货人姓名');
				// 	return;
				// }
				// if (!/(^1[3|4|5|7|8][0-9]{9}$)/.test(data.userPhone)) {
				// 	this.$api.msg('请输入正确的手机号码');
				// 	return;
				// }
				// if (!data.userAddress) {
				// 	this.$api.msg('请在地图选择所在位置');
				// 	return;
				// }
				// if (!data.userRoom) {
				// 	this.$api.msg('请填写门牌号信息');
				// 	return;
				// }

				//needApi:需要修改和新建地址的api

				console.log(this.addressData, 'submitAddress')
				let token = uni.getStorageSync("token")
				if (token == null) {
					console.log("token null")
					if (!common.doLogin()) {
						return
					}
				}
				//修改收货地址
				uni.request({
					url: this.$apiUrl + '/wechat/address/save',
					method: "POST",
					data: this.addressData,
					header: {
						"Authorization": token
					},
					success: function(res) {
						console.log(res, 'editAddressRes')


					}
				})
				uni.showToast({
					title: `地址${this.manageType=='edit' ? '修改': '添加'}成功`,
				});

				setTimeout(() => {
					uni.navigateBack()
				}, 800)
			},
		},
		onShow() {
			this.editType = uni.getStorageSync('editAddressType')
			let title = '新增收货地址';
			if (this.editType === 'edit') {
				title = '编辑收货地址'
				this.addressData = uni.getStorageSync('needEditAddress')
			}
			uni.setNavigationBarTitle({
				title
			})
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
