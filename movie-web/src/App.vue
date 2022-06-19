<script setup>
// This starter template is using Vue 3 <script setup> SFCs
// Check out https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup

// import Header from './components/Header.vue'
// import Footer from './components/Footer.vue'
// import Carousel from './components/Carousel.vue'
// import List from './components/List.vue'

// import Login  from  './view/Login.vue'
// import Register  from  './view/Register.vue'
// import AboutMe from './view/AboutMe.vue'
import HomePage from './view/HomePage.vue'
</script>

<template>
		
	<router-view />
	
</template>

<style>
	@import url("./assets/font/font.css");
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  
 /* 使用自定义字体
 font-family: 'boyangliuti';
  font-weight: 1000; */
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
body{
	font-family: "microsoft yahei",Arial,Helvetica,sans-serif,"宋体";
	}
</style>
