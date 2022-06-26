<template>
	<Header></Header>
	
		<div class="tablecenter">
			  <el-table
			    :data="tableData.data"
			    style="height: 450px;font-size: 20px;"			
			  >
			    <el-table-column type="index" label="排名" align="center"  width="200" />
			    <el-table-column prop="movieName" label="影片名称" align="center"  width="300" />
			    <el-table-column prop="orderPrice" label="票房(RNM)" align="center" sortable width="200" />
			    
			  </el-table>
		</div>
	
	
	<Footer></Footer>
</template>

<script setup>
	import Header from "../../components/Header.vue"
	import Footer from "../../components/Footer.vue"
	import {ref, reactive, inject,  } from 'vue'
	
	const tableData = reactive([]);
	
	//接收main.js的axios对象
	const $axios = inject('$axios');
	/**
	 * 获取统计价格，票房
	 */
	
	
	$axios.get('http://localhost:89/order1/getPrice').then((resp) => {
		console.log("11",resp.data.data.length);
		//将全部订单存入
		tableData.data = resp.data.data;
		//去掉同样名字的电影，票房加起来
		for(var i = 0; i < tableData.data.length; i++){
			for(var j = i + 1; j < tableData.data.length; ){
				if(tableData.data[i].movieName == tableData.data[j].movieName){	
					//重复名字的票房增加	 包括全部状态的订单的金额
					tableData.data[i].orderPrice = tableData.data[i].orderPrice + tableData.data[j].orderPrice;					
					//去掉重名的电影
					tableData.data.splice(j, 1);//1 删除j的元素，返回删除后的数组;0则删除0个；	
				}else{
					j++;
				}
			}
		}
		console.log(tableData.data)
		//判断订单的状态,为0的去掉
		// for(var i = 0; i < tableData.data.length; i++){		
		// 	if(tableData.data[i].orderStatus == 0){
		// 		tableData.data[i].orderPrice = tableData.data[i].orderPrice - 
		// 		tableData.data.splice(i, 1);
		// 	}
		// }
		console.log(tableData.data)
	
	}).catch((err)=>{
		console.log(err);
	});
	

</script>

<style scoped>
/* 自定义 */
	h1{
		margin-left: -450px;
	}
	.tablecenter{
		padding-top: 40px;
		padding-bottom: 200px;
		margin: 0 auto;
		width: 700px;
	}
	.el-table{
		
	}
</style>
