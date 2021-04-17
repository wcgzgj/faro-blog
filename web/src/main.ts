import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import axios from 'axios'
import VueMarkdownIt from 'vue3-markdown-it';
import './assets/ali_icon/iconfont.css'

import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';


import '@kangc/v-md-editor/lib/style/preview.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
import createTipPlugin from '@kangc/v-md-editor/lib/plugins/tip/index';

import '@kangc/v-md-editor/lib/style/base-editor.css';
import '@kangc/v-md-editor/lib/theme/style/github.css';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
import '@kangc/v-md-editor/lib/plugins/tip/tip.css';

// 按需引入 highlightjs 的语言包，此处以 json 为例
import json from 'highlight.js/lib/languages/json';
import java from 'highlight.js/lib/languages/java';
import {Tool} from "@/util/tool";


// 修改axios的baseUrl，这样axios就不用每次都在请求中填写 VUE_APP_SERVER  了
axios.defaults.baseURL=process.env.VUE_APP_SERVER;

VueMarkdownEditor.use(githubTheme,{
    extend(md, hljs) {
        // md为 markdown-it 实例，可以在此处进行修改配置,并使用 plugin 进行语法扩展
        // md.set(option).use(plugin);
        // 注册语言包
        hljs.registerLanguage('json', json);
        hljs.registerLanguage('java', java);
    }
});
VueMarkdownEditor.use(createLineNumbertPlugin());
VueMarkdownEditor.use(createCopyCodePlugin());
VueMarkdownEditor.use(createTipPlugin());




/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log('请求参数：', config);
    const token = store.state.user.token;
    if (Tool.isNotEmpty(token)) {
        config.headers.token = token;
        console.log("请求headers增加token:", token);
    }
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log('返回结果：', response);
    return response;
}, error => {
    console.log('返回错误：', error);
    const response = error.response;
    const status = response.status;
    if (status === 401) {
        // 判断状态码是401 跳转到首页或登录页
        console.log("未登录，跳到首页");
        store.commit("setUser", {});
        // message.error("未登录或登录超时");
        router.push('/');
    }
    return Promise.reject(error);
});




const app = createApp(App)
app.use(store)
    .use(router)
    .use(ElementPlus)
    .use(VueMarkdownIt)
    .use(VueMarkdownEditor)
    .mount('#app')


