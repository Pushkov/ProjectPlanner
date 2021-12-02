<template>


    <div v-if="true" class="w-75 m-auto">
        <OverviewListSelectors
                :months="getMonthes"
                :years="OVERVIEW_YEARS"
                :departments="OVERVIEW_DEP_NAMES"
        />

        <table class="table table-hover table-bordered table-striped">
            <thead class="thead-light">
            <tr>
                <th>№п/п</th>
                <th>Год</th>
                <th>Месяц</th>
                <th>Наименование проекта</th>
                <th>Обозначение проекта</th>
                <th>Разработавшее бюро</th>
                <th>Основание для разработки</th>
            </tr>
            </thead>
            <tbody>
            <OverviewListRow
                    v-for="item of ITEMS"
                    :item="item"
                    :key="item.id"
            />
            </tbody>
        </table>
        <!--    <nic-pagination-->
        <!--            v-if="PAGES > 1"-->
        <!--            :page="PAGE"-->
        <!--            :pages="PAGES"-->
        <!--            @selectPage="selectPage"-->
        <!--    />-->
    </div>
    <div v-else>
        <h4 class="m-xl-5">Список работников пуст</h4>
    </div>
</template>

<script>
    import OverviewListRow from "./OverviewListRow";
    import {mapActions, mapGetters} from "vuex";
    import OverviewListSelectors from "./OverviewListSelectors";

    export default {
        name: "OverviewList",
        components: {OverviewListSelectors, OverviewListRow},
        computed: {
            ...mapGetters([
                'ITEMS',
                'ITEM',
                'OVERVIEW_YEARS',
                'OVERVIEW_MONTHS',
                'OVERVIEW_DEP_NAMES',
                'ALL_LOCALE_MONTHS',
            ]),

            getMonthes() {
                let result = [];
                for (let i = 0; i < this.ALL_LOCALE_MONTHS.length; i++) {
                    for (let j = 0; j < this.OVERVIEW_MONTHS.length; j++) {
                        if (this.ALL_LOCALE_MONTHS[i].value === this.OVERVIEW_MONTHS[j]) {
                            result.push(this.ALL_LOCALE_MONTHS[i]);
                        }
                    }
                }
                return result;
            }
        },
        methods: {
            ...mapActions([
                'GET_ITEMS',
                'GET_ITEM',
                'GET_OVERVIEW_YEARS',
                'GET_OVERVIEW_MONTHS',
                'GET_OVERVIEW_DEP_NAMES',
            ]),
            initSelectorsData() {
                this.GET_OVERVIEW_YEARS();
                this.GET_OVERVIEW_MONTHS();
                this.GET_OVERVIEW_DEP_NAMES();
            }
        },
        mounted() {
            this.initSelectorsData();
            // this.GET_ITEMS();
        }
    }
</script>

<style scoped>

</style>