<template>
	<Header />
	<!-- 走马灯start -->
	<div>
		<el-carousel :interval="4000" type="card" height="300px" >
				<el-carousel-item v-for="item in carousel" :key="item">
					<img :src="item.src" class="carouselImg"/>
				</el-carousel-item>
		 </el-carousel>
	</div>
	<!-- 走马灯end -->
	<!-- 电影列表 start -->
	<div class="ListContext">
	
		<div id="Feature_film" class="jiange">
			<div>
				<h2>正在热映</h2>
				<span class="more">
					<router-link  to='Film'>更多></router-link>
				</span>
			</div>		
			<el-divider />
			<div>
				<ul class="img_list">
					<li>
						<a><img :src="movie_detail_1.data[movie_random.data[0]].movieImg" @click="dialog = true;checkdetail(movie_detail_1.data[movie_random.data[0]].movieId)"/></a>
						<br>
						<span>{{movie_detail_1.data[movie_random.data[0]].movieName}}</span>
					</li>
					<li>
						<a><img :src="movie_detail_1.data[movie_random.data[1]].movieImg" @click="dialog = true;checkdetail(movie_detail_1.data[movie_random.data[1]].movieId)"/></a>
						<br>
						<span>{{movie_detail_1.data[movie_random.data[1]].movieName}}</span>
					</li>
					<li>
						<a><img :src="movie_detail_1.data[movie_random.data[2]].movieImg" @click="dialog = true;checkdetail(movie_detail_1.data[movie_random.data[2]].movieId)"/></a>
						<br>
						<span>{{movie_detail_1.data[movie_random.data[2]].movieName}}</span>
					</li>
					<li>
						<a><img :src="movie_detail_1.data[movie_random.data[3]].movieImg" @click="dialog = true;checkdetail(movie_detail_1.data[movie_random.data[3]].movieId)"/></a>
						<br>
						<span>{{movie_detail_1.data[movie_random.data[3]].movieName}}</span>
					</li>
					
				</ul>
			</div>	
		</div>
	
	</div>
	
	
	<!-- dialog 点击展开右侧-->
	<el-drawer
	  ref="drawerRef"
	  v-model="dialog"
	  title=""
	  direction="ltr"
	  custom-class="demo-drawer"
	>
	  <div class="demo-drawer__content">
	   
			<img class="imgdialog" :src="movie_detail_1_message.data.movieImg"/>
			<div class="filmmessage">
				<div class="moviename">
					{{movie_detail_1_message.data.movieName}}
				</div>
				<div>
					导演：{{movie_detail_1_message.data.movieDirector}}
				</div>
				<div>
					演员：{{movie_detail_1_message.data.movieActor}}
				</div>
				<div>
					类型：{{movie_detail_1_message.data.movieTypes}}
				</div>
				
				<div>
					上映时间：{{movie_detail_1_message.data.movieUptime}}
				</div>
				<div>
					时长：{{movie_detail_1_message.data.movieDuration}}分钟
				</div>
				<div>
					详情：{{movie_detail_1_message.data.movieDetail}}
				</div>
				<br/><br/><br/><br/>
				<el-button class="buy" size="large" @click="dialogTableVisible = true;getMovie_Cinema_Schedule(movie_detail_1_message.data.movieId)">立即购票</el-button>
			</div>
			
	  </div> 
	 
	</el-drawer>
	<!-- 电影列表 end -->
	
	
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

		 <p>总价：￥ {{totalFee}}</p>
		 <el-button @click="buytickets()">购买</el-button>
		 
		 
	 </el-dialog>
	<!-- 选位置 start -->
	
	
	<Footer />
	

