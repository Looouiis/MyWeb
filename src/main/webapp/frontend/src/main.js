import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import axios from './interceptor'
import VueAxios from 'vue-axios';
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store'

createApp(App).use(store).use(router).use(ElementPlus).use(VueAxios,axios).mount('#app')
