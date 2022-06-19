<template>
	<Header></Header>
	<div id="">
		<div class="tablecenter">
			<h1>即将上映</h1>
			<el-table :data="tableData" height="250" style="width: 100%">
			    <el-table-column prop="date" label="上映日期" width="180" />
			    <el-table-column prop="name" label="名字" width="180" />
			    <el-table-column prop="address" label="期待人数" />
			  </el-table>
			   <el-divider />
		</div>
		<div class="tablecenter">
			<h1>已上映</h1>
			<el-table :data="tableData" height="250" style="width: 100%">
			    <el-table-column prop="date" label="上映日期" width="180" />
			    <el-table-column prop="name" label="名字" width="180" />
			    <el-table-column prop="address" label="观影人数" />
			  </el-table>
			   <el-divider />
		</div>
	</div>
	
	<Footer></Footer>
</template>

<script setup>
	import Header from "../components/Header.vue"
	import Footer from "../components/Footer.vue"
	
	
	const tableData = [
	  {
	    date: '2016-05-03',
	    name: '霸王别姬',
	    address: 10,
	  },
	  {
	    date: '2016-05-02',
	    name: '霸王',
	    address: 10,
	  },
	  {
	    date: '2016-05-04',
	    name: '霸王别',
	    address: 10,
	  },
	  {
	    date: '2016-05-01',
	    name: '王别姬',
	    address: 10,
	  },
	  {
	    date: '2016-05-08',
	    name: '霸',
	    address: 10,
	  },
	  {
	    date: '2016-05-06',
	    name: '王',
	    address: 10,
	  },
	  {
	    date: '2016-05-07',
	    name: '姬',
	    address: 10,
	  },
	]
</script>

<style scoped>
/* 自定义 */
	h1{
		margin-left: -450px;
	}
	.tablecenter{
		padding-top: 40px;
		margin: 0 auto;
		width: 600px;
	}
	.el-table{
		
	}
</style>
