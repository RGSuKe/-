<template>
	 
	 <div class="footer">
		 <el-divider />
	 	<ul>
			<!-- <li><a @click="AboutMe">关于我</a></li> -->
	 		<!-- <li><a href="#">加入我们</a></li>
			<li><a href="#">帮助中心</a></li>
			<li><a href="#">合作伙伴</a></li> -->
			<li><router-link  to='AboutMe'>关于我们</router-link></li>
			<li><router-link  to='TechnologyUsed'>使用技术</router-link></li>
			<li><router-link  to='HelpCenter'>帮助中心</router-link></li>
			<li><router-link  to='Partners'>合作伙伴</router-link></li>
	 	</ul>
		
	 </div>
	
	 
</template>


<style>
	a{
		color: #000000;
	}
	.footer{
		width: 700px;
		margin: 0 auto;
		padding-bottom: 30px;
		
	}
	ul li{
		float: left;
		margin-left: 10%;
	}
	
</style>




<script lang="ts" setup>
	
	// import { useRouter } from 'vue-router'
	
	// const router = useRouter()
	// const AboutMe = (() => {
	//     router.push('/AboutMe')
	// 	}
	// )
		
</script>