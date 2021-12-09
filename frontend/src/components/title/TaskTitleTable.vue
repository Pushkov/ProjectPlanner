<template>
    <table class="table table-hover table-bordered table-striped">
        <thead class="thead-light">
        <tr>
            <th colspan="100%">
                Список технических заданий
            </th>
        </tr>
        <tr>
            <th>Номер т/з</th>
            <th>Наименование т/з</th>
        </tr>
        </thead>
        <tbody>
        <TaskTitleRow v-for="task of taskList"
                      :key="task.id"
                      :task="task"
                      @selectTask="selectTask"
        />
        </tbody>
    </table>
</template>

<script>
    import TaskTitleRow from "./TaskTitleRow";

    export default {
        name: "TaskTitleTable",
        props: {
            taskList: {
                type: Array,
                default: () => {
                    return []
                }
            },
        },
        components: {TaskTitleRow},
        methods: {
            selectTask(item) {
                this.$emit('selectTask', item);
            },

        },
    }
</script>

<style scoped>

</style>