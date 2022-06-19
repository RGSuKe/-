<template>
	<Header />
	<div class="content">
		<el-tabs v-model="activeName" class="demo-tabs" tab-position="left"  @tab-click="handleClick">
			<!-- 正在热映 -->
		    <el-tab-pane  label="正在热映" name="first">
				<!-- <img src="../img/step.png" class="imgmessage"/> -->
				<div>
					<ul class="mainlist">
						<li class="partlist" @click="checkmovie_type('剧情')"><a>剧情</a></li>
						<li class="partlist" @click="checkmovie_type('历史')"><a>历史</a></li>
						<li class="partlist" @click="checkmovie_type('悬疑')"><a>悬疑</a></li>
						<li class="partlist" @click="checkmovie_type('古装')"><a>古装</a></li>
						<li class="partlist" @click="checkmovie_type('爱情')"><a>爱情</a></li>
						<li class="partlist" @click="checkmovie_type('戏曲')"><a>戏曲</a></li>
						<li class="partlist" @click="checkmovie_type('传记')"><a>传记</a></li>
						<li class="partlist" @click="checkmovie_type('科幻')"><a>科幻</a></li>
						<li class="partlist" @click="checkmovie_type('纪录')"><a>纪录</a></li>
					</ul>
				</div>	
				<el-divider />
					<!-- start -->
					<div class="contentmessage1" v-for="m1 in movie_detail_1.data">
						<div class="filmlist">
							<img :src="m1.movieImg" class="imgsize"/>								
						</div>						  
						<el-button size="large" @click="dialogTableVisible = true;getMovie_Cinema_Schedule(m1.movieId)">立即购票</el-button>					  			
						<!-- <el-button  @click="drawer = true;getMovie_Cinema_Schedule(m1.movieId)" size="large">查看详情</el-button> -->
						<div class="filmmessage">
							<div class="moviename">
								{{m1.movieName}}
							</div>
							<div>
								导演：{{m1.movieDirector}}
							</div>
							<div>
								演员：{{m1.movieActor}}
							</div>
							<div>
								类型：{{m1.movieTypes}}
							</div>
							<div>
								上映时间：{{m1.movieUptime}}
							</div>
							<div>
								时长：{{m1.movieDuration}}分钟
							</div>
							<div>
								详情：{{m1.movieDetail}}
							</div>
						</div>
						
					</div>
					 <el-divider />
					<!--  end -->
						  
				</el-tab-pane>
				
				
				
				
		<!-- 即将上映 -->		
				<el-tab-pane label="即将上映" name="second">
					<!-- <img src="../img/step.png" class="imgmessage"/> -->
					<ul class="mainlist">
						<li class="partlist" @click="checkmovie_type('剧情')"><a>剧情</a></li>
						<li class="partlist" @click="checkmovie_type('历史')"><a>历史</a></li>
						<li class="partlist" @click="checkmovie_type('悬疑')"><a>悬疑</a></li>
						<li class="partlist" @click="checkmovie_type('古装')"><a>古装</a></li>
						<li class="partlist" @click="checkmovie_type('爱情')"><a>爱情</a></li>
						<li class="partlist" @click="checkmovie_type('戏曲')"><a>戏曲</a></li>
						<li class="partlist" @click="checkmovie_type('传记')"><a>传记</a></li>
						<li class="partlist" @click="checkmovie_type('科幻')"><a>科幻</a></li>
						<li class="partlist" @click="checkmovie_type('纪录')"><a>纪录</a></li>
					</ul>
					<el-divider />	
						<!--  start -->
						<div class="contentmessage1" v-for="m0 in movie_detail_0.data">
						  
							  <div class="filmlist">
							  	<img :src="m0.movieImg" class="imgsize"/>				
							  </div>						  
							  <el-button size="large" @click="dialogTableVisible = true;getMovie_Cinema_Schedule(m0.movieId)">立即购票</el-button>					  			
							 <!-- <el-button size="large" @click="drawer = true;getMovie_Cinema_Schedule(m0.movieId)" >查看详情</el-button> -->
							  <div class="filmmessage">
							  	<div class="moviename">
							  		{{m0.movieName}}
							  	</div>
							  	<div>
							  		导演：{{m0.movieDirector}}
							  	</div>
							  	<div>
							  		演员：{{m0.movieActor}}
							  	</div>
							  	<div>
							  		类型：{{m0.movieTypes}}
							  	</div>
							  	<div>
							  		上映时间：{{m0.movieUptime}}
							  	</div>
							  	<div>
							  		时长：{{m0.movieDuration}}分钟
							  	</div>
							  	<div>
							  		详情：{{m0.movieDetail}}
							  	</div>
							  </div>						
						</div>
						<!--  end -->		
				</el-tab-pane>
		    
		  </el-tabs>
	</div>
	
	<!-- 点击查看详情弹出测边框 start -->
	<el-drawer v-model="drawer"  :with-header="false" >
		<div>
			<img :src="movie_cinema_schedule.data[0].movieImg" class="imgsize"/>
			<h2>片名：{{movie_cinema_schedule.data[0].movieName}}</h2><br>
			
			<div v-for="mcs in movie_cinema_schedule.data">
				<h2>影院：{{mcs.cinemaName}}</h2><br>
			 	票价：{{mcs.price}} 元<br>
				时间: {{mcs.scheduleBegintime}}-{{mcs.scheduleEndtime}}<br>
			 </div>
		</div>
		
	</el-drawer>
	<!-- 点击查看详情弹出测边框 end -->
	 
	
	<!-- 选影院 start -->
	 <!-- <el-dialog v-model="visible" :show-close="false">
	   
	  </el-dialog> -->
	   <el-dialog v-model="dialogTableVisible" title="购票安排">
	      <el-table :data="movie_cinema_schedule.data" >
			<el-table-column  type="index" label="序号" width="100" align="center"></el-table-column>
	        <el-table-column property="cinemaName" label="影院" width="150" />
	        <el-table-column property="scheduleBegintime" label="开始时间" width="200" />
			<el-table-column property="scheduleEndtime" label="结束时间" width="200" />
	        <el-table-column property="price" label="票价" />
			<el-table-column > 
			<template v-slot="scope">
				<el-button @click="ChooseSeat = true;select(scope.$index);getSeat(scope.row.scheduleId)">选择</el-button>
				<!-- <el-button @click="ChooseSeat = true;select(scope.row.scheduleId)">选择</el-button> -->
			 </template>
			</el-table-column>
	      </el-table>
		  <span v-if="dialogTableVisibleAlert">
			  <strong >该片暂时无安排，请选择其他！</strong>
		  </span>
		  
	    </el-dialog>
	<!-- 选影院 end -->
	
	<!-- 选位置 start -->
	<el-dialog v-model="ChooseSeat" title="选取座位">
	  <div class="seatstyle">
		 <el-checkbox-group v-model="checkboxGroup1" @change="changecheckbox">
			<el-checkbox border  v-for="s in seat.data" :label="s.seatId" :disabled="s.seatStatus === 1">
				{{s.seatRow}}-{{s.seatCol}}
			</el-checkbox> 
		 </el-checkbox-group>
	  </div>
	  
		 <p>总价：￥ {{totalFee}} </p>
		 <el-button @click="buytickets()">购买</el-button>
		 
		 
	 </el-dialog>
	<!-- 选位置 start -->
	
	
	<Footer />
