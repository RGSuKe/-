<template>

	<div>
		<div class="button">
			<div class="button_first">
				 <el-button type="primary" :icon="List" @click="getMovie">全部影片</el-button>
				<el-button type="primary" :icon="Promotion" @click="dialogFormVisible = true">提交电影</el-button>&nbsp;&nbsp;
			</div>
		 
			<br><br><br>
		   <el-input clear v-model="pagination.movieSearchName"></el-input>&nbsp;
		   <el-button type="primary" :icon="Search" @click="getMovie">搜索影片(名字)</el-button>
		 
		 
		</div>
		
		<el-table
			 :data="tableData.data" 
			  border
			  @selection-change="handleSelectionChange"
			 header-cell-class-name="headerBg" 
			 style="font-size: 15px;font-weight: 500;"
			  >
		    <el-table-column
		        type="selection"
		        width="40">
		    </el-table-column>
				<el-table-column prop="movieId" align="center" label="电影序号" width="90" ></el-table-column>
			   <el-table-column prop="movieName" align="center" label="影片名称" width="95"></el-table-column>
				<el-table-column prop="movieImg"  label="电影封面地址" align="center" ></el-table-column>
			   <el-table-column prop="movieDirector" label="影片导演" width="90"></el-table-column>
				<el-table-column prop="movieActor" label="影片演员" align="center" width="100"></el-table-column>
			   <el-table-column prop="movieUptime" label="影片上映时间" align="center" width="110"></el-table-column>
				<el-table-column prop="movieDuration" label="电影时长(分钟)" align="center" width="120"></el-table-column>
			   <el-table-column prop="movieDetail" label="影片详情" width="300" align="center"></el-table-column>
			   
			   
			   <!-- <el-table-column prop="movieStatus" label="影片状态" width="100" align="center"> -->
			  <el-table-column  label="影片状态" width="100" align="center">
				  
					<template #default="scope">
					  <el-tag v-if="scope.row.movieStatus === 0 ">
					    未上映
					  </el-tag>
					  <el-tag v-if="scope.row.movieStatus === 1 " type="success">
						已上映
					  </el-tag> 
					  
						  <el-switch
							 v-model="scope.row.movieStatus"
							 inline-prompt
							 :active-value="1"
							 :inactive-value="0"
							 @change="changeStatus(scope.row.movieId, scope.row.movieStatus)"
						   /> 
						
					</template>
			   </el-table-column>
			   
			   <!-- <el-table-column  label="影片状态" width="100" align="center">
					<template #default="scope">
					   <el-switch
					      v-model="scope.row.movieStatus"
					      inline-prompt
					      :active-value="1"
					      :inactive-value="2"
					    />
					</template>
			   </el-table-column> -->
			   
				<el-table-column label="操作" align="center" width="100">
					<template #default="scope">
						<el-button size="large" @click="dialogUpdateFormVisible = true;updateInfo(scope.$index)">修改</el-button>
						<br><br>	
						<el-popconfirm
							class="ml-5"
							confirm-button-text='确定删除'
							cancel-button-text='让我想想'
							icon="el-icon-info"
							icon-color="red"
							title="确定删除吗？"
							@confirm="deleteMovie"
						>
							<template #reference >
							  <el-button size="large" type="danger" >删除</el-button>
							</template>
						  </el-popconfirm>
					  </template>
		    </el-table-column>
		  </el-table>
	</div>
	
	
	<!-- 分页start -->
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
	

	<!-- 提交电影 start-->
	<div>
		    <el-dialog v-model="dialogFormVisible" title="新电影">
		       <el-form :model="form">
		         <el-form-item label="电影名称" :label-width="formLabelWidth">
		            <el-input v-model="form.movieName" clearable  maxlength="20" show-word-limit autocomplete="off" placeholder="Please input"/>
		         </el-form-item>
				 <el-form-item label="封面地址" :label-width="formLabelWidth">
				   <el-input v-model="form.movieImg"   maxlength="400" show-word-limit autocomplete="off" placeholder="Please input" type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" clearable/>
				 </el-form-item>
				 <el-form-item label="影片导演" :label-width="formLabelWidth">
				   <el-input v-model="form.movieDirector" clearable  autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="影片演员" :label-width="formLabelWidth">
				   <el-input v-model="form.movieActor" clearable  maxlength="100" show-word-limit autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="影片类型" :label-width="formLabelWidth">
				   <el-input v-model="form.movieTypes" clearable  maxlength="30" show-word-limit autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="影片上映时间" :label-width="formLabelWidth">
				   <el-input v-model="form.movieUptime" clearable  autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="电影时长(分钟)" :label-width="formLabelWidth">
				   <el-input v-model="form.movieDuration" clearable  autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="影片详情" :label-width="formLabelWidth">
				   <el-input v-model="form.movieDetail" maxlength="400" show-word-limit autocomplete="off" clearable placeholder="Please input" type="textarea" :autosize="{ minRows: 2, maxRows: 4 }"/>
				 </el-form-item>
				 <el-form-item label="影片状态" :label-width="formLabelWidth">
				   <el-input v-model="form.movieStatus" disabled autocomplete="off" clearable placeholder="Please input" type="number" /> 
				   
				 </el-form-item>
				 
		       </el-form>
		       <template #footer>
		         <span class="dialog-footer">
		           <el-button @click="dialogFormVisible = false">取消</el-button>
		           <el-button type="primary" @click="dialogFormVisible = false;addMoive()">确认</el-button>
		         </span>
		       </template>
		     </el-dialog>
	</div>
	<!-- 提交电影 end-->
	
	
	<!-- 修改电影 start-->
	<div>
		    <el-dialog v-model="dialogUpdateFormVisible" title="修改电影">
		       <el-form :model="updateForm.data">
		         <el-form-item label="电影名称" :label-width="formLabelWidth">
		            <el-input v-model="updateForm.data.movieName" clearable  maxlength="20" show-word-limit autocomplete="off" placeholder="Please input"/>
		         </el-form-item>
				 <el-form-item label="封面地址" :label-width="formLabelWidth">
				   <el-input v-model="updateForm.data.movieImg"   maxlength="400" show-word-limit autocomplete="off" placeholder="Please input" type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" clearable/>
				 </el-form-item>
				 <el-form-item label="影片导演" :label-width="formLabelWidth">
				   <el-input v-model="updateForm.data.movieDirector" clearable  autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="影片演员" :label-width="formLabelWidth">
				   <el-input v-model="updateForm.data.movieActor" clearable  maxlength="100" show-word-limit autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="影片类型" :label-width="formLabelWidth">
				   <el-input v-model="updateForm.data.movieTypes" clearable  maxlength="30" show-word-limit autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="影片上映时间" :label-width="formLabelWidth">
				   <el-input v-model="updateForm.data.movieUptime" clearable  autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="电影时长(分钟)" :label-width="formLabelWidth">
				   <el-input v-model="updateForm.data.movieDuration" clearable  autocomplete="off" placeholder="Please input"/>
				 </el-form-item>
				 <el-form-item label="影片详情" :label-width="formLabelWidth">
				   <el-input v-model="updateForm.data.movieDetail" maxlength="400" show-word-limit autocomplete="off" clearable placeholder="Please input" type="textarea" :autosize="{ minRows: 2, maxRows: 4 }"/>
				 </el-form-item>
				 	 
		       </el-form>
		       <template #footer>
		         <span class="dialog-footer">
		           <el-button @click="dialogUpdateFormVisible = false">取消</el-button>
		           <el-button type="primary" @click="dialogUpdateFormVisible = false;updateMovie()">确认</el-button>
		         </span>
		       </template>
		     </el-dialog>
	</div>
	<!-- 修改电影 end-->
	
	
	
