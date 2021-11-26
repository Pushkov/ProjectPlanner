import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/axios'
import './plugins/bootstrap-vue'
import App from './App.vue'
import router from './router'
import store from './vuex/store'

import MenuIcon from 'vue-material-design-icons/Menu.vue';
import FaceIcon from 'vue-material-design-icons/FaceMan.vue';
import PlusIcon from 'vue-material-design-icons/PlusCircle.vue';
import MinusIcon from 'vue-material-design-icons/MinusCircle.vue';

Vue.component('menu-icon', MenuIcon);
Vue.component('face-icon', FaceIcon);
Vue.component('plus-icon', PlusIcon);
Vue.component('minus-icon', MinusIcon);


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#app')
