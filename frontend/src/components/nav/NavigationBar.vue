<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="secondary">
      <b-navbar-brand>
        <face-icon/>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link to="/planner/index">
              <span class="text-white-50">Поиск</span>
            </router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link to="/planner/overview">
              <span class="text-white-50">Обзор работ</span>
            </router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link to="/planner/workshops">
              <span class="text-white-50">Цеха</span>
            </router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link to="/planner/title">
              <span class="text-white-50">Титульный список</span>
            </router-link>
          </b-nav-item>

          <b-nav-item href="#">
            <router-link to="/planner/plans">
              <span class="text-white-50">Планы</span>
            </router-link>
          </b-nav-item>

          <b-nav-item href="#">
            <router-link to="/planner/projects">
              <span class="text-white-50">Проекты</span>
            </router-link>
          </b-nav-item>

          <b-nav-item href="#">
            <router-link to="/planner/documents">
              <span class="text-white-50">Документы</span>
            </router-link>
          </b-nav-item>

          <b-nav-item href="#">
            <router-link to="/planner/personal">
              <span class="text-white-50">Персонал</span>
            </router-link>
          </b-nav-item>

          <b-nav-item href="#">
            <router-link to="/planner/roles">
              <span class="text-white-50">Должности</span>
            </router-link>
          </b-nav-item>
          <b-nav-item href="#">
            <router-link to="/planner/permissions">
              <span class="text-white-50">Разрешения</span>
            </router-link>
          </b-nav-item>
        </b-navbar-nav>


        <b-form-select
            v-model="currentLocale"
            class="ml-auto w-auto text-secondary rounded-lg"
            value-field="value"
            text-field="value"
            disabled-field="notEnabled"
            @change="setLocale"
        >
          <b-form-select-option :value="'ru'">RU</b-form-select-option>
          <b-form-select-option :value="'en'">EN</b-form-select-option>
        </b-form-select>
        <b-dropdown text="Меню" right variant="outline-light" class="ml-auto">
          <b-dropdown-item>
            <router-link to="/planner/index">
              Стартовая
            </router-link>
          </b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-item>
            <router-link to="/planner/admin">
              Admin
            </router-link>
          </b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-item>
            <router-link to="/planner/about">
              About
            </router-link>
          </b-dropdown-item>
          <b-dropdown-item>
            <router-link to="/planner/logout">
              LogOut
            </router-link>
          </b-dropdown-item>
        </b-dropdown>
      </b-collapse>

    </b-navbar>
  </div>
</template>

<script>

import {mapActions, mapGetters} from "vuex";

export default {
  name: "NavigationBar",

  data() {
    return {
      currentLocale: ''
    }
  },

  components: {},
  computed: {
    ...mapGetters([
      'GET_LOCALE',
    ]),
  },

  methods: {
    ...mapActions([
      'SET_APPLICATION_LOCALE',

    ]),
    setLocale() {
      this.SET_APPLICATION_LOCALE(this.currentLocale)
    },
    setUp() {
      this.currentLocale = this.GET_LOCALE;
      this.SET_APPLICATION_LOCALE(this.currentLocale)
    }
  },
  mounted() {
    this.setUp();
  }
}
</script>

<style scoped>

</style>