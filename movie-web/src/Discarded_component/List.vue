<template>
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
						<a><img src="../img/list/5.png" @click="dialog = true"/></a>
						<br>
						<span>撒大都市</span>
					</li>
					<li>
						<a><img src="../img/list/5.png" @click="dialog = true"/></a>
						<br>
						<span>撒大都市</span>
					</li>
					<li>
						<a><img src="../img/list/3.png" @click="dialog = true"/></a>
						<br>
						<span>撒大都市</span>
					</li>
					<li>
						<a><img src="../img/list/4.png" @click="dialog = true"/></a>
						<br>
						<span>撒大都市</span>
					</li>
				</ul>
			</div>	
		</div>
	
	</div>
	
	
	<!-- dialog -->
	<el-drawer
	  ref="drawerRef"
	  v-model="dialog"
	  title=""
	  :before-close="handleClose"
	  direction="ltr"
	  custom-class="demo-drawer"
	>
	  <div class="demo-drawer__content">
	   
			<img class="imgdialog" :src="urlList[1]"/>
			<el-button class="buy" size="large">立即购票</el-button>
			<div class="filmmessage">
				<div class="moviename">
					{{movie_detail.name[1]}}
				</div>
				<div>
					导演：{{movie_detail.director[1]}}
				</div>
				<div>
					演员：{{movie_detail.actor[1]}}
				</div>
				<div>
					类型：{{movie_detail.description[1]}}
				</div>
				<div>
					上映时间：{{movie_detail.uptime[1]}}
				</div>
				<div>
					时长：{{movie_detail.duration[1]}}分钟
				</div>
				<div>
					详情：{{movie_detail.detail[1]}}
				</div>
			</div>
	  </div> 
	</el-drawer>
	
	
</template>



<script lang="ts" setup>
	import { ref,reactive, inject } from 'vue'
	import { ElMessageBox } from 'element-plus'
	import type { ElDrawer } from 'element-plus'
	
	const formLabelWidth = '80px'
	let timer
	
	const table = ref(false)
	const dialog = ref(false)
	const loading = ref(false)
	
	const drawerRef = ref<InstanceType<typeof ElDrawer>>()
	const onClick = () => {
	  drawerRef.value!.close()
	}
	
	const handleClose = (done) => {
	  if (loading.value) {
	    return
	  }
	  ElMessageBox.confirm('是否退出?')
	    .then(() => {
	      loading.value = true
	      timer = setTimeout(() => {
	        done()
	        // 动画关闭需要一定的时间
	        setTimeout(() => {
	          loading.value = false
	        }, 400)
	      }, 1)
	    })
	    .catch(() => {
	      // catch error
	    })
	}
	
	const cancelForm = () => {
	  loading.value = false
	  dialog.value = false
	  clearTimeout(timer)
	}
	
	/**
	 * 获取影片信息 start
	 */
	const urlList = [
		"http://localhost:3000/src/img/filmlist/filmfengmian.png",
		"http://localhost:3000/src/img/filmlist/黎明遇见你.png",
	]
	const movie_detail = reactive({
		name: [
			"情书",
			"霸王别姬",
		],
		director: [
			"岩井俊二",
			"陈凯歌",
		],
		actor: [
			"xxxxxxx, xxxxxxx",
			"张丰yi/，张国荣",
		],
		description: [
			"喜剧/ 爱情",
			"喜剧/ 爱情",
		],
		uptime: [
			"2022-05-07",
			"2022-05-07",
		],
		detail: [
			"患有社交恐惧症的屌丝郝大毅与富家女杜鹃相遇，这对欢喜冤家在经历诸多事情后，开始相爱，然而这时，三年前，曾盗走郝大毅的心血的人突然出现，给他们的感情蒙上了不确定……",
			"患有社交恐惧症的屌丝郝大毅与富家女杜鹃相遇，这对欢喜冤家在经历诸多事情后，开始相爱，然而这时，三年前，曾盗走郝大毅的心血的人突然出现，给他们的感情蒙上了不确定……",
		],	
		duration: [130, 140],	
	})
	
	//已上映
	const movie_detail_1 = reactive({
		data:[]
	});
	//接收main.js的axios对象
	const $axios = inject('$axios');
	$axios.get('http://localhost:89/movie').then((resp) => {
		for(var i = 0; i < ressp.data.data.length; i++){
			if(resp.data.data[i].movieStatus == 1){
				movie_detail_1.data.push(resp.data.data[i]);
			}
		}
		
		console.log(movie_detail_1.data)
	}).catch((err) => {
		console.log(err)
	});
	
	/**
	 * 获取影片信息 end
	 */
</script>

<style scoped>
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
		border-radius: 8px;
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
		height: 430px;
	}
	.img_list li{
		width: 225px;
	}
	.img_list li:first-child{
		margin-left: 0px;
	}
	.ListContext div:last-child{
		margin-bottom: 150px;
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
		margin-left: 130px;
		margin-top: -35px;
	}
</style>