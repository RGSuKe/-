<template>
  
 <el-menu
	 router
    :default-active="$route.path"
    class="el-menu-vertical-demo"
	background-color=""
	active-text-color="black"
   
  >
  
	  <el-menu-item index="ScheduleList"> 
		 <el-icon><Notebook /></el-icon>
		 <span >计划管理</span>
	  </el-menu-item>
	  
	  <el-menu-item index="MovieList">
		 <el-icon><Film /></el-icon>
		 <span >电影管理</span>
	  </el-menu-item>
	  
		<el-menu-item index="BuyerList">
		 <el-icon><UserFilled /></el-icon>
		 <span >已购人数</span>
		</el-menu-item>
  </el-menu>

</template>

<script lang="ts" setup>
	import { Film, Notebook, UserFilled} from '@element-plus/icons-vue'
	
	

</script>

<style>
	.el-menu-vertical-demo:not(.el-menu--collapse) {
	  width: 200px;
	  min-height: 400px;
	}
</style>
