<template>
	 <el-carousel :interval="4000" type="card" height="300px" >
		<el-carousel-item v-for="item in carousel" :key="item">
			<img :src="item.src" />
		</el-carousel-item>
	  </el-carousel>
 
</template>

<style scoped>
	.el-carousel__item h3 {
	  color: #475669;
	  font-size: 14px;
	  opacity: 0.75;
	  line-height: 200px;
	  margin: 0;
	  text-align: center;
	}

	.el-carousel__item:nth-child(2n) {
	  background-color: #99a9bf;
	}

	.el-carousel__item:nth-child(2n + 1) {
	  background-color: #d3dce6;
	}
	.el-carousel{
		padding-top: 2%;
		width: 1000px;
		margin: 0 auto;
	}
	img{
		width: 500px;
		height: 300px;
	}
</style>


<script lang="ts" setup>
	import {reactive,ref } from 'vue'
	
	const carousel = reactive([
		{name: "one", src: new URL("../img/carousel/1.png", import.meta.url), title: "one"},
		{name: "tow", src: new URL("../img/carousel/2.png", import.meta.url), title: "two"},
		{name: "three", src: new URL("../img/carousel/3.png", import.meta.url), title: "three"},
		{name: "four", src: new URL("../img/carousel/4.png", import.meta.url), title: "four"},
		{name: "five", src: new URL("../img/carousel/5.png", import.meta.url), title: "five"},
		{name: "six", src: new URL("../img/carousel/6.png", import.meta.url), title: "six"},
	])
	
</script>