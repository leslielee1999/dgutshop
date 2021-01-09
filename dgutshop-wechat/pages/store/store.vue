<template>
	<view class="content">

		<uni-popup class="product-card" ref="productPopup" type="center" :animation="true">
			<view class="card-content">

				<scroll-view class="product-form" scroll-y="true">
					<view class="ctn">
						<view class="leftBox">
							<view class="ring" style="background-color:#b5b5b5">
								<uni-icons type="closeempty" color="#FFFFFF" @click="closeProductPopup"></uni-icons>

							</view>
						</view>
					</view>

					<view class="product-image">
						<image mode="heightFix" :src="selectProduct.picture"></image>
					</view>

					<view class="formContent">
						<view class="name">
							{{selectProduct.name}}
						</view>

						<!-- <uni-tag class="sort" text="含乳制品、茶" :circle="true" size="small"></uni-tag> -->
						<uni-tag v-if="selectProduct.heated" class="sort" text="可做热饮" :circle="true" size="small" type="error"></uni-tag>

						<view class="describe">
							<view class="title">产品描述</view>
							<view class="content">{{selectProduct.description}}</view>
						</view>

						<view class="size">
							<view class="title">规格</view>
							<view class="content">
								<view class="content filter-body-section-body style-flex style-flex-wrap">
									<axb-check-box ref="sizeCheckBox" @change="selectSize"></axb-check-box>
								</view>
							</view>
						</view>

						<view class="sweet">
							<view class="title">甜度</view>
							<view class="content filter-body-section-body style-flex style-flex-wrap">
								<axb-check-box ref="sweetCheckBox" @change="selectSweet"></axb-check-box>
							</view>
						</view>

						<view class="size">
							<view class="title">温度</view>
							<view class="content">
								<view class="content filter-body-section-body style-flex style-flex-wrap">
									<axb-check-box ref="iceCheckBox" @change="selectIce"></axb-check-box>
								</view>
							</view>
						</view>

						<view class="Topping">
							<view class="title">加料</view>
							<view>
								<view class="filter-body-section-body style-flex style-flex-wrap">
									<!-- TODO:加料算价钱还没有做 -->
									<axb-check-box ref="toppingCheckBox" :multi="true" @change="selectTopping"></axb-check-box>
								</view>
							</view>
						</view>
						<view class="fillHeight"></view>
					</view>


				</scroll-view>
				<view class="formBottom" v-model="formData">
					<view class="textAndcontrol">
						<view class="bottomText">
							<view class="price">￥{{formData.productActualPrice}}</view>
							<view class="mark" v-model="formData">{{formData.size}}{{formData.size==''?'':','}}{{formData.ice}}{{formData.ice==''?'':','}}{{formData.sweet}}{{formData.sweet==''?'':','}}{{formData.toppingTotal}}</view>
						</view>
						<view class="control">
							<view class="des">
								<image style="width: 100%;height: 100%;" src="../../static/shopcart/des.png" mode="aspectFill" @click="desFormDataCount"></image>
							</view>

							<text>{{formData.sum}}</text>
							<view class="add">
								<uni-icons @click="addFormDataCount" style="background-color: #DBA871;border-radius: 100%;height: 100%;width: 100%;display: flex;align-items: center;justify-content: center;"
								 type="plusempty" color="#FFFFFF"></uni-icons>
							</view>
						</view>

					</view>


					<button class="formButton" @click="submitOrderItem">加入购物袋</button>
				</view>
			</view>
		</uni-popup>


		<!-- 头部店铺 -->
		<view class="box" :style="[{'margin-top':statusBarHeight+'px'}]">
			<view class="shop-name">
				<view class="name">{{shopInfo.dgutshop_shop_name}}</view>
				<!-- TODO:增加查看店铺信息或更换店铺页面的跳转按钮 -->
				<view class="select">
					<view :class="[order.type == 1 ? 'on' : 'off']" @click="changePeisongType">自取</view>
					<view :class="[order.type == 0 ? 'on' : 'off']" @click="changePeisongType">外卖</view>
				</view>
			</view>
			<view class="distance-bar">
				<view class="distance">距离您{{distance}}千米</view>
				<navigator url="/pages/shopInfo/shopInfo" hover-class="none">
					<view class="shop-message">
						<view class="message">查看门店信息</view>
					</view>
				</navigator>
			</view>
		</view>

		<!-- TODO:可以加个纵向滚动公告 -->

		<!-- 滚动区域 -->
		<view class="scroll-panel" id="scroll-panel">
			<view class="list-box">
				<view class="left">


					<scroll-view style="height: 100%;" class="menus" :scroll-into-view="menuScrollIntoView" scroll-with-animation
					 scroll-y>
						<view class="wrapper">
							<view class="menu" :id="`menu-${item.id}`" :class="{'active': item.id === currentCateId}" v-for="(item, index) in menu"
							 :key="index" @tap="handleMenuTap(item.id)">
								<image class="icon" mode="aspectFit" :src="item.icon" @error="imageError"></image>
								<view class="name">{{item.name}}</view>
							</view>
							<view style="height: 150px;"></view>
						</view>

					</scroll-view>

				</view>
				<view class="main">
					<scroll-view style="height: 100%;" scroll-with-animation scroll-y :scroll-top="cateScrollTop" @scroll="handleGoodsScroll">
						<swiper class="ads" id="ads" autoplay :interval="3000" indicator-dots>
							<swiper-item v-for="(item, index) in ads" :key='index'>
								<image :src="item.picture"></image>
							</swiper-item>
						</swiper>
						<view class="item main-item" v-for="(item, index) in menu" :key="index" :id="`cate-${item.id}`">
							<view class="title">
								<view>{{ item.name }}</view>
							</view>
							<view class="goods" v-for="(item2, index2) in item.categoryItems" :key="index2" @click="openProductPopup(item2.product)">
								<image :src="item2.product.picture" mode="aspectFit"></image>
								<view>
									<view class="title">{{item2.product.name}}</view>
									<uni-tag v-if="item2.product.heated" class="heated" text="可做热饮" :circle="true" size="small" type="error"></uni-tag>
									<view class="describe">{{item2.product.description}}</view>
									<!-- TODO:加个销量显示 -->
									<view class="bottom">
										<!-- //TODO:现在显示的是中杯原价，如果有折扣的话，做划掉原价的ui -->
										<view class="price">
											<view>￥{{item2.product.mediumPromotePrice!=null?item2.product.mediumPromotePrice:item2.product.mediumOriginalPrice}}</view>
											<view class="originalPrice" v-if="item2.product.mediumPromotePrice!=null">{{item2.product.mediumOriginalPrice}}</view>
										</view>

										<button hover-class='none' class="button" size="mini">选规格</button>

									</view>

								</view>
							</view>
							<!-- 为了不被tarBar或购物车遮盖，进行的填充 -->

						</view>
						<view style="height: 150px;"></view>
						<!-- <view class="fill-last" :style="{ height: fillHeight + 'px' }"></view> -->
					</scroll-view>
				</view>
			</view>
		</view>


		<shopcart class="shopcart" @delAll="clearShopCart" @dec="desOrderItemCount" @add="addOrderItemCount" @settle="settle"
		 :order="order" :shopInfo="shopInfo" :orderConfig="orderConfig"></shopcart>

	</view>

