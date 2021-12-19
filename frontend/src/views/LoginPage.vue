<template>
  <div class="h-screen bg-yellow-200">
    <NavigationBarBlank/>
    <div class="container" style="margin-top: 10%">
      <div class="jumbotron" :class="dangerclass">
        <div class="text-center m-auto w-100">
          <span v-if="dangerclass"
                class="text-danger"
          >{{ $t('list.error_login') }}</span>
        </div>
        <br/>
        <form class="form-inline justify-content-center">

          <label class="mr-lg-2">Пользователь:</label>
          <input type="text" v-model="login" placeholder="ЛОГИН" class="form-control mr-lg-2" :class="dangerclass">
          <label class="mr-lg-2">Пароль:</label>
          <input type="password" v-model="password" placeholder="ПАРОЛЬ" class="form-control mr-lg2-2"
                 :class="dangerclass">
          <button type="submit" class="btn btn-secondary ml-lg-2" @click="auth_login">
            <router-link to="/planner/index">
              Вход
            </router-link>
          </button>
        </form>

      </div>
    </div>
  </div>
</template>

<script>
import {AXIOS} from "@/vuex/axios-export";
import NavigationBarBlank from "../components/nav/NavigationBarBlank";
import {mapActions, mapGetters} from 'vuex';
import router from "@/router";


export default {
  name: "LoginPage",
  data() {
    return {
      login: '',
      password: ''
    }
  },
  computed: {
    ...mapGetters([
      'isAuthenticated',
      'authStatus',
      'getUserToken'
    ]),
    dangerclass() {
      return this.authStatus === 'error' ? 'border border-danger' : '';
    }
  },
  components: {NavigationBarBlank},
  methods: {
    ...mapActions([
      'AUTH_LOGIN',
      'AUTH_ERROR'
    ]),


    auth_login() {

      AXIOS.post('/auth/login', {'login': this.login, 'password': this.password})
          .then(responce => {
                this.AUTH_LOGIN({
                  'token': responce.data.token,
                  'position': responce.data.position,
                  'login': responce.data.login,
                  'locale': responce.data.locale
                })
                console.log('reasponce ' + responce.data);
                if (localStorage.getItem('path-to') && localStorage.getItem('path-to') !== '/planer/auth/login') {
                  const url = localStorage.getItem('path-to');
                  localStorage.removeItem('path-to');
                  console.log('redirect url ' + url);
                  // this.$router.push(url).catch(()=>{})
                  this.$router.replace(url)
                } else {
                  console.log('tipa auth ');
                  localStorage.removeItem('path-to');
                  router.push('/');
                }
              }
          )
          .catch(() => {
            this.AUTH_ERROR();
            localStorage.removeItem('user-token');
          })
    },

  }
}
</script>
