<template class=" mt-xl-5">
    <div class="w-75 m-auto">

        <BasicModal
                v-if="isModal"
                :popup-title="getRoleModalTitle"
                :is-edit="isModalEdit"
                @closeUserModal="closeModal"
        >
            <BasicModalView
                    ref="engineerView"
                    :item="currentRole"
                    :is-edit="isModalEdit"
                    @returnUser='returnItem'
            />
            <BasicModalFooter
                    slot="footer"
                    @modalClose="closeModal"
                    @saveUser='saveItem'
                    @deleteUser="deleteItem"
                    @editItem="editItem"
                    :is-edit="isModalEdit"
                    :is-create="isModalCreate"
            />
        </BasicModal>


        <div v-if="ROLES.length > 0">
            <div class="text-left my-3">
                <!--          <b-button @click="updateEngineers" class="mr-1"><h5 class="m-auto">Обновить</h5></b-button>-->
                <b-button @click="createModal"><h5 class="m-auto">Создать</h5></b-button>
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
                  v-for="role of ROLES"
                  :role="role"
                  :key="role.id"
                  @showModal="showModal(role)"
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
    import BasicModal from "./modals/BasicModal";
    import BasicModalView from "./modals/BasicModalView";
    import BasicModalFooter from "./modals/BasicModalFooter";

    export default {
        name: "RolesList",
        data() {
            return {
                currentRole: {},
                fields: [
                    'id',
                    'name'
                ],

                isModal: false,
                isModalEdit: false,
                isModalCreate: false,

            }
  },
  components: {
      BasicModalFooter,
      BasicModalView,
      BasicModal,
      RoleTableRow
  },
  computed: {
      ...mapGetters([
          'IS_BUSY',
          'ROLES',
          'ERROR_LIST_LOAIDNG_MESSAGE'
      ]),
      getRoleModalTitle() {
          return this.isModalCreate
              ? 'Создать новую должность'
              : 'Информация о должности: ' + this.currentRole.name;
      }

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
      showModal(item) {
          this.isModal = true;
          this.currentRole = item;
      },
      closeModal() {
          this.isModal = false;
          this.isModalEdit = false;
          this.isModalCreate = false;
      },
      createModal() {
          this.isModal = true;
          this.isModalEdit = true;
          this.isModalCreate = true;
          this.currentRole = {};
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