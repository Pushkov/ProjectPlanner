<template>


    <div v-if="true" class="w-75 m-auto">
        <OverviewListSelectors
                :months="MONTHS"
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
                'MONTHS',
            ])
        },
        methods: {
            ...mapActions([
                'GET_ITEMS',
                'GET_ITEM',
            ])
        },
        mounted() {
            this.GET_ITEMS();
        }

    }
</script>

<style scoped>

</style>