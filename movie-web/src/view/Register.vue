<template>
	<Header/>
	
	<div class="form">
		<h1 >注册</h1>
		  <el-form
			label-width="100px"
			:model="formLabelAlign"
			style="max-width: 350px;margin-top: 20px;"
			:rules="rules"
		  >
			<el-form-item label="用户名" prop="username">
			  <el-input :suffix-icon="UserFilled" v-model.trim="formLabelAlign.username" clearable />
			</el-form-item>
			<el-form-item label="邮箱" prop="email">
			  <el-input :suffix-icon="Message" v-model.trim="formLabelAlign.email" clearable/>
			</el-form-item>
			<el-form-item label="联系方式" prop="phone">
			  <el-input :suffix-icon="Cellphone" v-model.trim="formLabelAlign.phone" clearable/>
			</el-form-item>
			<el-form-item  label="密码" prop="password">
			  <el-input :suffix-icon="Key" v-model.trim="formLabelAlign.password" clearable/>
			</el-form-item>
			 <!-- <el-form-item label="确认密码" prop="confirm_password">
			   <el-input :suffix-icon="Key" v-model="formLabelAlign.confirm_password" />
			 </el-form-item> -->
			<el-button   size="large" style="margin-bottom: 20px;margin-top: 20px;" @click="register">注册</el-button>			
		  </el-form>
		  
	  </div>
	  <Footer />
</template>

<script lang="ts" setup>
	import Header from '../components/Header.vue'
	import Footer from '../components/Footer.vue'
	import { reactive, ref , inject} from 'vue'
	import type { FormInstance} from 'element-plus'
	import {  ElMessage, ElMessageBox } from 'element-plus'
	import {UserFilled,Key,Message,Cellphone,} from '@element-plus/icons-vue'
	import { useRouter } from 'vue-router'
	
	
	
	const ruleFormRef = ref<FormInstance>()
	
	const rules = reactive({
		name:[
			{required: true, message: '用户名不能为空',trigger: 'blur'},
			{min: 5, max: 15, message: '用户名长度应为5-15位', trigger: 'blur'}
		],
		email:[
			{required: true, message: '邮箱不能为空',trigger: 'blur'},
			{type: 'email', message: '请输入正确的邮箱格式',trigger: 'blur'}
		],
		phone:[
			{required: true, message: '手机号码不能为空',trigger: 'blur'},
			// {type: 'number', message:'请输入正确的手机号码格式'},
			{pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,message: '请输入正确的手机号码'},
			{min: 11, max: 11, message:'手机号码应为11为', trigger: 'blur'}
		],
		password:[
			{required: true, message: '密码不能为空',trigger: 'blur'},
			{min: 5, max: 15, message: '密码长度应为5-15', trigger: 'blur'}
		],
		// confirm_password:[
		// 	{required: true, message: '确认密码不能为空',trigger: 'blur'},
		// 	{min: 5, max: 15, message: '密码长度应为5-15', trigger: 'blur'}
		// ],
		
	})
	
	//接收请求的对象
	const $axios = inject('$axios');
	const router = useRouter();
	/**
	 * 注册账号提交start
	 */
	const formLabelAlign = reactive({
	  username: '',
	  email: '',
	  phone: '',
	  password: '',
	  // confirm_password: '',
	})
	const register = () => {
		if(formLabelAlign.username != "" && formLabelAlign.password != ""){
			$axios.post('http://localhost:89/user/addone',formLabelAlign).then((resp) => {
				if(resp.data.flag == false){
					  // ElMessage.error(resp.data.msg);
					  ElMessageBox.alert(resp.data.msg);
				}else{
					ElMessage({
						message: '注册成功，3秒后将为您转去登录窗口',
						type: 'success',
					});
					 
					setTimeout(()=>{
							//需要延迟的代码 :3秒后延迟跳转到首页，可以加提示什么的					 
							router.push('Login');
					        //延迟时间：3秒
					    },3000);
				}
				
			});
		}else{
			 // ElMessage.error("用户名或密码不允许为空，请重新输入！");
			 ElMessageBox.alert('用户名或密码不允许为空，请重新输入！');
		}
	}
	

	/**
	 * 注册账号提交end
	 */
</script>


<style scoped>
	.form{
		width: 400px;
		margin: 11% auto;
		border: 1px solid #ccc;
		border-radius: 15px;
	}
	.el-button{
		display:block;
		margin-left:46%;
	}
</style>