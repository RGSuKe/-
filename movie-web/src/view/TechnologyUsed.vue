<template>
	<Header />
	<div class="allcard">
		<div class="innercard">
			<!-- 第一横 start -->
			<el-row :gutter="12">
				<el-col :span="1">
				  <el-card shadow="hover "> 前端 </el-card>
				  <el-card shadow="hover  ">
					<img src="../assets/HTML5_logo.png" class="HTML5style"/>
					<span>HTML5</span>
				  </el-card>
				</el-col>
			   <el-col :span="1">
				 <el-card shadow="always"> 前端 </el-card>
				 <el-card shadow="always">
					 <img src="../assets/CSS3_logo.png" class="CSS5style"/>
					 <span>CSS3</span>
				 </el-card>
			   </el-col>
				<el-col :span="1">
				  <el-card shadow="always"> 前端框架 </el-card>
				  <el-card shadow="always">
					  <img src="../assets/Vue_logo.png" class="Vuestyle"/>
					  <span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Vue</span>
				  </el-card>
				</el-col>
				<el-col :span="1">
				  <el-card shadow="always"> 网络请求库 </el-card>
				  <el-card shadow="always">
					  <img src="../assets/Axios_logo.png" class="Axiosstyle"/>
					  <span>Axios</span>
				  </el-card>
				</el-col>
				<el-col :span="1">
				  <el-card shadow="always"> 包管理工具 </el-card>
				  <el-card shadow="always">
					  <img src="../assets/npm_logo.png" class="npmtyle"/>
					  <span>npm</span>
				  </el-card>
				</el-col>
			  </el-row>
			<!-- 第一横 end -->
			<!-- 第二横 start -->
			<el-row :gutter="12">
				<el-col :span="1">
				  <el-card shadow="always"> UI界面 </el-card>
				  <el-card shadow="always">
					 <img src="../assets/ElementUI-Plus.png" class="ElementUIplusstyle"/>
					  <span>ElementUI-Plus</span>
				  </el-card>
				</el-col>
				<el-col :span="1">
				  <el-card shadow="always"> 后端语言 </el-card>
				  <el-card shadow="Hover ">
					<img src="../assets/Java_logo.png" class="Javastyle"/>
					<span>Java</span>
				  </el-card>
				</el-col>
			   <el-col :span="1">
				 <el-card shadow="always"> 后端框架 </el-card>
				 <el-card shadow="always">
					 <img src="../assets/SpringBoot_logo.png" class="SpringBootstyle"/>
					 <span>SpringBoot</span>
				 </el-card>
			   </el-col>
				<el-col :span="1">
				  <el-card shadow="always"> 后端框架 </el-card>
				  <el-card shadow="always">
					  <img src="../assets/MyBatisPlus_logo.png" class="MyBatisPlusstyle"/>
					  <span>MyBatisPlus</span>
				  </el-card>
				</el-col>
				<el-col :span="1">
				  <el-card shadow="always"> 项目管理工具 </el-card>
				  <el-card shadow="always">
					  <img src="../assets/Maven_logo.png" class="Mavenstyle"/>
					  <span>Maven</span>
				  </el-card>
				</el-col>
				
			  </el-row>
			  <!-- 第二横 end -->
			  <!-- 第三横 start -->
			  <el-row :gutter="12">
				  <el-col :span="1">
					<el-card shadow="always"> 数据库</el-card>
				  <el-card shadow="Hover ">
					<img src="../assets/MySQL_logo.png" class="MySQLstyle"/>
					<span>Vue</span>
				  </el-card>
				  </el-col>
				 <el-col :span="1">
				   <el-card shadow="always"> 缓存数据库 </el-card>
				 <el-card shadow="always">
					 <img src="../assets/Redis_logo.png" class="Redisstyle"/>
					 <span>Redis</span>
				 </el-card>
				 </el-col>
				  
				  <el-col :span="1">
					<el-card shadow="always"> 版本控制工具 </el-card>
					<el-card shadow="always">
						  <!-- <img src="" class=""/> -->
					  <span></span>
					</el-card>
				  </el-col>
				<el-col :span="1">
				  <el-card shadow="always"> 版本控制工具 </el-card>
				  <el-card shadow="always">
					  <img src="../assets/Github_logo.png" class="Githubsstyle"/>
					  <span>Github</span>
				  </el-card>
				</el-col>
				<el-col :span="2">
				  <el-card shadow="always"> 版本控制工具 </el-card>
				  <el-card shadow="always">
					  <img src="../assets/Git_logo.png" class="Gitstyle"/>
					  <span>Git</span>
				  </el-card>
				</el-col>
			  </el-row>
				<!-- 第三横 end -->
		</div>
		
		</div>	
	<Footer />
</template>

<script setup>
	import Header from '../components/Header.vue'
	import Footer from '../components/Footer.vue'

</script>

<style scoped>
/* ElementUI-plus */
	.text {
	  font-size: 14px;
	}

	.item {
	  padding: 18px 0;
	}

	.box-card {
	  width: 480px;
	}
	
/* 自定义 */
	img{
		width: 200px;
		height: 200px;
	}
	.allcard{
		width: 1600px;
	}
	.innercard{
		margin-left: -70px;
		width: 1600px;
	}
	.innercard .el-row{
		
		
	}
	.el-row{	
		margin: 0 auto;	
		margin-top: 2%;			
	}
	/* 鼠标碰到每张卡片 向上划  start*/
	.el-col{
		/* 此属性开了  卡片就叠不回去 */
		margin-left: 240px;	
		transition: all 0.5s;
	}
	.el-col:hover{
		margin-top: -30px;
	/* 	margin-right: 100px; */
	}
	/* 鼠标碰到每张卡片 向上划  end*/
	.el-card:nth-child(1){
		width: 300px;			
	}
	 .el-card:nth-child(2){
		width: 300px;
		height: 300px;	
	}
	.el-row:last-child{
		padding-bottom: 20px;
	}
	.HTML5style{
		width: 260px;
	}
	.CSS5style{
		width: 340px;
		height: 230px;
		margin-top: -30px;
		margin-left: 10px;
	}
	.Vuestyle{
		margin-left: 25px;
	}
	.Axiosstyle{
		width: 300px;
	}
	.npmtyle{
		width: 270px;
	}
	.ElementUIplusstyle{
		width: 320px;
		margin-left: -20px;
	}
	.Javastyle{
		width: 260px;
	}
	.SpringBootstyle{
		width: 280px;
	}
	.MyBatisPlusstyle{
		width: 210px;
	}
	.Mavenstyle{
		width: 280px;
	}
	.MySQLstyle{
		width: 240px;
	}
	.Redisstyle{
		width: 260px;
	}
	.Githubsstyle{
		width: 260px;
	}
	.Gitstyle{
		width: 240px;
	}
</style>

