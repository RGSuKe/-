<template>
	
	<Header></Header>
	
	<div class="mainMyOrder">
		 <el-table :data="tableData.data" style="width: 100%;height:100%"  @selection-change="handleSelectionChange"> 
			 <el-table-column
				 type="selection"
				 width="40">
			 </el-table-column>
			<el-table-column prop="username" align="center" label="用户名" width="100" />
			<el-table-column prop="cinemaName" align="center" label="影院" width="200" />
			<el-table-column prop="movieName" align="center" label="影片" width="200" />
			<el-table-column prop="seatCol" align="center" label="座位(排)" width="100" />
			<el-table-column prop="seatRow" align="center" label="座位(列)" width="100" />
			<el-table-column prop="orderCreatetime" align="center" label="下单时间" width="180" />
			<el-table-column align="center" prop="orderStatus" label="订单状态" width="100" >
				<template #default="scope">
				  <el-tag v-if="scope.row.orderStatus === 0 " type="info"> 
				    已退票
				  </el-tag>
				  <el-tag v-if="scope.row.orderStatus === 1 " type="success">
					已取票
				  </el-tag> <el-tag v-if="scope.row.orderStatus === 2 " >
					进行中
				  </el-tag> 		
				</template>
			</el-table-column>
			<el-table-column  label="操作" align="center" width="180" >
				
				<template #default>
				  <span class="dialog-footer">
					  <el-popconfirm
					  	class="ml-5"
					  	confirm-button-text='确定退票'
					  	cancel-button-text='让我想想'
					  	icon="el-icon-info"
					  	icon-color="red"
					  	title="确定退票吗？"
					  	@confirm="refundOrder"
					  >
					  	<template #reference >
					  	 <el-button @click="">退票</el-button>
					  	</template>
					  </el-popconfirm>
				    
					<el-popconfirm
						class="ml-5"
						confirm-button-text='确定票完成'
						cancel-button-text='让我想想'
						icon="el-icon-info"
						icon-color="red"
						title="确定取票吗？"
						@confirm="pickOrder"
					>
						<template #reference >
						 <el-button type="primary" @click="">取票</el-button>
						</template>
					 </el-popconfirm>
				    
				  </span>
				</template>
			</el-table-column>
		 </el-table >	
	</div>
	

	<Footer></Footer>
</template>

<script setup lang="ts">
	import Header from '../../components/Header.vue'
	import Footer from '../../components/Footer.vue'
	import { reactive, ref, inject } from 'vue'
	import {  ElMessage, ElMessageBox } from 'element-plus'
	
	
	const tableData = reactive({
		data:[]
	})
	
	//接收main.js的axios对象
	const $axios = inject('$axios');

	/**
	 * 查询自己的订单 start
	 */
	$axios.get("http://localhost:89/order1/myOrder/" + localStorage.getItem("userId")).then((resp) => {
		console.log(resp.data.data);
		tableData.data = resp.data.data;
	}).catch((err) => {
		console.log(err);
	})
	
	const myOrder = () =>{
		$axios.get("http://localhost:89/order1/myOrder/" + localStorage.getItem("userId")).then((resp) => {
			console.log(resp.data.data);
			tableData.data = resp.data.data;
		}).catch((err) => {
			console.log(err);
		})
	}
	/**
	 * 查询自己的订单 end
	 */
	
	//判断选中的序号的状态是否一样，一样true，不一样false
	function isAllEqual(array) {
	     if (array.length > 0) {
	         return !array.some(function (value, index) {
	             return value !== array[0];
	         });
	     } else {
	         return true;
	     }
	 }
	//获取选中的
	let multipleSelection = reactive([]);
	//向请求发送的选中的movieId的数组
	let deleteMovieIds = reactive([]);
	const handleSelectionChange = (val) =>{
		console.log(val)
		multipleSelection=val
	}
	/**
	 * 退票/取消  strt
	 */
	const refundOrder = () =>{
		//创建一个数组保存选中的序号的状态
		const isSame = reactive([]);
		//将状态添加进数组
		for(var i = 0; i < multipleSelection.length; i++){
			isSame.push(multipleSelection[i].orderStatus); 
		};
		console.log(isAllEqual(isSame));
		console.log("取消isSame：",isSame[0]);
		if(isAllEqual(isSame)){
			if(isSame[0] == 2){			
				$axios.post('http://localhost:89/order1/canceOrder',multipleSelection).then((resp) =>{
					if(resp.data.data = true){
						ElMessage({
							type: 'success',
							message: '该票已退！！！！',
						 });
						myOrder();
					}else{
						ElMessage.error("退票失败");
					}
				}).catch((err) => {
					console.log(err);
				})
			}else if(isSame[0] == 1){
				ElMessage.error("该票已取，不可再退！");
			}else if(isSame[0] == 0){
				ElMessage.error("该票已退，无法再退！");
			}
		}else{
			ElMessageBox({
				type: 'info',
				message: '请选中状态相同的订单在一起操作！！！',
			 })
		}
	}
	/**
	 * 退票/取消  end
	 */
	
	
	/**
	 * 取票/完成  strt
	 */
	const pickOrder = () =>{
		//创建一个数组保存选中的状态
		const isSame = reactive([]);
		//将状态添加进数组
		for(var i = 0; i < multipleSelection.length; i++){
			isSame.push(multipleSelection[i].orderStatus);
		}
		console.log(isAllEqual(isSame));
		console.log("完成isSame：",isSame[0]);
		if(isAllEqual(isSame)){
			if(isSame[0] == 2){			
				$axios.post('http://localhost:89/order1/finishOrder',multipleSelection).then((resp) =>{
					if(resp.data.data = true){
						ElMessage({
							type: 'success',
							message: '所选订单已完成！！！！',
						 });
						 myOrder();
					}else{
						ElMessage.error("完成失败");
					}
				}).catch((err) => {
					console.log(err);
				});
			}else if(isSame[0] == 1){
				ElMessage.error("订单已完成，无需再取消！");
			}else if(isSame[0] == 0){
				ElMessage.error("订单已取消，无法再完成！");
			}
		}else{
			ElMessageBox({
				type: 'info',
				message: '请选中状态相同的订单在一起操作！！！',
			 })
		}
	}
	
	/**
	 * 取票/完成  end
	 */
	
</script>

<style scoped>
	.mainMyOrder{
		width: 1200px;
		/* height: 600px; */
		/* background-color: gray; */
		margin: 60px auto 100px auto;
		
	}
</style>
