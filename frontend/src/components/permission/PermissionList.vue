<template class=" mt-xl-5">
  <div class="w-75 m-auto">

    <BasicModal
        v-if="MODAL_SHOW"
        :popup-title="getPermissionModalTitle"
        :is-edit="MODAL_EDIT"
        @closeModal="closeModal"
    >
      <PermissionModalView
          ref="permissionView"
          :item="currentPermission"
          :is-edit="MODAL_EDIT"
          :error="GET_ERROR"
          @returnItem='returnItem'
      />
      <BasicModalFooter
          slot="footer"
          @modalClose="closeModal"
          @saveItem='saveItem'
          @deleteItem="deleteItem"
          @editItem="editItem"
          :is-edit="MODAL_EDIT"
          :is-create="MODAL_CREATE"
      />
    </BasicModal>

    <div class="text-left my-3">
      <b-button @click="createModal"><h5 class="m-auto">Создать</h5></b-button>
    </div>
    <div v-if="MODAL_CREATE">
      <h1>create</h1>
    </div>

    <table class="table table-hover table-bordered table-striped" v-if="permList.length > 0">
      <thead class="thead-light">
      <tr>
        <th colspan="100%">
          Список разрешений
        </th>
      </tr>
      <tr>
        <th>Наименование</th>
        <th>Описание</th>
      </tr>
      </thead>
      <tbody>
      <PermissionRow v-for="perm of permList"
                     :key="perm.name"
                     :permission="perm"
                     @showModal="showModal(perm)"
      />

      </tbody>
    </table>
    <template v-else>
      <div class="row my-1 mx-2">
        <h4 class="m-auto">Разрешений не найдено</h4>
      </div>
    </template>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'

import PermissionRow from "./PermissionRow";
import BasicModalFooter from "../modals/BasicModalFooter";
import BasicModal from "../modals/BasicModal";
import PermissionModalView from "./PermissionModalView";

export default {
  name: "PermissionList",
  data() {
    return {
      currentPermission: {},
    }
  },
  props: {
    permList: {
      type: Array,
      default: () => {
        return []
      }
    },
  },
  computed: {
    ...mapGetters([
      'MODAL_SHOW',
      'MODAL_CREATE',
      'MODAL_EDIT',
      'GET_ERROR'
    ]),
    getPermissionModalTitle() {
      return this.isModalCreate
          ? 'Создать новое разрешение'
          : 'Информация о разрешении: ' + this.currentPermission.name;
    }
  },
  components: {
    PermissionModalView,
    BasicModalFooter,
    BasicModal,
    PermissionRow
  },
  methods: {
    ...mapActions([
      'SET_MODAL_STATE',
      'SET_IS_MODAL_EDIT',
      'SET_IS_MODAL_CREATE',
      'CREATE_PERMISSION',
      'UPDATE_PERMISSION',
      'DELETE_PERMISSION',
      'GET_ALL_PERMISSIONS',
      'SET_ERROR'
    ]),
    returnItem(item) {
      this.currentPermission = item;
    },
    showModal(item) {
      this.currentPermission = item;
      this.SET_MODAL_STATE(true);
    },
    closeModal() {
      this.SET_MODAL_STATE(false);
      this.SET_IS_MODAL_EDIT(false);
      this.SET_IS_MODAL_CREATE(false);
    },
    createModal() {
      this.currentPermission = {name: ''};
      this.SET_MODAL_STATE(true);
      this.SET_IS_MODAL_EDIT(true);
      this.SET_IS_MODAL_CREATE(true);
    },
    saveItem() {
      this.$refs.permissionView.returnItem();
      if (this.MODAL_CREATE) {
        this.CREATE_PERMISSION(this.currentPermission);
      } else
        this.UPDATE_PERMISSION(this.currentPermission);
      // this.closeModal();
    },
    deleteItem() {
      this.$refs.permissionView.returnItem();
      this.DELETE_PERMISSION(this.currentPermission);
      // this.closeModal();
    },
    editItem(isEdit) {
      this.SET_IS_MODAL_EDIT(isEdit);
      if (!isEdit) {
        let tempRole = this.currentPermission;
        this.closeModal();
        this.showModal(tempRole);
      }
    },
  },
  mounted() {
    this.SET_ERROR({});
    this.GET_ALL_PERMISSIONS();

  }
}
</script>

<style scoped>

</style>