import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router/index.js'
import axios from 'axios'
import locale from 'element-plus/lib/locale/lang/zh-cn'

const app = createApp(App)


app.use(ElementPlus, {locale})
app.use(router)
app.mount('#app')
app.provide('$axios', axios)

axios.interceptors.request.use(

    config => {
      if (localStorage.getItem('token') != null && localStorage.getItem('token') !== '') {
        config.headers.token = localStorage.getItem('token'); // 让每个请求携带自定义token
      }
	  console.log('请求参数：', config);
      return config;
    },
    error => {
      console.log(error);
    }
	
)