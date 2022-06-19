<template>
	<Header />
	<div class="main">
		<div class="top">
			<h1>影院</h1>
				<div class="search">
					<el-input class="input_search" v-model="pagination.cinemaSearchName" placeholder="请输入" clearable size="large" />	
					<el-button class="search_icon button_search" :icon="Search" type="info" plain size="large"  @click="getAll"/>
				</div>
				<div class="list" >
					<!-- 1 指定数组索引 -->
					<!-- <div class="list_item" > 
						<img :src="urlCinema[0]"/>
						<div class="cinemaMessage" >
							<div class="cinemaName">{{cinemaMessage.data[0].cinemaName}}</div>
							<div class="cinemaAddress">{{cinemaMessage.data[0].cinemaAddress}}</div>
						<br>
						<el-button  @click="dialog = true">查看详情</el-button>
						</div>
					</div> -->
					<!-- 2 v-for指令遍历 -->
					<div class="list_item" v-for="c in cinemaMessage.data" >
						<img :src="urlCinema[0]"/>
						<div class="cinemaMessage" v-for="cinema in cinemaMessage">
							<div class="cinemaName">{{c.cinemaName}}</div>
							<div class="cinemaAddress">{{c.cinemaAddress}}
						</div><br>						
						<el-button  @click="dialog = true;checkdetail(c.cinemaId);getCinema_Movie_Schedule(c.cinemaId)" >查看详情</el-button>
						</div>
					</div>
					
					
				</div>
		</div>
	</div>
	
	 
	
	<!-- 点击查看详情弹出测边框 start -->
	<el-drawer
	  ref="drawerRef"
	  v-model="dialog"
	  title=""
	  :before-close="handleClose"
	  direction="ltr"
	  custom-class="demo-drawer"
	>
	  <div class="demo-drawer__content">
		<img :src="urlCinema[0]"/>
		<div class="cinemaMessage_left">
			<div class="cinemaName">{{cinemaDetailMessage.data.cinemaName}}</div><br>
			<div class="cinemaAddress">地址：<b>{{cinemaDetailMessage.data.cinemaAddress}}</b></div><br>
			<div>营业时间：<b>{{cinemaDetailMessage.data.cinemaWorktime}}</b></div><br>
			<div>联系电话：<b>{{cinemaDetailMessage.data.cinemaPhone}}</b></div><br>
	  </div>	
		<h1>排片计划</h1>
		<div >			
			<div v-for="cms in cinema_movie_schedule.data">
				<h2>片名：{{cms.movieName}}</h2><br>
			 	票价：{{cms.price}} 元<br>
				时间: {{cms.scheduleBegintime}}-{{cms.scheduleEndtime}}<br>
			 </div>	
						 
		</div>
	  </div>
	</el-drawer>
	<!-- 点击查看详情弹出测边框 end -->
	
	<!-- 分页start -->
	<div class="demo-pagination-block">
	    <el-pagination
	      :page-sizes="[1, 2, 5, 10]"
		  :current-page="pagination.currentPage"
		  :total="pagination.total"
		  :small="small"
	      :disabled="disabled"
	      :background="background"
	      layout="total, sizes, prev, pager, next, jumper"		  
	      @current-change="handleCurrentChange"
		  @size-change="handleSizeChange"
		  v-if="hidden"
		>
		  </el-pagination>	    
	</div> 
	<!-- 分页end -->
	<Footer />
	
</template>