</template>

<script setup lang="ts">
	import Header from '../../components/Header.vue'
	import Footer from '../../components/Footer.vue'
	import { computed, ref, reactive, inject, onUpdated, } from 'vue'
	import type { TabsPaneContext, ElMessage, ElMessageBox  } from 'element-plus'
	import type { ElDrawer, Action } from 'element-plus'
	import { CircleCloseFilled } from '@element-plus/icons-vue'
	import type { ElButton, ElDialog } from 'element-plus'
	
	const activeName = ref('first')
	
	const handleClick = (tab: TabsPaneContext, event: Event) => {
	  console.log(tab, event)
	}
	

	/**
	 * 获取影片信息 start
	 */
	// const url = [
	// 	"http://localhost:3000/src/img/filmlist/filmfengmian.png",
	// 	"http://localhost:3000/src/img/filmlist/filmfengmian.png",
	// ]
	const url = [
		"http://www.e-ging.com/bbs/thread-9536.html",
		"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg3.doubanio.com%2Fview%2Fphoto%2Fl%2Fpublic%2Fp2207554012.jpg&refer=http%3A%2F%2Fimg3.doubanio.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655103773&t=e351fdd7682bd5432a6bd5fe7dc8928a",
	]
	// const movie_detail = reactive({
	// 	img:["http://localhost:3000/src/img/carousel/1.png","http://localhost:3000/src/img/carousel/1.png",],
	// 	name: ["情书","霸王别姬",],
	// 	director: ["岩井俊二","陈凯歌",],
	// 	actor: ["xxxxxxx, xxxxxxx","张丰yi/，张国荣",],
	// 	description: ["喜剧/ 爱情","喜剧/ 爱情",],
	// 	uptime: ["2022-05-07","2022-05-07",],
	// 	detail: [
	// 		"患有社交恐惧症的屌丝郝大毅与富家女杜鹃相遇，这对欢喜冤家在经历诸多事情后，开始相爱，然而这时，三年前，曾盗走郝大毅的心血的人突然出现，给他们的感情蒙上了不确定……",
	// 		"患有社交恐惧症的屌丝郝大毅与富家女杜鹃相遇，这对欢喜冤家在经历诸多事情后，开始相爱，然而这时，三年前，曾盗走郝大毅的心血的人突然出现，给他们的感情蒙上了不确定……",
	// 	],	
	// 	duration: [130, 140],
	// })
	//保存影片信息
	//存取  未上映
	const movie_detail_0 = reactive({
		data:[]
	});
	//存取  已上映
	const movie_detail_1 = reactive({
		data:[]
	});
	//接收main.js的axios对象
	const $axios = inject('$axios');
	
	$axios.get('http://localhost:89/movie').then((resp) => {
		// movie_detail.data = resp.data.data;
		for(var i = 0; i < resp.data.data.length; i++){
			// console.log(resp.data.data[i])
			if(resp.data.data[i].movieStatus == 0){
				movie_detail_0.data.push(resp.data.data[i]) ;			
			}else if(resp.data.data[i].movieStatus === 1){
				movie_detail_1.data.push(resp.data.data[i]) ;				
			}
		}
		console.log("未上映",movie_detail_0.data);
		console.log("已上映",movie_detail_1.data);
	}).catch((err) => {
		console.log(err);
	});
	
	
	//点击某种类型 start
	const checkmovie_type = (movietype) =>{
		$axios.get('http://localhost:89/movie/movie_types/'+movietype).then((resp) => {
			// console.log(movietype);	
			//先清空上面存入的数据，不然循环获取数据时，存取数据会重复
			movie_detail_0.data=[];
			movie_detail_1.data=[];
			for(var i = 0; i < resp.data.data.length; i++){			
				if(resp.data.data[i].movieStatus == 0){
					movie_detail_0.data.push(resp.data.data[i]) ;
				}else if(resp.data.data[i].movieStatus === 1){
					movie_detail_1.data.push(resp.data.data[i]) ;				
				}	
			}
			console.log(movietype,movie_detail_1.data)
		}).catch((err) => {
			console.log(err);
		});
		
	}
	
	
	//点击某种类型 end
	
	/**
	 * 获取影片信息 end
	 */
	
	/**
	 * 获取该电影哪个影院有 start
	 */
	// 点击“查看详情”弹出左边的框 
	const drawer = ref(false)
	// 点击立即购票  弹出一个 对话框  
	const dialogTableVisible = ref(false)
	//没有信息时，会显示   “该片暂时无安排，请选择其他！”
	const dialogTableVisibleAlert = ref(false)
	//存储数据-存取安排
	const movie_cinema_schedule = reactive({
		data: []
	});
	
	const getMovie_Cinema_Schedule = (movieId) =>{
		//避免上次查出来，然后这次查出空，然后未覆盖。
		movie_cinema_schedule.data=[];
		//跟新提示信息的状态
		dialogTableVisibleAlert.value = false;
		$axios.get('http://localhost:89/movie/movie_cinema_schedule/'+movieId).then((resp) => {		
			movie_cinema_schedule.data = resp.data.data;			 
			 console.log("安排",movie_cinema_schedule.data);
			  // console.log(movie_cinema_schedule.data.length);
			 if(movie_cinema_schedule.data.length == 0){
				drawer.value = false;	
				dialogTableVisibleAlert.value = true;
				 // alert("抱歉，该电影还未有安排，可先选择其他的电影！");
				 movie_cinema_schedule.data=[];
			 }			
		}).catch((err) =>{
			console.log(err);
		});
	}
	
	
	/**
	 * 获取该电影哪个影院有 end
	 */
	
	/**
	 * 获取座位 start
	 */
	const seat = reactive({
		data: []
	}); 
	
	const getSeat = (scheduleId) =>{
		console.log("点击选的的影院id",scheduleId)
		$axios.get('http://localhost:89/seat/getSeatByScheduleId/'+scheduleId).then((resp) => {
			seat.data = resp.data.data;	
			console.log("座位",seat.data)
		}).catch((err) =>{
			console.log(err);
		});
	}
	/**
	 * 获取座位 end
	 */
	
	
	/**
	 * 购票  start
	 */
	//购票窗口
	const ChooseSeat = ref(false);
	//获取计划的某行的序号
	const Index = ref(0);
	const orderMessage = reactive({
		//数据名字要和后端的实体类一样		
		userId: "",
		scheduleId: "",
		seatId: [],
		orderCreatetime: "",
	});
	//获取计划的某行的序号
	const select = (index) => {
		Index.value = index;
		console.log("安排序号",Index.value);
	}
	
	//获取某行某的值
	// function select(index){
	// 	Index.value = index ;
	// 	console.log("值",Index.value);
	// }
	//总票价
	const totalFee = ref(0);
	//存储选中的长度
	const all = ref(0);
	//显示选择的框的值
	const checkboxGroup1 = ref([]);
	//控制台弹出已选的座位  val就是checkboxGroup1
	const changecheckbox = (val) => {
		console.log("选中的座位",val)
		if(all.value < val.length){		
			totalFee.value = totalFee.value + movie_cinema_schedule.data[Index.value].price;	
		}else if(all.value > val.length){
			totalFee.value = totalFee.value - movie_cinema_schedule.data[Index.value].price;
		}else{
			totalFee.value = totalFee.value;
		}		 			 
		console.log("总票价	", totalFee.value);	
		all.value = val.length;
		console.log("选择值数量：",all.value);
	}
	
	const buytickets = () => {
		orderMessage.userId = localStorage.getItem("userId");
		orderMessage.scheduleId = movie_cinema_schedule.data[Index.value].scheduleId;
		orderMessage.seatId = checkboxGroup1;
		//如果没有选座位，则不发送请求
		if(orderMessage.seatId != null){
			$axios.post('http://localhost:89/order1/addone',orderMessage).then((resp) => {
				if(resp.data.flag == false || resp.data.data == false){
					alert('请先选择座位，再次购买！');
				}else {		  
					console.log("购买的订单：", orderMessage);
					 alert("购买成功！");
					 //关闭购票窗口
					 ChooseSeat.value = false;
					 //关闭选电影院窗口
					 dialogTableVisible.value = false;			 
				}
				
			}).catch((err) =>{
				console.log(err);
			});
			orderMessage.seatId = [];
			console.log(orderMessage );
		}
	}
	
	/**
	 * 购票  end
	 */
