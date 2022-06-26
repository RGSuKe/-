<template>
	<Header/>
	
	<div class="changelogincart">
		<el-tabs type="border-card" class="demo-tabs">
		  <!-- 用户名登录start-->
		  <el-tab-pane label="用户名登录">
				<!-- <h2>登录</h2>	 -->		 
				<el-form
					:label-position="labelPosition"
					label-width="100px"
					:model="formLabelAlign"
					style="max-width: 350px;margin-top: 20px;"
				>
				<div>
					  <el-form-item label="用户名">
						<el-input :suffix-icon="UserFilled" v-model="formLabelAlign.username" />
					  </el-form-item>
					  <el-form-item label="密码">
						<el-input :suffix-icon="Key" v-model="formLabelAlign.password" />
					  </el-form-item>
				</div>
			
				<div class="submit">
					 <el-button  size="large" style="margin-left: 100px ;20px;margin-top: 19px;" @click="login()">登录</el-button>
				</div>
				</el-form>
		  </el-tab-pane>
			<!--用户名登录end-->
			
			<!--邮箱登录start-->
		  <!-- <el-tab-pane label="邮箱登录">
					<el-form
						:label-position="labelPosition"
						label-width="100px"
						:model="formLabelAlign"
						style="max-width: 350px;margin-top: 20px;"
					>
					<div>
						  <el-form-item label="邮箱">					 
							  <el-input :suffix-icon="Message" v-model="formLabelAlign.email" />
							  <span>
								  <el-button >获取邮箱码</el-button>
								   邮箱码有限时间：60秒
							  </span>
							  
						  </el-form-item>
						  
						  <el-form-item label="邮箱码">
							<el-input :suffix-icon="Key" v-model="formLabelAlign.emailword" />
						  </el-form-item>
						  
					</div>
								
					<div class="submit">
						 <el-radio v-model="radio1" label="1" size="small" >记住我</el-radio>
						 <el-button  size="large" style="margin-bottom: 20px;margin-top: 19px;">登录</el-button>
					</div>
					</el-form>
			</el-tab-pane> -->
			<!--邮箱登录end-->
			
			<!--手机登录end-->
		  <!-- <el-tab-pane label="手机登录">
				<el-form
					:label-position="labelPosition"
					label-width="100px"
					:model="formLabelAlign"
					style="max-width: 350px;margin-top: 20px;"
				>
				<div>
					  <el-form-item label="手机">
						<el-input :suffix-icon="Cellphone" v-model="formLabelAlign.phone" />
						<span>
							<el-button >获取验证码</el-button>
							验证码有效时间：60秒
						</span>
						
					  </el-form-item>
					  <el-form-item label="验证码">
						<el-input :suffix-icon="Key" v-model="formLabelAlign.verification" />
					  </el-form-item>
				</div>
							
				<div class="submit">
					 <el-radio v-model="radio1" label="1" size="small" >记住我</el-radio>
					 <el-button  size="large" style="margin-bottom: 20px;margin-top: 19px;">登录</el-button>
				</div>
				</el-form>	
			</el-tab-pane> -->
			<!--手机登录end-->
		</el-tabs>
	</div>
		
	
	
	   <Footer />
</template>

<script lang="ts" setup>
	import Header from '../../components/Header.vue'
	import Footer from '../../components/Footer.vue'
	import { reactive, ref, inject } from 'vue'
	import {  ElMessage, ElMessageBox } from 'element-plus'
	import {UserFilled,Key,  Calendar, Message, Cellphone,} from '@element-plus/icons-vue'
	import { useRouter } from 'vue-router'
	
	
	
	const labelPosition = ref('right')
	
	const formLabelAlign = reactive({
	  username: '',
	  password: '',
	})
	
	const radio1 = ref('1');
	
	
	
	//接收main.js的axios对象
	const $axios = inject('$axios');
	
	//
	const router = useRouter();
	/**
	 * 登录 start
	 */
	const login = () => {
		
		if(formLabelAlign.username != '' && formLabelAlign.password != ''){
				$axios.post("http://localhost:89/user/login", formLabelAlign).then((resp) => {
					console.log(resp.data.data);
					if(resp.data.flag == true){
						 localStorage.setItem("userId", resp.data.data.userId);
						 localStorage.setItem("username", resp.data.data.username);
						 localStorage.setItem("password", resp.data.data.password);
						 localStorage.setItem("token", resp.data.data.token);					
						 ElMessage({
							message: '验证通过，2秒后为您跳转到主页',
							type: 'success',
						 });	
						 setTimeout(()=>{
						 		//需要延迟的代码 :2秒后延迟跳转到首页，可以加提示什么的				 
						 		router.push('/HomePage');
						         //延迟时间：3秒
						     },2000);
						
					}else{
						alert("asddsadasd");
						console.log("dsadasdasdasdas");
					}
				}).catch((err) => {
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


