<template>

	<div class="common-layout">
	    <el-container>
			<!-- 头 -->
	      <el-header><CinemaerHeader /></el-header>
		  
	      <el-container>
			  <!-- 测边-->
	        <el-aside >
				<el-button size="large" class="backUser" @click="backUser"><el-icon :size="20"><ArrowLeft  /></el-icon>&nbsp;回到用户</el-button>
				<CinemaAside />
			</el-aside>
			
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
	import { ArrowLeft } from '@element-plus/icons-vue'
	import { useRouter } from 'vue-router'
	
	
	const router = useRouter();
	const backUser = () =>{
		setTimeout(()=>{
				//需要延迟的代码 :2秒后延迟跳转到首页，可以加提示什么的				 
				router.push('/HomePage');
		        //延迟时间：3秒
		    },500);
	}
</script>

<style scoped>
	.backUser{
		margin-right: 140px;
	}
</style>