</script>

<style scoped>
	/* elementuiplus */
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
	/* elementuiplus */
	.el-descriptions {
	  margin-top: 20px;
	}
	.demo-tabs > .el-tabs__content {
	  padding: 32px;
	  color: #6b778c;
	  font-size: 15px;
	  font-weight: 600;
	}
	.content{
		margin-left: 18% ;
		margin-top: 40px;
		margin-right: 18%;
	}
	.mainlist{
		width: 600px;
		height: 100px;
	}
	.partlist{
		float: left;
		margin-top: 20px;
	}
	.nofilm{
		width: 500px;
		height: 400px;
		margin: 0 ;
	}
	.contentmessage1{
	/* 	border: 1px solid #000000; */
		border-radius: 5px;
		width: 1020px;
		height: 400px;
		margin-bottom: 100px;
		margin-left: 20px;
	}
	.imgmessage{
		float: right;		
	}
	.filmlist{
		float: left;
	}
	.filmmessage{
		margin-left: 40px;
		float: left;
		width: 500px;
		text-align: left;	
	}
	.filmmessage div{
		margin-top: 10px;
	}
	.moviename{
		font-size: 25px;
	}
	.imgsize{
		width: 200px;
		height: 270px;
		border-radius: 8px 8px 8px 8px;
	}
/* 	.seatstyle{
		
	}
	.seatstyle > table{
		margin: 0 auto;
	}
	.seatstyle > table > tr > td {
		vertical-align: middle;
	} */
	
	
</style>