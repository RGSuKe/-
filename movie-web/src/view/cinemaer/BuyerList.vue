<template>
	<div>
		<div class="button">
			<div class="button_first">
				<el-button type="primary" :icon="List" @click="getOrder1">全部订单</el-button>
			</div>
			<br><br><br>
			<div class="button_second">
				<el-input v-model="pagination.userSearchName" ></el-input>&nbsp;&nbsp;
				<el-button type="primary" :icon="Search" @click="getOrder1">搜索订单(用户名)</el-button>
			</div>
			
		</div>
			
		<br><br><br>
		<el-table 
			:data="tableData.data" 
			style="width: 100%;font-size: 16px;font-weight: 500;" 
			header-cell-class-name="headerBg" 
			 @selection-change="handleSelectionChange"
			border>	
		<el-table-column
		    type="selection"
		    width="40">
		</el-table-column>
			<el-table-column align="center" prop="orderId" label="订单id" width="84" />
			<el-table-column align="center" prop="username" label="用户名" width="100" />
			<!-- <el-table-column align="center" prop="scheduleId" label="计划id" width="80" /> -->
			<el-table-column align="center" prop="cinemaName" label="影院名" width="170" />
			<el-table-column align="center" prop="movieName" label="片名" width="170" />
			<el-table-column align="center" prop="scheduleBegintime" label="开始时间" width="190" />
			<el-table-column align="center" prop="scheduleEndtime" label="结束时间" width="190" />
			<el-table-column align="center" prop="seatRow" label="座位-排" width="80" />
			<el-table-column align="center" prop="seatCol" label="座位-列" width="80" />
			<el-table-column align="center" prop="orderCreatetime" label="下单时间" width="190" />
			<el-table-column align="center" prop="orderStatus" label="订单状态" width="110" >
				<template #default="scope">
				  <el-tag v-if="scope.row.orderStatus === 0 " type="info"> 
				    已取消
				  </el-tag>
				  <el-tag v-if="scope.row.orderStatus === 1 " type="success">
					已完成
				  </el-tag> <el-tag v-if="scope.row.orderStatus === 2 " >
					进行中
				  </el-tag> 		
				</template>
			</el-table-column>
			<el-table-column align="center" label="操作" width="140" >
				<el-popconfirm
					class="ml-5"
					confirm-button-text='确定取消'
					cancel-button-text='让我想想'
					icon="el-icon-info"
					icon-color="red"
					title="确定取消吗？"
					@confirm="cancelOrder"
				>
					<template #reference >
					 <el-button type="warning">取消</el-button>
					</template>
				</el-popconfirm>
				  
				<br><br>
				
				<el-popconfirm
					class="ml-5"
					confirm-button-text='确定删除'
					cancel-button-text='让我想想'
					icon="el-icon-info"
					icon-color="red"
					title="确定删除吗？"
					@confirm="deleteOrder"
				>
					<template #reference >
					 <el-button type="danger">删除</el-button>
					</template>
				 </el-popconfirm>
				  
				<br><br>
				
				<el-popconfirm
					class="ml-5"
					confirm-button-text='确定完成'
					cancel-button-text='让我想想'
					icon="el-icon-info"
					icon-color="red"
					title="确定完成吗？"
					@confirm="finishOrder"
				>
					<template #reference >
					 <el-button type="success">完成</el-button>
					</template>
				 </el-popconfirm>	
			</el-table-column>
			
		</el-table>

	</div>
	
	
	
	
	<!-- 分页start -->
	<br><br>
	<div class="demo-pagination-block button—bock">
	    <el-pagination
		  :default-page-size="pagination.pageSize"
	      :page-sizes="[1, 3, 5, 7]"
		  :currentPage="pagination.currentPage"
		  :total="pagination.total"
		  :small="small"
		  :disabled="disabled"
		  :background="background"
	      layout="total, sizes, prev, pager, next, jumper"	
	      @current-change="handleCurrentChange"
	      @size-change="handleSizeChange"
		>
		  </el-pagination>	    
	</div> 
	<!-- 分页end -->
	
</template>

