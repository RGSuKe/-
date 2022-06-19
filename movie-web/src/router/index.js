import { createRouter, createWebHashHistory } from "vue-router"
// import Login  from  '../view/Login.vue'
// import Register  from  '../view/Register.vue'
// import AboutMe from '../view/AboutMe.vue'
// import HomePage from '../view/HomePage.vue'

// 配置路由映射 
const routes = [
	// { path: '/HomePage', component: HomePage},
	// { path: '/Login', component: Login },
	// { path: '/Register', component: Register },
	//{ path: '/AboutMe', component: AboutMe },
	//弃用组件
	// { path: '/Carousel', name: 'Carousel', component: () => import('../Discarded_component/Carousel.vue') },
	// { path: '/FilmPhoto', name: 'FilmPhoto', component: () => import('../Discarded_component/FilmPhoto.vue') },
	// { path: '/List', name: 'List', component: () => import('../Discarded_component/List.vue') },
	// { path: '/Rank', name: 'Rank', component: () => import('../Discarded_component/Rank.vue') },
	
	
	//主页
	{ path: '/', redirect: { name: 'Login' }},
	
	//用户
	// { path: '/HomePage', name: 'HomePage', component: () => import('../view/HomePage.vue')},
	// { path: '/Film', name: 'Film', component: () =>import('../view/Film.vue')},
	// { path: '/Login', name: 'Login', component: () => import('../view/Login.vue') },
	// { path: '/Register', name: 'Register', component: () => import('../view/Register.vue') },
	// { path: '/Cinema', name: 'Cinema', component: () => import('../view/Cinema.vue') },
		
	{ path: '/HomePage', name: 'HomePage', component: () => import('../view/user/HomePage.vue')},
	{ path: '/Film', name: 'Film', component: () =>import('../view/user/Film.vue')},
	{ path: '/Login', name: 'Login', component: () => import('../view/user/Login.vue') },
	{ path: '/Register', name: 'Register', component: () => import('../view/user/Register.vue') },
	{ path: '/Cinema', name: 'Cinema', component: () => import('../view/user/Cinema.vue') },
	
	//通用组件
	// { path: '/AboutMe', name: 'AboutMe', component: () => import('../view/AboutMe.vue') },
	// { path: '/TechnologyUsed', name: 'TechnologyUsed', component: () => import('../view/TechnologyUsed.vue') },
	// { path: '/HelpCenter', name: 'HelpCenter', component: () => import('../view/HelpCenter.vue') },
	// { path: '/Partners', name: 'Partners', component: () => import('../view/Partners.vue') },
	
	{ path: '/AboutMe', name: 'AboutMe', component: () => import('../components/footer/AboutMe.vue') },
	{ path: '/TechnologyUsed', name: 'TechnologyUsed', component: () => import('../components/footer/TechnologyUsed.vue') },
	{ path: '/HelpCenter', name: 'HelpCenter', component: () => import('../components/footer/HelpCenter.vue') },
	{ path: '/Partners', name: 'Partners', component: () => import('../components/footer/Partners.vue') },
	
	
	//影院方
	{ path: '/CinemaLogin', name: 'CinemaLogin', component: () => import('../view/cinemaer/CinemaLogin.vue') },
	{ path: '/CinemaManage', name: 'CinemaManage', component: () => import('../view/cinemaer/CinemaManage.vue'),
		children: [
			{path: '/CinemaAside', name: 'CinemaAside', component: () => import('../view/cinemaer/CinemaAside.vue'),},
			{path: '/ScheduleList', name:'ScheduleList', component: () => import('../view/cinemaer/ScheduleList.vue'),},
			{path: '/MovieList', name: 'MovieList', component: () => import('../view/cinemaer/MovieList.vue'),},
			{path: '/BuyerList', name: 'BuyerList', component: () => import('../view/cinemaer/BuyerList.vue'),},
		],
	 },
	
]

//创建路由对象
const router = createRouter({
	routes,
	history: createWebHashHistory()
})

export default router
