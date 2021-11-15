<template class="mt-xl-5">
    <div class="  w-75 m-auto py-3">
        <table class="table table-hover table-bordered table-striped">
            <thead class="thead-light">
            <tr>
                <th>Номер ТЗ</th>
                <th>Название</th>
            </tr>
            </thead>
            <tbody>
            <template v-if="TITLE_LIST.technicalTaskDtoShort !== undefined">
                <tr v-for="task of TITLE_LIST.technicalTaskDtoShort "
                    :key="task.id"
                >
                    <td>
                        {{task.number}}
                    </td>
                    <td>
                        {{task.name}}
                    </td>
                </tr>
            </template>
            <template v-else>
                <tr>
                    <td colspan="2">
                        -
                    </td>
                </tr>
            </template>
            </tbody>
        </table>
        <table class="table table-hover table-bordered table-striped">
            <thead class="thead-light">
            <tr>
                <th>Номер с/з</th>
                <th>Дата с/з</th>
            </tr>
            </thead>
            <tbody>
            <template v-if="TITLE_LIST.memoDtoShort !== undefined">
                <tr v-for="memo of TITLE_LIST.memoDtoShort "
                    :key="memo.id"
                >
                    <td>
                        {{memo.number}}
                    </td>
                    <td>
                        {{memo.dateTime}}
                    </td>
                </tr>
            </template>
            <template v-else>
                <tr>
                    <td colspan="2">
                        -
                    </td>
                </tr>
            </template>
            </tbody>
        </table>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'

    export default {
        name: "TitleListMain",
        data() {
            return {}
        },
        components: {},
        computed: {
            ...mapGetters([
                'TITLE_LIST'
            ])
        },
        methods: {
            ...mapActions([
                'GET_TITLE_LIST'
            ]),
        },
        mounted() {
            this.GET_TITLE_LIST(2021)
        }
    }
</script>

<style scoped>

</style>