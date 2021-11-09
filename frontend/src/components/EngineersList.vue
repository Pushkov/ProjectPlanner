<template class=" mt-xl-5">
    <div class="w-75 m-auto">

      <EngineerModal
          v-if="isModal"
          :popup-title="getEngineerModalTitle"
          :is-edit="isModalEdit"
          @closeUserModal="closeEngineerModal"
      >
        <EngineerViewModal
            ref="engineerView"
            :item="currentEngineer"
            :is-edit="isModalEdit"
            @returnUser='returnUser'
        />
        <EngineerFooterModal
            slot="footer"
            @modalClose="closeEngineerModal"
            @saveUser='saveEngineer'
            @deleteUser="deleteEngineer"
            @editItem="editItem"
            :is-edit="isModalEdit"
            :is-create="isModalCreate"
        />
      </EngineerModal>

      <div v-if="ENGINEERS.length > 0">
        <div class="text-left my-3">
          <b-button @click="updateEngineers" class="mr-1"><h5 class="m-auto">Обновить</h5></b-button>
          <b-button @click="createEngineerModal"><h5 class="m-auto">Создать</h5></b-button>
        </div>

        <table class="table table-hover table-bordered table-striped">
          <thead class="thead-light">
          <tr>
            <th>Id</th>
            <th>Имя</th>
            <th>Фамилия</th>
          </tr>
          </thead>
          <tbody>
          <EngineerTableRow
              v-for="engineer of ENGINEERS"
              :engineer="engineer"
              :key="engineer.id"
              @showEngineerModal="getEngineerModal(engineer)"
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
import EngineerModal from "@/components/modals/EngineerModal";
import EngineerViewModal from "@/components/modals/EngineerViewModal";
import EngineerTableRow from "@/components/EngineerTableRow";
import EngineerFooterModal from "@/components/modals/EngineerFooterModal";

export default {
  name: "EngineersList",
  data() {
    return {
      currentEngineer: {},
      isModal: false,
      isModalEdit: false,
      isModalCreate: false,
      isBusy: true,
      fields: [
        'id',
        'login',
        'firstName',
        'lastName',
        'position'
      ],
    }
  },
  components: {
    EngineerTableRow,
    EngineerViewModal,
    EngineerFooterModal,
    EngineerModal
  },
  computed: {
    ...mapGetters([
      'IS_BUSY',
      'ENGINEERS',
      'ERROR_LIST_LOAIDNG_MESSAGE'
    ]),
    getEngineerModalTitle() {
      return this.isModalCreate
          ? 'Создать нового инженера'
          : 'Информация о пользователе: ' + this.currentEngineer.lastName + ' ' + this.currentEngineer.firstName;
    }
  },
  methods: {
    ...mapActions([
      'GET_ALL_ENGINEERS',
      'CREATE_ENGINEER',
      'UPDATE_ENGINEER',
      'DELETE_ENGINEER',
      'SET_TABLE_BUSY'
    ]),
    editItem(isEdit) {
      if (!isEdit) {
        let eng = this.currentEngineer;
        this.closeEngineerModal();
        this.getEngineerModal(eng);
      }
      this.isModalEdit = isEdit;
    },

    createEngineerModal() {
      this.isModal = true;
      this.isModalEdit = true;
      this.isModalCreate = true;
      this.currentEngineer = {};
    },
    getEngineerModal(engineer) {
      this.isModal = true;
      this.currentEngineer = engineer;
    },
    closeEngineerModal() {
      this.isModal = false;
      this.isModalEdit = false;
      this.isModalCreate = false;
    },
    updateEngineers() {
      this.SET_TABLE_BUSY(true);
      this.GET_ALL_ENGINEERS();
    },
    returnUser(engineer) {
      this.currentEngineer = engineer;
    },
    saveEngineer() {
      this.$refs.engineerView.returnUser();

      if (this.currentEngineer.id === undefined) {
        // console.log('return and save ' + this.currentEngineer.login);
        this.CREATE_ENGINEER(this.currentEngineer);
      } else
        this.UPDATE_ENGINEER(this.currentEngineer);
      this.closeEngineerModal();
    },
    deleteEngineer() {
      this.$refs.engineerView.returnUser();
      this.DELETE_ENGINEER(this.currentEngineer);
      this.closeEngineerModal();
    }

  },
  mounted() {
    this.SET_TABLE_BUSY(true);
    this.GET_ALL_ENGINEERS();
  }
}
</script>

<style scoped>

</style>