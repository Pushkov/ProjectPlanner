<template class=" mt-xl-5">
    <div class="w-75 m-auto">

        <BasicModal
                v-if="IS_MODAL_SHOW"
                :popup-title="getRoleModalTitle"
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
                    @editItem="editItem"
                    :is-edit="isModalEdit"
                    :is-create="isModalCreate"
            />
        </BasicModal>

        <div v-if="ROLES.length > 0">
            <div class="text-left my-3">
                <b-button @click="createModal"><h5 class="m-auto">Создать</h5></b-button>
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
            <h1>ERROR_LIST_LOAIDNG_MESSAGE</h1>
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
                // fields: [
                //     'id',
                //     'name'
                // ],
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
                'IS_ROLES_BUSY',
                'ROLES',
                'ROLE',
                'IS_MODAL_SHOW'
                // 'ERROR_ROLE_LIST_LOAIDNG_MESSAGE'
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
                'GET_ROLE',
                'CLOSE_MODAL',
                'CREATE_ROLE',
                'UPDATE_ROLE',
                'DELETE_ROLE',
                'SET_TABLE_BUSY'
            ]),

            returnRole(item) {
                this.currentRole = item;
            },
            showModal(item) {
                this.GET_ROLE(item.id);
                this.currentRole = this.ROLE;
                // this.isModal = true;
            },
            closeModal() {
                this.CLOSE_MODAL();
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