</template>

<script lang="ts" setup>
	import { reactive, ref, inject } from 'vue'
	import { Search, List, Promotion } from '@element-plus/icons-vue'
	import { ElMessage, ElMessageBox } from 'element-plus'
	
	
	const tableData = reactive({
		
	});
		
	//接收main.js的axios对象
	const $axios = inject('$axios');
	
	/**
	 * 查询全部  start
	 */
	// const getMovie = () =>{
	// 	$axios.get("http://localhost:89/movie").then((resp) => {
	// 		console.log(resp.data.data);
	// 		tableData.data = resp.data.data;
	// 	}).catch((err) => {
	// 		console.log(err);
	// 	})
	// }
	/**
	 * 查询全部 end
	 */
	
	/**
	 * 分页查询 start
	 */
	const small = ref(false)
	const background = ref(false)
	const disabled = ref(false)
	const pagination = reactive({
		currentPage: 1,//当前页码
		pageSize: 3,//每页显示的记录数
		total: 0,//总记录数
		//需要根据查询的条件
		movieSearchName:''	
	})
	/**
	 * 点开网页，先执行一边查询全部的方法  生命周期是vue2的beforeCreate和created   start
	 */
	$axios.get('http://localhost:89/movie/'+pagination.currentPage+"/"+pagination.pageSize+"?movieName="+pagination.movieSearchName).then((resp) => {
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
	
	const getMovie = () => {
		$axios.get('http://localhost:89/movie/'+pagination.currentPage+"/"+pagination.pageSize+"?movieName="+pagination.movieSearchName).then((resp) => {
					console.log(resp.data.data)
					tableData.data = resp.data.data.records;
					pagination.pageSize = resp.data.data.size;
					pagination.total = resp.data.data.total;
					pagination.currentPage = resp.data.data.current;
					 console.log("每页几条",resp.data.data.size+"..总条数"+resp.data.data.total+"..当前页"+resp.data.data.current)
		});
		//清空搜索栏
		pagination.movieSearchName = '';
	}
	//更新当前页
	const handleCurrentChange = (currentPage) =>{
		console.log("当前页",currentPage);
		pagination.currentPage = currentPage;
		getMovie();
	}
	
	//更新每页大小
	const handleSizeChange = (size) =>{
		console.log("每页多少条",size);
		pagination.pageSize = size;
		getMovie();
	}
	
	/**
	 * 分页查询 end
	 */
	
	/**
	 * 更改影片状态 start
	 */
	
	const changeStatus = (movieId, movieStatus) =>{
		console.log(movieId, movieStatus-1);
		if(movieStatus === 1){
			movieStatus = movieStatus - 1;
			$axios.post("http://localhost:89/movie/changeMovieStatus/" + movieId + "/" + movieStatus).then((resp) => {
				if(resp.data.data == true){
					ElMessage({
						message: '修改电影状态为-已上映！',
						type: 'success',
					});
				}else{
					ElMessage.error("修改状态失败");
				}
				
			}).catch((err) => {
				console.log(err);
			})
		}else{
			movieStatus = movieStatus + 1;
			$axios.post("http://localhost:89/movie/changeMovieStatus/" + movieId + "/" + movieStatus).then((resp) => {
				if(resp.data.data == true){
					ElMessage({
						message: '修改电影状态为-未上映！',
						type: 'success',
					});
				}else{
					ElMessage.error("修改状态失败");
				}
				
			}).catch((err) => {
				console.log(err);
			})
		}
		
	}
	
	/**
	 * 更改影片状态 end
	 */
	
	
	/**
	 * 修改电影信息 start
	 */
	//修改窗口状态
	const dialogUpdateFormVisible = ref(false);
	//存放修改窗口数据
	const updateForm = reactive({
		data:{}
	});
	const updateInfo = (ID) => {
		updateForm.data = tableData.data[ID];
		console.log("update",updateForm.data) ;
	}
	const updateMovie = () =>{
			$axios.post("http://localhost:89/movie/updateMovie", updateForm.data).then((resp) => {
				if(resp.data.data == true){
					ElMessage({
						message: '修改成功，已为你呈递了最新数据！！',
						type: 'success',
					});
				}else{
					ElMessage.error('修改失败！')
				}
			}).catch((err) => {
				console.log(err);
			})
		
		
	}
	
	/**
	 * 修改电影信息 end
	 */
	
	//获取选中的
	let multipleSelection = reactive([]);
	//向请求发送的选中的movieId的数组
	const deleteMovieIds = reactive([]);
	const handleSelectionChange = (val) =>{
		console.log(val)
		multipleSelection=val
	}
	/**
	 * 删除  start
	 */
	const deleteMovie = () =>{
		console.log("multipleSelection:",multipleSelection);
		//将选中的movieId存入deleteMovieIds，
		for(let i = 0; i < multipleSelection.length; i++){
			deleteMovieIds.push(multipleSelection[i].movieId);
		}
		console.log("deleteMovieIds:",deleteMovieIds);
		console.log(deleteMovieIds.length);
		if(deleteMovieIds.length != 0){
			if(deleteMovieIds.length != 0){
				$axios.post("http://localhost:89/movie/deleteMovie", deleteMovieIds).then((resp) => {
					console.log(resp.data);
					if(resp.data.data == true){
						ElMessage({
							message: '删除成功，已为你呈递了最新数据！！',
							type: 'success',
						});
						getMovie();
						deleteMovieIds = reactive([]);
						
					}else{
						ElMessage.error('删除失败！');
					}
				}).catch((err) =>{
					console.log(err);
				})
			}else{
			ElMessage.error('请先选择要删除的选项！')
		}		
			
		}
	}
	/**
	 * 删除 end
	 */
	
	/**
	 * 添加 start
	 */
	const dialogFormVisible = ref(false)
	const formLabelWidth = '140px'
	// const diaolog = ref(false);
	const form = reactive({
	  movieName: '',
	  movieImg: '',
	  movieDirector: '',
	  movieActor: '',
	  movieTypes: '',
	  movieUptime: '',
	  movieDuration: '',
	  movieDetail: '',
	  movieStatus: '0',
	})
	const addMoive = () =>{
		console.log(form);
		if(form.movieName != '' &&
		   form.movieImg != '' &&
		   form.movieDirector != '' &&
		   form.movieActor != '' &&
		   form.movieTypes != '' &&
		   form.movieUptime != '' &&
		   form.movieDuration != '' &&
		   form.movieDetail != '' &&
		   form.movieStatus != ''  ){
			$axios.post("http://localhost:89/movie/addMovie", form).then((resp) => {
				console.log(resp.data.data);
				if(resp.data.data === true){
					ElMessage({
						message: '提交电影成功，已为你呈递了最新数据！！',
						type: 'success',
					});
					getMovie();
					//置空form表单
					Object.keys(form).map(
						key => {
							delete form[key]
						}
					);
					console.log("提交的表单",form)
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
	 * 添加 end
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
	.button_first{
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
	.button—bock{
		margin-top: 20px;
	}
</style>
