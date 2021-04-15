import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import axios from 'axios'
import './assets/ali_icon/iconfont.css'

// 修改axios的baseUrl，这样axios就不用每次都在请求中填写 VUE_APP_SERVER  了
axios.defaults.baseURL=process.env.VUE_APP_SERVER;

const app = createApp(App)
app.use(store)
    .use(router)
    .use(ElementPlus)
    .mount('#app')
