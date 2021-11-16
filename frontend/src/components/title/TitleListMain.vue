<template class="mt-xl-5">
    <div class="  w-75 m-auto py-3">
        <TaskTitleTable
                v-if="TITLE_LIST.technicalTaskDtoShort !== undefined"
                :task-list="TITLE_LIST.technicalTaskDtoShort"
        />
        <template v-else>
            <h2>no technical tasks found</h2>
        </template>
        <MemoTitleTable
                v-if="TITLE_LIST.memoDtoShort !== undefined"
                :memo-list="TITLE_LIST.memoDtoShort"
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


    export default {
        name: "TitleListMain",
        data() {
            return {}
        },
        components: {TaskTitleTable, MemoTitleTable},
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