</template>
<script>
	import uniPopup from "@/components/uni-popup/uni-popup.vue"
	import shopcart from "@/myComponents/shopcart/shopcart.vue"
	import cartcontrol from '@/myComponents/cartcontrol/cartcontrol.vue'
	import uniTag from "@/components/uni-tag/uni-tag.vue"
	import axbCheckBox from '@/components/axb-checkbox/axb-checkbox.vue'
	import common from "../../common/util.js"
	export default {
		components: {
			uniPopup,
			shopcart,
			uniTag,
			axbCheckBox
		},

		data() {
			return {
				distance: 0,
				statusBarHeight: 0, //顶部导航栏高度
				isClick: false,
				menuScrollIntoView: '',
				cateScrollTop: 0,
				currentCateId: 0,
				sizeCalcState: false,

				shopInfo: {},
				orderConfig:{},
				scrollHeight: 400, //滚动区域高度
				scrollTopSize: 0,
				fillHeight: 0, // 填充高度，用于最后一项低于滚动区域时使用
				leftList: [],
				menu: [],
				topArr: [],
				leftIndex: 0,
				scrollInto: '',
				ads: [],
				sweet: [{
						name: '标准甜',
						value: '标准甜',
						checked: 1
					},
					{
						name: '七分甜',
						value: '七分甜',
						checked: 0
					},
					{
						name: '五分甜',
						value: '五分甜',
						checked: 0
					},
					{
						name: '三分甜',
						value: '三分甜',
						checked: 0
					},
					{
						name: '无糖',
						value: '无糖',
						checked: 0
					}
				],
				size: [{
						name: '中杯',
						value: '中杯',
						checked: 1
					},
					{
						name: '大杯',
						value: '大杯',
						checked: 0
					},
				],
				ice: [{
						name: '冰',
						value: '冰',
						checked: 1
					},
					{
						name: '少冰',
						value: '少冰',
						checked: 0
					},
					{
						name: '去冰',
						value: '去冰',
						checked: 0
					},
					{
						name: '热',
						value: '热',
						checked: 0
					},
				],
				ice_noHot: [{
						name: '冰',
						value: '冰',
						checked: 1
					},
					{
						name: '少冰',
						value: '少冰',
						checked: 0
					},
					{
						name: '去冰',
						value: '去冰',
						checked: 0
					},
				],
				toppingList: [],
				toppingNameAndPrice: {},
				selectProduct: {},
				formData: {
					product_name: '',
					sum: 1,
					productOriginalPrice: 0, //商品原价
					productPromotePrice: 0, //商品折扣价
					topping_pric: 0, //小料价
					productActualPrice: 0, //商品实际价（折扣价+小料价）
					sweet: '标准甜',
					ice: '冰',
					size: '中杯',
					topping: [],
					toppingTotal: '',
					price: 0,
					note: ''
				},
				order: {
					type: 1,
					productPrice: 0, //商品总价
					deliveryPrice: 0, //配送费
					orderPrice: 0,
					consignee: '',
					consigneePhone: '',
					consigneeAddress: '',
					consigneeRoom: '',
					orderItemList: [],
					// TODO:配送费，多少以上免配送费，多少以上才配送
				},
				orderItem: {
					product_name: '', //商品名称
					productOriginalPrice: 0, //商品原价
					productPromotePrice: 0, //商品折扣价
					topping_pric: 0, //小料价
					productActualPrice: 0, //商品实际价（折扣价+小料价）
					product_picture: '', //图片
					total_item_price: 0, //订单项
					note: '', //备注
				}



			}
		},
		methods: {
			changePeisongType() {
				this.order.type = this.order.type == 0 ? 1 : 0;
				if(this.order.type){
					this.order.deliveryPrice=0
				}else{
					this.order.deliveryPrice=this.orderConfig.dgutshop_order_delivery
				}
				
				this.computedOrderTotalPrice()
			},
			imageError: function(e) {
				console.error('image发生error事件，携带值为' + e.detail.errMsg)
			},
			openProductPopup(item) {
				this.$refs.toppingCheckBox.setItems(this.toppingList)
				if(item.heated){
					this.$refs.iceCheckBox.setItems(this.ice)
				}else{
					this.$refs.iceCheckBox.setItems(this.ice_noHot)
				}
				this.$refs.sizeCheckBox.setItems(this.size)
				this.$refs.sweetCheckBox.setItems(this.sweet)
				this.selectProduct = item
				this.formData = {
					sum: 1,
					productOriginalPrice: item.mediumOriginalPrice, //商品原价
					productPromotePrice: item.mediumPromotePrice, //商品折扣价
					toppingPrice: 0, //小料价
					productActualPrice: 0, //商品实际价（折扣价+小料价）
					sweet: '标准甜',
					ice: '冰',
					size: '中杯',
					topping: [],
					toppingTotal: '',
					note: ''
				}


				for (let i = 0; i < this.toppingList.length; i++) {
					this.toppingList[i].checked = 0
				}
				//needAPI:把商品信息复制到formData和selectProduct
				this.formData.note = this.formData.size + ',' + this.formData.ice + ',' + this.formData.sweet + ','
				this.computedFormDataTotalPrice()
				this.$refs.productPopup.open()
				console.log("打开")
			},
			closeProductPopup() {
				this.$refs.productPopup.close()
				console.log("关掉")
			},
			selectSweet(val) {
				this.formData.sweet = val
			},
			selectSize(val) {
				this.formData.size = val
				this.computedFormDataTotalPrice()
				console.log(this.formData)
			},
			selectIce(val) {
				this.formData.ice = val
			},
			selectTopping(val) {
				console.log('选了')
				console.log(this.toppingList)
				this.formData.topping = val
				this.formData.toppingTotal = ''
				this.formData.toppingPrice = 0
				for (const val of this.formData.topping) {
					this.formData.toppingTotal = this.formData.toppingTotal + val + ','
					this.formData.toppingPrice += this.toppingNameAndPrice[val]
					this.computedFormDataTotalPrice()
				}
				//TODO:要算加料价格！！！
			},
			submitOrderItem() {
				this.formData.product = this.selectProduct
				let f = this.formData
				this.formData.note = f.size + ',' + f.ice + ',' + f.sweet + ',' + f.toppingTotal

				this.formData.size = this.formData.size == '中杯' ? 0 : 1;
				delete this.formData.topping
				delete this.formData.toppingTotal
				delete this.formData.sweet
				delete this.formData.ice

				console.log('成功将商品加入购物车')
				console.log(this.order)
				//needAPI:提交form的时候计算价格，拼接note，然后赋值到this.order.orderItems[this.order.orderItems.length-1]
				this.order.orderItemList.push(Object.assign({}, this.formData))
				this.computedOrderTotalPrice()
				this.closeProductPopup()


			},
			desFormDataCount() {
				if (this.formData.sum > 1) {
					this.formData.sum--
					this.computedFormDataTotalPrice()
				}

			},
			addFormDataCount() {
				this.formData.sum++
				this.computedFormDataTotalPrice()
			},
			computedFormDataTotalPrice() {
				let product = this.selectProduct
				if (this.formData.size == '中杯') {
					this.formData.productOriginalPrice = product.mediumOriginalPrice
					this.formData.productPromotePrice = product.mediumPromotePrice ? product.mediumPromotePrice : product.mediumOriginalPrice
					if (product.mediumPromotePrice != null) {
						this.formData.productActualPrice = product.mediumPromotePrice
					} else {
						this.formData.productActualPrice = product.mediumOriginalPrice
					}
				} else {
					this.formData.productOriginalPrice = product.bigOriginalPrice
					this.formData.productPromotePrice = product.bigPromotePrice ? product.bigPromotePrice : product.bigOriginalPrice
					if (product.bigPromotePrice != null) {
						this.formData.productActualPrice = product.bigPromotePrice
					} else {
						this.formData.productActualPrice = product.bigOriginalPrice
					}
				}
				this.formData.productActualPrice += this.formData.toppingPrice
				this.formData.productActualPrice = this.formData.productActualPrice * this.formData.sum

			},
			computedOrderTotalPrice() {
				this.order.productPrice = 0
				this.order.orderPrice = 0
				let list = this.orderItemList
				for (let i = 0; i < this.order.orderItemList.length; i++) {
					this.order.productPrice += this.order.orderItemList[i].productActualPrice
				}

				this.order.deliveryPrice=Number(this.order.deliveryPrice)
				this.order.orderPrice = this.order.productPrice + this.order.deliveryPrice
				console.log('计算订单总价')
				console.log(this.order)
			},
			computedOrderDeliveryPrice() {
				if(this.order.type=='0'){
					return this.orderConfig.dgutshop_order_delivery
				}
				return 0

			},
			clearShopCart() {
				//清空购物车，直接重置订单
				this.order = {
					type: 1,
					productPrice: 0, //商品总价
					deliveryPrice: 0, //配送费
					orderPrice: 0,
					consignee: '',
					consigneePhone: '',
					consigneeAddress: '',
					consigneeRoom: '',
					orderItemList: [],
					// TODO:配送费，多少以上免配送费，多少以上才配送
				}
			},
			desOrderItemCount(index) {
				//订单项减少
				if (this.order.orderItemList[index].sum <= 1) {
					this.order.orderItemList.splice(index, 1)
					this.computedOrderTotalPrice()
					return
				}
				let singlePrice = this.order.orderItemList[index].productActualPrice / this.order.orderItemList[index].sum
				this.order.orderItemList[index].sum--;

				this.order.orderItemList[index].productActualPrice = singlePrice * this.order.orderItemList[index].sum
				this.computedOrderTotalPrice()
			},
			addOrderItemCount(index) {
				//订单项增加
				let singlePrice = this.order.orderItemList[index].productActualPrice / this.order.orderItemList[index].sum
				this.order.orderItemList[index].sum++;

				this.order.orderItemList[index].productActualPrice = singlePrice * this.order.orderItemList[index].sum
				this.computedOrderTotalPrice()
			},
			settle() {
				let isLogin = uni.getStorageSync("isLogin")
				let that = this
				if (!isLogin || isLogin == null) {
					uni.showToast({
						title: '请登录',
						icon:'loading'
					});
					uni.navigateTo({
						url: '../login/login'
					})
					return
				}
				
				let userStatus=uni.getStorageSync("userStatus")
				
				if(userStatus=='1'||userStatus=='2'){
					uni.showToast({
						title: '无权限',
						icon:'none'
					});
					return
				}

				if (this.order.orderItemList.length <= 0) {
					uni.showToast({
						title: '请添加商品',
						image: '/static/shopcart/shopcart.png'
					});
				} else {
					console.log(this.distance, "distance")
					if (this.distance > 15 && this.order.type == '1') {
						uni.showModal({
							title: '距离门店过远',
							content: '您距离门店' + this.distance + "千米，距离过远，请确认是否下单",
							success: function(res) {
								if (res.confirm) {
									uni.setStorageSync('settle_order', that.order)
									uni.navigateTo({
										url: '../orderPreview/orderPreview'
									})


								} else if (res.cancel) {

								}
							}
						});
					} else {
						uni.setStorageSync('settle_order', this.order)
						uni.navigateTo({
							url: '../orderPreview/orderPreview'
						})
					}

					//结算函数

				}

			},

			handleMenuTap(id) { //点击菜单项事件

				this.isClick = true
				if (!this.sizeCalcState) {
					this.calcSize()
				}

				this.currentCateId = id
				this.$nextTick(() => this.cateScrollTop = this.menu.find(item => item.id == id).top)

			},
			handleGoodsScroll({
				detail
			}) { //商品列表滚动事件
				if (this.isClick) {
					this.isClick = false
					return
				}
				if (!this.sizeCalcState) {
					this.calcSize()
				}
				const {
					scrollTop
				} = detail
				let tabs = this.menu.filter(item => item.top + 10 <= scrollTop).reverse()
				if (tabs.length > 0) {
					this.currentCateId = tabs[0].id
					console.log("avtive change")
				}



			},
			calcSize() {
				let h = 0

				let view = uni.createSelectorQuery().select('#ads')
				view.fields({
					size: true
				}, data => {
					h += Math.floor(data.height)
				}).exec()

				this.menu.forEach(item => {
					let view = uni.createSelectorQuery().select(`#cate-${item.id}`)
					view.fields({
						size: true
					}, data => {
						item.top = h
						h += data.height
						item.bottom = h
					}).exec()
				})
				this.sizeCalcState = true
			},

			findXy() { //获取用户的经纬度
				var _this = this
				wx.getLocation({
					type: 'wgs84',
					success(res) {
						_this.distance = _this.getDistance(res.latitude, res.longitude, _this.shopInfo.dgutshop_shop_latitude, _this.shopInfo
							.dgutshop_shop_longitude)
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
		},

		computed: {

		},
		mounted() {

		},
		onShow() {
			console.log("isTakeOut:", uni.getStorageSync("isTakeOut"))
			if (uni.getStorageSync("isTakeOut")) {
				this.order.type = 0
			} else {
				this.order.type = 1
			}
			uni.removeStorageSync('isTakeOut');
		},
		onLoad() {
			// common.doLogin()
			let that = this
			//获取小料表
			uni.request({
				url: this.$apiUrl + '/wechat/topping/list',
				method: 'GET',
				success: (res) => {
					console.log('topping')
					console.log(res)
					let list = res.data
					for (let i = 0; i < list.length; i++) {
						let toping = new Object
						toping.name = list[i].name
						toping.value = list[i].name
						toping.checked = 0
						that.toppingList.push(toping)
						that.toppingNameAndPrice[list[i].name] = Number(list[i].price)
					}

					console.log(that.toppingList)
					console.log(that.toppingNameAndPrice)
				}
			})


			console.log('状态栏高度')
			let system = new Object
			wx.getSystemInfo({
				success: res => {
					system = res
				}
			})
			console.log(system)
			this.statusBarHeight = system.statusBarHeight
			console.log('导航栏')
			console.log()
			let topMenu = wx.getMenuButtonBoundingClientRect()


			this.statusBarHeight = (topMenu.top - system.statusBarHeight) * 2 + topMenu.height + this.statusBarHeight
			if (this.statusBarHeight < 80) {
				this.statusBarHeight += 25
			}
			console.log(this.statusBarHeight)

			//获取商家信息
			this.shopInfo = uni.getStorageSync("shopInfo")
			this.orderConfig=uni.getStorageSync("orderConfig")



			//获取轮播图
			uni.request({
				url: this.$apiUrl + '/wechat/slideshow/list',
				method: 'GET',
				success: (res) => {
					console.log('slideShowList')
					that.ads = res.data
					that.ads.sort(function(a, b) {
						return a.index - b.index
					})
				}
			})

			uni.request({
				url: this.$apiUrl + '/wechat/catalog/list',
				method: 'GET',
				success: (res) => {
					console.log('menu')
					that.menu = res.data.data.list
					console.log(that.menu)
					that.menu.sort(function(a, b) {
						return a.index - b.index
					})
					for (let i = 0; i < that.menu.length; i++) {
						that.menu[i].categoryItems.sort(function(a, b) {
							return a.index - b.index
						})
					}
				}
			})


			//计算距离
			this.findXy()


			//needApi：获取用户address并把default设进缓存
		},

	}
</script>

<style lang="scss">
	page {
		height: 100%;
	}

	.content {
		top: 0;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		overflow: hidden;

		.box {
			display: flex;
			flex-direction: column;
			flex: 99;
			height: 333px;
			border-style: solid;
			box-shadow: 0px 2px 2px 1px #DDDDDD;
			border-width: 0rpx;
			border-bottom: #E9E9E9 solid 1px;
			// margin-top: calc(var(--status-bar-height)+200px);

			.shop-name {
				display: flex;
				justify-content: space-between;
				align-items: center;
				margin: 0rpx 30rpx 0rpx 30rpx;
			}

			.shop-name .name {
				font-size: 35rpx;
			}

			.shop-name .select {
				display: flex;
				align-items: center;
				font-size: 25rpx;
				width: 180rpx;
				height: 50rpx;
				border-radius: 32rpx;
				background-color: #f6f6f6;
				padding: 2px;
				border: 1px solid #dedede;
				margin-top: 8px;
			}

			.shop-name .select .on {
				width: 90rpx;
				height: 60rpx;
				background-color: #343434;
				color: #ffffff;
				border-radius: 32rpx;
				text-align: center;
				line-height: 60rpx;
			}

			.shop-name .select .off {
				width: 90rpx;
				height: 60rpx;
				color: #d2d2d2;
				border-radius: 25rpx;
				text-align: center;
				line-height: 60rpx;
			}


			.distance-bar {
				display: flex;
				justify-content: space-between;
				margin: 30rpx;
			}

			.distance-bar .distance {
				font-size: 28rpx;
			}

			.distance-bar .shop-message {
				display: flex;
				align-items: center;
			}

			.distance-bar .shop-message .message {
				font-size: 26rpx;
			}

			.distance-bar .shop-message .go {
				height: 32rpx;
				width: 32rpx;
			}


		}

	}








	.scroll-panel {
		height: 100%;

	}


	.scroll-panel .list-box {
		display: flex;
		flex-direction: row;
		flex-wrap: nowrap;
		justify-content: flex-start;
		align-items: flex-start;
		align-content: flex-start;
		font-size: 28rpx;
		height: 100%;
		margin-bottom: var(--window-bottom);

		.left {
			width: 180rpx;
			background-color: #f6f6f6;
			// line-height: 80rpx;
			box-sizing: border-box;
			overflow: hidden;
			height: 100%;

			.menu {
				border-left: 5px #f6f6f6 solid;
				padding-top: 9px;
				padding-bottom: 9px;
				// position: relative;

				&:not(:first-child) {


					// &::after {
					// 	content: '';
					// 	display: block;
					// 	height: 0;
					// 	border-top: #d6d6d6 solid 1px;
					// 	width: 620upx;
					// 	position: absolute;
					// 	top: -1px;
					// 	right: 0;
					// 	transform: scaleY(0.5);
					// 	/* 1px像素 */
					// }
				}

				&.active {
					color: #343434;
					background-color: #fff;
					border-left: 5px #DBA871 solid;

					.icon {
						background-color: #fff;
					}

					.name {
						color: #343434;
						font-weight: 700;
					}
				}

				.icon {
					margin-left: 32px;
					width: 25px;
					height: 25px;
					background-color: #f6f6f6;
				}

				.name {
					color: #cdcdcd;
					font-size: 15px;
					font-weight: 400;
					display: flex;
					flex-direction: row;
					justify-content: center;
				}
			}

			.fill-last {
				height: 0;
				width: 100%;
				background: none;
			}
		}

		.main {
			height: 100%;
			background-color: #fff;
			padding-left: 20rpx;
			width: 0;
			flex: 1;
			box-sizing: border-box;
			overflow: hidden;


			.ads {
				height: calc(300 / 550 * 510rpx);

				image {
					width: 100%;
					height: 100%;
					border-radius: 8rpx;
				}
			}

			.title {
				line-height: 64rpx;
				font-size: 26rpx;
				font-weight: 500;
				color: #626262;
				background-color: #fff;
				// position: sticky;
				top: 0;
				z-index: 19;
			}
			
			

			.item {
				padding-bottom: 10rpx;
				border-bottom: #E9E9E9 solid 1px;
			}

			.goods {
				display: flex;
				flex-direction: row;
				flex-wrap: nowrap;
				justify-content: flex-start;
				align-items: center;
				align-content: center;
				margin-top: 15rpx;
				margin-bottom: 40rpx;

				&>image {
					width: 120rpx;
					height: 120rpx;
					margin-right: 16rpx;
					margin-left: 2px;
				}

				.title {
					color: #000000;
					font-size: 30rpx;
					font-weight: 700;
					margin-top: 10rpx;
					margin-bottom: 10rpx;
				}
				
				.heated{
					display: flex;
					flex-direction: row;
					justify-content: flex-start;
				}

				.describe {
					font-size: 24rpx;
					width: 360rpx;
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
					width: 100%;
					display: flex;
					flex-direction: row;
					justify-content: space-between;
					align-content: space-between;

					.price {
						width: 70%;
						display: flex;
						flex-direction: row;
						align-items: flex-end;

						.originalPrice {
							text-decoration: line-through;
							margin-left: 5px;
							font-size: 28rpx;
							color: #737373;
							margin-bottom: 1px;
						}

						margin-top: 10px;
						font-size: 35rpx;
						font-weight: 600;
						color: #000000;
					}


					.button {
						// margin-left: 55px;
						width: 30%;
						margin-top: 10px;
						font-size: 13px;
						color: #FFFFFF;
						background-color: #DBA871;
						width: 100rpx;
						height: 40rpx;
						padding-left: 2px;
						padding-right: 2px;
						display: flex;
						align-items: center;
						justify-content: center;
						// padding-top: 0px;



						-webkit-border-radius: 30px;
						-moz-border-radius: 30px;
						border-radius: 30px;


						-webkit-transition: all 0.15s ease;
						-moz-transition: all 0.15s ease;
						-o-transition: all 0.15s ease;
						-ms-transition: all 0.15s ease;
						transition: all 0.15s ease;
					}

				}


			}
		}
	}

	.product-card {
		z-index: 200;
		display: flex;
		width: 100%;
		height: 100%;
		margin-bottom: var(--window-bottom);


		.card-content {
			height: 100vh; //vh指view hight，基于窗口高度设置
			display: flex;
			flex-direction: column;
			justify-content: center;
			// align-items:flex-end;

			.product-form {
				background-color: #FFFFFF;
				width: 660rpx;
				height: 65%;
				// margin-bottom: 80rpx;
				border-top-left-radius: 5px;
				border-top-right-radius: 5px;




				.product-image {
					height: 150px;
					width: 100%;
					display: flex;
					flex-direction: column;
					align-items: center;
					position: relative;
				}

				.ring {
					background-color: rgba(0, 0, 0, 0.3);
					width: 28px;
					height: 28px;
					border-radius: 100%;
					display: flex;
					align-items: center;
					justify-content: center;
					font-size: 16px;
				}

				.ctn {
					z-index: 2;
					/* border: 1px solid #e3e3e3; */
					width: 100%;
					display: flex;
					justify-content: flex-end;
					overflow: hidden;
					align-items: center;
					position: fixed;

					.leftBox {
						display: flex;
						width: 60px;
						justify-content: space-between;
						flex: none;
						margin-top: 10px;
						margin-right: 30px;
						align-items: center;
					}

					.jrNull {
						/* #ifdef MP */
						width: 95px;
						flex: none;
						/* #endif */
					}
				}

				.fillHeight {
					height: 50px;
				}

				.formContent {
					margin-left: 18px;
					margin-right: 18px;
					font-size: 13px;

					* {
						.title {
							color: #666666;
							margin-top: 7px;
							margin-bottom: 7px;

						}
					}


					.name {
						font-size: 20px;
						font-weight: 800;
						color: #000000;
					}

					.describe {
						font-size: 13px;
						color: #898989;
					}

					.sort {
						margin-top: 5px;
						margin-bottom: 5px;
						width: auto;
						display: inline-block !important;
						display: inline;
						background-color: #F6F6F6;
						color: #BABABA;
					}

					.ice {
						margin-top: 10px;
					}

					.sweet {
						margin-top: 25px;
					}

					.size {
						margin-top: 25px;
					}
				}

			}

			.formBottom {
				width: 660rpx;
				height: 15%;
				max-height: 130px;
				background-color: #FFFFFF;
				flex-direction: column;
				align-items: stretch;
				border-bottom-left-radius: 5px;
				border-bottom-right-radius: 5px;
				display: flex;
				justify-content: space-between;


				.textAndcontrol {
					display: flex;
					flex-direction: row;
					justify-content: space-between;


					.bottomText {
						display: flex;
						flex-direction: column;

						height: 40%;
						width: 70%;

						.price {
							width: 90%;
							height: 20%;
							margin-left: 20px;
							margin-right: 20px;
							margin-top: 5px;
							margin-bottom: 5px;
							font-size: 18px;
							color: #DBA871;
						}

						.mark {
							font-size: 10px;
							color: #878787;
							height: 80%;
							width: 90%;
							margin-left: 20px;
							margin-right: 20px;
							margin-top: 10px;
						}
					}

					.control {
						display: flex;
						flex-direction: row;
						justify-content: space-around;
						align-items: center;
						width: 30%;
						height: 100%;


						.des {
							display: flex;
							align-items: center;
							justify-content: center;
							width: 32px;
							height: 32px;
							box-sizing: border-box;
							border-radius: 100%;
						}

						.add {
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


				// TODO:修改formButtom样式，编写加入购物车逻辑（要计算价格）




				none::after {
					border: none;

				}

				.formButton {
					display: flex;
					height: 30%;
					width: 85%;
					justify-content: center;
					align-items: center;
					background-color: #DBA871;
					font-size: 14px;
					margin-bottom: 10px;
					color: #FFFFFF;
				}
			}
		}


	}
</style>
