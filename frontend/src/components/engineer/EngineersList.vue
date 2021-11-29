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

            <div class=" row text-left my-3">
                <b-button class="col-2" @click="createEngineerModal"><h5 class="m-auto">Создать</h5></b-button>
                <div class="col-sm"></div>
                <b-check class="col-auto m-auto" :checked="IS_PAGEABLE" @change="viewIsPageable"> Отобразить
                    постранично
                </b-check>
                <div class="col-auto m-auto row">
                    <span class="col-sm m-auto mr-0 text-right"> Отобразить кол-во:</span>
                    <b-input class="col-2 mx-2 form-data" v-model="getInPage"/>
                </div>

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
            <nic-pagination
                    v-if="PAGES > 1"
                    :page="PAGE"
                    :pages="PAGES"
                    @selectPage="selectPage"
            />
        </div>
        <div v-else>
            <h4 class="m-xl-5">Список работников пуст</h4>
        </div>
    </div>
</template>


<script>
    import {mapActions, mapGetters} from 'vuex'
    import BasicModal from "@/components/modals/BasicModal";
    import EngineerTableRow from "@/components/engineer/EngineerTableRow";
    import BasicModalFooter from "@/components/modals/BasicModalFooter";
    import EngineerViewModal from "@/components/engineer/EngineerViewModal";
    import NicPagination from "@/components/basictable/nic-pagination";

    export default {
        name: "EngineersList",
        data() {
            return {
                currentEngineer: {},
                isBusy: true,
            }
        },
        components: {
            NicPagination,
            EngineerViewModal,
            BasicModal,
            BasicModalFooter,
            EngineerTableRow,
        },
        computed: {
            ...mapGetters([
                'IS_BUSY',
                'ENGINEERS',
                'ENGINEER_COUNT',
                'ROLES',
                'DEPARTMENTS',
                'ENGINEER_STATUSES',
                'MODAL_SHOW',
                'MODAL_CREATE',
                'MODAL_EDIT',
                'GET_ERROR',
                'PAGE',
                'PAGES',
                'IN_PAGE',
                'IS_PAGEABLE',

            ]),
            getEngineerModalTitle() {
                return this.isModalCreate
                    ? 'Создать нового инженера'
                    : 'Информация о пользователе: ' + this.currentEngineer.lastName + ' ' + this.currentEngineer.firstName;
            },
            getInPage: {
                // getter
                get() {
                    return this.IN_PAGE
                },
                // setter
                set(newValue) {
                    this.SET_IN_PAGE(newValue);
                    this.GET_ALL_ENGINEER_LIST({
                        'currentPage': this.PAGE,
                        'inPage': this.IN_PAGE,
                        'isPageable': this.IS_PAGEABLE,
                    });
                }
            }
        },
        methods: {
            ...mapActions([
                'GET_ALL_ROLES',
                'GET_ALL_DEPARTMENTS',
                'GET_ALL_ENGINEER_STATUSES',
                'GET_ALL_ENGINEER_LIST',
                'GET_ALL_ENGINEER_PAGE',
                'SET_MODAL_STATE',
                'SET_IS_MODAL_CREATE',
                'SET_IS_MODAL_EDIT',
                'GET_ALL_ENGINEERS',
                'CREATE_ENGINEER',
                'UPDATE_ENGINEER',
                'DELETE_ENGINEER',
                'SET_TABLE_BUSY',
                'SET_ERROR',
                'SET_IS_PAGEABLE',
                'SET_IN_PAGE'
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
            returnUser(engineer) {
                this.currentEngineer = engineer;
            },
            saveEngineer() {
                this.$refs.engineerView.returnUser();
                if (this.currentEngineer.id === undefined) {
                    this.CREATE_ENGINEER(this.currentEngineer);
                } else
                    this.UPDATE_ENGINEER(this.currentEngineer);
            },
            deleteEngineer() {
                this.$refs.engineerView.returnUser();
                this.DELETE_ENGINEER(this.currentEngineer);
            },
            selectPage(page) {
                this.page = page;
                let par = {
                    "page": this.page,
                    "offset": this.IN_PAGE,
                }
                this.GET_ALL_ENGINEER_PAGE(par);
            },
            viewIsPageable(value) {
                this.SET_IS_PAGEABLE(value);
                this.GET_ALL_ENGINEER_LIST({
                    'currentPage': this.PAGE,
                    'inPage': this.IN_PAGE,
                    'isPageable': this.IS_PAGEABLE,
                });
            },
        },
        mounted() {
            this.SET_ERROR({});
            this.SET_TABLE_BUSY(true);
            this.GET_ALL_ENGINEER_LIST({
                'currentPage': 0,
                'inPage': this.IN_PAGE,
                'isPageable': this.IS_PAGEABLE,
            });
            this.GET_ALL_ROLES();
            this.GET_ALL_DEPARTMENTS();
            this.GET_ALL_ENGINEER_STATUSES();
        }
    }
</script>

<style scoped>

</style>