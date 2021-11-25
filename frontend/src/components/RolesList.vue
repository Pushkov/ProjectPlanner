<template class=" mt-xl-5">
    <div class="w-50 m-auto">

        <BasicModal
                v-if="IS_MODAL_SECOND_SHOW"
                :popup-title="'Second Modal'"
                :is-edit="isModalEdit"
                :is-second="true"
                @closeModal="closeModal"
        >
            <RolePermissionModalView
                    ref="roleViewSecond"
                    :item="PERMISSIONS_EXCLUDE_ROLE"
                    :is-edit="isModalEdit"
                    @returnItem='returnRole'
            />
            <!--            <BasicModalFooter-->
            <!--                    slot="footer"-->
            <!--                    @modalClose="closeModalSecond"-->
            <!--                    @saveUser='saveItem'-->
            <!--                    @deleteUser="deleteItem"-->
            <!--                    @editItem="editItem"-->
            <!--                    :is-edit="isModalEdit"-->
            <!--                    :is-create="isModalCreate"-->
            <!--            />-->
        </BasicModal>


        <BasicModal
                v-if="IS_MODAL_SHOW"
                :popup-title="'First Modal'"
                :is-edit="isModalEdit"
                @closeModal="closeModal"
        >
            <BasicModalView
                    ref="roleView"
                    :item="ROLE"
                    :is-edit="isModalEdit"
                    @returnItem='returnRole'
            />
            <BasicModalFooter
                    slot="footer"
                    @modalClose="closeModal"
                    @saveUser='saveItem'
                    @deleteUser="deleteItem"
                    @editItem="showModalSecond"
                    :is-edit="isModalEdit"
                    :is-create="isModalCreate"
            />
        </BasicModal>

        <div v-if="ROLES.length > 0">
            <div class="text-left my-3">
                <b-button @click="createModal"><h5 class="m-auto" :class="getErrorClass">Создать</h5></b-button>
            </div>

            <table class="table table-hover table-bordered table-striped">
                <thead class="thead-light">
                <tr>
                    <th>Id</th>
                    <th>Наименование должности</th>
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
            <h1 v-text="ERROR_MESSAGE">ERROR_MESSAGE</h1>
        </div>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import RoleTableRow from "@/components/RoleTableRow";
    import BasicModal from "./modals/BasicModal";
    import BasicModalView from "./modals/BasicModalView";
    import BasicModalFooter from "./modals/BasicModalFooter";
    import RolePermissionModalView from "@/components/modals/RolePermissionsModalView";

    export default {
        name: "RolesList",
        data() {
            return {
                currentRole: {},
                isModal: false,
                isModalEdit: false,
                isModalCreate: false,
            }
        },
        components: {
    RolePermissionModalView,
    BasicModalFooter,
    BasicModalView,
    BasicModal,
    RoleTableRow
  },
        computed: {
            ...mapGetters([
                'IS_ROLES_BUSY',
                'ROLES',
                'ROLE',
                'IS_MODAL_SHOW',
                'IS_MODAL_SECOND_SHOW',
                'IS_ERROR',
                'ERROR_MESSAGE',
                'PERMISSIONS_EXCLUDE_ROLE'
            ]),
            getRoleModalTitle() {
                return this.isModalCreate
                    ? 'Создать новую должность'
                    : 'Информация о должности: ' + this.currentRole.name;
            },
            getErrorClass() {
                return this.IS_ERROR ? 'border border-danger' : '';
            }
        },
        methods: {
            ...mapActions([
                'GET_ALL_ROLES',
                'GET_ROLE',
                'SET_MODAL_STATE',
                'SET_MODAL_SECOND_STATE',
                'CREATE_ROLE',
                'UPDATE_ROLE',
                'DELETE_ROLE',
                'SET_TABLE_BUSY',
                'GET_ALL_PERMISSIONS_EXCLUDE_ROLE'
            ]),

            returnRole(item) {
                this.currentRole = item;
            },
            showModal(item) {
                this.GET_ALL_PERMISSIONS_EXCLUDE_ROLE(item.id);
                this.GET_ROLE(item.id);
                this.currentRole = this.ROLE;
                // this.isModal = true;
                // this.SET_MODAL_STATE(true);

            },
            showModalSecond() {
                // this.SET_MODAL_STATE(false);
                this.SET_MODAL_SECOND_STATE(true);
                // this.GET_ROLE(item.id);
                // this.currentRole = this.ROLE;
                // this.isModal = true;

            },

            closeModal() {
                this.SET_MODAL_SECOND_STATE(false);
                this.SET_MODAL_STATE(false);
                // this.CLOSE_MODAL();
                // this.isModal = false;
                // this.isModalEdit = false;
                // this.isModalCreate = false;
                this.currentRole = {};
            },
            closeModalSecond() {
                this.SET_MODAL_SECOND_STATE(false);
                this.SET_MODAL_STATE(true);
                // this.CLOSE_MODAL();
                // this.isModal = false;
                // this.isModalEdit = false;
                // this.isModalCreate = false;
            },

            createModal() {
                this.isModal = true;
                this.isModalEdit = true;
                this.isModalCreate = true;
                this.currentRole = {};
            },
            saveItem() {
                this.$refs.roleView.returnItem();
                if (this.currentEngineer.id === undefined) {
                    this.CREATE_ROLE(this.currentRole);
                } else
                    this.UPDATE_ROLE(this.currentRole);
                this.closeModal();
            },
            deleteItem() {
                this.$refs.roleView.returnItem();
                this.DELETE_ROLE(this.currentRole);
                this.closeModal();
            },
            editItem(isEdit) {
                if (!isEdit) {
                    let tempRole = this.currentRole;
                    this.closeModal();
                    this.showModal(tempRole);
                }
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