<template class=" mt-xl-5">
    <div class="w-75 m-auto">
        <div class="row m-auto">
            <b-button class="col-2 my-2" @click="toProjects">
                {{ $t('button.back') }}
            </b-button>
            <div class="col-sm"/>
        </div> <!--HEADER BUTTON BACK-->


        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.status') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    v-if="!isEdit"
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    readonly
                    :value="currentProject.status"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
            <b-form-select
                    v-else-if="isEdit"
                    v-model="currentProject.status"
                    :options="getStatuses"
                    class="col-sm text-secondary border rounded-lg m-0"
                    value-field="name"
                    text-field="name"
                    disabled-field="notEnabled"
            />
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.designation') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :readonly="!isEdit"
                    :value="currentProject.designation"
                    v-model="currentProject.designation"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.name') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :readonly="!isEdit"
                    :value="currentProject.name"
                    v-model="currentProject.name"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('list.workshop') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    v-if="!isEdit"
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    readonly
                    :value="currentProject.workshopName"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
            <b-form-select
                    v-else-if="isEdit"
                    v-model="currentProject.workshopId"
                    :options="WORKSHOPS"
                    class="col-sm text-secondary border rounded-lg m-0"
                    value-field="id"
                    text-field="name"
                    disabled-field="notEnabled"
            />
        </div>


        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.department') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    v-if="!isEdit"
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    readonly
                    :value="currentProject.departmentName"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
            <b-form-select
                    v-else-if="isEdit"
                    v-model="currentProject.departmentId"
                    :options="DEPARTMENTS"
                    class="col-sm text-secondary border rounded-lg m-0"
                    value-field="id"
                    text-field="name"
                    disabled-field="notEnabled"
            />
        </div>


        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('document.included_in') }}
            </div>
            <plus-icon
                    v-if="isEdit"
                    class="col-1"
                    fill-color="green"
                    @click="addBasicProject"
            />
            <div v-if="!isEdit" class="col-1"/>
            <div class="col-sm text-left text-secondary border rounded-lg m-0" :style="getReadOnlyStyle">
                <template v-if="currentProject.basicProject !== undefined && currentProject.basicProject.length > 0">
                    <div
                            v-for="bp of currentProject.basicProject"
                            :key="bp.id"
                    >{{bp.designation}}
                    </div>
                </template>
            </div>
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('document.refers_to') }}
            </div>
            <div class="col-1"/>
            <div class="col-sm text-left text-secondary border rounded-lg m-0" style="background: #e9ecef">
                <template v-if="currentProject.projects !== undefined && currentProject.projects.length > 0">
                    <div v-for="p of currentProject.projects"
                         :key="p.id"
                    >{{p.designation}}
                    </div>
                </template>
            </div>
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.included_in_plan') }}
            </div>
            <div class="col-1"/>
            <div class="col-sm text-left text-secondary border rounded-lg m-0" style="background: #e9ecef">
                <template v-if="currentProject.planPoints !== undefined && currentProject.planPoints.length > 0">
                    <div v-for="pp of currentProject.planPoints"
                         :key="pp.id"
                    >{{pp.departmentName}} - {{getMonth(pp.month)}} - {{pp.year}}
                    </div>
                </template>
            </div>
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.refers_to_docs') }}
            </div>
            <div class="col-1"/>
            <div class="col-sm text-left text-secondary border rounded-lg m-0" style="background: #e9ecef">
                <template v-if="currentProject.documentsDto !== undefined && currentProject.documentsDto.length > 0">
                    <div v-for="d of currentProject.documentsDto"
                         :key="d.id"
                    >{{d.designation}}
                    </div>
                </template>
            </div>
        </div>

        <!--APPROVALS START-->

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.designer') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    v-if="!isEdit"
                    readonly
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :value="currentProject.projectApprovalsDto.designerName"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
            <b-form-select
                    v-else-if="isEdit"
                    v-model="currentProject.projectApprovalsDto.designerId"
                    :options="ENGINEERS"
                    class="col-sm text-secondary border rounded-lg m-0"
                    value-field="id"
                    text-field="lastName"
                    disabled-field="notEnabled"
            />
        </div>
        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('document.date') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :readonly="!isEdit"
                    :value="currentProject.projectApprovalsDto.designerSign"
                    v-model="currentProject.projectApprovalsDto.designerSign"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.verifier') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    v-if="!isEdit"
                    readonly
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :value="currentProject.projectApprovalsDto.verifierName"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
            <b-form-select
                    v-else-if="isEdit"
                    v-model="currentProject.projectApprovalsDto.verifierId"
                    :options="ENGINEERS"
                    class="col-sm text-secondary border rounded-lg m-0"
                    value-field="id"
                    text-field="lastName"
                    disabled-field="notEnabled"
            />
        </div>
        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('document.date') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :readonly="!isEdit"
                    :value="currentProject.projectApprovalsDto.verifierSign"
                    v-model="currentProject.projectApprovalsDto.verifierSign"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.norm_contr') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    v-if="!isEdit"
                    readonly
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :value="currentProject.projectApprovalsDto.normControlName"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
            <b-form-select
                    v-else-if="isEdit"
                    v-model="currentProject.projectApprovalsDto.normControlId"
                    :options="ENGINEERS"
                    class="col-sm text-secondary border rounded-lg m-0"
                    value-field="id"
                    text-field="lastName"
                    disabled-field="notEnabled"
            />
        </div>
        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('document.date') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :readonly="!isEdit"
                    :value="currentProject.projectApprovalsDto.normControlSign"
                    v-model="currentProject.projectApprovalsDto.normControlSign"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.agree') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    v-if="!isEdit"
                    readonly
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :value="currentProject.projectApprovalsDto.agreeName"
                    aria-describedby="input-login-help input-login-feedback"
            />
            <b-form-select
                    v-else-if="isEdit"
                    v-model="currentProject.projectApprovalsDto.agreeId"
                    :options="ENGINEERS"
                    class="col-sm text-secondary border rounded-lg m-0"
                    value-field="id"
                    text-field="lastName"
                    disabled-field="notEnabled"
            />
        </div>
        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('document.date') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :readonly="!isEdit"
                    :value="currentProject.projectApprovalsDto.agreeSign"
                    v-model="currentProject.projectApprovalsDto.agreeSign"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
        </div>

        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('project.approve') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    v-if="!isEdit"
                    readonly
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :value="currentProject.projectApprovalsDto.approveName"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
            <b-form-select
                    v-else-if="isEdit"
                    v-model="currentProject.projectApprovalsDto.approveId"
                    :options="ENGINEERS"
                    class="col-sm text-secondary border rounded-lg m-0"
                    value-field="id"
                    text-field="lastName"
                    disabled-field="notEnabled"
            />
        </div>
        <div class="row my-2">
            <div class="col-4 m-auto mr-2 ">
                {{ $t('document.date') }}
            </div>
            <div class="col-1"/>
            <b-form-input
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :readonly="!isEdit"
                    :value="currentProject.projectApprovalsDto.approveSign"
                    v-model="currentProject.projectApprovalsDto.approveSign"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
        </div>

        <!--APPROVALS END-->

        <div class="row m-auto mt-xl-5">
            <div class="col-sm"/>
            <div class="col-5" v-if=" !isEdit">
                <button
                        class=" w-100 btn btn-outline-warning rounded-lg"
                        @click="editItem"
                >
                    Edit
                </button>
            </div>
            <div class="col-5" v-if="isEdit">
                <button
                        class="w-100 btn btn-outline-success rounded-lg"
                        @click="saveItem"
                >
                    Save
                </button>
            </div>
            <div class="col-sm"/>
            <div class="col-5" v-if="isEdit">
                <button
                        class="w-100 btn btn-outline-danger rounded-lg"
                        @click="deleteProject"
                >
                    Delete
                </button>
            </div>
            <div class="col-sm" v-if="isEdit"/>
        </div> <!-- FOOTER BUTTONS EDIT-SAVE-->
    </div>
