import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/axios'
import './plugins/bootstrap-vue'
import App from './App.vue'
import router from './router'
import store from './vuex/store'


import MenuIcon from 'vue-material-design-icons/Menu.vue';
import PlusIcon from 'vue-material-design-icons/Plus.vue';

Vue.component('menu-icon', MenuIcon);
Vue.component('plus-icon', PlusIcon);


new Vue({
  router,
  render: h => h(App),
  store

}).$mount('#app')
