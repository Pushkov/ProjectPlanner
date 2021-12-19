<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="secondary">
            <b-collapse id="nav-collapse" is-nav>

                <b-navbar-nav class="ml-auto mr-auto">
                    <b-nav-text>{{ $t('list.login') }}</b-nav-text>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'NavigationBarBlank',
  props: {},
  methods: {
    login() {
      const {username, password} = this;
      console.log('Username: ' + username + ' Password: ' + password);
      return axios.post('http://localhost:8080/api/login', {username, password})
          .then(response => {
            return response;
          })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
