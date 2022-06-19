<template>
  <div>
	  <!-- 顶部按钮 start-->
	  <div class="button">
		  <el-button type="primary" :icon="List" @click="getScheduel">全部计划</el-button>
		  <el-button type="primary" :icon="Promotion" text @click="dialogFormVisible = true">提交计划</el-button>
	  </div>
	   <!-- 顶部按钮 end-->
	  
	  <!-- 表单 start-->
     <el-table 
	 :data="tableData.data" 
	 border
	  @selection-change="handleSelectionChange"
	  header-cell-class-name="headerBg"  
	  :row-style="{height: '80px'}"
	   style="font-size: 15px">
	  >
       <el-table-column
           type="selection"
           width="50"
		   align="center">
       </el-table-column>
       <el-table-column prop="scheduleId" label="计划序号" align="center" width="100"></el-table-column>
	   <!-- <el-table-column prop="cinemaId" label="影院序号" width="100"></el-table-column> -->
       <!-- <el-table-column prop="cinemaName" label="影院名字" align="center"></el-table-column> -->
	   <!-- <el-table-column prop="movieId" label="影片序号" width="100"></el-table-column> -->
       <el-table-column prop="movieName" label="影片名字" align="center" width="250"></el-table-column>
	   <el-table-column prop="scheduleBegintime" label="开始时间" align="center" width="300"></el-table-column>
       <el-table-column prop="scheduleEndtime" label="结束时间" align="center" width="300"></el-table-column>
	   <el-table-column prop="price" label="价格(CNY)" width="200" align="center" ></el-table-column>
       <el-table-column label="操作" align="center" >
			<template #default="scope">
				<el-button size="large" @click="dialogUpdateFormVisible = true;updateInfo(scope.$index)">修改</el-button>
				<el-popconfirm
					class="ml-5"
					confirm-button-text='确定删除'
					cancel-button-text='让我想想'
					icon="el-icon-info"
					icon-color="red"
					title="确定删除吗？"
					@confirm="deleteSchedule"
				>
					<template #reference >
					  <el-button size="large" type="danger" >删除</el-button>
					</template>
				  </el-popconfirm>
			  </template>
       </el-table-column>
     </el-table>
   </div>
   <!-- 表单 end-->


   <!-- 添加窗口 start-->
   <div>
	    <el-dialog v-model="dialogFormVisible" title="新计划">
	       <el-form :model="form"  >
	         <el-form-item label="影院ID"   :label-width="formLabelWidth">
	            <el-input v-model="form.cinemaId" disabled clearable placeholder="Please input"/>   
	         </el-form-item>
			 <el-form-item label="电影名称" :label-width="formLabelWidth">   
				<el-select v-model="form.movieId"  placeholder="Please input">
				  <el-option
				       v-for="item in movieForm.data"
				       :key="item.movieId"
				       :label="item.movieName"
				       :value="item.movieId"
				     />
				</el-select>
			 </el-form-item>
			 <el-form-item label="影片开始时间" :label-width="formLabelWidth">
			   <el-input v-model="form.scheduleBegintime" clearable placeholder="Input format:2022-05-12 17:30:00"/>
			 </el-form-item>
			 <el-form-item label="影片结束时间" :label-width="formLabelWidth">
			   <el-input v-model="form.scheduleEndtime" clearable placeholder="Input format:2022-05-12 17:30:00"/>
			 </el-form-item>
			 <el-form-item label="单价" :label-width="formLabelWidth">
			   <el-input v-model="form.price" clearable placeholder="Please input"/>
			 </el-form-item>
			 <el-form-item label="总行数" :label-width="formLabelWidth">
				<el-input v-model="form.seatRow" type="number" placeholder="Please input"/>
			 </el-form-item>
			 <el-form-item label="总列数" :label-width="formLabelWidth">
			 	<el-input v-model="form.seatCol" type="number" placeholder="Please input"/>
			 </el-form-item>
	       </el-form>
	       <template #footer>
	         <span class="dialog-footer">
	           <el-button @click="dialogFormVisible = false">取消</el-button>
	           <el-button type="primary" @click="dialogFormVisible = false;addSchedule()">确认</el-button>
	         </span>
	       </template>
	     </el-dialog>
   </div>
  <!-- 添加计划 end-->
   
   
   <!-- 修改计划start-->
   <div>
   	    <el-dialog v-model="dialogUpdateFormVisible" title="修改计划">
   	       <el-form :model="updateForm.data"  >
   	         <el-form-item label="影院ID"  :label-width="formLabelWidth">
   	            <el-input v-model="updateForm.data.cinemaId" disabled clearable placeholder="Please input"/>   
   	         </el-form-item>
   	    	 <el-form-item  label="电影名称" :label-width="formLabelWidth">   
   	    		<el-select v-model="updateForm.data.movieId"  placeholder="Please input">
   	    		  <el-option
   	    		       v-for="item in movieForm.data"
   	    		       :key="item.movieId"
   	    		       :label="item.movieName"
   	    		       :value="item.movieId"
   	    		     />
   	    		</el-select>
   	    	 </el-form-item>
   	    	 <el-form-item label="影片开始时间" :label-width="formLabelWidth">
   	    	   <el-input v-model="updateForm.data.scheduleBegintime" clearable placeholder="Input format:2022-05-12 17:30:00"/>
   	    	 </el-form-item>
   	    	 <el-form-item label="影片结束时间" :label-width="formLabelWidth">
   	    	   <el-input v-model="updateForm.data.scheduleEndtime" clearable placeholder="Input format:2022-05-12 17:30:00"/>
   	    	 </el-form-item>
   	    	 <el-form-item label="单价" :label-width="formLabelWidth">
   	    	   <el-input v-model="updateForm.data.price" clearable placeholder="Please input"/>
   	    	 </el-form-item>
   	    	
   	       </el-form>
   	       <template #footer>
   	         <span class="dialog-footer">
   	           <el-button @click="dialogUpdateFormVisible = false">取消</el-button>
   	           <el-button type="primary" @click="dialogUpdateFormVisible = false;updateSchedule()">确认</el-button>
   	         </span>
   	       </template>
   	     </el-dialog>
   </div>
   <!-- 修改电影 end-->
