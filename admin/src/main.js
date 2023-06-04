import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'ant-design-vue/dist/antd.css';
import * as Icons from '@ant-design/icons-vue';
import axios from 'axios';
import './assets/js/enums'; // 将枚举数组放到单独的文件中
//import { notification } from 'ant-design-vue';

const app =createApp(App)
app.use(Antd).use(store).use(router).mount('#app');

//全局使用图标
const icons = Icons;
for (const i in icons){
    app.component(i, icons[i]);
}





axios.defaults.baseURL = process.env.VUE_APP_SERVER;
console.log('环境',process.env.NODE_ENV);
console.log('服务端',process.env.VUE_APP_SERVER);