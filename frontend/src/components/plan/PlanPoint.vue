<template class="mt-xl-5">
    <div>
        <NavigationBar/>

        <div class="  w-75 m-auto py-3">
            <div class="row m-auto">
                <b-button
                        @click="goBackToPlan"
                ><h5 class="m-auto">{{ $t("button.back") }}</h5>
                </b-button>
            </div>
            PlanPoint id={{ getId }}
            project - {{ currentPoint.projectDesignation }}

            <div class="row my-2">
                <div class="col-4 m-auto mr-2 ">
                    {{ $t('plan.order') }}
                </div>
                <b-form-input
                        id="input-login"
                        class="col-sm text-secondary border rounded-lg m-0"
                        readonly
                        :value="currentPoint.orderInPlan"
                        aria-describedby="input-login-help input-login-feedback"
                        trim
                />
            </div>
            <div class="row my-2">
                <div class="col-4 m-auto mr-2">
                    {{ $t('plan.project') }}
                </div>
                <b-form-input
                        v-if="!isEdit"
                        id="input-login"
                        class="col-sm text-secondary border rounded-lg m-0"
                        readonly
                        :value="currentPoint.projectDesignation"
                        aria-describedby="input-login-help input-login-feedback"
                        trim
                />
                <b-form-select
                        v-else-if="isEdit"
                        v-model="currentPoint.projectDesignation"
                        :options="PROJECTS"
                        class="col-sm text-secondary border rounded-lg m-0"
                        value-field="designation"
                        text-field="designation"
                        disabled-field="notEnabled"
                />
            </div>
            <div class="row my-2">
                <div class="col-4 m-auto mr-2 ">
                    {{ $t('plan.designer') }}
                </div>
                <b-form-input
                        v-if="!isEdit"
                        id="input-login"
                        class="col-sm text-secondary border rounded-lg m-0"
                        readonly
                        :value="currentPoint.designerName"
                        aria-describedby="input-login-help input-login-feedback"
                        trim
                />
                <b-form-select
                        v-else-if="isEdit"
                        v-model="currentPoint.designerId"
                        :options="ENGINEERS"
                        class="col-sm text-secondary border rounded-lg m-0"
                        value-field="id"
                        text-field="lastName"
                        disabled-field="notEnabled"
                />
            </div>
            <div class="row my-2">
                <div class="col-4 m-auto mr-2 ">
                    {{ $t('plan.date_start') }}
                </div>
                <div class="col-sm row">
                    <b-form-input
                            class="col-sm"
                            readonly
                            :value="currentPoint.startDate"
                            :class="getErrorDate"
                            trim
                    />
                    <b-form-datepicker
                            class="col-auto"
                            right
                            button-only
                            :disabled="!isEdit"
                            :min="getNowDate"
                            :date-format-options="{ year: 'numeric', month: '2-digit', day: '2-digit' }"
                            locale="RU"
                            @context="setStartDate"
                    ></b-form-datepicker>
                </div>

            </div>
            <div class="row my-2">
                <div class="col-4 m-auto mr-2 ">
                    {{ $t('plan.date_end') }}
                </div>
                <div class="col-sm row">
                    <b-form-input
                            class="col-sm"
                            readonly
                            :value="currentPoint.endDate"
                            :class="getErrorDate"
                            trim
                    />
                    <b-form-datepicker
                            class="col-auto"
                            right
                            button-only
                            :disabled="!isEdit"
                            :min="getNowDate"
                            :date-format-options="{ year: 'numeric', month: '2-digit', day: '2-digit' }"
                            locale="RU"
                            @context="setEndDate"
                    ></b-form-datepicker>
                </div>

            </div>
            <div class="row my-2">
                <div class="col-4 m-auto mr-2 ">
                    {{ $t('plan.result') }} ,%
                </div>
                <b-form-input
                        id="input-login"
                        class="col-sm text-secondary border rounded-lg m-0"
                        :readonly="!isEdit"
                        :value="currentPoint.readiness"
                        v-model="currentPoint.readiness"
                        aria-describedby="input-login-help input-login-feedback"
                        trim
                />
            </div>

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
            </div>
        </div>
    </div>
</template>

<script>
    import router from "@/router";
    import {mapActions, mapGetters} from "vuex";
    import moment from "moment";
    import NavigationBar from "../nav/NavigationBar";


    export default {
        name: "PlanPoint",
        components: {NavigationBar},
        data() {
            return {
                currentPoint: {},
                isEdit: false,
                isCreate: false,
            }
        },
        computed: {
            ...mapGetters([
                'PLAN_POINT',
                'PROJECTS',
                'ENGINEERS',
                'PLAN'
            ]),
            getNowDate() {
                const now = new Date()
                const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
                const minDate = new Date(today)
                return minDate;
            },
            getYear() {
                return this.$route.params.year;
            },
            getMonth() {
                return this.$route.params.month;
            },
            getDepartmentId() {
                return this.$route.params.department_id;
            },
            getId() {
                const ppId = this.$route.params.id;
                return ppId
            },
            getErrorDate() {
                return false
            },
        },
        methods: {
            ...mapActions([
                'GET_PLAN_POINT',
                'GET_ALL_ENGINEERS',
                'GET_ALL_PROJECTS',
                'SAVE_PLAN_POINT',
                'UPDATE_PLAN_POINT',
                'GET_PLAN',

            ]),
            goBackToPlan() {
                router.go(-1);
            },
            editItem() {
                this.isEdit = true;

            },
            saveItem() {
                this.isEdit = false;
                this.currentPoint.year = this.getYear;
                this.currentPoint.month = this.getMonth;
                this.currentPoint.departmentId = this.getDepartmentId;
                if (this.currentPoint.orderInPlan === undefined) {
                    this.currentPoint.orderInPlan = this.PLAN.planPointsDto.length + 1;

                }
                const plan = {
                    'year': this.getYear,
                    'month': this.getMonth,
                    'department_id': this.getDepartmentId,
                };
                if (this.getId == 0) {
                    this.SAVE_PLAN_POINT(this.currentPoint);
                } else {
                    this.UPDATE_PLAN_POINT(this.currentPoint);
                }
                this.GET_PLAN(plan).then(() => {
                    router.push('/planner/plans/' + this.getYear + '/' + this.getMonth + '/' + this.getDepartmentId);
                });
            },
            setStartDate(value) {
                const date = moment(value.selectedYMD).format('DD-MM-YYYY');
                if (date !== 'Invalid date') {
                    this.currentPoint.startDate = date;
                }
            },
            setEndDate(value) {
                const date = moment(value.selectedYMD).format('DD-MM-YYYY');
                if (date !== 'Invalid date') {
                    this.currentPoint.endDate = date;
                }
            },
        },
        mounted() {
            if (this.getId > 0) {
                this.GET_PLAN_POINT(this.getId)
                    .then(() => {
                        this.currentPoint = this.PLAN_POINT;
                    });
                this.isEdit = false;
                this.isCreate = false;
            } else {
                this.currentPoint = {};
                this.currentPoint.startDate = '';
                this.currentPoint.endDate = '';
                this.isEdit = true;
                this.isCreate = true;
            }
            this.GET_ALL_ENGINEERS();
            this.GET_ALL_PROJECTS();
        }
    }
</script>

<style scoped>

</style>