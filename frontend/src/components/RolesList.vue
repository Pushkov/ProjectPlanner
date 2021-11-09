<template class=" mt-xl-5">
    <div class="w-75 m-auto">

      <div v-if="ROLES.length > 0">
        <div class="text-left my-3">
          <b-button @click="updateEngineers" class="mr-1"><h5 class="m-auto">Обновить</h5></b-button>
          <b-button @click="createEngineerModal"><h5 class="m-auto">Создать</h5></b-button>
        </div>

        <table class="table table-hover table-bordered table-striped">
          <thead class="thead-light">
          <tr>
            <th>Id</th>
            <th>Наименование должности</th>
            <th>Разрешения должности</th>
          </tr>
          </thead>
          <tbody>
          <RoleTableRow
              v-for="r of ROLES"
              :role="r"
              :key="r.id"
          />
          </tbody>
        </table>
      </div>
      <div v-else>
        {{ ERROR_LIST_LOAIDNG_MESSAGE }}
      </div>
    </div>
</template>


<script>
import {mapActions, mapGetters} from 'vuex'
import RoleTableRow from "@/components/RoleTableRow";

export default {
  name: "RolesList",
  data() {
    return {
      currentRole: {},
      fields: [
        'id',
        'name'
      ],
    }
  },
  components: {
    RoleTableRow
  },
  computed: {
    ...mapGetters([
      'IS_BUSY',
      'ROLES',
      'ERROR_LIST_LOAIDNG_MESSAGE'
    ]),
  },
  methods: {
    ...mapActions([
      'GET_ALL_ROLES',
      'CREATE_ROLE',
      'UPDATE_ROLE',
      'DELETE_ROLE',
      'SET_TABLE_BUSY'
    ]),
    returnUser(engineer) {
      this.currentRole = engineer;
    },
  },
  mounted() {
    this.SET_TABLE_BUSY(true);
    this.GET_ALL_ROLES();
  }
}
</script>

<style scoped>

</style>