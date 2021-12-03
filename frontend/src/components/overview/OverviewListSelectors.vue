<template>
    <div class="w-100">
        <table class="table table-borderless">
            <thead class="thead-light">
            <tr>
                <td rowspan="2" class="col-1">
                    <b-button
                            @click="showAll"
                    ><h5>{{ $t("message.overview.show-all") }}</h5>
                    </b-button>
                </td>
                <td class="col-2">
                    <h5>{{ $t("message.overview.year") }}</h5>
                </td>
                <td class="col-2">
                    <h5>{{ $t("message.overview.month") }}</h5>
                </td>
                <td class="col-2">
                    <h5>{{ $t("message.overview.project-name") }}</h5>
                </td>
                <td class="col-2">
                    <h5>{{ $t("message.overview.project-designation") }}</h5>
                </td>
                <td class="col-2">
                    <h5>{{ $t("message.overview.department") }}</h5>
                </td>
                <td class="col-2">
                    <h5>{{ $t("message.overview.basics") }}</h5>
                </td>
                <td class="col-1">
                    <b-button
                            @click="showCurrent"
                    ><h5>{{ $t("message.overview.show-current") }}</h5></b-button>
                </td>
            </tr>
            <tr>
                <td>
                    <b-form-select
                            v-model="currentYear"
                            :options="years"
                            value-field="value"
                            text-field="value"
                            disabled-field="notEnabled"
                            @change="searchItem"
                    >
                        <b-form-select-option :value="null">Все года</b-form-select-option>
                    </b-form-select>
                </td>
                <td>
                    <b-form-select
                            v-model="currentMonth"
                            :options="ALL_LOCALE_MONTHS"
                            value-field="value"
                            text-field="name"
                            disabled-field="notEnabled"
                            @change="searchItem"
                    >
                        <b-form-select-option :value="null">За весь год</b-form-select-option>

                    </b-form-select>
                </td>
                <td>
                    <b-input
                            :value="currentProjectName"
                            v-model="currentProjectName"
                            @keyup="searchItem"
                    />
                </td>
                <td>
                    <b-input
                            :value="currentProjectDes"
                            v-model="currentProjectDes"
                            @keyup="searchItem"
                    />
                </td>
                <td>
                    <b-form-select
                            v-model="currentDepartment"
                            :options="departments"
                            value-field="value"
                            text-field="value"
                            disabled-field="notEnabled"
                            @change="searchItem"
                    >
                        <b-form-select-option :value="null">Вся контора</b-form-select-option>
                    </b-form-select>
                </td>
                <td>
                    <b-input
                            :value="currentTaskName"
                            v-model="currentTaskName"
                            @keyup="searchItem"
                    />
                </td>
            </tr>
            </thead>
        </table>

    </div>
</template>

<script>
    import {mapActions, mapGetters} from "vuex";

    export default {
        name: "OverviewListSelectors",
        data() {
            return {
                currentYear: 2021,
                currentMonth: 12,
                currentDepartment: 'УПиР',
                currentProjectDes: '',
                currentProjectName: '',
                currentTaskName: ''
            }
        },
        props: {
            years: {
                type: Array,
                default:
                    () => {
                        return []
                    }
            },
            months: {
                type: Array,
                default:
                    () => {
                        return []
                    }
            },
            departments: {
                type: Array,
                default:
                    () => {
                        return []
                    }
            },
        },
        computed: {
            ...mapGetters([
                'ALL_LOCALE_MONTHS',
            ]),
        },
        methods: {
            ...mapActions([
                'SEARCH_OVERVIEW_ITEM',
            ]),
            searchItem() {
                let item = {
                    "year": this.currentYear,
                    "month": this.currentMonth,
                    "depName": this.currentDepartment,
                    "termProjectDes": this.currentProjectDes,
                    "termProjectName": this.currentProjectName,
                    "termTaskName": this.currentTaskName,
                };
                this.SEARCH_OVERVIEW_ITEM(item);

            },
            showAll() {
                this.currentYear = null;
                this.currentMonth = null;
                this.currentDepartment = null;
                this.currentProjectDes = null;
                this.currentProjectName = null;
                this.currentTaskName = null;
                this.searchItem();
            },
            showCurrent() {
                this.currentYear = new Date(Date.now()).getFullYear();
                this.currentMonth = new Date(Date.now()).getMonth() + 1;
                this.currentDepartment = null;
                this.currentProjectDes = null;
                this.currentProjectName = null;
                this.currentTaskName = null;
                this.searchItem();
            }
        },
        watch: {
            term: function (val) {
                this.test_term = val;
            }
        },
        mounted() {
            this.searchItem();
        }

    }

</script>

<style scoped>

</style>