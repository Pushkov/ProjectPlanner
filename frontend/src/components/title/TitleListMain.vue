<template class="mt-xl-5">
    <div class="  w-75 m-auto py-3">
        <BasicModal
                v-if="isTaskModalShow"
                :popup-title="getTaskTitle"
                :is-edit="isEdit"
                @closeModal="closeModal"
        >
            <div>
                <div role="group" class="row m-0 mt-1 mx-2">
                    <div class="col-4 mr-2 ">
                        {{ $t('task.number') }}:
                    </div>
                    <b-form-input
                            id="input-login"
                            class="col-sm text-secondary border rounded-lg m-0"
                            :readonly="!isEdit"
                            :value="currentTask.number"
                            v-model="currentTask.number"
                            :class="getErrorNumber"
                            trim
                    />
                </div>
                <div role="group" class="row m-0 mt-1 mx-2">
                    <div class="col-4 mr-2 ">
                        {{ $t('task.date') }}:
                    </div>
                    <b-form-input
                            id="input-login"
                            class="col-sm text-secondary border rounded-lg m-0"
                            :readonly="!isEdit"
                            :value="currentTask.dateTime"
                            v-model="currentTask.dateTime"
                            :class="getErrorDate"
                            trim
                    />
                </div>
                <div role="group" class="row m-0 mt-1 mx-2">
                    <div class="col-4 mr-2 ">
                        {{ $t('task.name') }}:
                    </div>
                    <b-form-input
                            id="input-login"
                            class="col-sm text-secondary border rounded-lg m-0"
                            :readonly="!isEdit"
                            :value="currentTask.name"
                            v-model="currentTask.name"
                            :class="getErrorDate"
                            trim
                    />
                </div>
                <div class="row my-1 mx-2">
                    <div class="col-4 mr-2">
                        {{ $t('list.workshop') }}:
                    </div>
                    <b-input
                            v-if="!isEdit"
                            class="col-sm text-secondary border rounded-lg"
                            readonly
                            :class="getErrorNumber"
                            :value="currentTask.workshopId"
                            v-model="currentTask.workshopName"/>
                    <b-form-select
                            :class="getErrorNumber"
                            v-else
                            v-model="currentTask.workshopId"
                            :options="WORKSHOPS"
                            class="col-sm text-secondary border rounded-lg"
                            value-field="id"
                            text-field="name"
                            disabled-field="notEnabled"
                    ></b-form-select>
                </div>
                <div class="row my-1 mx-2">
                    <div class="col-4 mr-2">
                        {{ $t('navigation.title_list') }}:
                    </div>
                    <b-input
                            v-if="!isEdit"
                            class="col-sm text-secondary border rounded-lg"
                            readonly
                            :class="getErrorNumber"
                            :value="currentTask.titleListYear"
                            v-model="currentTask.titleListYear"/>
                    <b-form-select
                            :class="getErrorNumber"
                            v-else
                            v-model="currentTask.titleListYear"
                            :options="TITLE_LISTS_YEARS"
                            class="col-sm text-secondary border rounded-lg"
                            value-field="id"
                            text-field="name"
                            disabled-field="notEnabled"
                    ></b-form-select>
                </div>
                <div class="row my-1 mx-2" v-if="currentTask.baseTask !== undefined">
                    <div class="col-4 mr-2">
                        {{ $t('task.base_task') }}:
                    </div>
                    <b-input
                            v-if="!isEdit"
                            class="col-sm text-secondary border rounded-lg"
                            readonly
                            :class="getErrorNumber"
                            :value="currentTask.baseTask.id"
                            v-model="currentTask.baseTask.number"/>
                    <b-form-select
                            :class="getErrorNumber"
                            v-else
                            v-model="currentTask.baseTask.number"
                            :options="TASKS"
                            class="col-sm text-secondary border rounded-lg"
                            value-field="id"
                            text-field="name"
                            disabled-field="notEnabled"
                    ></b-form-select>
                </div>
                <div class="row my-1 mx-2"
                     v-if="currentTask.extensions !== undefined && currentTask.extensions.length > 0 && !isEdit">
                    <div class="col-4 mr-2">
                        {{ $t('task.extensions') }}:
                    </div>
                    <div
                            v-if="!isEdit"
                            class="h-auto col-sm text-secondary border rounded-lg form-control"
                            readonly
                            :class="getErrorNumber"
                    >
                        <div
                                class="row mx-2"
                                v-for="ext of currentTask.extensions"
                                :key="ext.id"
                        >
                            {{ ext.number }} от {{ ext.dateTime }}
                        </div>

                    </div>
                </div>


            </div>


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
            <div>
                <div role="group" class="row m-0 mt-1 mx-2">
                    <div class="col-4 mr-2 ">
                        {{ $t('memo.number') }}:
                    </div>
                    <b-form-input
                            id="input-login"
                            class="col-sm text-secondary border rounded-lg m-0"
                            :readonly="!isEdit"
                            :value="currentMemo.number"
                            v-model="currentMemo.number"
                            :class="getErrorNumber"
                            trim
                    />
                </div>
                <div role="group" class="row m-0 mt-1 mx-2">
                    <div class="col-4 mr-2 ">
                        {{ $t('memo.date') }}:
                    </div>
                    <b-form-input
                            v-if="!isEdit"
                            class="col-sm"
                            readonly
                            :value="currentMemo.dateTime"
                            :class="getErrorDate"
                            trim
                    />
                    <div class="col-sm row" v-if="isEdit">
                        <b-form-input
                                id="input-login"
                                class="col-sm"
                                readonly
                                :value="currentMemo.dateTime"
                                :class="getErrorDate"
                                trim
                        />
                        <b-form-datepicker
                                class="col-auto"
                                right
                                button-only
                                :max="getNowDate"
                                :date-format-options="{ year: 'numeric', month: '2-digit', day: '2-digit' }"
                                locale="RU"
                                @context="setMemoDate"
                        ></b-form-datepicker>
                    </div>
                </div>

            </div>
            <div class="row my-1 mx-2">
                <div class="col-4 mr-2">
                    {{ $t('list.workshop') }}:
                </div>
                <b-input
                        v-if="!isEdit"
                        class="col-sm text-secondary border rounded-lg"
                        readonly
                        :class="getErrorNumber"
                        :value="currentMemo.workshopId"
                        v-model="currentMemo.workshopName"/>
                <b-form-select
                        :class="getErrorNumber"
                        v-else
                        v-model="currentMemo.workshopId"
                        :options="WORKSHOPS"
                        class="col-sm text-secondary border rounded-lg"
                        value-field="id"
                        text-field="name"
                        disabled-field="notEnabled"
                ></b-form-select>
            </div>
            <div class="row my-1 mx-2">
                <div class="col-4 mr-2">
                    {{ $t('navigation.title_list') }}:
                </div>
                <b-input
                        v-if="!isEdit"
                        class="col-sm text-secondary border rounded-lg"
                        readonly
                        :class="getErrorNumber"
                        :value="currentMemo.titleListYear"
                        v-model="currentMemo.titleListYear"/>
                <b-form-select
                        :class="getErrorNumber"
                        v-else
                        v-model="currentMemo.titleListYear"
                        :options="TITLE_LISTS_YEARS"
                        class="col-sm text-secondary border rounded-lg"
                        value-field="id"
                        text-field="name"
                        disabled-field="notEnabled"
                ></b-form-select>
            </div>
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
                {{ $t('list.select_title_list_year') }}
            </h5>
        </div>
        <div class="row text-left m-2">
            <b-form-select
                    v-model="year"
                    :value="year"
                    :options="TITLE_LISTS_YEARS"
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

    import moment from 'moment'


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
                test: ''
            }
        },
        components: {BasicModalFooter, BasicModal, TaskTitleTable, MemoTitleTable},


        computed: {
            ...
                mapGetters([
                    'TITLE_LIST',
                    'TITLE_LISTS',
                    'TITLE_LISTS_YEARS',
                    'TITLE_LIST_ERROR',
                    'WORKSHOPS',
                    'TASKS',
                ]),

            getNowDate() {
                const now = new Date()
                const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
                const minDate = new Date(today)
                return minDate;
            },
            getMemoDate() {
                return this.test === 'Invalid date' ? '' : this.currentMemo.dateTime
            },


            getTaskTitle() {
                return this.isCreate ? 'Create technical task' : 'Info about task number: ' + this.currentTask.number;
            }
            ,
            getMemoTitle() {
                return this.isCreate ? 'Create memo' : 'Info about memo number: ' + this.currentMemo.number;
            }
            ,
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
            }
            ,
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
            ,
            getErrorNumber() {
                if (this.TITLE_LIST_ERROR !== undefined) {
                    return this.TITLE_LIST_ERROR.number !== undefined ? 'border border-danger' : '';
                }
                return false;
            }
            ,
            getErrorDate() {
                if (this.TITLE_LIST_ERROR !== undefined) {
                    return this.TITLE_LIST_ERROR.dateTime !== undefined ? 'border border-danger' : '';
                }
                return false;
            },


        }
        ,
        methods: {
            ...
                mapActions([
                    'GET_TITLE_LIST',
                    'GET_TITLE_YEARS',

                    'SAVE_MEMO',
                    'DELETE_MEMO',
                    'SAVE_TASK',
                    'DELETE_TASK',
                    'GET_ALL_TASKS',

                    'GET_ALL_WORKSHOPS',
                ]),
            createTask() {
                this.currentTask = {
                    number: ''
                };
                this.isEdit = true;
                this.isCreate = true;
                this.isTaskModalShow = true;
            }
            ,
            createMemo() {
                this.currentMemo = {
                    number: '',
                    titleListYear: new Date().getFullYear()
                };
                this.isEdit = true;
                this.isCreate = true;
                this.isMemoModalShow = true;
            }
            ,
            editModal(value) {
                this.isEdit = value;
            }
            ,
            editTask(item) {
                this.isCreate = false;
                this.currentTask = item;
                this.isTaskModalShow = true;
            }
            ,
            editMemo(item) {
                this.isCreate = false;
                this.currentMemo = item;
                this.isMemoModalShow = true;
            }
            ,
            closeModal() {

                this.isTaskModalShow = false;
                this.isMemoModalShow = false;
                this.isCreate = false;
                this.isEdit = false;
            }
            ,
            saveTask() {
                this.SAVE_TASK(this.currentTask, this.year).then(() => {
                    if (!this.getErrorNumber && !this.getErrorDate) {
                        this.closeModal();
                    }
                });
            }
            ,
            saveMemo() {
                this.SAVE_MEMO(this.currentMemo, this.year).then(() => {
                    if (!this.getErrorNumber && !this.getErrorDate) {
                        this.closeModal();
                    }
                });
            }
            ,
            deleteTask() {
                this.DELETE_MEMO(this.currentTask, this.year);
                this.closeModal();
            }
            ,
            deleteMemo() {
                this.DELETE_MEMO(this.currentMemo, this.year);
                this.closeModal();
            },
            setMemoDate(value) {
                this.test = moment(value.selectedYMD).format('DD-MM-YYYY');
                if (this.test !== 'Invalid date') {
                    this.currentMemo.dateTime = this.test;
                }
            }
        }
        ,
        mounted() {
            this.GET_TITLE_YEARS();
            this.GET_TITLE_LIST(this.year)
            this.GET_ALL_WORKSHOPS();
            this.GET_ALL_TASKS();
        }
    }
</script>

<style scoped>

</style>