</template>

<script lang="ts" setup>
	import { reactive, ref, inject } from 'vue'
	import { Search, List, Promotion } from '@element-plus/icons-vue'
	import {  ElMessage, ElMessageBox } from 'element-plus'
	
	const tableData = reactive({
		
	});

	//接收main.js的axios对象
	const $axios = inject('$axios');
	//获取用户名
	const cinemaName = ref(localStorage.getItem("cinemaName"));
	/**
	 * 点开网页，先执行一边查询全部的方法  生命周期是vue2的beforeCreate和created     start
	 */
	$axios.get("http://localhost:89/schedule/getSchedule/"+cinemaName.value).then((resp) => {
		tableData.data = resp.data.data;
		console.log(tableData.data);
	}).catch((err) => {
		console.log(err);
	})
	/**
	 * 点开网页，先执行一边查询全部的方法  生命周期是vue2的beforeCreate和created   end
	 */
	
	
	/**
	 * 查询全部  start
	 */
	const getScheduel = () => {
		$axios.get("http://localhost:89/schedule/getSchedule/"+cinemaName.value).then((resp) => {			
			tableData.data = resp.data.data;
			console.log(tableData.data);
		}).catch((err) => {
			console.log(err);
		});
	}
	/**
	 * 查询全部 end
	 */
	
	
	/**
	 * 删除  start
	 */
	//获取选中的
	let multipleSelection = reactive([]);
	//向请求发送的选中的
	const deleteScheduleIds = reactive([]);
	const handleSelectionChange = (val) =>{
		console.log(val)
		multipleSelection=val
		
	}
	const deleteSchedule = () =>{
		for(let i = 0; i<multipleSelection.length; i++){
			deleteScheduleIds.push(multipleSelection[i].scheduleId)
		};
		console.log(deleteScheduleIds.length);
		if(deleteScheduleIds.length != 0){
			$axios.post("http://localhost:89/schedule/deleteSchedule", deleteScheduleIds).then((resp) => {
				console.log(resp.data);
				if(resp.data.data == true){
					ElMessage({
						message: '删除成功，已为你呈递了最新数据！！',
						type: 'success',
					});
					getScheduel();
					deleteScheduleIds = reactive([]);
					
				}else{
					ElMessage.error('删除失败')
				}
			}).catch((err) => {
				console.log(err);
			});	
		}else{
			ElMessage.error('请先选择要删除的选项！')
		}		
	}
	/**
	 * 删除 end
	 */
	
	
	/**
	 * 提交内容 start
	 */
	const dialogFormVisible = ref(false)
	const formLabelWidth = '140px'
	const diaolog = ref(false);
	// const seatRow = ref();
	// const seatCol = ref();
	const cinemaId = localStorage.getItem("cinemaId")
	const form = reactive({
	  cinemaId: cinemaId,
	  movieId: "",
	  scheduleBegintime: '',
	  scheduleEndtime: '',
	  price: "",
	  seatRow: 0,
	  seatCol: 0
	})
	const movieForm = reactive({
		data:[]
	});
	// const getMovieInfo = () =>{
		$axios.get("http://localhost:89/movie").then((resp) => {
		 		console.log(resp.data.data);
				 movieForm.data = resp.data.data;
				
				// for(var i = 0; i < resp.data.data.length; i++){
				// 	if(resp.data.data[i].movieStatus == 1){
				// 		movieForm.data.push(resp.data.data[i])
				// 	}
				// 	// cinemaForm.data[i] = resp.data.data[i];
				// 	// cinemaForm.data[i].movieName = resp.data.data[i].movieName;
				// }
				console.log(movieForm)
		 	}).catch((err) => {
		 		console.log(err);
		 	});
	// }

	const addSchedule = () =>{
		console.log(form);
		if(form.cinemaId != ''
		 &&form.movieId != '' 
		 &&form.scheduleBegintime != ''
		 &&form.scheduleEndtime != ''
		 &&form.price != ''){
			$axios.post("http://localhost:89/schedule/addSchedule", form).then((resp) => {
				console.log(resp.data.data);
				if(resp.data.data === true){
					ElMessage({
						message: '提交计划成功，已为你呈递了最新数据！！',
						type: 'success',
					});
					getScheduel();
					//置空form表单
					Object.keys(form).map(
						key => {
							delete form[key];
						}
					);
					console.log("提交的表单",form)
				}else{					
					ElMessageBox({
						type: 'info',
						message: '请检查填写信息是否符合格式！！！',
					 })
					  
				}
			}).catch((err) => {
				console.log(err);
			});
		}else{
			 ElMessageBox({
				type: 'info',
				message: '请填写完整信息，再提交。',
			  })
			  dialogFormVisible.value = true;
		}
		
	}
	/**
	 * 提交内容  end
	 */
	
	
	/**
	 * 修改内容 start
	 */
	//修改窗口状态
	const dialogUpdateFormVisible = ref(false);
	//存放修改窗口数据
	const updateForm = reactive({
		data:{
			scheduleId: "",
			cinemaId: "",
			movieId: "",
			scheduleBegintime: "",
			scheduleEndtime: "",
			price: ""
		}
	});
	const updateInfo = (ID) => {
		updateForm.data.scheduleId = tableData.data[ID].scheduleId;
		updateForm.data.cinemaId = tableData.data[ID].cinemaId;
		updateForm.data.movieId = tableData.data[ID].movieId;
		updateForm.data.scheduleBegintime = tableData.data[ID].scheduleBegintime.replace(/T/g, ' ');
		updateForm.data.scheduleEndtime = tableData.data[ID].scheduleEndtime.replace(/T/g, ' ');
		updateForm.data.price = tableData.data[ID].price;
		
		console.log("update",updateForm.data) ;
	}
	const updateSchedule = () =>{
		$axios.post("http://localhost:89/schedule/updateSchedule", updateForm.data).then((resp) => {
			if(resp.data.data == true){
				ElMessage({
					message: '修改成功，已为你呈递了最新数据！！',
					type: 'success',
				});
				getScheduel();
			}else{
				ElMessageBox({
					type: 'info',
					message: '请检查填写信息是否符合格式！！！',
				 })
			}
		}).catch((err) =>{
			console.log(err);
		})
	}
	/**
	 * 修改内容 end
	 */
</script>

<style scoped>
	.headerBg{
	  background-color: #cccccc!important;
	}
	.button{
		margin-bottom: 20px;
		float: left;
	}
	.el-button--text {
	  margin-right: 15px;
	}
	.el-select {
	  width: 300px;
	}
	.el-input {
	  width: 300px;
	}
	.dialog-footer button:first-child {
	  margin-right: 10px;
	}
</style>
