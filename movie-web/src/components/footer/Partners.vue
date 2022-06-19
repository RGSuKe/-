<template>
	<Header />
	<h1>使用工具</h1>
	<div>
		<el-tabs type="border-card">
		  <el-tab-pane label="前端工具">HBuildx</el-tab-pane>
		  <el-tab-pane label="后端工具">IDEA</el-tab-pane>
		  <el-tab-pane label="数据库可视化工具">Navicat</el-tab-pane>
		  <el-tab-pane label="缓存数据库可视化工具">RESP</el-tab-pane>
		</el-tabs>
	</div>
	<Footer />
</template>


<script setup>
	import Header from '../../components/Header.vue'
	import Footer from '../../components/Footer.vue'
	
</script>

<style scoped>
	div{
		width: 800px;
		margin: 0 auto;
		margin-bottom: 149px;
	}
	.el-tabs{
		font-size: 20px;
	}
	h1{
		margin-left: -650px;
	}
</style>
