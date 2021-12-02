<template>
    <div class="row p-3">

        <table class="table table-striped">
            <thead class="thead-light">
            <tr>
                <td>
                    <div class="col-sm">
                        <b-button
                                @click="showAll"
                        ><h6 class="m-auto">Показать все</h6></b-button>
                    </div>
                </td>
                <td>
                    <div class="col-sm">
                        <div class="row">
                            <h5 class="mx-3">Year:</h5>
                        </div>
                        <b-form-select
                                v-model="currentYear"
                                :options="years"
                                class="row text-secondary border rounded-lg"
                                value-field="value"
                                text-field="value"
                                disabled-field="notEnabled"
                                @change="searchItem"
                        >
                            <b-form-select-option :value="null">Все года</b-form-select-option>
                        </b-form-select>
                    </div>
                </td>
                <td>
                    <div class="col-sm">
                        <div class="row">
                            <h5 class="mx-3">Month: {{currentMonth}}</h5>
                        </div>
                        <b-form-select
                                v-model="currentMonth"
                                :options="ALL_LOCALE_MONTHS"
                                class="row text-secondary border rounded-lg"
                                value-field="value"
                                text-field="name"
                                disabled-field="notEnabled"
                                @change="searchItem"
                        >
                            <b-form-select-option :value="null">За весь год</b-form-select-option>

                        </b-form-select>
                    </div>
                </td>
                <td>Наименование проекта</td>
                <td>Обозначение проекта</td>
                <td>
                    <div class="col-sm">
                        <div class="row">
                            <h5 class="mx-3">Department:</h5>
                        </div>
                        <b-form-select
                                v-model="currentDepartment"
                                :options="departments"
                                class="row text-secondary border rounded-lg"
                                value-field="value"
                                text-field="value"
                                disabled-field="notEnabled"
                                @change="searchItem"
                        >
                            <b-form-select-option :value="null">Вся контора</b-form-select-option>
                        </b-form-select>
                    </div>
                </td>
                <td>Основание для разработки</td>
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
                currentDepartment: 'УПиР'
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
                    "departmentName": this.currentDepartment,
                };
                this.SEARCH_OVERVIEW_ITEM(item);

            },
            showAll() {
                this.currentYear = null;
                this.currentMonth = null;
                this.currentDepartment = null;
                this.searchItem();
            }
        },
        mounted() {
            this.searchItem();
        }

    }

</script>

<style scoped>

</style>