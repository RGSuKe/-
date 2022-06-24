 <template>
	<Header />
	 <div class="demo-image__placeholder">
		 <!-- 1111111111111111111111111111111111111111-->
	    <div class="block">
	      <span class="demonstration " >霸王别姬</span>
	      <el-image :preview-src-list="srcList1" :src="srcList1[1]" />
	      <el-image :preview-src-list="srcList1" :src="srcList1[2]" />
	      <el-image :preview-src-list="srcList1" :src="srcList1[3]" />
	      <el-image :preview-src-list="srcList1" :src="srcList1[4]" />
		  <el-image :preview-src-list="srcList1" :src="srcList1[1]" />
		  <el-image :preview-src-list="srcList1" :src="srcList1[2]" />
		  <el-image :preview-src-list="srcList1" :src="srcList1[3]" />
		  <el-image :preview-src-list="srcList1" :src="srcList1[4]" />
	    </div>
	    <div class="block">
	      <span class="demonstration">霸王别姬</span>
	      <!-- <el-image :src="src">
	        <template #placeholder>
	          <div class="image-slot">Loading<span class="dot">...</span></div>
	        </template>
	      </el-image> -->
		  
		 <div class="rightphoto">
			 <el-carousel :interval="4000" type="card" height="300px" >
		 		<el-carousel-item v-for="item in carousel" :key="item">
		 			<!-- <img :src="item.src" /> -->
					<el-image :src="item.src" :preview-src-list="carousel_big" class="el-image-right"/>
		 		</el-carousel-item>
		  </el-carousel>
		 </div> 
	    </div>
		<!-- 1111111111111111111111111111111111111111-->
		 <el-divider />
		
	  </div>
	<Footer />
</template>

<script setup>
	import Header from '../components/Header.vue'
	import Footer from '../components/Footer.vue'
	import {reactive,ref } from 'vue'
	
	/* 走马灯 */
	const carousel = reactive([
		{name: "one", src: new URL("../img/carousel/1.png", import.meta.url), title: "one"},
		{name: "tow", src: new URL("../img/carousel/2.png", import.meta.url), title: "two"},
		{name: "three", src: new URL("../img/carousel/3.png", import.meta.url), title: "three"},
		{name: "four", src: new URL("../img/carousel/4.png", import.meta.url), title: "four"},
		{name: "five", src: new URL("../img/carousel/5.png", import.meta.url), title: "five"},
		{name: "six", src: new URL("../img/carousel/6.png", import.meta.url), title: "six"},
	])
	
	const carousel_big = [
				'http://localhost:3000/src/img/carousel/1.png',
				'http://localhost:3000/src/img/carousel/2.png',
				'http://localhost:3000/src/img/carousel/3.png',
				'http://localhost:3000/src/img/carousel/4.png',
				'http://localhost:3000/src/img/carousel/5.png',
				'http://localhost:3000/src/img/carousel/6.png',
	]
	  
	  const srcList1 = [
			'http://localhost:3000/src/img/filmphoto/1-1.png',
			'http://localhost:3000/src/img/filmphoto/1-2.png',
			'http://localhost:3000/src/img/filmphoto/1-3.png',
			'http://localhost:3000/src/img/filmphoto/1-4.png',
			
	  ]
	 
	
</script>

<style scoped>
	/* elementUI-PLus */
	.demo-image__placeholder .block {
	  padding: 30px 0;
	  text-align: center;
	  border-right: solid 1px var(--el-border-color);
	  display: inline-block;
	  width: 49%;
	  box-sizing: border-box;
	  vertical-align: top;
	  margin: 0 auto;
	  padding-top: 40px;
	  padding-bottom: 40px;
	}
	.demo-image__placeholder .demonstration {
	  display: block;
	  color: var(--el-text-color-secondary);
	  font-size: 14px;
	  margin-bottom: 20px;
	}
	.demo-image__placeholder .el-image {
	  padding: 0 5px;
	  max-width: 300px;
	  max-height: 200px;
	}

	.demo-image__placeholder.image-slot {
	  display: flex;
	  justify-content: center;
	  align-items: center;
	  width: 100%;
	  height: 100%;
	  background: var(--el-fill-color-light);
	  color: var(--el-text-color-secondary);
	  font-size: 14px;
	}
	.demo-image__placeholder .dot {
	  animation: dot 2s infinite steps(3, start);
	  overflow: hidden;
	}
	
	.demo-image__error .image-slot {
	  font-size: 30px;
	}
	.demo-image__error .image-slot .el-icon {
	  font-size: 30px;
	}
	.demo-image__error .el-image {
	  width: 100%;
	  height: 200px;
	}
	/* 自定义 */
	.rightphoto{
		margin-left: 10px;
		margin-right: 10px;
	}
	.imagesize2{
		width: 280px;
		height: 200px;
	}
	.el-image{
		width: 200px;
		height: 150px;
		margin-top: 5px;
	}
	.el-image-right{
		width: 200px;
		height: 150px;
	}
</style>