<script lang="ts" setup>
	import { reactive, ref, inject } from 'vue'
	import { Search, List, Promotion } from '@element-plus/icons-vue'
	import {  ElMessage, ElMessageBox } from 'element-plus'
	
	
	const tableData = reactive({
		
	});
		
	//接收main.js的axios对象
	const $axios = inject('$axios');
	
	const small = ref(false)
	const background = ref(false)
	const disabled = ref(false)
	const pagination = reactive({
		currentPage: 1,//当前页码
		pageSize: 5,//每页显示的记录数
		total: 0,//总记录数
		//需要根据查询的条件
		userSearchName:''	
	})
	/**
	 * 点开网页，先执行一边查询全部的方法  生命周期是vue2的beforeCreate和created   start
	 */
	$axios.get('http://localhost:89/order1/'+pagination.currentPage+"/"+pagination.pageSize+"?username="+pagination.userSearchName).then((resp) => {
				console.log(resp.data.data)
				tableData.data = resp.data.data.records;
				pagination.pageSize = resp.data.data.size;
				pagination.total = resp.data.data.total;
				pagination.currentPage = resp.data.data.current;
				 console.log("每页几条",resp.data.data.size+"..总条数"+resp.data.data.total+"..当前页"+resp.data.data.current)
	});
	/**
	 * 点开网页，先执行一边查询全部的方法  生命周期是vue2的beforeCreate和created  end
	 */
	
	/**
	 * 分页查询 start
	 */
	const getOrder1 = () => {	
		$axios.get('http://localhost:89/order1/'+pagination.currentPage+"/"+pagination.pageSize+"?username="+pagination.userSearchName).then((resp) => {
					console.log(resp.data.data)
					tableData.data = resp.data.data.records;
					pagination.pageSize = resp.data.data.size;
					pagination.total = resp.data.data.total;
					pagination.currentPage = resp.data.data.current;
					 console.log("每页几条",resp.data.data.size+"..总条数"+resp.data.data.total+"..当前页"+resp.data.data.current)
		});
		//清空搜索栏
		pagination.userSearchName = '';
	}
	//更新当前页
	const handleCurrentChange = (currentPage) =>{
		console.log("当前页",currentPage);
		pagination.currentPage = currentPage;
		getOrder1();
	}
	
	//更新每页大小
	const handleSizeChange = (size) =>{
		console.log("每页多少条",size);
		pagination.pageSize = size;
		getOrder1();
	}
	/**
	 * 分页查询 end
	 */
	
	//获取选中的
	let multipleSelection = reactive([]);
	
	//选中的
	const handleSelectionChange = (val) =>{
		console.log(val)
		multipleSelection=val
		console.log("multipleSelection",multipleSelection);	
	}
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
	 
	 
	/**
	 * 取消订单  start
	 */
	//循环选中的序号中的订单，有不同状态的为true
	// const getOrderStatus = () =>{
	// 	//创建一个数组保存选中的序号的状态
	// 	const isSame = reactive([]);
	// 	//将状态添加进数组
	// 	for(var i = 0; i < multipleSelection.length; i++){
	// 		isSame.push(multipleSelection[i].orderStatus);
	// 	};
	// 	console.log( isAllEqual(isSame));
	
	// }

	const cancelOrder = () =>{
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
							message: '所选订单已取消！！！！',
						 });
						 getOrder1();
					}else{
						ElMessage.error("取消失败");
					}
				}).catch((err) => {
					console.log(err);
				})
			}else if(isSame[0] == 1){
				ElMessage.error("订单已完成，无法再取消！");
			}else if(isSame[0] == 0){
				ElMessage.error("订单已取消，无需再取消！");
			}
		}else{
			ElMessageBox({
				type: 'info',
				message: '请选中状态相同的订单在一起操作！！！',
			 })
		}
	}
	
	/**
	 * 取消订单   end
	 */
	
	
	/**
	 * 完成订单  start
	 */
	const finishOrder = () =>{
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
						 getOrder1();
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
	 * 完成订单 end
	 */
	
	
	/**
	 * 删除订单  start
	 */
	//向删除请求发送选中的
	const orderIds = reactive([]);
	const deleteOrder = () =>{
		//创建一个数组保存选中的状态
		const isSame = reactive([]);
		//将状态添加进数组
		for(var i = 0; i < multipleSelection.length; i++){
			isSame.push(multipleSelection[i].orderStatus);
			//将要删除的orderId存入
			orderIds.push(multipleSelection[i].orderId);
		}
		console.log("orderIds",orderIds);
		console.log("删除orderId：",isSame[0]);
		if(isAllEqual(isSame)){
			if(isSame[0] == 1 || isSame[0] == 0){			
				$axios.post('http://localhost:89/order1/deleteOrder',orderIds).then((resp) =>{
					if(resp.data.data = true){
						ElMessage({
							type: 'success',
							message: '所选订单已删除！！！！',
						 });
						 getOrder1();
					}else{
						ElMessage.error("删除失败");
					}
				}).catch((err) => {
					console.log(err);
				});
			}else if(isSame[0] == 2){
				ElMessage.error("订单正在进行，无法删除！");
			}
		}else{
			ElMessageBox({
				type: 'info',
				message: '请选中状态相同的订单在一起操作！！！',
			 })
		}
	}
	/**
	 * 删除订单 end
	 */
</script>

<style scope>
	.headerBg{
	  background-color: #cccccc!important;
	}
	.button{
		margin-bottom: 20px;
		float: left;
	}
	.button_first{
		float: left;
	}
	.button_second{
		float: left;
		display: flex;
		
	}
</style>
