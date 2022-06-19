<template>
	<div class="common-layout">
	    <el-container>
			<!-- 头 -->
	      <el-header><CinemaerHeader /></el-header>
		  
	      <el-container>
			  <!-- 测边-->
	        <el-aside ><CinemaAside /></el-aside>
			
	        <el-container>
				<!-- 计划数据 -->
	          <el-main>
				 <router-view></router-view>
			  </el-main>
			  
			  <!-- 脚-->
	          <el-footer><Footer /></el-footer>
			  
	        </el-container>
	      </el-container>
	    </el-container>
	  </div>
	  
</template>

<script lang="ts" setup>
	import CinemaAside from './CinemaAside.vue'
	import CinemaerHeader from '../../components/CinemaerHeader.vue'
	import Footer from '../../components/Footer.vue'
	import ScheduleList from './ScheduleList.vue'
</script>

<style>
</style>