</template>
<script lang="ts" setup>
	// import Carousel from '../components/Carousel.vue'
	// import List from '../components/List.vue'
	import Header from '../components/Header.vue'	
	import Footer from '../components/Footer.vue'
	import { ref,reactive, inject,onMounted  } from 'vue'
	import { ElMessageBox } from 'element-plus'
	import type { ElDrawer } from 'element-plus'
	
	
	const homemovie_random = reactive({
		data:[]
	});
	//产生四个随机数，让首页的7个电影不一样；
	for(var i = 0; i < 7 ; i++){
		homemovie_random.data[i] = Math.floor(Math.random()*20);
		for(var j = 0; i < j ; i++){
			if(homemovie_random.data[i] == homemovie_random.data[j]){
				i = i - 1;
			}
		}
	}
	console.log(homemovie_random.data)
	
	
	const homemivie0 = homemovie_random.data[0];
	const homemivie1 = homemovie_random.data[1];
	const homemivie2 = homemovie_random.data[2];
	const homemivie3 = homemovie_random.data[3];
	const homemivie4 = homemovie_random.data[4];
	const homemivie5 = homemovie_random.data[5];
	const homemivie6 = homemovie_random.data[6];
	/* 走马灯start */
	const carousel = reactive([
		{name: "one", src: new URL("../img/carousel/"+ homemivie1 +".png", import.meta.url), title: "one"},
		{name: "tow", src: new URL("../img/carousel/"+ homemivie2 +".png", import.meta.url), title: "two"},
		{name: "three", src: new URL("../img/carousel/"+ homemivie3 +".png", import.meta.url), title: "three"},
		{name: "four", src: new URL("../img/carousel/"+ homemivie4 +".png", import.meta.url), title: "four"},
		{name: "five", src: new URL("../img/carousel/"+ homemivie5 +".png", import.meta.url), title: "five"},
		{name: "six", src: new URL("../img/carousel/"+ homemivie6 +".png", import.meta.url), title: "six"},
		{name: "six", src: new URL("../img/carousel/"+ homemivie0 +".png", import.meta.url), title: "six"}
	])
	/* 走马灯end */
	
	
	/**
	 * 列表start
	 */
	const formLabelWidth = '80px'
	let timer
	
	const table = ref(false)
	const dialog = ref(false)
	const loading = ref(false)
	
	const drawerRef = ref<InstanceType<typeof ElDrawer>>()
	const onClick = () => {
	  drawerRef.value!.close()
	}
	
	
	const cancelForm = () => {
	  loading.value = false
	  dialog.value = false
	  clearTimeout(timer)
	}
	
	
	//存放 已上映 
	const movie_detail_1 = reactive({
		data:[]
	});
	//存放四个随机数
	const movie_random = reactive({
		data:[]
	});
	
	//接收main.js的axios对象
	const $axios = inject('$axios');
	$axios.get('http://localhost:89/movie').then((resp) => {
		for(var i = 0; i < resp.data.data.length; i++){
			if(resp.data.data[i].movieStatus == 1){
				movie_detail_1.data.push(resp.data.data[i]);
			}
		}
		//产生四个随机数，让首页的四个电影不一样；
		for(var i = 0; i < 4; i++){
			movie_random.data[i] =  Math.floor(Math.random()*movie_detail_1.data.length);	
			for(var j = 0; j < i; j++){
				if(movie_random.data[i] == movie_random.data[j]){
					i = i - 1;
				}
			}
		}
			
		const movie1 = movie_random.data[1];
		const movie2 = movie_random.data[2];
		const movie3 = movie_random.data[3];
		const movie4 = movie_random.data[4];
		
		console.log(movie_detail_1.data)
	}).catch((err) => {
		console.log(err)
	});
	
	
	
	/*获取影片信息 end*/
	/* 查看电影详情 start*/
	// 存一条记录
	const movie_detail_1_message = reactive({
		data:[]
	});

	const checkdetail = (movieId) => {
		$axios.get('http://localhost:89/movie/'+movieId).then((resp) => {
			 	movie_detail_1_message.data = resp.data.data
				console.log(movie_detail_1_message.data)
			 }).catch((err) => {
			 	console.log(err)
			 });
	}
	/* 查看电影详情 end*/
	/**
	 * 列表end 
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
	
	//选位置的窗口
	const ChooseSeat = ref(false);
	//被选中的影院的索引
	const Index = ref(0);
	const orderMessage = reactive({
		//数据名字要和后端的实体类一样		
		userId: "",
		scheduleId: "",
		seatId: [],
		orderCreatetime: "",
	});
	//获取某行某的序号
	const select = (index) => {
		Index.value = index;
		console.log("安排的序号",Index.value);
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
	//控制台弹出已选的座位  elementuiplus自带的方法，选中或取消选中会执行
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
	//发送 选好的订单
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
		}else{
			 alert("请先选好座位，再购买!!");
		}
	}
	
	/**
	 * 购票  end
	 */
</script>


<style scoped>
	/* 走马灯 start */
	.el-carousel__item h3 {
	  color: #475669;
	  font-size: 14px;
	  opacity: 0.75;
	  line-height: 200px;
	  margin: 0;
	  text-align: center;
	}
	
	.el-carousel__item:nth-child(2n) {
	  background-color: #99a9bf;
	}
	
	.el-carousel__item:nth-child(2n + 1) {
	  background-color: #d3dce6;
	}
	.el-carousel{
		padding-top: 2%;
		width: 1000px;
		margin: 0 auto;
	}
	.carouselImg{
		width: 500px;
		height: 300px;
		border-radius: 15px;
	}
	/* 走马灯 end */
	/* 列表 start*/
	/* ElmentUI-PLUS */
		
	/* 自定义 */
		li{
			float: left;
			margin-left: 20px;
		}
		h2{
			margin-left: -900px;
		}
		img{
			width: 180px;
			height: 260px;
			border-radius: 10px;
		}
		li a{
			font-size: 15px;
		}
		.ListContext{
			margin: 0 auto;	
			width: 1000px;
		}	
		.jiange{
			margin-top: 20px;
			height: 400px;
		}
		.img_list li{
			width: 225px;
		}
		.img_list li:first-child{
			margin-left: 0px;
		}
		.ListContext div:last-child{
			margin-bottom: 0px;
		}
		.more{
			display: block;
			float: right;
			margin-top: -25px;
		}
		.imgdialog{
			
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
		.buy{
			/* margin-left: 130px;
			margin-top: -35px; */
			
		}
		/* 列表 end*/
</style>
