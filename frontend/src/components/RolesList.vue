<template class=" mt-xl-5">
    <div class="w-50 m-auto">

        <BasicModal
                v-if="MODAL_SECOND_SHOW"
                :popup-title="'Second Modal'"
                :is-edit="MODAL_EDIT"
                :is-second="true"
                @closeModal="closeModal"
        >
            <RolePermissionModalView
                    ref="roleViewSecond"
                    :item="getPermissionsExcludeRole"
                    :is-edit="MODAL_EDIT"
                    @returnPermission='returnPermission'
            />
            <RolePermissionsModalFooter
                    slot="footer"
                    @cancel="closeModalSecond"
                    @addItem="addPermission"
            />
        </BasicModal>


        <BasicModal
                v-if="MODAL_SHOW"
                :popup-title="getRoleModalTitle"
                :is-edit="MODAL_EDIT"
                @closeModal="closeModal"
        >
            <RoleModalView
                    ref="roleView"
                    :item="currentRole"
                    :is-edit="MODAL_EDIT"
                    @returnItem='returnRole'
                    @addPermission="showModalSecond"
                    @removePermission="removePermission"
            />
            <BasicModalFooter
                    slot="footer"
                    @modalClose="closeModal"
                    @saveItem='saveItem'
                    @deleteItem="deleteItem"
                    @editItem="editItem"
                    :is-edit="MODAL_EDIT"
                    :is-create="MODAL_CREATE"
                    :error-message="ERROR_MESSAGE"
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
    import BasicModalFooter from "./modals/BasicModalFooter";
    import RolePermissionModalView from "@/components/modals/RolePermissionsModalView";
    import RoleModalView from "@/components/modals/RoleModalView";
    import RolePermissionsModalFooter from "./modals/RolePermissionsModalFooter";

    export default {
        name: "RolesList",
        data() {
            return {
                currentRole: {},
                currentPermission: {}
            }
        },
        components: {
            RolePermissionsModalFooter,
            RoleModalView,
            RolePermissionModalView,
            BasicModalFooter,
            BasicModal,
            RoleTableRow
        },
        computed: {
            ...mapGetters([
                'IS_ROLES_BUSY',
                'PERMISSIONS',
                'ROLES',
                'ROLE',
                'MODAL_SHOW',
                'MODAL_SECOND_SHOW',
                'MODAL_CREATE',
                'MODAL_EDIT',
                'IS_ERROR',
                'ERROR_MESSAGE',
            ]),
            getRoleModalTitle() {
                return this.MODAL_CREATE
                    ? 'Создать новую должность'
                    : 'Информация о должности: ' + this.currentRole.name;
            },
            getErrorClass() {
                return this.IS_ERROR ? 'border border-danger' : '';
            },
            getPermissionsExcludeRole() {
                let ids = [];
                for (let i = 0; i < this.PERMISSIONS.length; i++) {
                    let isExist = false;
                    for (let j = 0; j < this.currentRole.permissionsDto.length; j++) {
                        if (this.PERMISSIONS[i].id === this.currentRole.permissionsDto[j].id) {
                            isExist = true;
                        }
                    }
                    if (!isExist) {
                        ids.push(this.PERMISSIONS[i]);
                    }
                }
                return ids;
            }
        },
        methods: {
            ...mapActions([
                'GET_ALL_ROLES',
                'GET_ROLE',
                'SET_MODAL_STATE',
                'SET_MODAL_SECOND_STATE',
                'SET_IS_MODAL_EDIT',
                'SET_IS_MODAL_CREATE',
                'GET_ALL_PERMISSIONS',
                'CREATE_ROLE',
                'UPDATE_ROLE',
                'DELETE_ROLE',
                'SET_TABLE_BUSY',
            ]),

            returnRole(item) {
                this.currentRole = item;
            },
            returnPermission(item) {
                this.currentPermission = item;
            },
            showModal(item) {
                this.GET_ROLE(item.id).then(() => {
                        this.currentRole = this.ROLE;
                        this.SET_MODAL_STATE(true);
                    }
                );
            },
            showModalSecond() {
                this.SET_MODAL_SECOND_STATE(true);
            },
            closeModal() {
                this.SET_MODAL_SECOND_STATE(false);
                this.SET_MODAL_STATE(false);
                this.SET_IS_MODAL_EDIT(false);
                this.SET_IS_MODAL_CREATE(false);
                this.currentRole = {};
                this.currentPermission = {};
            },
            closeModalSecond() {
                this.SET_MODAL_SECOND_STATE(false);
            },
            createModal() {
                this.currentRole = {};
                this.currentRole.permissionsDto = [];
                this.SET_IS_MODAL_CREATE(true);
                this.SET_IS_MODAL_EDIT(true);
                this.SET_MODAL_STATE(true);
            },
            saveItem() {
                this.$refs.roleView.returnItem();
                if (this.currentRole.id === undefined) {
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
                    this.SET_IS_MODAL_EDIT(false);
                    let tempRole = this.currentRole;
                    // this.closeModal();
                    this.showModal(tempRole);
                } else {
                    this.SET_IS_MODAL_EDIT(true);
                }
            },
            addPermission() {
                this.$refs.roleViewSecond.returnItem();
                this.$refs.roleView.returnItem();
                this.currentRole.permissionsDto.push(this.currentPermission);
                this.closeModalSecond();
            },
            removePermission(item) {
                this.$refs.roleView.returnItem();
                this.currentRole.permissionsDto = this.currentRole.permissionsDto.filter(x => x.id !== item.id)
            }
        },
        mounted() {
            // this.SET_TABLE_BUSY(true);
            this.GET_ALL_ROLES();
            this.GET_ALL_PERMISSIONS();
        }
    }
</script>

<style scoped>

</style>