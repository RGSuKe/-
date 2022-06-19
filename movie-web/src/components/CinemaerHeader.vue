<template>
	<div class="header">
		<span v-if="cinemaName == null">影院管理员登录</span>
		 <span>{{cinemaName}}</span>
		 
		 <el-button class="loginout" size="large" v-if="cinemaName != null" @click="loginout">退出</el-button>
	</div>
	
</template>

<script lang="ts" setup>
	import { ref,  } from 'vue'
	import { useRouter } from 'vue-router'
	import {  ElMessage, ElMessageBox } from 'element-plus'
	
	const cinemaName = localStorage.getItem("cinemaName");
	
	const router = useRouter();
	
	
	const loginout = () =>{
		localStorage.removeItem("cinemaName");
		localStorage.removeItem("cinemaId");
		localStorage.removeItem("cinemaUsername");
		ElMessage({
			message: '退出成功，将在2秒后为您跳转登录页面',
			type: 'success',
		});	
		setTimeout(()=>{
				//需要延迟的代码 :2秒后延迟跳转到首页，可以加提示什么的					 
				router.push('CinemaLogin');
		        //延迟时间：2秒
		    },2000);		 
		 	 	 
	}
</script>

<style scoped>
	.header{
		margin-top: 5px;
		margin-bottom: 50px;
		font-size: 30px;
	}
	.loginout{
		float: right;
	}
</style>
