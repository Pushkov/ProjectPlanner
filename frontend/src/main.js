// import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/axios'
import './plugins/bootstrap-vue'
import App from './App.vue'
import router from './router'
import store from './vuex/store'
import langEn from './i18n/lang/en'
import langRu from './i18n/lang/ru'
import vuexI18n from 'vuex-i18n';

import Vuelidate from 'vuelidate'
import MenuIcon from 'vue-material-design-icons/Menu.vue';
import FaceIcon from 'vue-material-design-icons/FaceMan.vue';
import PlusIcon from 'vue-material-design-icons/PlusCircle.vue';
import MinusIcon from 'vue-material-design-icons/MinusCircle.vue';
import ArrowLeftIcon from 'vue-material-design-icons/ArrowLeft.vue';
import ArrowLeftEndIcon from 'vue-material-design-icons/ArrowCollapseLeft.vue';
import ArrowRightIcon from 'vue-material-design-icons/ArrowRight.vue';
import ArrowRightEndIcon from 'vue-material-design-icons/ArrowCollapseRight.vue';
import Language from 'vue-material-design-icons/Web.vue';
import Search from 'vue-material-design-icons/SearchWeb.vue';
import ArrowUpIcon from 'vue-material-design-icons/ArrowUp.vue';
import ArrowDownIcon from 'vue-material-design-icons/ArrowDown.vue';

import moment from 'moment'

Vue.prototype.moment = moment


Vue.use(Vuelidate)


Vue.use(vuexI18n.plugin, store);

Vue.i18n.add('en', langEn);
Vue.i18n.add('ru', langRu);
Vue.i18n.set('ru')


Vue.use(vuexI18n.plugin, store);

Vue.i18n.add('en', langEn);
Vue.i18n.add('ru', langRu);
Vue.i18n.set('ru')


Vue.component('menu-icon', MenuIcon);
Vue.component('face-icon', FaceIcon);
Vue.component('plus-icon', PlusIcon);
Vue.component('minus-icon', MinusIcon);
Vue.component('arrow-left-icon', ArrowLeftIcon);
Vue.component('arrow-collapse-left-icon', ArrowLeftEndIcon);
Vue.component('arrow-right-icon', ArrowRightIcon);
Vue.component('arrow-collapse-right-icon', ArrowRightEndIcon);
Vue.component('language-icon', Language);
Vue.component('search-icon', Search);
Vue.component('arrow-up-icon', ArrowUpIcon);
Vue.component('arrow-down-icon', ArrowDownIcon);


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#app')
