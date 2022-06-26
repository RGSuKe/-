<template>
	<CinemaerHeader />
	
	<div class="changelogincart">
		<el-tabs type="border-card" class="demo-tabs">
		  <!-- 用户名登录start-->
		  <el-tab-pane label="影院方登录">
				<!-- <h2>登录</h2>	 -->		 
				<el-form
					:label-position="labelPosition"
					label-width="100px"
					:model="formLabelAlign"
					style="max-width: 350px;margin-top: 20px;"
				>
				<div>
					  <el-form-item label="用户名">
						<el-input :suffix-icon="UserFilled" v-model="formLabelAlign.cinemaUsername" />
					  </el-form-item>
					  <el-form-item label="密码">
						<el-input :suffix-icon="Key" v-model="formLabelAlign.cinemaPassword" />
					  </el-form-item>
				</div>
			
				<div class="submit">
					 <el-button  size="large" style="margin-left: 100px ;20px;margin-top: 19px;" @click="cinemaLogin()">登录</el-button>
				</div>
				</el-form>
		  </el-tab-pane>
			<!--用户名登录end-->
			
		</el-tabs>
	</div>
		
	
	<Footer />
	  
</template>

<script lang="ts" setup>
	import ScheduleList from '../view/cinemaer/ScheduleList.vue'
	import Footer from '../../components/Footer.vue'
	import CinemaerHeader from '../../components/CinemaerHeader.vue'
	import { reactive, ref, inject } from 'vue'
	import {  ElMessage, ElMessageBox } from 'element-plus'
	import {UserFilled,Key,  Calendar, Message, Cellphone,} from '@element-plus/icons-vue'
	import { useRouter } from 'vue-router'
	
	
	
	const labelPosition = ref('right')
	
	const formLabelAlign = reactive({
	  cinemaUsername: '',
	  cinemaPassword: '',
	})
	
	const radio1 = ref('1');
	
	
	
	//接收main.js的axios对象
	const $axios = inject('$axios');
	
	//
	const router = useRouter();
	/**
	 * 登录 start
	 */
	const cinemaLogin = () => {
		if(formLabelAlign.cinemaUsername != '' && formLabelAlign.cinemaPassword != ''){
			$axios.post("http://localhost:89/cinema/cinemaLogin", formLabelAlign).then((resp) => {
				console.log(resp.data.data);
				if(resp.data.flag == true){
					localStorage.setItem("cinemaId", resp.data.data.cinemaId);
					localStorage.setItem("cinemaUsername", resp.data.data.cinemaUsername);
					localStorage.setItem("cinemaName", resp.data.data.cinemaName);
					
					 ElMessage({
						message: '验证通过，2秒后为您跳转到主页',
						type: 'success',
					 });	
					 setTimeout(()=>{
					 		//需要延迟的代码 :2秒后延迟跳转到首页，可以加提示什么的				 
					 		router.push('/CinemaManage');
					         //延迟时间：3秒
					     },2000);
				}else{
						ElMessage({
							message: '验证不通过，请检查您的用户名或密码是否错误',
							type: 'error',
						});	
					}
			})
		.catch((err) => {
					console.log(err);
				});	
		}else{
			alert("输入的账号密码不能为空！");
		}
	}
	
	
	/**
	 * 登录 end
	 */
</script>

<style scoped>
	/* elementUIPlus */
	.demo-tabs > .el-tabs__content {
	  padding: 32px;
	  color: #6b778c;
	  font-size: 32px;
	  font-weight: 600;
	}
	.demo-tabs .custom-tabs-label .el-icon {
	  vertical-align: middle;
	}
	.demo-tabs .custom-tabs-label span {
	  vertical-align: middle;
	  margin-left: 4px;
	}
	/* 自定义 */
	.form{
		width: 400px;
		margin: 13% auto;
		border: 1px solid #ccc;
		border-radius: 15px;
	}
	.changelogincart{
		width: 500px;
		margin: 0 auto;
		margin-top: 149px;
		margin-bottom: 100px;
	}
	.el-tabs{
		font-size: 20px;
	}
</style>


