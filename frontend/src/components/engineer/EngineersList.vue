<template class=" mt-xl-5">
  <div class="w-75 m-auto">

    <BasicModal
        v-if="MODAL_SHOW"
        :popup-title="getEngineerModalTitle"
        :is-edit="MODAL_EDIT"
        @closeModal="closeEngineerModal"
    >
      <EngineerViewModal
          ref="engineerView"
          :item="currentEngineer"
          :is-edit="MODAL_EDIT"
          :roles="ROLES"
          :departments="DEPARTMENTS"
          :statuses="ENGINEER_STATUSES"
          :error="GET_ERROR"
          @returnUser='returnUser'
      />
      <BasicModalFooter
          slot="footer"
          @modalClose="closeEngineerModal"
          @saveItem='saveEngineer'
          @deleteItem="deleteEngineer"
          @editItem="editItem"
          :is-edit="MODAL_EDIT"
          :is-create="MODAL_CREATE"
      />
    </BasicModal>

    <div v-if="ENGINEERS.length > 0">
      <div class="text-left my-3">
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
      ERROR_MESSAGE
    </div>
  </div>
</template>


<script>
import {mapActions, mapGetters} from 'vuex'
import BasicModal from "@/components/modals/BasicModal";
import EngineerTableRow from "@/components/engineer/EngineerTableRow";
import BasicModalFooter from "@/components/modals/BasicModalFooter";
import EngineerViewModal from "@/components/engineer/EngineerViewModal";

export default {
  name: "EngineersList",
  data() {
    return {
      currentEngineer: {},
      isBusy: true,
    }
  },
  components: {
    EngineerViewModal,
    BasicModal,
    BasicModalFooter,
    EngineerTableRow,
  },
  computed: {
    ...mapGetters([
      'IS_BUSY',
      'ENGINEERS',
      'ROLES',
      'DEPARTMENTS',
      'ENGINEER_STATUSES',
      'MODAL_SHOW',
      'MODAL_CREATE',
      'MODAL_EDIT',
      'GET_ERROR',
    ]),
    getEngineerModalTitle() {
      return this.isModalCreate
          ? 'Создать нового инженера'
          : 'Информация о пользователе: ' + this.currentEngineer.lastName + ' ' + this.currentEngineer.firstName;
    }
  },
  methods: {
    ...mapActions([
      'GET_ALL_ROLES',
      'GET_ALL_DEPARTMENTS',
      'GET_ALL_ENGINEER_STATUSES',
      'SET_MODAL_STATE',
      'SET_IS_MODAL_CREATE',
      'SET_IS_MODAL_EDIT',
      'GET_ALL_ENGINEERS',
      'CREATE_ENGINEER',
      'UPDATE_ENGINEER',
      'DELETE_ENGINEER',
      'SET_TABLE_BUSY',
      'SET_ERROR'
    ]),
    editItem(isEdit) {
      if (!isEdit) {
        let eng = this.currentEngineer;
        this.closeEngineerModal();
        this.getEngineerModal(eng);
      }
      this.SET_IS_MODAL_EDIT(isEdit);
    },
    createEngineerModal() {
      this.SET_ERROR({});
      this.currentEngineer = {};
      this.currentEngineer.roleSimpleDto = {};
      this.currentEngineer.departmentSimpleDto = {};
      this.currentEngineer.contactDetails = {};
      this.currentEngineer.status = 'ACTIVE';
      this.SET_MODAL_STATE(true);
      this.SET_IS_MODAL_EDIT(true);
      this.SET_IS_MODAL_CREATE(true);
    },
    getEngineerModal(engineer) {
      this.currentEngineer = engineer;
      this.SET_MODAL_STATE(true);
    },
    closeEngineerModal() {
      this.SET_IS_MODAL_EDIT(false)
      this.SET_MODAL_STATE(false);
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
    },
    deleteEngineer() {
      this.$refs.engineerView.returnUser();
      this.DELETE_ENGINEER(this.currentEngineer);
    }

  },
  mounted() {
    this.SET_ERROR({});
    this.SET_TABLE_BUSY(true);
    this.GET_ALL_ENGINEERS();
    this.GET_ALL_ROLES();
    this.GET_ALL_DEPARTMENTS();
    this.GET_ALL_ENGINEER_STATUSES();
  }
}
</script>

<style scoped>

</style>