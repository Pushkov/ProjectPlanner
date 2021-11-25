<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="secondary">
      <b-navbar-brand>
        <face-icon/>
        <!--        <i class="material-icons">face</i>-->
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">
            <router-link to="/planner/index">
              <span class="text-white-50">Главная</span>
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

        <b-dropdown text="Меню" right variant="outline-light" class="ml-auto col-xl-1">
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
            LogOut
          </b-dropdown-item>
        </b-dropdown>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import FaceIcon from 'vue-material-design-icons/FaceMan.vue';

export default {
  name: "NavigationBar",
  components: {
    FaceIcon
  }
}
</script>

<style scoped>

</style>