</template>


<script>
    import {mapActions, mapGetters} from "vuex";
    import router from "../../router";

    export default {
        name: "Project",
        data() {
            return {
                currentProject: {
                    status: 'Не приступали',
                    projectApprovalsDto: {}
                },
                isEdit: false,
                isCreate: false,
            }
        },
        computed: {
            ...mapGetters([
                'PROJECT',
                'WORKSHOPS',
                'DEPARTMENTS',
                'ALL_LOCALE_MONTHS',
                'ENGINEERS'
            ]),
            getId() {
                return this.$route.params.id;
            },
            getStatuses() {
                return ['В работе', 'Не приступали', 'Выполнен']

            },
            getReadOnlyStyle() {
                return this.isEdit ? '' : 'background: #e9ecef'
            }


        },
        methods: {
            ...mapActions([
                'GET_ALL_PROJECTS',
                'GET_PROJECT',
                'GET_ALL_WORKSHOPS',
                'GET_ALL_DEPARTMENTS',
                'GET_ALL_ENGINEERS',
                'CREATE_PROJECT',
                'UPDATE_PROJECT',
                'DELETE_PROJECT',
            ]),
            toProjects() {
                router.push('/planner/projects');
            },
            editItem() {
                this.isEdit = true;
            },
            saveItem() {
                this.isEdit = false;
                if (this.getId == 0) {
                    this.CREATE_PROJECT(this.currentProject)
                } else {
                    this.UPDATE_PROJECT(this.currentProject)
                }
                router.push('/planner/projects')
            },
            getMonth(value) {
                return this.ALL_LOCALE_MONTHS.filter(x => x.value === value)[0].name;
            },
            addBasicProject() {
            },
            deleteProject() {
                this.DELETE_PROJECT(this.currentProject)
                router.push('/planner/projects')
            }

        },
        mounted() {
            this.GET_ALL_WORKSHOPS();
            this.GET_ALL_DEPARTMENTS();
            this.GET_ALL_ENGINEERS();

            if (this.getId == 0) {
                this.isEdit = true;
                this.isCreate = true;
                this.currentProject = {
                    status: 'Не приступали',
                    projectApprovalsDto: {}
                };
            } else {
                this.isCreate = false;
                this.GET_PROJECT(this.getId).then(() => {
                    this.currentProject = this.PROJECT;
                    if (this.currentProject.projectApprovalsDto === undefined || this.currentProject.projectApprovalsDto === null) {
                        this.currentProject.projectApprovalsDto = {}
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>