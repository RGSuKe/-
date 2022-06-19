<template>
	<Header/>
	<el-card class="box-card">
	    <div  class="text item">{{ "学校：" + myselfmessage.school }}</div>
		<div  class="text item">{{ "专业：" + myselfmessage.profession }}</div>
		<div  class="text item">{{ "小组：" + myselfmessage.group }}</div>
		<div  class="text item">{{ "人数：" + myselfmessage.number +"人"}}</div>
	</el-card>
	<Footer />
</template>

<style scoped>
.el-card{
	margin: 0 auto;
	margin-top: 10%;
	margin-bottom: 10%;
}
.text {
  font-size: 17px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
</style>


<script setup lang="ts">
	import Header from '../../components/Header.vue'
	import Footer from '../../components/Footer.vue'
	import {reactive } from 'vue'
	
	const myselfmessage = reactive({
		school: '广西科技师范学院',
		profession: '软件工程',
		group: '踏雪寻梅',
		number: '1',
	})
	
	
</script>