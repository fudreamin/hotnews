import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import axios from 'axios';
import echarts from 'echarts'
import dataV from '@jiaminghi/data-view'
Vue.prototype.$echarts = echarts
Vue.prototype.$axios = axios;
Vue.config.productionTip = false
Vue.use(dataV)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