<script setup lang="ts">
		import Header from '../components/Header.vue'
		import Footer from '../components/Footer.vue'
		import {House, List, VideoCamera, Aim, Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
		import { ref , reactive, watch, inject, getCurrentInstance} from 'vue'
		import { ElMessageBox } from 'element-plus'
		import type { ElDrawer } from 'element-plus'
		import { onMounted  } from 'vue';
		
		
		
		// 点击“查看详情”弹出左边的框
		const dialog = ref(false)
		// const formLabelWidth = '80px'
		// let timer	
		// const table = ref(false)
		// const loading = ref(false)
		// const drawerRef = ref<InstanceType<typeof ElDrawer>>()
		// const onClick = () => {
		//   drawerRef.value!.close()
		// }
		
		// const handleClose = (done) => {
		//   if (loading.value) {
		//     return
		//   }
		//   ElMessageBox.confirm('是否退出?')
		//     .then(() => {
		//       loading.value = true
		//       timer = setTimeout(() => {
		//         done()
		//         // 动画关闭需要一定的时间
		//         setTimeout(() => {
		//           loading.value = false
		//         }, 400)
		//       }, 1)
		//     })
		//     .catch(() => {
		//       // catch error
		//     })
		// }
		
		// const cancelForm = () => {
		//   loading.value = false
		//   dialog.value = false
		//   clearTimeout(timer)
		// }
		// 点击“查看详情”弹出左边的框 end
		
		
	/**
	 * 获取影院信息 start
	 */
		// const cinemaMessage = reactive({
		// 	cinemaName:["踏雪寻梅影院",],
		// 	address:["广西科技师范学院6栋218号",],
		// 	worktime:["9:00-23:00",],
		// 	telephone:["021-60271866",],		
		// });
		//保存影院的信息
		const cinemaMessage = reactive({
			data: []
		});

		const urlCinema = [
			"http://localhost:3000/src/img/cinema/c1.png",
			"http://localhost:3000/src/img/cinema/c2.png",
		]
		
		//接收main.js的axios对象
		const $axios = inject('$axios');
		 
		$axios.get('http://localhost:89/cinema').then((resp) => {
			// console.log(resp.data);
			//将请求返回的值赋予自定义的变量
			cinemaMessage.data = resp.data.data	;
				
			// console.log(cinemaMessage.data );
			}).catch((err) => {
				console.log(err);
			});

		
	/**
	 * 获取影院信息 end
	 */
		
	/**
	 * 查看详情 start
	 */
		const cinemaDetailMessage = reactive({
			data: []
		});
		const checkdetail = (cinemaId) =>{
			$axios.get('http://localhost:89/cinema/'+cinemaId).then((resp) => {
				cinemaDetailMessage.data = resp.data.data;
				console.log(cinemaDetailMessage.data);
			}).catch((err) => {
				console.log(err);
			});
		}
	
	/**
	 * 查看详情 end
	 */	
	
	
	/**
	 * 获取排片计划 start
	 */
	const cinema_movie_schedule = reactive({
		data: []
	})
	
	const getCinema_Movie_Schedule = (cinemaId) => {
		$axios.get('http://localhost:89/cinema/cinema_movie_schedule/'+cinemaId).then((resp) => {
			cinema_movie_schedule.data = resp.data.data;
			console.log(cinema_movie_schedule.data);
		}).catch((err) =>{
			console.log(err);
		});
	}
	/**
	 * 获取排片计划 end
	 */
	
	
	/**
	 * 分页 start
	 */
	const hidden = ref(false);
	const small = ref(false);
	const background = ref(false);
	const disabled = ref(false);
	const pagination = reactive({
		currentPage: 1,//当前页码
		pageSize: 5,//每页显示的记录数
		total: 0,//总记录数
		//需要根据查询的条件
		cinemaSearchName:''
		
	})
	
	// const handleCurrentChange = (val: number) => {
	//   console.log(`current page: ${val}`)
	// }
	const getAll = () => {
		$axios.get('http://localhost:89/cinema/'+pagination.currentPage+"/"+pagination.pageSize+"?cinemaName="+pagination.cinemaSearchName).then((resp) => {
					 console.log(resp.data.data)
					 cinemaMessage.data = resp.data.data.records;
					 pagination.pageSize = resp.data.data.size;
					 pagination.total = resp.data.data.total;
					 pagination.currentPage = resp.data.data.current;
					 hidden.value = true;
					 console.log("每页几条",resp.data.data.size+"..总条数"+resp.data.data.total+"..当前页"+resp.data.data.current)
		});
	}
	
	//更新当前页
	const handleCurrentChange = (currentPage) =>{
		console.log("当前页",currentPage);
		pagination.currentPage = currentPage;
		getAll();
	}

	//更新每页大小
	const handleSizeChange = (size) =>{
		console.log("每页多少条",size);
		pagination.pageSize = size;
		getAll();
	}

	/**
	 * 分页 end
	 */
</script>

<style scoped>
	.demo-pagination-block + .demo-pagination-block {
	  margin-top: 10px;
	}
	.demo-pagination-block{
		margin-left: 540px;
		margin-top: -30px;
	}
	.demo-pagination-block .demonstration {
	  margin-bottom: 16px;
	  
	}
	.el-button_detail{
		
	}
	.main{
		margin: 0 auto;
		width: 800px;
		height: auto;
	}
	.top{
		margin: 0 auto;
	}
	.input_search{
		width: 400px;
		
	}
	.button_search{
		margin-top: -2px;
	}
	.list{
		width: 800px;
		height: auto;
		margin-top: 50px;
	}
	.list_item{
		width: 800px;
		height: 200px;
		
		margin-top: 20px;
	}
	.list_item img{
		float: left;
	}
	.cinemaMessage{
		margin-left: 50px;
		float: left;
	}
	.cinemaName{
		font-size: 25px;
		font-weight: 500;
	}
	.cinemaAddress{
		margin-top: 10px;
		font-size: 15px;
		font-weight: 400;
	}
	.cinemaMessage_left{
		text-align: center;
	}
</style>
