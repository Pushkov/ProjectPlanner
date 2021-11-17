<template class=" mt-xl-5">
    <div class="w-75 m-auto">

        <BasicModal
                v-if="isModal"
                :popup-title="getPermissionModalTitle"
                :is-edit="isModalEdit"
                @closeModal="closeModal"
        >
            <PermissionModalView
                    ref="permissionView"
                    :item="currentPermission"
                    :is-edit="isModalEdit"
                    @returnItem='returnItem'
            />
            <BasicModalFooter
                    slot="footer"
                    @modalClose="closeModal"
                    @saveItem='saveItem'
                    @deleteItem="deleteItem"
                    @editItem="editItem"
                    :is-edit="isModalEdit"
                    :is-create="isModalCreate"
            />
        </BasicModal>

        <div class="text-left my-3">
            <b-button @click="createModal"><h5 class="m-auto">Создать</h5></b-button>
        </div>
        <div v-if="isModalCreate">
            <h1>create</h1>
        </div>

        <table class="table table-hover table-bordered table-striped">
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
    </div>
</template>

<script>
    import {mapActions} from 'vuex'

    import PermissionRow from "./PermissionRow";
    import BasicModalFooter from "../modals/BasicModalFooter";
    import BasicModal from "../modals/BasicModal";
    import PermissionModalView from "./PermissionModalView";

    export default {
        name: "PermissionList",
        data() {
            return {
                currentPermission: {},
                isModal: false,
                isModalEdit: false,
                isModalCreate: false,
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
                'CREATE_PERMISSION',
                'UPDATE_PERMISSION',
                'DELETE_PERMISSION',
            ]),
            returnItem(item) {
                this.currentPermission = item;
            },
            showModal(item) {
                this.isModal = true;
                this.currentPermission = item;
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
                this.currentPermission = {name: ''};
            },
            saveItem() {
                this.$refs.permissionView.returnItem();
                if (this.isModalCreate) {
                    this.CREATE_PERMISSION(this.currentPermission);
                } else
                    this.UPDATE_PERMISSION(this.currentPermission);
                this.closeModal();
            },
            deleteItem() {
                this.$refs.permissionView.returnItem();
                this.DELETE_PERMISSION(this.currentPermission);
                this.closeModal();
            },
            editItem(isEdit) {
                this.isModalEdit = isEdit;
                if (!isEdit) {
                    let tempRole = this.currentPermission;
                    this.closeModal();
                    this.showModal(tempRole);
                }
            },
        }
    }
</script>

<style scoped>

</style>