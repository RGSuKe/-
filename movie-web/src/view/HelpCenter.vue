<template>
	<Header />
	<div class="demo-collapse">
	    <el-collapse accordion>
	      <el-collapse-item name="1">
	        <template #title>
	          出现问题怎么办？<el-icon class="header-icon">
	            <info-filled />
	          </el-icon>
	        </template>
	        <div>
	         不解决，无法解决。
	        </div>
	        
	      </el-collapse-item>
	      <el-collapse-item title="有逻辑错误怎么办？" name="2">
	        <div>
				错就错了。
	        </div>
	        <div>
	          
	        </div>
	      </el-collapse-item>
	     
	    </el-collapse>
	  </div>
	<Footer />
</template>

<script setup >
	import Header from '../components/Header.vue'
	import Footer from '../components/Footer.vue'
	import { InfoFilled } from '@element-plus/icons-vue'
</script>

<style scoped>
	.demo-collapse{
		padding-top: 20px;
		width: 600px;
		height: auto;
		margin: 0 auto;
		padding-bottom: 200px;
	}
</style>
