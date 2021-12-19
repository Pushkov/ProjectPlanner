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
        <form class="form-inline justify-content-center" v-on:submit.prevent="auth_login">

          <label class="mr-lg-2">Пользователь:</label>
          <input type="text" v-model="login" placeholder="ЛОГИН" class="form-control mr-lg-2" :class="dangerclass">
          <label class="mr-lg-2">Пароль:</label>
          <input type="password" v-model="password" placeholder="ПАРОЛЬ" class="form-control mr-lg2-2"
                 :class="dangerclass">

          <button class="btn btn-secondary ml-lg-2">{{ $t('button.submit') }}</button>
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
    test_login() {
      console.log('*****');

    },

    auth_login() {
      AXIOS.post('/auth/login', {'login': this.login, 'password': this.password})
          .then(responce => {
                this.AUTH_LOGIN({
                  'token': responce.data.token,
                  'position': responce.data.position,
                  'login': responce.data.login,
                  'locale': responce.data.locale,
                  'id': responce.data.id
                })
                if (localStorage.getItem('path-to') && localStorage.getItem('path-to') !== '/planer/auth/login') {
                  const url = localStorage.getItem('path-to');
                  localStorage.removeItem('path-to');
                  console.log('url ' + url)
                  this.$router.replace(url)
                } else {
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
    auth_login_form() {
      AXIOS.post('/auth/login', {'login': this.login, 'password': this.password},
          {
            headers: {
              'Api-Secret': this.password,
              'Api-Key': this.login,
            }
          }
      )
          .then(() => {
                console.log('ok')
              }
          )
          .catch(() => {
            console.log(' error')
          })
    },

  },
  mounted() {

  }
}
</script>
