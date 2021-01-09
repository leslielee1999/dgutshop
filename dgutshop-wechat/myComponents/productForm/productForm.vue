<template>
	<view class="product-form">
	
		<view class="ctn">
			<view class="leftBox">
				<view class="ring" style="background-color:#b5b5b5">
					<uni-icons type="closeempty" color="#FFFFFF" @click="closeProductPopup"></uni-icons>
	
				</view>
			</view>
		</view>
	
		<view class="product-image">
			<!-- TODO:改成商品图片 -->
			<image src="https://s3.ax1x.com/2020/12/12/rZBzdK.png"></image>
		</view>
		<view>
			
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			product: {
				type: Array
			}
		},
		data() {
		},
		components: {
			cartcontrol
		},
		computed: {
		},
		methods: {

		},
	}
</script>

<style>
</style>
