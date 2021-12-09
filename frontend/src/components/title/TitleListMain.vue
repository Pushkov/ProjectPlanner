<template class="mt-xl-5">
    <div class="  w-75 m-auto py-3">
        <BasicModal
                v-if="isTaskModalShow"
                :popup-title="getTaskTitle"
                :is-edit="isEdit"
                @closeModal="closeModal"
        >
            <BasicModalFooter
                    slot="footer"
                    @modalClose="closeModal"
                    @saveItem='saveTask'
                    @deleteItem="deleteTask"
                    @editItem="editModal"
                    :is-edit="isEdit"
                    :is-create="isCreate"
            />
        </BasicModal>

        <BasicModal
                v-if="isMemoModalShow"
                :popup-title="getMemoTitle"
                :is-edit="isEdit"
                @closeModal="closeModal"
        >
            MEMO -- {{ currentMemo}}
            <br/>

            <BasicModalFooter
                    slot="footer"
                    @modalClose="closeModal"
                    @saveItem='saveMemo'
                    @deleteItem="deleteMemo"
                    @editItem="editModal"
                    :is-edit="isEdit"
                    :is-create="isCreate"
            />
        </BasicModal>
        <div class="row text-left m-2">
            <h5>
                ! SELECT YEAR
            </h5>
        </div>
        <div class="row text-left m-2">
            <b-form-select
                    v-model="year"
                    :value="year"
                    :options="OVERVIEW_YEARS"
                    value-field="value"
                    text-field="value"
                    disabled-field="notEnabled"
                    @change="GET_TITLE_LIST"
            >
                <b-form-select-option value="-1">All years</b-form-select-option>
            </b-form-select>

        </div>
        <div class="row text-left m-2">
            <b-button class="col-2" @click="createTask">
                {{ $t('message.button.create') }}
            </b-button>

        </div>
        <TaskTitleTable
                v-if="getAllTasks.length > 0"
                :task-list="getAllTasks"
                @selectTask="editTask"
        />
        <template v-else>
            <h2>no technical tasks found</h2>
        </template>
        <div class="row text-left m-2">
            <b-button class="col-2" @click="createMemo">
                {{ $t('message.button.create') }}
            </b-button>

        </div>
        <MemoTitleTable
                v-if="getAllMemos.length > 0"
                :memo-list="getAllMemos"
                @selectMemo="editMemo"
        />
        <template v-else>
            <h2>no memos found</h2>
        </template>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import MemoTitleTable from "./MemoTitleTable";
    import TaskTitleTable from "./TaskTitleTable";
    import BasicModal from "../modals/BasicModal";
    import BasicModalFooter from "../modals/BasicModalFooter";


    export default {
        name: "TitleListMain",
        data() {
            return {
                currentTask: {
                    number: ''
                },
                currentMemo: {
                    number: ''
                },
                isTaskModalShow: false,
                isMemoModalShow: false,
                isEdit: false,
                isCreate: false,
                year: new Date().getFullYear(),
            }
        },
        components: {BasicModalFooter, BasicModal, TaskTitleTable, MemoTitleTable},
        computed: {
            ...mapGetters([
                'TITLE_LIST',
                'TITLE_LISTS',
                'OVERVIEW_YEARS'
            ]),

            getTaskTitle() {
                return this.isCreate ? 'Create technical task' : 'Info about task number: ' + this.currentTask.number;
            },
            getMemoTitle() {
                return this.isCreate ? 'Create memo' : 'Info about memo number: ' + this.currentMemo.number;
            },
            getAllTasks() {
                let tasks = [];
                const titles = this.TITLE_LISTS;
                if (titles !== undefined && titles.length > 0) {
                    for (const title of titles) {
                        if (title.technicalTaskDto !== undefined && title.technicalTaskDto.length > 0) {
                            for (const tt of title.technicalTaskDto) {
                                tasks.push(tt);
                            }
                        }
                    }
                    return tasks;
                } else if (this.TITLE_LIST.technicalTaskDto !== undefined && this.TITLE_LIST.technicalTaskDto.length > 0) {
                    return this.TITLE_LIST.technicalTaskDto
                } else {
                    return tasks;
                }
            },
            getAllMemos() {
                let memos = [];
                const titles = this.TITLE_LISTS;
                if (titles !== undefined && titles.length > 0) {
                    for (const title of titles) {
                        if (title.memoDto !== undefined && title.memoDto.length > 0) {
                            for (const mem of title.memoDto) {
                                memos.push(mem);
                            }
                        }
                    }
                    return memos;
                } else if (this.TITLE_LIST.memoDto !== undefined && this.TITLE_LIST.memoDto.length > 0) {
                    return this.TITLE_LIST.memoDto
                } else {
                    return memos;
                }
            }

        },
        methods: {
            ...mapActions([
                'GET_TITLE_LIST',
                'GET_OVERVIEW_YEARS',
            ]),
            createTask() {
                this.currentTask = {
                    number: ''
                };
                this.isEdit = true;
                this.isCreate = true;
                this.isTaskModalShow = true;
            },
            createMemo() {
                this.currentMemo = {
                    number: ''
                };
                this.isEdit = true;
                this.isCreate = true;
                this.isMemoModalShow = true;
            },
            editModal(value) {
                this.isEdit = value;
            },
            editTask(item) {
                this.isCreate = false;
                this.currentTask = item;
                this.isTaskModalShow = true;
            },
            editMemo(item) {
                this.isCreate = false;
                this.currentMemo = item;
                this.isMemoModalShow = true;
            },
            closeModal() {

                this.isTaskModalShow = false;
                this.isMemoModalShow = false;
                this.isCreate = false;
                this.isEdit = false;
            },
            saveTask() {
                console.log('save task');
                this.closeModal();
            },
            saveMemo() {
                console.log('save memo');
                this.closeModal();
            },
            deleteTask() {
                console.log('delete task');
                this.closeModal();
            },
            deleteMemo() {
                console.log('delete memo');
                this.closeModal();
            },

        },
        mounted() {
            this.GET_OVERVIEW_YEARS();
            this.GET_TITLE_LIST(this.year)
        }
    }
</script>

<style scoped>

</style>