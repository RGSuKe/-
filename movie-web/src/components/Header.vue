<template>
	 <div class="header">
	        <div class="header_inside">
	            <!-- <div class="logo">               
				  <router-link to='HomePage'><a href="#"><img src="../img/logo名字.png"></a> </router-link>
	            </div> --> 
	            <div class="context">
	                <ul>
	                    <li><router-link  to='HomePage'>首页</router-link></li>
	                    <li><router-link  to='Film'>电影</router-link></li>
	                    <li><router-link  to='Rank'>票房</router-link></li>
						<li><router-link  to='Cinema'>影院</router-link></li>
						<!-- <li><router-link  to='FilmPhoto'>111影院</router-link></li> -->
						<li><router-link  to='CinemaManage'>管理员/影院方</router-link></li>
	                </ul>
					
	            </div>
	            <div>
	                <form action="">
						<div class="search">
							 <el-input v-model="input" placeholder="请输入" clearable />
							
						</div>
						<el-button class="search_icon" :icon="Search" type="info" plain />
	                </form>
	            </div>
				<div>
					<ul class="login" >
						<!-- <a href="https://www.baidu.com/">登录</a> -->
						<router-link to="/Login"   v-show="loginstatus === true"><el-button class="register"  size="large" round>请登录</el-button></router-link>
						<div v-if="loginstatus === false">欢迎您，<strong>{{username}}</strong></div>
						
					</ul>
				</div>
				
				<div>
					<ul >
						
						<el-button v-if="loginstatus === false"  @click="loginout()"  class="register"  size="large" round >退出</el-button>&nbsp;&nbsp;
						
						<router-link to="/Register"  ><el-button class="register"  size="large" round>注册新用户</el-button></router-link>
					</ul>
				</div>
	        </div>
			
	    </div>
		
</template>

<script lang="ts" setup>
	import { Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
	import { ref , reactive, watch, onMounted } from 'vue'
	import { useRouter } from 'vue-router'
	import {  ElMessage, ElMessageBox } from 'element-plus'
	
	const input = ref('')
	const loginstatus = ref(true);
	const username = ref(localStorage.getItem("username") == null ? '' : localStorage.getItem("username") );
	const token = ref(localStorage.getItem("token") == null ? '' : localStorage.getItem("token") );

	const router = useRouter();
	onMounted(() => {
	    if(username.value != "" && token.value != ''){
			loginstatus.value = false;	
		}
	})
	
	

	const loginout = () =>{
		localStorage.removeItem("userId");
		localStorage.removeItem("username");
		localStorage.removeItem("password");
		localStorage.removeItem("token");
		loginstatus.value = true;
		ElMessage({
			message: '退出成功，将在2秒后为您跳转登录页面',
			type: 'success',
		});	
		setTimeout(()=>{
				//需要延迟的代码 :2秒后延迟跳转到首页，可以加提示什么的					 
				router.push('Login');
		        //延迟时间：2秒
		    },2000);		 
		 // localStorage.removeItem("username");
		 // localStorage.removeItem("password");
		 // localStorage.removeItem("token");	 	 
	}
	
	

</script>

<style scoped>
	
	li{
		list-style: none
	}
	a{
		text-decoration: none;
		cursor: pointer;
	}	
	a:hover{
		color: #409EFF;
	}
	.header{
	    height: 50px;	    
	    margin-top: -8px;
	    border-bottom: 1px solid #ccc;
	}
	.header_inside{
	    width: 1400px;
	    margin: 0 auto;
	}
	.header_inside div{
	    float: left;
	}
	.logo img{ 
	    height: 50px;
	}
	.context{
	    float: left;
	    margin-left: 80px; 
	}
	.context ul li {
	    float: left;
	    margin-left: 30px;
	}
	.context ul li a{
	    font-size: 18px;
	    color: black;		
	}
	.search{
		margin-left: 50px;
		margin-top: 11px;
	}
	.search_icon{
		height: 32px;
		margin-top: 11px;
	}	
	.login{
		margin-left: 20px;
	}
	.register{
		margin-top: -9px;
	}
	.register a{
		text-decoration: none;
	}
	
</style> 

